package com.quind.financiera.entity.dto;

import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountRequestDto {

    private AccountType accountType;
    private Status status;
    private Double balance;
    private Boolean gmf;
    private String cedula;
    private Integer id;

}
