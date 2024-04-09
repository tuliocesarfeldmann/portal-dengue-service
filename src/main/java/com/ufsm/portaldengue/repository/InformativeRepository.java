package com.ufsm.portaldengue.repository;

import com.ufsm.portaldengue.model.entity.Informative;
import com.ufsm.portaldengue.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformativeRepository extends JpaRepository<Informative, Long> {

}
