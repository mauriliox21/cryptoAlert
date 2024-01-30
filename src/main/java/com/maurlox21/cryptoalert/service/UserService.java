package com.maurlox21.cryptoalert.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.exception.EntityNotFoundException;
import com.maurlox21.cryptoalert.repostory.UserRepository;
import com.maurlox21.cryptoalert.repostory.projection.UserProjection;

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

    @Transactional(readOnly = true)
    public User getUserByUserId(Long id) {
        Optional<User> user = this.repository.findById(id);
        
        if(user.isEmpty())
            throw new EntityNotFoundException("User not found");
        
        return user.get();
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        Optional<User> user = this.repository.findByTxEmail(email);
        
        if(user.isEmpty())
            throw new RuntimeException("Usuario não encontrado");
        
        return user.get();
    }

    @Transactional(readOnly = true)
    public String getRoleByEmail(String email) {
        return this.repository.findRoleByEmail(email);
    }

    @Transactional(readOnly = true)
    public Page<UserProjection> findAllUsers(Pageable pageable){
        return this.repository.findAllPageable(pageable);
    }
}
