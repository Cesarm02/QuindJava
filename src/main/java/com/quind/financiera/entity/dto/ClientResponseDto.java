package com.quind.financiera.entity.dto;

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
public class ClientResponseDto {

    private String idType;
    private String idNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaModificacion;

}
