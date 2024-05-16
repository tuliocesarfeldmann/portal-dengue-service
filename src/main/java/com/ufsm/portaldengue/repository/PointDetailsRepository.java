package com.ufsm.portaldengue.repository;

import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.PointDetails;
import com.ufsm.portaldengue.model.entity.PointSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PointDetailsRepository extends JpaRepository<PointDetails, Long> {
}
