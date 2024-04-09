package com.ufsm.portaldengue.service;

import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    PointRepository repository;

    public Point register(Point point) {
        return repository.save(point);
    }

}
