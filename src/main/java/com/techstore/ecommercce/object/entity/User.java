package com.techstore.ecommercce.object.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String username;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private boolean gender;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(columnDefinition = "varchar(12)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(200)", nullable = false)
    private String address;

    @Column(columnDefinition = "text", nullable = false)
    private String image;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities().stream()
                   .map(authority -> new SimpleGrantedAuthority(authority.toUpperCase()))
                   .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
