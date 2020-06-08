package com.tarasPlus.restClient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
public class Role implements GrantedAuthority {

    private Long id;

    private String role;

    public Role(){
        this.role = "USER";
    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

}