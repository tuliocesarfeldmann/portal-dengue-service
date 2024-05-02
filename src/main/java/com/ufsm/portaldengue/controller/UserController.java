package com.ufsm.portaldengue.controller;

import com.ufsm.portaldengue.model.dto.UserDTO;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.User;
import com.ufsm.portaldengue.service.PointService;
import com.ufsm.portaldengue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user){
        try {
            return ResponseEntity.ok(service.login(user));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserDTO user){
        try {
            return ResponseEntity.ok(service.register(user));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
