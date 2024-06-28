package com.api.superpatos.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.superpatos.model.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, String> {
}