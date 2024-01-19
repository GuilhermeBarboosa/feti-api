package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Edital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditalRepository extends JpaRepository<Edital, Long> {

    List<Edital> findAll();

    Optional<Edital> findById(Long id);

    @Query("select e from Edital e where e.actived = false")
    List<Edital> findAllEditalDesactived();

    @Query("select e from Edital e where e.id = :id and e.actived = false")
    Optional<Edital> findByIdDesactived(Long id);


}
