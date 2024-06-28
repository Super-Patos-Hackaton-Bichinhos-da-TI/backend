package com.api.superpatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.superpatos.dto.CreateChallengeDTO;
import com.api.superpatos.model.Challenge;
import com.api.superpatos.service.ChallengeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ChallengeController {
    
    final private ChallengeService challengeService;

    @Autowired
    public ChallengeController(final ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }

    @PostMapping("/challenge/create")
    public ResponseEntity<HttpStatus> createChallenge(@RequestBody @Valid CreateChallengeDTO dto) {
        challengeService.createChallenge(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/challenge/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable("id") String id) {
        Optional<Challenge> challenge = challengeService.getChallenge(id);

        return challenge.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    } 
    
    @DeleteMapping("/challenge/{id}")
    public ResponseEntity<HttpStatus> deleteChallengeById(@PathVariable("id") String id) {
        return new ResponseEntity<>(challengeService.delete(id) == true ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
