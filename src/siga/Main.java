package siga;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - CRUD completo (Etapa 4) ===\n");

        AlunoDAO dao = new AlunoDAOMemoria();
        ServicoAluno servico = new ServicoAluno(dao);

        
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 8.5));
        cadastrar(servico, new Aluno("João Souza",  "2026002", 6.0));
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 7.0));    
        cadastrar(servico, new Aluno("Média Absurda", "2026003", 50));   
        cadastrar(servico, new Aluno("  ", "2026004", 7.0));             
        System.out.println();

        
        listar(servico);
        consultar(servico, "2026001");   
        consultar(servico, "0000000");   

        System.out.println();
        alterar(servico, new Aluno("Maria Silva", "2026001", 9.0));   
        alterar(servico, new Aluno("Fantasma", "0000000", 5.0));      
        alterar(servico, new Aluno("Maria Silva", "2026001", 15.0));  


        System.out.println();
        excluir(servico, "2026002");  
        excluir(servico, "0000000");   
        System.out.println();

        listar(servico);
    }



    private static void cadastrar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.cadastrar(aluno);
            System.out.println("Cadastrado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private static void consultar(ServicoAluno servico, String matricula) {
        try {
            Aluno aluno = servico.consultar(matricula);
            System.out.println("Encontrado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível consultar: " + e.getMessage());
        }
    }

    private static void alterar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.alterar(aluno);
            System.out.println("Atualizado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível alterar: " + e.getMessage());
        }
    }

    private static void excluir(ServicoAluno servico, String matricula) {
        try {
            servico.excluir(matricula);
            System.out.println("Aluno " + matricula + " removido com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível excluir: " + e.getMessage());
        }
    }

    private static void listar(ServicoAluno servico) {
        System.out.println("Alunos cadastrados:");
        for (Aluno aluno : servico.listar()) {
            System.out.println("  " + aluno);
        }
    }
}