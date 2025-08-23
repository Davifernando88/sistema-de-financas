// repository/ReceitaRepository.java
package com.financas.FinancasMaven.repository;

import com.financas.FinancasMaven.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByDataBetween(LocalDate inicio, LocalDate fim);
}
