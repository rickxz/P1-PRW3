package services;

import dao.AlunoDAO;
import exceptions.AlunoNotFoundException;
import model.Aluno;

import java.lang.reflect.Field;
import java.util.List;

public class AlunoService {
    private final AlunoDAO alunoDAO;

    public AlunoService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void register(Aluno aluno) {
        alunoDAO.register(aluno);
    }

    public Aluno findByName(String name) {
        return alunoDAO.findByName(name)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado: " + name));
    }

    public void remove(String name) throws AlunoNotFoundException {
        Aluno toRemove = alunoDAO.findByName(name)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado: " + name));

        alunoDAO.remove(toRemove);
    }

    public void update(Aluno existingAluno, Aluno updatedAluno) {
        copyProperties(updatedAluno, existingAluno);
        alunoDAO.update(existingAluno);
    }

    public List<Aluno> getAll() {
        return alunoDAO.getAll();
    }

    private void copyProperties(Aluno sourceAluno, Aluno targetAluno) {
        try {
            Field[] fields = Aluno.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    Object value = field.get(sourceAluno);
                    field.set(targetAluno, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao copiar propriedades", e);
        }
    }
}
