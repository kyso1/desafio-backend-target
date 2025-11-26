package com.desafio.desafio.Services;

// EstoqueService.java
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.desafio.desafio.DTO.MovimentacaoDTO;

import jakarta.annotation.PostConstruct;

@Service
public class EstoqueService {

    // Simula o banco de dados em memória
    private final Map<Integer, Integer> estoqueAtual = new ConcurrentHashMap<>();

    // Carrega o JSON inicial fornecido no exercício [cite: 50]
    @PostConstruct
    public void carregarCargaInicial() {
        estoqueAtual.put(101, 150); // Caneta Azul
        estoqueAtual.put(102, 75);  // Caderno
        estoqueAtual.put(103, 200); // Borracha
        estoqueAtual.put(104, 320); // Lápis
        estoqueAtual.put(105, 90);  // Marcador 
    }

    public Map<String, Object> processarMovimentacao(MovimentacaoDTO movimento) {
        if (!estoqueAtual.containsKey(movimento.getCodigoProduto())) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        int saldoAtual = estoqueAtual.get(movimento.getCodigoProduto());
        int novaQuantidade;

        if ("ENTRADA".equalsIgnoreCase(movimento.getTipo())) {
            novaQuantidade = saldoAtual + movimento.getQuantidade();
        } else if ("SAIDA".equalsIgnoreCase(movimento.getTipo())) {
            if (saldoAtual < movimento.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente.");
            }
            novaQuantidade = saldoAtual - movimento.getQuantidade();
        } else {
            throw new IllegalArgumentException("Tipo de movimentação inválido.");
        }

        // Atualiza estado
        estoqueAtual.put(movimento.getCodigoProduto(), novaQuantidade);

        // Retorna resposta com ID único e saldo final [cite: 47, 49]
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("idMovimentacao", UUID.randomUUID().toString());
        resposta.put("mensagem", movimento.getDescricaoMovimentacao());
        resposta.put("saldoFinal", novaQuantidade);
        
        return resposta;
    }

    public void resetarMemoria() {
        estoqueAtual.clear(); 
        carregarCargaInicial(); 
    }
}