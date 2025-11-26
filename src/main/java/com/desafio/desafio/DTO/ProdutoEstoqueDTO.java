package com.desafio.desafio.DTO;

import lombok.Data;

@Data
public class ProdutoEstoqueDTO {
    private Integer codigoProduto;
    private String descricaoProduto;
    private int estoque;
}