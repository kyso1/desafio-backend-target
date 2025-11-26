package com.desafio.desafio.DTO;

// MovimentacaoDTO.java
import lombok.Data;

@Data
public class MovimentacaoDTO {
    private Integer codigoProduto;
    private String descricaoMovimentacao; // Ex: "Entrada NF 123" [cite: 48]
    private String tipo; // "ENTRADA" ou "SAIDA"
    private int quantidade;
}
