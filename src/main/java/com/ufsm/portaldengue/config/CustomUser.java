package com.ufsm.portaldengue.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUser extends User {
    private com.ufsm.portaldengue.model.entity.User user;

    public CustomUser(com.ufsm.portaldengue.model.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getHash(), authorities);
        this.user = user;
    }
}
