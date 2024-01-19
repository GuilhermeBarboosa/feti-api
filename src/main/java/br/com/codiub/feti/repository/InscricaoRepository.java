package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findAll();

    Optional<Inscricao> findById(Long id);

    @Query("select e from Inscricao e where e.actived = false")
    List<Inscricao> findAllInscricaoDesactived();

    @Query("select e from Inscricao e where e.id = :id and e.actived = false")
    Optional<Inscricao> findByIdDesactived(Long id);

    @Query("select e from Inscricao e where e.usuario.id = :idUser and e.actived = true")
    Optional<Inscricao> findByUser(Long idUser);

    @Query("select e from Inscricao e where e.usuario.id = :idUser and e.funcao.id = " +
            ":idFuncao and e.edital.id = " +
            ":idEdital  and e.actived = true")
    Optional<Inscricao> findEditalInscricao(Long idEdital, Long idFuncao, Long idUser);
    @Query("select e from Inscricao e where e.usuario.id = :id and e.actived = true")
    List<Inscricao> findByAllUser(Long id);
}
