package com.example.test.model;


import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Transient
    private String passwordConfirm;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    private String gender;

    @NonNull
    private String password;
    @NonNull

    private String role;


    @NonNull

    private String username;

    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String country;

    @NonNull
    private boolean active;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(getRole()));

    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
