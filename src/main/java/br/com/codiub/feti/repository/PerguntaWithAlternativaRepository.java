package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerguntaWithAlternativaRepository extends JpaRepository<Inscricao, Long> {


    @Query("select e from Inscricao e where e.id = :id and e.actived = false")
    Optional<Inscricao> findByIdDesactived(Long id);

    @Query("select e from Inscricao e where e.id = :id")
    Optional<Inscricao> findInscricao(Long id);
}
