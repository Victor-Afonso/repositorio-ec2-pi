package br.com.bandtec.banco.teste;

public class Equipe {

    private Integer id;
    private String nome;
    private String area;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return String.format("\nID: %d\nNOME: %s\nAREA: %s\n",
                this.id, this.nome, this.area);
    }

    
}
