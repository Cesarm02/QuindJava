package com.quind.financiera.entity.dto;

import com.quind.financiera.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionRequestDto {

    private TransactionType transactionType;
    private double value;
    private String numberAccount;
    private String send;
    private String receive;


}
