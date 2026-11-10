package com.example.estoque.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ProdutoRequestDTO {

    @NotBlank(message = "não pode estar vazio")
    @Size(max = 100, message = "deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "é obrigatório")
    @Min(value = 0, message = "deve ser maior ou igual a zero")
    private Integer quantidadeEstoque;

    @Size(max = 100, message = "deve ter no máximo 100 caracteres")
    private String categoria;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
