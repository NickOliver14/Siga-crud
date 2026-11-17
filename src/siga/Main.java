package siga;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - CRUD completo (Etapa 3) ===\n");

        AlunoDAO dao = new AlunoDAOMemoria();
        ServicoAluno servico = new ServicoAluno(dao);

        
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 8.5));
        cadastrar(servico, new Aluno("João Souza",  "2026002", 6.0));
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 7.0));   
        cadastrar(servico, new Aluno("Média Absurda", "2026003", 50));  
        System.out.println();

       
        listar(servico);


        List<Aluno> lista = servico.listar();
        lista.add(new Aluno("Intruso Silva", "9999999", 10));
        System.out.println("\nApós tentar inserir um aluno direto na lista devolvida:");
        listar(servico);

      
        System.out.println();
        try {
            servico.alterar(new Aluno("Maria Silva", "2026001", 9.0));
            System.out.println("Aluno atualizado: " + servico.consultar("2026001"));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível atualizar: " + e.getMessage());
        }

      
        excluir(servico, "2026002");   
        excluir(servico, "0000000");   
        System.out.println();
        listar(servico);
    }

    private static void listar(ServicoAluno servico) {
        System.out.println("Alunos cadastrados:");
        for (Aluno aluno : servico.listar()) {
            System.out.println("  " + aluno);
        }
    }

    
    private static void cadastrar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.cadastrar(aluno);
            System.out.println("Cadastrado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
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
}