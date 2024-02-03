package com.quind.financiera.entity.dto;

import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountResponseDto {

    private String accountType;
    private String numberAccount;
    private Status status;
    private Double balance;
    private boolean GMF;
    private LocalDate fechaCreacion;
    private LocalDateTime fechaModificacion;
    private ClientEntity clientResponseDto;


}
