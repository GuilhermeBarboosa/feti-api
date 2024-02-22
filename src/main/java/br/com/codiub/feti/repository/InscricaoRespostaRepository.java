package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.InscricaoResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRespostaRepository extends JpaRepository<InscricaoResposta, Long> {

    List<InscricaoResposta> findAll();

    Optional<InscricaoResposta> findById(Long id);

    @Query("select e from InscricaoResposta e where e.actived = false")
    List<InscricaoResposta> findAllInscricaoRespostaDesactived();

    @Query("select e from InscricaoResposta e where e.id = :id and e.actived = false")
    Optional<InscricaoResposta> findByIdDesactived(Long id);

    @Query("select e from InscricaoResposta e where e.inscricao.id = :id and e.actived = true")
    List<InscricaoResposta> findByInscricao(Long id);

    void deleteByInscricao(Long inscricao);
}
