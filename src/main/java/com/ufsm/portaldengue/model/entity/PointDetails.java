package com.ufsm.portaldengue.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DETALHES_PONTOS")
@Getter
@Setter
public class PointDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua")
    private String road;

    @Column(name = "numero")
    private Integer number;

    @Column(name = "bairro")
    private String neighborhood;

    @Column(name = "cidade")
    private String town;

    @Column
    private String cep;

    @Column(name = "pais")
    private String country;

    @Column(name = "estado")
    private String state;

}