package controller;

import exceptions.AlunoNotFoundException;
import model.Aluno;
import services.AlunoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AlunoController {

    private final AlunoService alunoService;
    private final Scanner scanner;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option;
        do {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> registerStudent();
                case 2 -> deleteStudent();
                case 3 -> updateStudent();
                case 4 -> findStudentByName();
                case 5 -> listStudentsWithStatus();
                case 6 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida!");
            }
        } while (option != 6);
    }

    private void displayMenu() {
        System.out.println("\nCADASTRO DE ALUNOS");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Excluir aluno");
        System.out.println("3 - Alterar aluno");
        System.out.println("4 - Buscar aluno pelo nome");
        System.out.println("5 - Listar alunos (com status aprovação)");
        System.out.println("6 - FIM");
        System.out.print("Escolha uma opção: ");
    }

    private void registerStudent() {
        System.out.print("Nome do aluno: ");
        String name = scanner.nextLine();

        System.out.print("RA do aluno: ");
        String registrationNumber = scanner.nextLine();

        System.out.print("Email do aluno: ");
        String email = scanner.nextLine();

        System.out.print("Nota 1: ");
        BigDecimal grade1 = BigDecimal.valueOf(scanner.nextDouble());

        System.out.print("Nota 2: ");
        BigDecimal grade2 = BigDecimal.valueOf(scanner.nextDouble());

        System.out.print("Nota 3: ");
        BigDecimal grade3 = BigDecimal.valueOf(scanner.nextDouble());

        Aluno aluno = new Aluno(name, registrationNumber, email, grade1, grade2, grade3);
        alunoService.register(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void deleteStudent() {
        System.out.print("Nome do aluno a ser excluído: ");
        String name = scanner.nextLine();

        try {
            alunoService.remove(name);
            System.out.println("Aluno excluído com sucesso!");
        } catch (AlunoNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStudent() {
        System.out.print("Nome do aluno a ser alterado: ");
        String oldName = scanner.nextLine();

        Aluno existingAluno;

        try {
            existingAluno = alunoService.findByName(oldName);
        } catch (AlunoNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }


        System.out.print("Novo nome: ");
        String newName = scanner.nextLine();

        System.out.print("Novo RA: ");
        String newRegistrationNumber = scanner.nextLine();

        System.out.print("Novo email: ");
        String newEmail = scanner.nextLine();

        System.out.print("Nova Nota 1: ");
        BigDecimal newGrade1 = BigDecimal.valueOf(scanner.nextDouble());

        System.out.print("Nova Nota 2: ");
        BigDecimal newGrade2 = BigDecimal.valueOf(scanner.nextDouble());

        System.out.print("Nova Nota 3: ");
        BigDecimal newGrade3 = BigDecimal.valueOf(scanner.nextDouble());

        Aluno updatedAluno = new Aluno(newName, newRegistrationNumber, newEmail, newGrade1, newGrade2, newGrade3);
        try {
            alunoService.update(existingAluno, updatedAluno);
            System.out.println("Aluno alterado com sucesso!");
        } catch (AlunoNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findStudentByName() {
        System.out.print("Nome do aluno: ");
        String name = scanner.nextLine();

        try {
            Aluno aluno = alunoService.findByName(name);
            System.out.println(aluno);
        } catch (AlunoNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listStudentsWithStatus() {
        System.out.println("\nLista de Alunos (Status de Aprovação):");
        List<Aluno> alunos = alunoService.getAll();
        if (alunos.isEmpty()) {
            System.out.println("Não existem alunos cadastrados no sistema");
            return;
        }

        alunos.forEach(System.out::println);
    }
}
