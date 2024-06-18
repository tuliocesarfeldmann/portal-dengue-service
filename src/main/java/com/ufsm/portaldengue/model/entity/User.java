package com.ufsm.portaldengue.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String hash;

    @Column
    @JsonIgnore
    private String salt;

    @Column(name = "nome")
    private String name;

    @Column
    private String email;

    @Column(name = "data_criacao")
    private Date creationDate;

    @Column(name = "ativo")
    private Boolean active;

}