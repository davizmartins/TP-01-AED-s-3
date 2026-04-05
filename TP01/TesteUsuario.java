package TP01;

public class TesteUsuario {
    public static void main(String[] args) {
        try {
            ArquivoUsuario arq = new ArquivoUsuario();

            System.out.println("=== TESTE 1: CREATE ===");
            Usuario u1 = new Usuario(-1, "Ricardo", "ricardo@gmail.com", "hash123", "cor favorita?", "azulhash");
            int id1 = arq.create(u1);
            System.out.println("Usuário criado com ID: " + id1);

            System.out.println("\n=== TESTE 2: READ POR EMAIL ===");
            Usuario buscado = arq.read("ricardo@gmail.com");
            if (buscado != null)
                System.out.println(buscado);
            else
                System.out.println("Usuário não encontrado.");

            System.out.println("\n=== TESTE 3: UPDATE EMAIL ===");
            if (buscado != null) {
                buscado.email = "ricardo123@gmail.com";
                buscado.nome = "Ricardo Silva";
                boolean atualizado = arq.update(buscado);
                System.out.println("Atualizado? " + atualizado);
            }

            System.out.println("\n=== TESTE 4: READ EMAIL ANTIGO ===");
            Usuario antigo = arq.read("ricardo@gmail.com");
            System.out.println(antigo == null ? "Não encontrado, correto." : antigo);

            System.out.println("\n=== TESTE 5: READ EMAIL NOVO ===");
            Usuario novo = arq.read("ricardo123@gmail.com");
            if (novo != null)
                System.out.println(novo);
            else
                System.out.println("Usuário não encontrado.");

            System.out.println("\n=== TESTE 6: DELETE POR EMAIL ===");
            boolean excluiu = arq.delete("ricardo123@gmail.com");
            System.out.println("Excluído? " + excluiu);

            System.out.println("\n=== TESTE 7: READ APÓS DELETE ===");
            Usuario apagado = arq.read("ricardo123@gmail.com");
            System.out.println(apagado == null ? "Usuário removido com sucesso." : apagado);

            arq.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}