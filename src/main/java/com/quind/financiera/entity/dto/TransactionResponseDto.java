package com.quind.financiera.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionResponseDto {

    private String description;
    private String status;
    private Double value;
    private String receive;

}
