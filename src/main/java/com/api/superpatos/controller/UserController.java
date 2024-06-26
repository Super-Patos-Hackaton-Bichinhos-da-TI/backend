package com.api.superpatos.controller;

import com.api.superpatos.dto.LoginDTO;
import com.api.superpatos.dto.AuthenticationDTO;
import com.api.superpatos.dto.CreateDTO;
import com.api.superpatos.dto.FindByUserDTO;
import com.api.superpatos.dto.UpdateNewUserDTO;
import com.api.superpatos.dto.UpdateUserByAdminDTO;
import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;
import com.api.superpatos.model.User;
import com.api.superpatos.security.TokenService;
import com.api.superpatos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService service;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO dto){

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new ResponseEntity<>(new LoginDTO(token), HttpStatus.OK);
    }

    @PostMapping(path = "/auth/create-user")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateDTO create){
        service.createUser(create);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findByUser(@PathVariable("id")String id, @RequestBody @Valid FindByUserDTO dto){
        User user = service.findByUser(id, dto);

        return new ResponseEntity<>(user, user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateNewUser(@PathVariable("id")String id, @RequestBody @Valid UpdateNewUserDTO dto){
        service.updateNewUser(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/auth/{id}")
    public ResponseEntity<Void> updateUserByAdmin(@PathVariable("id")String id, @RequestBody @Valid UpdateUserByAdminDTO dto){
        service.updateUserByAdmin(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/auth/{id}")
    public ResponseEntity<Void> deleteUserByAdmin(@PathVariable("id")String id,
                                                  @RequestParam String email,
                                                  @RequestParam Squad squad,
                                                  @RequestParam Role role){
        service.deleteUserByAdmin(id, email, squad, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
