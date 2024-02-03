package com.quind.financiera.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(unique = true)
    private String identificationNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion = LocalDate.now();
    private LocalDateTime fechaModificacion;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
           // orphanRemoval = true,
            mappedBy = "client"
    )
    private List<AccountEntity> accounts;

}
