package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

    List<Alternativa> findAll();

    Optional<Alternativa> findById(Long id);

    @Query("select a from Alternativa a where a.actived = false")
    List<Alternativa> findAllAlternativaDesactived();

    @Query("select a from Alternativa a where a.id = :id and a.actived = false")
    Optional<Alternativa> findByIdDesactived(Long id);

    @Query("select a from Alternativa a where a.pergunta.funcao.id = :idFuncao and a.actived = true and a.pergunta.actived = true and a.pergunta.funcao.actived = true")
    List<Alternativa> findAlternativasWithFuncao(Long idFuncao);

    @Query("select a from Alternativa a where a.pergunta.id = :id and a.actived = true")
    List<Alternativa> getAlternativaByPergunta(Long id);
}
