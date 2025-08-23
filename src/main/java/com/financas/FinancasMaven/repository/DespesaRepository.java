// repository/DespesaRepository.java
package com.financas.FinancasMaven.repository;

import com.financas.FinancasMaven.model.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesas, Long> {
    List<Despesas> findByDataBetween(LocalDate inicio, LocalDate fim);
}
