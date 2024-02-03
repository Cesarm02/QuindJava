package com.quind.financiera.entity;

import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity(name = "account")
@NoArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private AccountType accountType;
    @Column(unique = true)
    private String numberAccount;
    private Status status;
    private Double balance;
    private Boolean GMF;
    private LocalDate fechaCreacion = LocalDate.now();
    private LocalDateTime fechaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

}
