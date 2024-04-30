package com.ufsm.portaldengue.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIOS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cpf;

    @Column
    private String hash;

    @Column
    private String salt;

    @Column(name = "nome")
    private String name;

    @Column
    private String email;

    @Column(name = "data_criacao")
    private Date creationDate;

}