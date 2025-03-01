package model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "alunos")
public class Aluno {

    public Aluno() {}

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    public BigDecimal getMedia() {
        BigDecimal soma = getNota1().add(getNota2()).add(getNota3());
        return soma.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
    }

    public String getSituacao() {
        BigDecimal media = getMedia();
        String status = (media.compareTo(BigDecimal.valueOf(4)) < 0) ? "Reprovado" :
                (media.compareTo(BigDecimal.valueOf(6)) < 0) ? "Recuperação" : "Aprovado";

        return status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(getNome()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("RA: ").append(getRa()).append("\n");
        sb.append("Notas: ").append(String.format("%.2f - %.2f - %.2f", getNota1(), getNota2(), getNota3())).append("\n");
        sb.append("Média: ").append(String.format("%.2f", getMedia())).append("\n");
        sb.append("Situação: ").append(getSituacao());
        return sb.toString();
    }
}
