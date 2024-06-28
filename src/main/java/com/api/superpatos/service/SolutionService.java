package com.api.superpatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.superpatos.dto.SolutionDTO;
import com.api.superpatos.model.Challenge;
import com.api.superpatos.model.Solution;
import com.api.superpatos.model.User;
import com.api.superpatos.repository.ChallengeRepository;
import com.api.superpatos.repository.SolutionRepository;
import com.api.superpatos.repository.UserRepository;

@Service
public class SolutionService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final SolutionRepository solutionRepository;

    @Autowired
    public SolutionService(final UserRepository userRepository, final ChallengeRepository challengeRepository,
            final SolutionRepository solutionRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.solutionRepository = solutionRepository;
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

        user.addXp(challenge.getXp_reward());

        solutionRepository.save(solution);
        userRepository.save(user);
    }

    public Optional<Solution> getSolution(String solution_id) {
        return solutionRepository.findById(solution_id);
    }

    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public Boolean delete(String solution_id) {
        Optional<Solution> solution = solutionRepository.findById(solution_id);
        if (solution.isEmpty()) {
            return false;
        }

        solutionRepository.deleteById(solution_id);
        return true;
    }
}
