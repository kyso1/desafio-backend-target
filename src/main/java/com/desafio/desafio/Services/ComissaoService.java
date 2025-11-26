package com.desafio.desafio.Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.desafio.desafio.DTO.VendaDTO;

@Service
public class ComissaoService {

    public Map<String, BigDecimal> calcularComissoes(List<VendaDTO> vendas) {
        // Agrupa por vendedor e soma as comissões calculadas individualmente
        return vendas.stream()
            .collect(Collectors.groupingBy(
                VendaDTO::getVendedor,
                Collectors.reducing(
                    BigDecimal.ZERO,
                    this::calcularComissaoPorVenda,
                    BigDecimal::add
                )
            ));
    }

    private BigDecimal calcularComissaoPorVenda(VendaDTO venda) {
        BigDecimal valor = venda.getValor();
        
        if (valor.compareTo(new BigDecimal("100.00")) < 0) {
            return BigDecimal.ZERO;
        } 
        else if (valor.compareTo(new BigDecimal("500.00")) < 0) {
            return valor.multiply(new BigDecimal("0.01"));
        } 
        else {
            return valor.multiply(new BigDecimal("0.05"));
        }
    }
}