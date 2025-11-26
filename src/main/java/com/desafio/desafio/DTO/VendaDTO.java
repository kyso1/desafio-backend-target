package com.desafio.desafio.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VendaDTO {
    private String vendedor;
    private BigDecimal valor;
}