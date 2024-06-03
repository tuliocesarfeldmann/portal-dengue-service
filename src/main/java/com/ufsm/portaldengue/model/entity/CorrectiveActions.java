package com.ufsm.portaldengue.model.entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "ACOES_CORRETIVAS")
@Getter
@Setter
public class CorrectiveActions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "pontos_id")
    @OneToOne
    private Point pointId;

    @Column(name = "data_correcao")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "descricao")
    private String description;
}
