package com.ufsm.portaldengue.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "PONTOS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(name = "descricao")
    private String description;

    @Column(name = "data_criacao")
    private LocalDateTime creationDate = LocalDateTime.now();

    @JoinColumn(name = "id_situacao")
    @ManyToOne
    private PointSituation pointSituation = PointSituation.getDefaultPointSituation();

}
