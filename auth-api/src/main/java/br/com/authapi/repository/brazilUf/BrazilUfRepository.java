package br.com.authapi.repository.brazilUf;

import br.com.authapi.entities.brazilUf.BrazilUf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrazilUfRepository extends JpaRepository<BrazilUf, Long> {
}
