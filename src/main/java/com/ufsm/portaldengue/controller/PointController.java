package com.ufsm.portaldengue.controller;

import com.ufsm.portaldengue.model.dto.ConfirmPointDTO;
import com.ufsm.portaldengue.model.dto.UpdateStatusDTO;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.enums.StatusEnum;
import com.ufsm.portaldengue.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("point")
public class PointController {
    @Autowired
    PointService service;

    @PostMapping("/public/register")
    public ResponseEntity<?> register(@RequestBody Point point){
        try {
            return ResponseEntity.ok(service.register(point));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        try {
            return ResponseEntity.ok(service.listPointsRequiringAcceptance());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusDTO updateStatus){
        try {
            StatusEnum status = StatusEnum.fromString(updateStatus.getStatus());

            service.updateStatus(updateStatus.getPointId(), status);

            return ResponseEntity.ok()
                    .body("Atualizado com sucesso!");
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/address/details")
    public ResponseEntity<?> addressDetails(
            @RequestParam("lat") String lat,
            @RequestParam("lon") String lon
    ){
        try {
            return ResponseEntity.ok(service.getAddressDetails(lat, lon));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPoint(@RequestBody ConfirmPointDTO confirmPoint){
        try {
            service.confirmPoint(confirmPoint);

            return ResponseEntity.ok("Ponto confirmado com sucesso!");
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}