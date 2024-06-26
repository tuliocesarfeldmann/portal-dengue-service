package com.ufsm.portaldengue.service;

import com.ufsm.portaldengue.model.dto.UserDTO;
import com.ufsm.portaldengue.model.dto.UserLoginDTO;
import com.ufsm.portaldengue.model.entity.User;
import com.ufsm.portaldengue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public boolean login(UserLoginDTO userDTO) {
        User user = repository.findByEmailAndActiveTrue(userDTO.getEmail());

        if (isNull(user)) {
            throw new RuntimeException("Usuário não encontrado para o email " + userDTO.getEmail());
        }

        String password = user.getHash();
        String salt = user.getSalt();
        String informedPassword = userDTO.getPassword();

        if(!new BCryptPasswordEncoder().matches(informedPassword + salt, password)) {
            throw new RuntimeException("Senha inválida para o email " + userDTO.getEmail());
        }

        return true;
    }

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
                .active(true)
                .build();

        return repository.save(user);
    }

    @Transactional
    public void updateUserStatus(Long id, boolean active) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(active);
        repository.save(user);
    }
}
