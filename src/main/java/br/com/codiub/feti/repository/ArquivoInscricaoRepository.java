package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.ArquivoInscricao;
import br.com.codiub.feti.model.entity.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArquivoInscricaoRepository extends JpaRepository<ArquivoInscricao, Long> {

    List<ArquivoInscricao> findAll();

    Optional<ArquivoInscricao> findById(Long id);

    @Query("select p from ArquivoInscricao p where p.actived = false")
    List<ArquivoInscricao> findAllPerguntaDesactived();

    @Query("select p from ArquivoInscricao p where p.id = :id and p.actived = false")
    Optional<ArquivoInscricao> findByIdDesactived(Long id);

    @Query("select p from ArquivoInscricao p where p.inscricao.id = :inscricao and p.actived = true and p.inscricao.actived = true")
    List<ArquivoInscricao> findByInscricao(Long inscricao);
}
