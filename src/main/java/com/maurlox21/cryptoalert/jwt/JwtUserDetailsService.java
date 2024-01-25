package com.maurlox21.cryptoalert.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.maurlox21.cryptoalert.entity.User;
import com.maurlox21.cryptoalert.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email){
        User user = userService.getUserByEmail(email);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String email) {
        String role = userService.getRoleByEmail(email);
        return JwtUtils.createToken(email, role.substring("ROLE_".length()));
    }
}
