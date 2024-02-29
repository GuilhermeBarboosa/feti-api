package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.entity.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleTelaRepository extends JpaRepository<RoleTela, Long> {

    @Query("select r from RoleTela r where r.role.id = :id")
    List<RoleTela> findByRole(Long id);
}
