package TP01;

public class Main {
    public static void main(String[] args) {
        try {
            // Instanciar os componentes
            VisaoUsuario visaoUsuario = new VisaoUsuario();
            ArquivoUsuario arquivoUsuario = new ArquivoUsuario();
            
            VisaoCurso visaoCurso = new VisaoCurso();
            ArquivoCurso arquivoCurso = new ArquivoCurso();
            ControleCurso controleCurso = new ControleCurso(visaoCurso, arquivoCurso);
            
            // Controle de usuários
            ControleUsuario controleUsuario = new ControleUsuario(visaoUsuario, arquivoUsuario, controleCurso);

            // Iniciar o sistema
            controleUsuario.mostrarMenu();

        } catch (Exception e) {
            System.out.println("✗ Erro crítico ao inicializar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}