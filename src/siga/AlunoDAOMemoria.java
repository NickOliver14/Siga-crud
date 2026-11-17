package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * Código INICIAL da atividade — CRUD INCOMPLETO e com deslizes PROPOSITAIS.
 *
 * DESLIZE 1 — coleção interna exposta (etapa 3):
 * listarTodos() devolve a PRÓPRIA lista interna. Quem chamar o método pode
 * adicionar ou remover alunos diretamente, por fora do DAO, quebrando o
 * encapsulamento estudado na Aula 1. A correção é a cópia defensiva:
 * devolver uma nova lista com os mesmos elementos.
 *
 * DESLIZE 2 — exclusão silenciosa (etapa 3):
 * remover() não verifica se o aluno existia. Quando a matrícula não é
 * encontrada, nada acontece — mas o sistema informa sucesso ao usuário.
 * Falhar em silêncio é pior que falhar com erro.
 *
 * PENDENTE (etapa 1): o método atualizar() ainda não foi implementado, e
 * inserir() não impede matrícula duplicada.
 *
 * Tarefa:
 * - Etapa 1: completar as quatro operações do CRUD;
 * - Etapa 3: corrigir a coleção exposta e a exclusão silenciosa.
 */
public class AlunoDAOMemoria implements AlunoDAO {

    private final List<Aluno> armazem = new ArrayList<>();

    @Override
    public void inserir(Aluno aluno) {
        if (buscarPorMatricula(aluno.getMatricula()) != null) {
            throw new IllegalStateException(
                    "Já existe aluno com a matrícula " + aluno.getMatricula());
        }
        armazem.add(aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno aluno : armazem) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null; 
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(armazem); 
    }

    @Override
    public void atualizar(Aluno aluno) {
        for (int i = 0; i < armazem.size(); i++) {
            if (armazem.get(i).getMatricula().equals(aluno.getMatricula())) {
                armazem.set(i, aluno);
                return;
            }
        }
        throw new IllegalStateException(
                "Aluno não encontrado: " + aluno.getMatricula());
    }

    @Override
    public void remover(String matricula) {
        Aluno encontrado = buscarPorMatricula(matricula);
        if (encontrado == null) {
            throw new IllegalStateException("Aluno não encontrado: " + matricula);
        }
        armazem.remove(encontrado);
    }
}