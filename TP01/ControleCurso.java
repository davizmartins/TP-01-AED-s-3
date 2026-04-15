package TP01;

import java.util.ArrayList;

public class ControleCurso {
    private VisaoCurso visaoCurso;
    private ArquivoCurso arquivoCurso;

    public ControleCurso(VisaoCurso visaoCurso, ArquivoCurso arquivoCurso) {
        this.visaoCurso = visaoCurso;
        this.arquivoCurso = arquivoCurso;
    }

    public ArquivoCurso getArquivoCurso() {
        return arquivoCurso;
    }

    // Exibe o menu de cursos do usuário ativo
    public void mostrarMenu(Usuario usuarioAtivo) {
        while (true) {
            try {
                ArrayList<Curso> listaCursos = arquivoCurso.readByUser(usuarioAtivo.getId());

                Curso[] cursos = listaCursos.toArray(new Curso[0]);
                cursos = ordenarCursosAlfabeticamente(cursos);

                visaoCurso.mostrarCursos(cursos);
                char opcao = visaoCurso.pegarOpcao();

                switch (opcao) {
                    case 'A': // Novo curso
                        criarNovoCurso(usuarioAtivo);
                        break;
                    case 'R': // Retornar
                        return;
                    default:

                        if (opcao >= '0' && opcao <= '9') {
                            int indiceCurso = Character.getNumericValue(opcao) - 1;
                            if (indiceCurso >= 0 && indiceCurso < cursos.length) {
                                selecionarCurso(usuarioAtivo, cursos[indiceCurso]);
                            } else {
                                visaoCurso.mostrarMensagemOpcaoInvalida();
                            }
                        } else {
                            visaoCurso.mostrarMensagemOpcaoInvalida();
                        }
                }
            } catch (Exception e) {
                visaoCurso.mostrarMensagemErro("Erro ao acessar os cursos: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private Curso[] ordenarCursosAlfabeticamente(Curso[] cursos) {
        // Bubble Sort simples - ordena do A ao Z
        for (int i = 0; i < cursos.length - 1; i++) {
            for (int j = 0; j < cursos.length - i - 1; j++) {
                // Se o nome do curso[j] vem DEPOIS do curso[j+1] na ordem alfabética
                if (cursos[j].getNome().compareToIgnoreCase(cursos[j + 1].getNome()) > 0) {
                    // Troca os dois cursos de lugar
                    Curso temp = cursos[j];
                    cursos[j] = cursos[j + 1];
                    cursos[j + 1] = temp;
                }
            }
        }
        return cursos;
    }

    // Criar novo curso
    private void criarNovoCurso(Usuario usuarioAtivo) {
        try {
            Curso novoCurso = visaoCurso.pegarDadosNovoCurso();
            novoCurso.setIdUsuario(usuarioAtivo.getId());
            arquivoCurso.create(novoCurso);
            visaoCurso.mostrarMensagemSucessoCadastro();
        } catch (Exception e) {
            visaoCurso.mostrarMensagemErro("Erro ao criar curso: " + e.getMessage());
        }
    }

    // Selecionar um curso específico
    private void selecionarCurso(Usuario usuarioAtivo, Curso curso) {
        while (true) {
            visaoCurso.mostrarDetalheCurso(curso);
            char opcao = visaoCurso.pegarOpcao();

            switch (opcao) {
                case 'B': // Corrigir dados
                    atualizarCurso(curso);
                    break;
                case 'C': // Encerrar inscrições
                    alterarEstadoCurso(curso, Curso.ATIVO_SEM_INSCRICOES);
                    break;
                case 'D': // Concluir curso
                    alterarEstadoCurso(curso, Curso.CONCLUIDO);
                    break;
                case 'E': // Cancelar curso
                    alterarEstadoCurso(curso, Curso.CANCELADO);
                    break;
                case 'R': // Retornar
                    return;
                default:
                    visaoCurso.mostrarMensagemOpcaoInvalida();
            }
        }
    }

    // Atualizar dados do curso
    private void atualizarCurso(Curso curso) {
        try {
            Curso cursoAtualizado = visaoCurso.pegarDadosAtualizacaoCurso(curso);
            if (arquivoCurso.update(cursoAtualizado)) {
                visaoCurso.mostrarMensagemSucesso("Curso atualizado com sucesso!");
            } else {
                visaoCurso.mostrarMensagemErro("Erro ao atualizar curso.");
            }
        } catch (Exception e) {
            visaoCurso.mostrarMensagemErro("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    // Alterar estado do curso
    private void alterarEstadoCurso(Curso curso, byte novoEstado) {
        try {
            curso.setEstado(novoEstado);
            if (arquivoCurso.update(curso)) {
                visaoCurso.mostrarMensagemSucesso("Estado do curso alterado!");
            }
        } catch (Exception e) {
            visaoCurso.mostrarMensagemErro("Erro ao alterar estado: " + e.getMessage());
        }
    }

    // Excluir um curso
    public void excluirCurso(int idCurso) {
        try {
            if (arquivoCurso.delete(idCurso)) {
                visaoCurso.mostrarMensagemSucesso("Curso excluído com sucesso.");
            } else {
                visaoCurso.mostrarMensagemErro("Erro ao excluir o curso.");
            }
        } catch (Exception e) {
            visaoCurso.mostrarMensagemErro("Erro ao excluir curso: " + e.getMessage());
        }
    }
}