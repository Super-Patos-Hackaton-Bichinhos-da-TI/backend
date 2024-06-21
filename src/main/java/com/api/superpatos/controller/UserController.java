package com.api.superpatos.controller;

import com.api.superpatos.dto.CreateDTO;
import com.api.superpatos.dto.UpdateNewUserDTO;
import com.api.superpatos.dto.UpdateUserByAdmin;
import com.api.superpatos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(path = "/auth/create-user")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateDTO create){
        service.createUser(create);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateNewUser(@PathVariable("id")String id, @RequestBody @Valid UpdateNewUserDTO dto){
        service.updateNewUser(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/auth/{id}")
    public ResponseEntity<Void> updateUserByAdmin(@PathVariable("id")String id, @RequestBody @Valid UpdateUserByAdmin dto){
        service.updateUserByAdmin(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
