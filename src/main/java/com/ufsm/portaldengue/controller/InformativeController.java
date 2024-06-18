package com.ufsm.portaldengue.controller;

import com.ufsm.portaldengue.model.dto.UpdateUserDTO;
import com.ufsm.portaldengue.model.entity.Informative;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.repository.InformativeRepository;
import com.ufsm.portaldengue.service.InformativeService;
import com.ufsm.portaldengue.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("informative")
public class InformativeController {
    @Autowired
    InformativeService service;

    @Autowired
    InformativeRepository repository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Informative informative){
        try {
            return ResponseEntity.ok(service.register(informative));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/public/list")
    public ResponseEntity<?> list(){
        try {
            return ResponseEntity.ok(service.list());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}