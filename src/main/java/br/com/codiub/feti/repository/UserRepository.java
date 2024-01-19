package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    @Query("select u from User u where u.actived = false")
    List<User> findAllUserDesactived();

    @Query("select u from User u where u.id = :id and u.actived = false")
    Optional<User> findByIdDesactived(Long id);

    Optional<User> findByEmail(String email);

}
