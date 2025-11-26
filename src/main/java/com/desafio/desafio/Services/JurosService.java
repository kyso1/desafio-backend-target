package com.desafio.desafio.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class JurosService {

    public BigDecimal calcularDividaAtual(BigDecimal valorOriginal, LocalDate dataVencimento) {
        LocalDate hoje = LocalDate.now();

        if (!hoje.isAfter(dataVencimento)) {
            return valorOriginal;
        }

        long diasAtraso = ChronoUnit.DAYS.between(dataVencimento, hoje);
         
        BigDecimal taxaDiaria = new BigDecimal("0.025");
        BigDecimal totalTaxa = taxaDiaria.multiply(new BigDecimal(diasAtraso));
        
        BigDecimal valorJuros = valorOriginal.multiply(totalTaxa);
        
        return valorOriginal.add(valorJuros);
    }
}