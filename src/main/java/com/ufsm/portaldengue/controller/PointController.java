package com.ufsm.portaldengue.controller;

import com.ufsm.portaldengue.model.dto.*;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.enums.StatusEnum;
import com.ufsm.portaldengue.repository.PointRepository;
import com.ufsm.portaldengue.service.PointService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

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

    @Autowired
    PointRepository pointRepository;

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

    @GetMapping("/public/list")
    public ResponseEntity<?> listAcceptedPoints(){
        try {
            Collection<Point> acceptedPointList = service.listAcceptedPoints();
            List<List<Double>> formatedPoints = acceptedPointList.stream()
              .map(point -> List.of(point.getLatitude(), point.getLongitude(), 1.0))
              .toList();

            return ResponseEntity.ok(formatedPoints);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/public/daily-count")
    public ResponseEntity<List<DailyCountDTO>> getDailyCounts(@RequestParam long days, @RequestParam long statusId) {
        LocalDateTime daysAgo = LocalDateTime.of(LocalDate.now().minusDays(days), LocalTime.MIN);
        List<DailyCountDTO> dailyCounts = pointRepository.findDailyCounts(daysAgo, statusId);
        return ResponseEntity.ok(dailyCounts);
    }

    @GetMapping("/public/neighborhood-count")
    public ResponseEntity<List<NeighborhoodCountDTO>> getNeighborhoodCounts(@RequestParam long days, @RequestParam long statusId) {
        LocalDateTime daysAgo = LocalDateTime.of(LocalDate.now().minusDays(days), LocalTime.MIN);
        List<NeighborhoodCountDTO> pointsByNeighborhood = pointRepository.findPointsByNeighborhood(daysAgo, statusId);
        return ResponseEntity.ok(pointsByNeighborhood);
    }

    @GetMapping("/public/status-count")
    public ResponseEntity<List<StatusCountDTO>> getStatusCounts(@RequestParam long days) {
        LocalDateTime daysAgo = LocalDateTime.of(LocalDate.now().minusDays(days), LocalTime.MIN);
        List<StatusCountDTO> pointsByStatus = pointRepository.findPointsByStatus(daysAgo);
        return ResponseEntity.ok(pointsByStatus);
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

    @GetMapping("/listPointsToFix")
    public ResponseEntity<?> listPointsToFix(){
        try {
            return ResponseEntity.ok(service.listAcceptedPoints());
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

    @PostMapping("/applyFix")
    public ResponseEntity<?> applyFix(@RequestBody ApplyFixDTO applyFix){
        try {
            service.applyFix(applyFix.getPointId(), applyFix.getAppliedFix());

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