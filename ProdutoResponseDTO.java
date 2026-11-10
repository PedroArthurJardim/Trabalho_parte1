package com.example.estoque.dto;

import com.example.estoque.entity.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoResponseDTO {
    private final Long id;
    private final String nome;
    private final BigDecimal preco;
    private final Integer quantidadeEstoque;
    private final String categoria;
    private final LocalDateTime dataCriacao;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.categoria = produto.getCategoria();
        this.dataCriacao = produto.getDataCriacao();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
}
