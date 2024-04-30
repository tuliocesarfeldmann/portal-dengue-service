package com.ufsm.portaldengue.service;

import com.ufsm.portaldengue.model.dto.UserDTO;
import com.ufsm.portaldengue.model.entity.User;
import com.ufsm.portaldengue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User register(UserDTO userDTO) {

        String salt = BCrypt.gensalt();

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword() + salt);

        User user = User.builder()
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .salt(salt)
                .hash(hashedPassword)
                .creationDate(new Date())
                .build();

        return repository.save(user);
    }

}
