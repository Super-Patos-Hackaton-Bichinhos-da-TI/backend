package com.api.superpatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.superpatos.dto.SolutionDTO;
import com.api.superpatos.model.Solution;
import com.api.superpatos.service.SolutionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class SolutionController {

    final private SolutionService solutionService;

    @Autowired
    public SolutionController(final SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("/solutions")
    public ResponseEntity<List<Solution>> getAllSolutions() {
        List<Solution> challenges = solutionService.getAllSolutions();
        return ResponseEntity.ok(challenges);
    }

    @PostMapping("/solution/send/{user_id}")
    public ResponseEntity<HttpStatus> sendSolution(@PathVariable("user_id") String user_id, @RequestBody @Valid SolutionDTO solutionDTO) {
        
        System.out.println(user_id);
        try {
            solutionService.sendSolution(user_id, solutionDTO);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/solution/{id}")
    public ResponseEntity<Solution> getSolution(@PathVariable("id") String id) {
        Optional<Solution> solution = solutionService.getSolution(id);

        return solution.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
