package com.quind.financiera.service;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.ClientRequestDto;
import com.quind.financiera.entity.dto.ClientResponseDto;
import com.quind.financiera.repository.ClientRepository;
import com.quind.financiera.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void saveClient(){

        ClientRequestDto clientRequestDto =ClientRequestDto.builder()
                .email("email")
                .fechaNacimiento(LocalDate.of(1999,06,02))
                .idType("idType")
                .lastname("lastname")
                .name("name")
                .idNumber("idNumber")
                .build();

        ClientResponseDto clientResponseDto = ClientResponseDto.builder()
                .email("email")
                .fechaNacimiento(LocalDate.of(1999,06,02))
                .fechaModificacion(LocalDateTime.now())
                .name("name")
                .idNumber("idNumber")
                .idType("idType")
                .build();

        ClientEntity client = ClientEntity.builder()
                .fechaCreacion(LocalDate.now())
                .identificationType("idType")
                .identificationNumber("idNumber")
                .email("email")
                .name("name")
                .lastname("lastname")
                .build();

        Mockito.when(clientRepository.findByIdentificationNumber(Mockito.any()))
                .thenReturn(null);

        Assertions.assertNotNull(clientService.saveClient(clientRequestDto));

    }

    @Test
    void updateClient(){
        ClientRequestDto clientRequestDto =ClientRequestDto.builder()
                .email("email")
                .fechaNacimiento(LocalDate.of(1999,06,02))
                .idType("idType")
                .lastname("lastname")
                .name("name")
                .idNumber("idNumber")
                .build();

        ClientEntity client = ClientEntity.builder()
                .fechaCreacion(LocalDate.now())
                .identificationType("idType")
                .identificationNumber("idNumber")
                .email("email")
                .name("name")
                .lastname("lastname")
                .build();

        Mockito.when(clientRepository.findByIdentificationNumber(Mockito.any()))
                .thenReturn(client);

        Assertions.assertNotNull(clientService.updateClient( clientRequestDto));

    }

    @Test
    void deleteClient(){

        List<AccountEntity> accountEntities = new ArrayList<>();

        ClientEntity client = ClientEntity.builder()
                .fechaCreacion(LocalDate.now())
                .identificationType("idType")
                .identificationNumber("idNumber")
                .email("email")
                .name("name")
                .lastname("lastname")
                .accounts(accountEntities)
                .build();

        Mockito.when(clientRepository.findByIdentificationNumber(Mockito.any()))
                .thenReturn(client);

        Assertions.assertNotNull(clientService.deleteClient(Mockito.anyString()));
    }


}
