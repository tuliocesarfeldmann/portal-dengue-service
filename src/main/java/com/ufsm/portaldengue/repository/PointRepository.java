package com.ufsm.portaldengue.repository;

import com.ufsm.portaldengue.model.dto.DailyCountDTO;
import com.ufsm.portaldengue.model.dto.NeighborhoodCountDTO;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.PointSituation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

  List<Point> findByPointSituation(PointSituation pointSituation);

  @Transactional
  @Modifying
  @Query("UPDATE Point p SET p.pointSituation = :status WHERE p.id = :pointId")
  void updateStatusById(Long pointId, PointSituation status);

  @Query("SELECT new com.ufsm.portaldengue.model.dto.DailyCountDTO(DATE_FORMAT(p.creationDate, '%d/%m/%Y'), COUNT(p)) " +
          "FROM Point p " +
          "WHERE p.creationDate >= :startDate " +
          "GROUP BY DATE_FORMAT(p.creationDate, '%d/%m/%Y') " +
          "ORDER BY DATE_FORMAT(p.creationDate, '%d/%m/%Y')")
  List<DailyCountDTO> findDailyCounts(@Param("startDate") LocalDateTime startDate);

  @Query("SELECT new com.ufsm.portaldengue.model.dto.NeighborhoodCountDTO(dp.neighborhood, COUNT(p)) " +
          "FROM Point p " +
          "JOIN PointDetails dp ON p.id = dp.point.id " +
          "GROUP BY dp.neighborhood")
  List<NeighborhoodCountDTO> findPointsByNeighborhood();
}
