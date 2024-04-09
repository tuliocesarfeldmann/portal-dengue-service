package com.ufsm.portaldengue.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "INFORMATIVOS")
@Getter
@Setter
public class Informative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String description;

    @Column(name = "caminho_imagem")
    private String imagePath;

}