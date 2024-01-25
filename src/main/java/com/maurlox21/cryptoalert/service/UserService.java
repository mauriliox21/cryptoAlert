package com.maurlox21.cryptoalert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.repostory.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired   
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser (User user){
        
        user.setTxPassword(passwordEncoder.encode(user.getTxPassword()));
        User userCreated = this.repository.save(user);
        
        return userCreated;
    }

    public User getUserByUserId(Long id) {
        Optional<User> user = this.repository.findById(id);
        
        if(user.isEmpty())
            throw new RuntimeException("Usuario não encontrado");
        
        return user.get();
    }

    public User getUserByEmail(String email) {
        Optional<User> user = this.repository.findByTxEmail(email);
        
        if(user.isEmpty())
            throw new RuntimeException("Usuario não encontrado");
        
        return user.get();
    }

    public String getRoleByEmail(String email) {
        return this.repository.findRoleByEmail(email);
    }
}
