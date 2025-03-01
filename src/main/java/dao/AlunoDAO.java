package dao;

import jakarta.persistence.EntityManager;
import model.Aluno;

import java.util.List;
import java.util.Optional;

public class AlunoDAO {
    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void register(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> getAll() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Optional<Aluno> findByName(String name) {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = ?1";
        return em.createQuery(jpql, Aluno.class)
                .setParameter(1, name)
                .getResultStream()
                .findFirst();
    }

    public void update(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void remove(Aluno aluno) {
        try {
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao remover aluno: " + e.getMessage());
        }
    }
}