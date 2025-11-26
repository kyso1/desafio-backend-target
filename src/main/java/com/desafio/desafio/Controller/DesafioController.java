package com.desafio.desafio.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.desafio.DTO.MovimentacaoDTO;
import com.desafio.desafio.DTO.VendaDTO;
import com.desafio.desafio.Services.ComissaoService;
import com.desafio.desafio.Services.EstoqueService;
import com.desafio.desafio.Services.JurosService;

@RestController
@RequestMapping("/api/desafio")
public class DesafioController {

    @Autowired private ComissaoService comissaoService;
    @Autowired private EstoqueService estoqueService;
    @Autowired private JurosService jurosService;

    // Exercício 1: POST para calcular comissões
    @PostMapping("/comissoes")
    public Map<String, BigDecimal> calcularComissoes(@RequestBody Map<String, List<VendaDTO>> payload) {
        return comissaoService.calcularComissoes(payload.get("vendas"));
    }

    // Exercício 2: POST para movimentar estoque
    @PostMapping("/estoque/movimentar")
    public Map<String, Object> movimentarEstoque(@RequestBody MovimentacaoDTO dto) {
        return estoqueService.processarMovimentacao(dto);
    }

    // Exercício 3: GET para calcular juros
    @GetMapping("/juros")
    public BigDecimal calcularJuros(
            @RequestParam BigDecimal valor, 
            @RequestParam String vencimento) { 
        return jurosService.calcularDividaAtual(valor, LocalDate.parse(vencimento));
    }
    // Endpoint adicional para resetar o estoque e não precisar reiniciar a aplicação
    @PostMapping("/estoque/reset")
    public String resetarEstoque() {
        estoqueService.resetarMemoria();
        return "Estoque resetado com sucesso!";
    }
}