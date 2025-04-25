package br.com.fiap.arcadia.model;

public class Personagem {
    private Long id;
    private String nome;
    private TipoClasse classe;
    private int nivel;
    private int moedas;

    public Personagem(Long id, String nome, TipoClasse classe, int nivel, int moedas) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.moedas = moedas;
    }

    public Personagem() {
    }

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

    public TipoClasse getClasse() {
        return classe;
    }

    public void setClasse(TipoClasse classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }
}
