package com.example.javacrud.domain.models;

public class Produto {

    private Integer id;
    private String nome;
    private String marca;
    private Double preco;
    private Fornecedor fornecedor;

    public Produto(Integer id, String nome, String marca, Double preco, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
