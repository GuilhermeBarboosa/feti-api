package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.entity.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleTelaRepository extends JpaRepository<RoleTela, Long> {

    @Query("select r from RoleTela r where r.role.id = :id")
    List<RoleTela> findByRole(Long id);
    @Query("delete from RoleTela r where r.role.id = :roleId")
    void deleteByRole(Long role);
    @Query("select r from RoleTela r where r.tela.id = :tela and r.role.id = :role")
    Optional<RoleTela> findByRoleByTela(Long role, Long tela);
}
