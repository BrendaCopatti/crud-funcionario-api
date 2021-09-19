package com.brendacopatti.crudfuncionarioapi.features.funcionario;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="funcionario")
public class Funcionario {

    @Id
    private Long id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 50)
    private String sobrenome;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "nis", nullable = false, length = 11)
    private String nis;

}
