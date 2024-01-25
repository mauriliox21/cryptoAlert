package com.maurlox21.cryptoalert.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maurlox21.cryptoalert.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByTxEmail(String txEmail);

    @Query("select u.txRole from User u where u.txEmail like :email")
    public String findRoleByEmail(String email);
}   
