package com.ufsm.portaldengue.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SITUACAO_PONTOS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PointSituation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String description;

    public static PointSituation getDefaultPointSituation() {
      return new PointSituation(1L, "EM ANÁLISE");
    }

    public static PointSituation getAcceptedPointSituation() {
      return new PointSituation(3L, "APROVADO");
    }

}