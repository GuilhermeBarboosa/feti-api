package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findAll();

    Optional<Pergunta> findById(Long id);

    @Query("select p from Pergunta p where p.actived = false")
    List<Pergunta> findAllPerguntaDesactived();

    @Query("select p from Pergunta p where p.id = :id and p.actived = false")
    Optional<Pergunta> findByIdDesactived(Long id);
    @Query("select p from Pergunta p where p.funcao.id = :idFuncao and p.actived = true and p.funcao.actived = true")
    List<Pergunta> listPerguntasWithFuncao(Long idFuncao);
}
