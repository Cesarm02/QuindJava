package com.quind.financiera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity(name = "client")
@NoArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String identificationType;
    private Integer identificationNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion = LocalDate.now();
    private LocalDate fechaModificacion;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "client"
    )
    private List<AccountEntity> accounts;

}
