// controller/FinancasController.java
package com.financas.FinancasMaven.controller;

import com.financas.FinancasMaven.model.Despesas;
import com.financas.FinancasMaven.model.Receita;
import com.financas.FinancasMaven.service.FinancasService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/financas")
public class FinancasController {

    private final FinancasService service;

    public FinancasController(FinancasService service) {
        this.service = service;
    }

    @PostMapping("/despesa")
    public Despesas adicionarDespesa(@RequestBody Despesas d) {
        return service.salvarDespesa(d);
    }

    @PostMapping("/receita")
    public Receita adicionarReceita(@RequestBody Receita r) {
        return service.salvarReceita(r);
    }

    @GetMapping("/despesas")
    public List<Despesas> listarDespesas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.listarDespesas(inicio, fim);
    }

    @GetMapping("/receitas")
    public List<Receita> listarReceitas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.listarReceitas(inicio, fim);
    }

    @GetMapping("/saldo")
    public double saldo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.calcularSaldo(inicio, fim);
    }
}
