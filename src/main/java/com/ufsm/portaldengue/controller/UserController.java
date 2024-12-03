package com.ufsm.portaldengue.controller;

import com.ufsm.portaldengue.model.dto.UpdateUserDTO;
import com.ufsm.portaldengue.model.dto.UserDTO;
import com.ufsm.portaldengue.model.dto.UserLoginDTO;
import com.ufsm.portaldengue.repository.UserRepository;
import com.ufsm.portaldengue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelo acesso de usuários
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    UserRepository repository;

    /**
     * Realiza o login do usuário
     *
     * @param user JSON com os dados de login do usuário
     */
    @PostMapping("/public/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO user) {
        try {
            return ResponseEntity.ok(service.login(user));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

    /**
     * Método interno para realizar o registro de usuários administradores
     * 
     * @param user JSON com os dados completos do usuário
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserDTO user) {
        try {
            return ResponseEntity.ok(service.register(user));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Método para listar usuários na página de controle de usuários
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        try {
            return ResponseEntity
                    .ok()
                    .body(repository.findAll());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Método para marcar usuário como ativo ou inativo
     * 
     * @param id      identifica sobre qual usuário sera alterado
     * @param request identifica se o usuário deverá ser marcado como ativo ou
     *                inativo
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserStatus(@PathVariable Long id, @RequestBody UpdateUserDTO request) {
        service.updateUserStatus(id, request.isActive());
        return ResponseEntity.ok().build();
    }
}
