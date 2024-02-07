package com.nur.usuarios.repositories;

import com.nur.usuarios.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?2 and u.password = ?3")
//    List<User> findByUsernameOrEmailAndPasswordQuery(String username, String email, String password);

    List<User> findByUsernameOrEmailAndPassword(String username, String email, String password);

    Optional<User> findByUsername (String username);
}
