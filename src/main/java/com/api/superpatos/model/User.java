package com.api.superpatos.model;

import com.api.superpatos.enums.Pet;
import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String email;
    private String password;
    private String biography;
    private String office;
    private String xp;

    @Enumerated(EnumType.STRING)
    private Squad squad;
    
    @Enumerated(EnumType.STRING)
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email, Squad squad, String office, Role role){
        this.email = email;
        this.squad = squad;
        this.office = office;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            default -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        };
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
