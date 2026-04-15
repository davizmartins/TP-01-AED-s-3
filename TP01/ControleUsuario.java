package TP01;

import java.util.ArrayList;

public class ControleUsuario {
    private VisaoUsuario visaoUsuario;
    private ArquivoUsuario arquivoUsuario;
    private ControleCurso controleCurso;

    public ControleUsuario(VisaoUsuario visaoUsuario, ArquivoUsuario arquivoUsuario, ControleCurso controleCurso) {
        this.visaoUsuario = visaoUsuario;
        this.arquivoUsuario = arquivoUsuario;
        this.controleCurso = controleCurso;
    }

    public void mostrarMenu() {
        while (true) {
            visaoUsuario.mostrarMenuPrincipal();
            char opcao = visaoUsuario.pegarOpcao();

            switch (opcao) {
                case 'A': // Login
                    Usuario usuarioLogado = realizarLogin();
                    if (usuarioLogado != null) {
                        mostrarMenuPrincipais(usuarioLogado);
                    }
                    break;
                case 'B': // Novo usuário
                    cadastrarNovoUsuario();
                    break;
                case 'S': // Sair
                    visaoUsuario.mostrarMensagemSaida();
                    System.exit(0);
                    break;
                default:
                    visaoUsuario.mostrarMensagemOpcaoInvalida();
            }
        }
    }

    // Realizar login de usuário
    private Usuario realizarLogin() {
        String[] dadosLogin = visaoUsuario.pegarDadosLogin();
        String email = dadosLogin[0];
        String senha = dadosLogin[1];

        try {
            Usuario usuario = arquivoUsuario.read(email);
            if (usuario != null && usuario.getHashSenha().equals(Integer.toString(senha.hashCode()))) {
                visaoUsuario.mostrarMensagemLoginSucesso(usuario.getNome());
                return usuario;
            } else {
                visaoUsuario.mostrarMensagemErroLogin();
                return null;
            }
        } catch (Exception e) {
            visaoUsuario.mostrarMensagemErroLogin();
            return null;
        }
    }

    // Cadastrar novo usuário
    private void cadastrarNovoUsuario() {
        Usuario novoUsuario = visaoUsuario.pegarDadosNovoUsuario();
        try {
            arquivoUsuario.create(novoUsuario);
            visaoUsuario.mostrarMensagemSucessoCadastro();
        } catch (Exception e) {
            visaoUsuario.mostrarMensagemErroCadastro();
        }
    }

    private boolean excluirUsuario(Usuario usuario) {
        try {
            ArrayList<Curso> cursos = controleCurso.getArquivoCurso().readByUser(usuario.getId());

            boolean temCursosAtivos = false;
            for (Curso c : cursos) {
                if (c.getEstado() == Curso.ATIVO_INSCRICOES || c.getEstado() == Curso.ATIVO_SEM_INSCRICOES) {
                    temCursosAtivos = true;
                    break;
                }
            }

            if (temCursosAtivos) {
                visaoUsuario.mostrarMensagemErro("Não é possível excluir a conta. Você possui cursos ativos.");
                return false;
            }

            for (Curso c : cursos) {
                if (c.getEstado() == Curso.CONCLUIDO || c.getEstado() == Curso.CANCELADO) {
                    controleCurso.getArquivoCurso().delete(c.getId());
                }
            }

            if (arquivoUsuario.delete(usuario.getId())) {
                visaoUsuario.mostrarMensagemSucesso("Conta excluída com sucesso!");
                return true;
            } else {
                visaoUsuario.mostrarMensagemErro("Erro ao excluir conta. Tente novamente.");
                return false;
            }
        } catch (Exception e) {
            visaoUsuario.mostrarMensagemErro("Erro ao excluir conta: " + e.getMessage());
            return false;
        }
    }

    // Menu principal após login
    private void mostrarMenuPrincipais(Usuario usuarioAtivo) {
        while (true) {
            visaoUsuario.mostrarMenuPrincipal(usuarioAtivo);
            char opcao = visaoUsuario.pegarOpcao();

            switch (opcao) {
                case 'A': // Meus dados
                    mostrarDadosUsuario(usuarioAtivo);
                    break;
                case 'B': // Meus cursos
                    controleCurso.mostrarMenu(usuarioAtivo);
                    break;
                case 'C': // Minhas inscrições (TP2)
                    visaoUsuario.mostrarMensagemEmDesenvolvimento();
                    break;
                case 'D': // Excluir conta
                    boolean deveSair = excluirUsuario(usuarioAtivo);
                    if (deveSair) {
                        return; 
                    }
                    break;
                case 'S': // Logout
                    visaoUsuario.mostrarMensagemLogout();
                    return;
                default:
                    visaoUsuario.mostrarMensagemOpcaoInvalida();
            }
        }
    }

    // Mostrar dados do usuário ativo
    private void mostrarDadosUsuario(Usuario usuario) {
        visaoUsuario.mostrarDadosUsuario(usuario);
    }
}