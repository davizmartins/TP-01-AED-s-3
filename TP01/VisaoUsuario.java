package TP01;

import java.util.Scanner;

public class VisaoUsuario {
    private Scanner scanner = new Scanner(System.in);

    // Menu inicial (Login/Cadastro)
    public void mostrarMenuPrincipal() {

        System.out.println("       EntrePares 1.0 - Login          ");

        System.out.println("(A) Login                             ");
        System.out.println("(B) Novo usuário                      ");
        System.out.println("(S) Sair                              ");

        System.out.print("Opção: ");
    }

    // Menu principal após login
    public void mostrarMenuPrincipal(Usuario usuarioAtivo) {

        System.out.println("       EntrePares 1.0                  ");
        System.out.println(" > Início                              ");

        System.out.println(" (A) Meus dados                        ║");
        System.out.println(" (B) Meus cursos                       ║");
        System.out.println(" (C) Minhas inscrições                 ║");
        System.out.println(" (D) Excluir conta                     ║");
        System.out.println(" (S) Sair/Logout                       ");

        System.out.print("Opção: ");
    }

    // Pega dados de login
    public String[] pegarDadosLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new String[] { email, senha };
    }

    // Pega dados de novo usuário
    public Usuario pegarDadosNovoUsuario() {
        System.out.println("\n--- Cadastro de Novo Usuário ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Pergunta secreta: ");
        String perguntaSecreta = scanner.nextLine();
        System.out.print("Resposta secreta: ");
        String respostaSecreta = scanner.nextLine();

        String hashSenha = gerarHashSenha(senha);
        String hashResposta = gerarHashSenha(respostaSecreta);

        return new Usuario(0, nome, email, hashSenha, perguntaSecreta, hashResposta);
    }

    // Mostra dados do usuário
    public void mostrarDadosUsuario(Usuario usuario) {
        System.out.println("> Início > Meus Dados                 ");
        System.out.println("NOME.....: " + usuario.getNome());
        System.out.println("E-MAIL...: " + usuario.getEmail());
        System.out.println("PERGUNTA.: " + usuario.getPerguntaSecreta());
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    // Pega opção do usuário
    public char pegarOpcao() {
        String entrada = scanner.nextLine().toUpperCase().trim();
        if (entrada.length() > 0) {
            return entrada.charAt(0);
        }
        return ' ';
    }

    // Métodos de mensagens
    private String gerarHashSenha(String senha) {
        return Integer.toString(senha.hashCode());
    }

    public void mostrarMensagemSucessoCadastro() {
        System.out.println(" Cadastro realizado com sucesso!");
    }

    public void mostrarMensagemErroCadastro() {
        System.out.println(" Erro ao cadastrar usuário.");
    }

    public void mostrarMensagemSucesso(String mensagem) {
        System.out.println("Sucesso: " + mensagem);
    }

    public void mostrarMensagemErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    public void mostrarMensagemErroLogin() {
        System.out.println(" Erro ao fazer login. E-mail ou senha inválidos.");
    }

    public void mostrarMensagemLoginSucesso(String nomeUsuario) {
        System.out.println(" Bem-vindo, " + nomeUsuario + "!");
    }

    public void mostrarMensagemLogout() {
        System.out.println("Logout realizado com sucesso!");
    }

    public void mostrarMensagemSaida() {
        System.out.println(" Encerrando sistema...");
    }

    public void mostrarMensagemOpcaoInvalida() {
        System.out.println(" Opção inválida!");
    }

    public void mostrarMensagemEmDesenvolvimento() {
        System.out.println("Funcionalidade em desenvolvimento (TP2).");
    }
}