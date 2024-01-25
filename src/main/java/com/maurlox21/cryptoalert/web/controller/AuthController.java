package com.maurlox21.cryptoalert.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurlox21.cryptoalert.jwt.JwtToken;
import com.maurlox21.cryptoalert.jwt.JwtUserDetailsService;
import com.maurlox21.cryptoalert.web.dto.AuthenticationDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;
    
    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationDto auth) {
        try{
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(auth.getTxEmail(), auth.getTxPassword());

            authenticationManager.authenticate(authentication);

            JwtToken token = detailsService.getTokenAuthenticated(auth.getTxEmail());

            return ResponseEntity.ok(token);

        }catch(AuthenticationException ex){
            System.out.println("Bad Credentials from " + auth.getTxEmail());
        }

        return ResponseEntity
                .badRequest()
                .body("Credencias invalidas");
    }
}
