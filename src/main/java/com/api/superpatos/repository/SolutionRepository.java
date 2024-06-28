package com.api.superpatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.superpatos.model.Solution;

public interface SolutionRepository extends JpaRepository<Solution, String> {
    
}
