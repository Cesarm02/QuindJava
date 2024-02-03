package com.quind.financiera.controller;

import com.quind.financiera.entity.dto.ClientRequestDto;
import com.quind.financiera.entity.dto.ClientResponseDto;
import com.quind.financiera.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void saveClient(){

        ClientResponseDto responseDto = ClientResponseDto.builder()
                .lastname("lastname")
                .email("email")
                .fechaModificacion(LocalDateTime.now())
                .name("name")
                .fechaNacimiento(LocalDate.now())
                .idNumber("idNumber")
                .idType("idType")
                .build();

        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
                .lastname("lastname")
                .email("email")
                .idType("idTYpe")
                .id(123)
                .fechaNacimiento(LocalDate.now())
                .build();

        Mockito.when(clientService.saveClient(clientRequestDto))
                .thenReturn(responseDto);

        Assertions.assertNotNull(clientController.saveClient(clientRequestDto));
    }


    @Test
    void updateClient(){

        ClientResponseDto responseDto = ClientResponseDto.builder()
                .lastname("lastname")
                .email("email")
                .fechaModificacion(LocalDateTime.now())
                .name("name")
                .fechaNacimiento(LocalDate.now())
                .idNumber("idNumber")
                .idType("idType")
                .build();

        ClientRequestDto clientRequestDto = ClientRequestDto.builder()
                .lastname("lastname")
                .email("email")
                .idType("idTYpe")
                .id(123)
                .fechaNacimiento(LocalDate.now())
                .build();

        Mockito.when(clientService.updateClient( clientRequestDto))
                .thenReturn(responseDto);

        Assertions.assertNotNull(clientController.updateClient( clientRequestDto));
    }

    @Test
    void deleteClient(){

        Mockito.when(clientService.deleteClient( Mockito.anyString()))
                .thenReturn("String");

        Assertions.assertNotNull(clientController.deleteClient("String"));
    }


}
