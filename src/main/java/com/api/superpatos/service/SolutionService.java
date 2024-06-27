package com.api.superpatos.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.superpatos.dto.SolutionDTO;
import com.api.superpatos.model.Challenge;
import com.api.superpatos.model.Solution;
import com.api.superpatos.model.User;
import com.api.superpatos.repository.ChallengeRepository;
import com.api.superpatos.repository.UserRepository;

public class SolutionService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    @Autowired
    public SolutionService(final UserRepository userRepository, final ChallengeRepository challengeRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }

    public void sendSolution(String user_id, SolutionDTO solutionDto) {
        if (user_id == null || solutionDto.link() == null || solutionDto.description() == null
                || solutionDto.challenge_id() == null) {
            throw new IllegalArgumentException("Unable to send solution: one or more fields are null.");
        }

        User user = userRepository.findById(user_id)
                .orElseThrow(RuntimeException::new);

        Challenge challenge = challengeRepository.findById(solutionDto.challenge_id())
                .orElseThrow(RuntimeException::new);

        Solution solution = new Solution(solutionDto.link(), solutionDto.description());
        solution.setUser(user);
        solution.setChallenge(challenge);
    }
}
