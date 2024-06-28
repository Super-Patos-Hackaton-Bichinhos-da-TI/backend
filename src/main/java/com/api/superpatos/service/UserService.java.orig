package com.api.superpatos.service;

import com.api.superpatos.dto.CreateDTO;
import com.api.superpatos.dto.UpdateNewUserDTO;
import com.api.superpatos.dto.UpdateUserByAdminDTO;
import com.api.superpatos.dto.FindByUserDTO;
import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;
import com.api.superpatos.model.User;
import com.api.superpatos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void createUser(CreateDTO dto){
        if(notNull(dto.email(), dto.squad(), dto.office(), dto.roles())){
            User entity = new User(dto.email(), dto.squad(), dto.office(), dto.roles());
            userRepository.save(entity);

            emailService.sendEmailText(entity.getEmail(),
                    "Novo usuário cadastrado",
                    "Seja bem vindo ao Bichinhos! \n Para completar o cadastro, acesse o link: \n possívelLink");
        }else{
            throw new IllegalArgumentException("Unable to save the user: one or more fields are null.");
        }
    }

    public void updateNewUser(String id, UpdateNewUserDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        if(notNull(dto.username(), dto.email(), dto.biography(), dto.pet(), dto.password())){

            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

            user.setUsername(dto.username());
            user.setEmail(dto.email());
            user.setPassword(encryptedPassword);
            user.setBiography(dto.biography());
            user.setPet(dto.pet());
        }

        userRepository.save(user);
    }

    public void updateUserByAdmin(String id, UpdateUserByAdminDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        if(notNull(dto.username(), dto.email(), dto.password(), dto.biography(), dto.office(), dto.squad(), dto.pet(), dto.role())){

            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

            user.setUsername(dto.username());
            user.setEmail(dto.email());
            user.setPassword(encryptedPassword);
            user.setBiography(dto.biography());
            user.setOffice(dto.office());
            user.setSquad(dto.squad());
            user.setPet(dto.pet());
            user.setRole(dto.role());
        }

        userRepository.save(user);
    }

    public User findByUser(String id, FindByUserDTO dto){
        userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        if(notNull(dto.email(), dto.squad(), dto.role())){
            return userRepository.findByEmailAndSquadAndRole(dto.email(), dto.squad(), dto.role());
        }else {
            throw new RuntimeException("User not found with email, squad and role");
        }
    }

    public void deleteUserByAdmin(String id, String email, Squad squad, Role role){
        userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        FindByUserDTO findByUserDTO = new FindByUserDTO(email, squad, role);
        User user = findByUser(id, findByUserDTO);
        userRepository.delete(user);
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
