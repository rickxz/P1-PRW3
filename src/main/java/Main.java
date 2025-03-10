import controller.AlunoController;
import dao.AlunoDAO;
import jakarta.persistence.EntityManager;
import services.AlunoService;
import utils.JPAUtil;

// Integrantes: Herick Victor Rodrigues | SC3038904

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunoDAO alunoDAO = new AlunoDAO(em);

        AlunoService alunoService = new AlunoService(alunoDAO);
        AlunoController alunoController = new AlunoController(alunoService);

        alunoController.start();
    }
}
