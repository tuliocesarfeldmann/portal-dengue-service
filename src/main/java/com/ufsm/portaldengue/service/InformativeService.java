package com.ufsm.portaldengue.service;

import com.ufsm.portaldengue.model.entity.Informative;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.repository.InformativeRepository;
import com.ufsm.portaldengue.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformativeService {
    @Autowired
    InformativeRepository repository;

    public Informative register(Informative point) {
        return repository.save(point);
    }

    public List<Informative> list() {
        return repository.findAll();
    }
}
