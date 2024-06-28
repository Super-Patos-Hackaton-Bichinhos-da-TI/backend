package com.api.superpatos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.superpatos.dto.CreateChallengeDTO;
import com.api.superpatos.model.Challenge;
import com.api.superpatos.repository.ChallengeRepository;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(final ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public void createChallenge(CreateChallengeDTO challengeDto) {
        if (challengeDto.title() == null || challengeDto.description() == null || challengeDto.difficult() == null
                || challengeDto.tags() == null) {
            throw new IllegalArgumentException("Unable to create challenge: one or more fields are null.");
        }

        Challenge challenge = new Challenge(challengeDto.title(), challengeDto.description_interface(),
                challengeDto.description(), challengeDto.difficult(), challengeDto.tags(),
                challengeDto.squad(), challengeDto.link_img(), challengeDto.date());
        challengeRepository.save(challenge);
    }
}
