package com.api.superpatos.repository;

import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;
import com.api.superpatos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
    User findByUser(String email, Squad squad, Role role);
}
