package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

    List<Funcao> findAll();

    Optional<Funcao> findById(Long id);

    @Query("select f from Funcao f where f.actived = false")
    List<Funcao> findAllFuncaoDesactived();

    @Query("select f from Funcao f where f.id = :id and f.actived = false")
    Optional<Funcao> findByIdDesactived(Long id);

    @Query("select f from Funcao f where f.edital.id = :idEdital and f.actived = true")
    List<Funcao> findByIdEdital(Long idEdital);
}
