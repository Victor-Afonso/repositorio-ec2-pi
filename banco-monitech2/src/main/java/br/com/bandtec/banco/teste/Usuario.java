package br.com.bandtec.banco.teste;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private Integer FK_Gerente;
    private Integer FK_Equipe;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getFK_Gerente() {
        return FK_Gerente;
    }

    public void setFK_Gerente(Integer FK_Gerente) {
        this.FK_Gerente = FK_Gerente;
    }

    public Integer getFK_Equipe() {
        return FK_Equipe;
    }

    public void setFK_Equipe(Integer FK_Equipe) {
        this.FK_Equipe = FK_Equipe;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", "
                + "email=" + email + ", senha=" + senha + ", "
                + "cargo=" + cargo + ","
                + " FK_Gerente=" + FK_Gerente + ", FK_Equipe="
                + FK_Equipe + '}';
    }

}
