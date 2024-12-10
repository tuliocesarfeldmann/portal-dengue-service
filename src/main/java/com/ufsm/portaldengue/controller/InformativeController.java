package com.ufsm.portaldengue.controller;

import com.ufsm.portaldengue.model.entity.Informative;
import com.ufsm.portaldengue.repository.InformativeRepository;
import com.ufsm.portaldengue.service.InformativeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelos dados da tela de informativos
 */
@Slf4j
@RestController
@RequestMapping("informative")
public class InformativeController {
    @Autowired
    InformativeService service;

    @Autowired
    InformativeRepository repository;

    /**
     * Método responsável por registrar um novo informativo
     *
     * @param informative JSON contendo os dados do informativo
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Informative informative) {
        try {
            return ResponseEntity.ok(service.register(informative));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Método responsável por retornar lista com todos informativos ativos
     */
    @GetMapping("/public/list")
    public ResponseEntity<?> list() {
        try {
            return ResponseEntity.ok(service.list());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Método responsável por marcar um informativo como deletado
     *
     * @param id id do informativo a ser marcado como deletado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
