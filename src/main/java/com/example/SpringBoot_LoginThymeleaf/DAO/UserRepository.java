package com.example.SpringBoot_LoginThymeleaf.DAO;

import com.example.SpringBoot_LoginThymeleaf.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
