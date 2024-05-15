package com.ufsm.portaldengue.repository;

import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.PointSituation;

import java.util.List;

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
}
