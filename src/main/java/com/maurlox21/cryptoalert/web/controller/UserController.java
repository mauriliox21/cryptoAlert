package com.maurlox21.cryptoalert.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.jwt.JwtUserDetails;
import com.maurlox21.cryptoalert.repostory.projection.UserProjection;
import com.maurlox21.cryptoalert.service.UserService;
import com.maurlox21.cryptoalert.web.dto.PageDto;
import com.maurlox21.cryptoalert.web.dto.UserCreateDto;
import com.maurlox21.cryptoalert.web.dto.UserResponseDto;
import com.maurlox21.cryptoalert.web.dto.mapper.PageMapper;
import com.maurlox21.cryptoalert.web.dto.mapper.UserMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;
    
    
    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userDto) {
        
        User user = this.service.createUser(UserMapper.toEntity(userDto));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    } 
    
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageDto> getUsers(@PageableDefault Pageable pageable) {
        
        Page<UserProjection> users = this.service.findAllUsers(pageable);

        return ResponseEntity.ok(PageMapper.toDto(users));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        
        User user = this.service.getUserByUserId(id);

        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping("/details")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponseDto> getDetails(@AuthenticationPrincipal JwtUserDetails userDetails){

        User user = this.service.getUserByUserId(userDetails.getId());
        
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

}
