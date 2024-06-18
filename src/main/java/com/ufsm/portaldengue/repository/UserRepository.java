package com.ufsm.portaldengue.repository;

import com.ufsm.portaldengue.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndActiveTrue(String email);
}
