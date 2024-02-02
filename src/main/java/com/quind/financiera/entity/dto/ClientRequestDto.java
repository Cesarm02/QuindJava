package com.quind.financiera.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientRequestDto {

    private String idType;
    private String idNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate fechaNacimiento;

    private Integer id;



}
