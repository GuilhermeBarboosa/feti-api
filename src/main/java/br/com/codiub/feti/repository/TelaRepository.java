package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Role;
import br.com.codiub.feti.model.entity.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Long> {

}
