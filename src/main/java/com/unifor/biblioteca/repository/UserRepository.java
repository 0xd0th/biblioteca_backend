package com.unifor.biblioteca.repository;
import com.unifor.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMatricula(int matricula); 
}