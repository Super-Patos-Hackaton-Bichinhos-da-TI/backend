package com.api.superpatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.superpatos.dto.CreateChallengeDTO;
import com.api.superpatos.model.Challenge;
import com.api.superpatos.repository.ChallengeRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(final ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public void createChallenge(CreateChallengeDTO challengeDto) {
        if (!notNull(challengeDto.title(), challengeDto.description(), challengeDto.difficult(),
                challengeDto.tags(), challengeDto.xp_reward()) || challengeDto.xp_reward() < 0) {
            throw new IllegalArgumentException("Unable to create challenge: one or more fields are null.");
        }

        Challenge challenge = new Challenge(challengeDto.title(), challengeDto.description_interface(),
                challengeDto.description(), challengeDto.difficult(), challengeDto.tags(),
                challengeDto.squad(), challengeDto.link_img(), challengeDto.date(), challengeDto.xp_reward());
        challengeRepository.save(challenge);
    }

    public Optional<Challenge> getChallenge(String challenge_id) {
        return challengeRepository.findById(challenge_id);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Boolean delete(String challenge_id) {
        Optional<Challenge> challenge = challengeRepository.findById(challenge_id);
        if (challenge.isEmpty()) {
            return false;
        }

        challengeRepository.deleteById(challenge_id);
        return true;
    }

    private static  boolean notNull(Object... parameters){
        for(Object paremeter : parameters){
            if(paremeter == null){
                return false;
            }
        }
        return true;
    }
}
