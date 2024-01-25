package com.maurlox21.cryptoalert.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.maurlox21.cryptoalert.entity.User;

public class JwtUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public JwtUserDetails(User  user) {
        super(user.getTxEmail(), user.getTxPassword(), AuthorityUtils.createAuthorityList(user.getTxRole()));
        this.user = user;
    }

    public Long getId() {
        return this.user.getId(); 
    }
    
    public String getRole(){
        return  this.user.getTxRole();
    }
}
