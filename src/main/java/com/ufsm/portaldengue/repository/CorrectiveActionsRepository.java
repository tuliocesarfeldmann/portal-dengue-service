package com.ufsm.portaldengue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufsm.portaldengue.model.entity.CorrectiveActions;

@Repository
public interface CorrectiveActionsRepository extends JpaRepository<CorrectiveActions, Long> {
  
}
