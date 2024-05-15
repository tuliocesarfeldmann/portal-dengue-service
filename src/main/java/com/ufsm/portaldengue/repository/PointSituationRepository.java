package com.ufsm.portaldengue.repository;

import com.ufsm.portaldengue.model.entity.PointSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointSituationRepository extends JpaRepository<PointSituation, Long> {

  Optional<PointSituation> findByDescription(String description);
}
