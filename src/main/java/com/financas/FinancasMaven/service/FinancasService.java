// service/FinancasService.java
package com.financas.FinancasMaven.service;

import com.financas.FinancasMaven.model.Despesas;
import com.financas.FinancasMaven.model.Receita;
import com.financas.FinancasMaven.repository.DespesaRepository;
import com.financas.FinancasMaven.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancasService {

    private final DespesaRepository despesaRepo;
    private final ReceitaRepository receitaRepo;

    public FinancasService(DespesaRepository despesaRepo, ReceitaRepository receitaRepo) {
        this.despesaRepo = despesaRepo;
        this.receitaRepo = receitaRepo;
    }

    public List<Despesas> listarDespesas(LocalDate inicio, LocalDate fim) {
        return despesaRepo.findByDataBetween(inicio, fim);
    }

    public List<Receita> listarReceitas(LocalDate inicio, LocalDate fim) {
        return receitaRepo.findByDataBetween(inicio, fim);
    }

    public Despesas salvarDespesa(Despesas d) {
        return despesaRepo.save(d);
    }

    public Receita salvarReceita(Receita r) {
        return receitaRepo.save(r);
    }

    public double calcularSaldo(LocalDate inicio, LocalDate fim) {
        double totalReceitas = listarReceitas(inicio, fim).stream().mapToDouble(Receita::getValor).sum();
        double totalDespesas = listarDespesas(inicio, fim).stream().mapToDouble(Despesas::getValor).sum();
        return totalReceitas - totalDespesas;
    }
}
