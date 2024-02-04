package com.quind.financiera.service;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.AccountRequestDto;
import com.quind.financiera.entity.dto.AccountResponseDto;
import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import com.quind.financiera.repository.AccountRepository;
import com.quind.financiera.repository.ClientRepository;
import com.quind.financiera.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class AccounServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void saveAccount(){

        ClientEntity client = ClientEntity.builder()
                .id(123)
                .name("name")
                .email("email")
                .identificationNumber("123")
                .identificationType("idType")
                .fechaCreacion(LocalDate.now())
                .build();

        AccountResponseDto accountResponseDto = AccountResponseDto
                .builder()
                .clientResponseDto(client)
                .accountType("accountType")
                .balance(200.00)
                .GMF(true)
                .numberAccount("123")
                .status(Status.ACTIVA)
                .fechaCreacion(LocalDate.now())
                .fechaModificacion(LocalDateTime.now())
                .build();

        AccountRequestDto accountRequestDto = AccountRequestDto.builder()
                .accountType(AccountType.AHORROS)
                .balance(200.00)
                .cedula("123")
                .gmf(true)
                .status(Status.ACTIVA)
                .build();

        AccountEntity accountEntity = AccountEntity
                .builder()
                .accountType(AccountType.AHORROS)
                .numberAccount("123")
                .GMF(true)
                .balance(200.00)
                .fechaCreacion(LocalDate.now())
                .status(Status.ACTIVA)
                .fechaModificacion(LocalDateTime.now())
                .client(client)
                .build();

        Mockito.when(clientRepository.findByIdentificationNumber(accountRequestDto.getCedula()))
                .thenReturn(client);

        Mockito.when(accountRepository.save(Mockito.any()))
                .thenReturn(accountEntity);

        Assertions.assertNotNull(accountService.saveAccount(accountRequestDto));
    }

    @Test
    void changeStatusTest(){
        ClientEntity client = ClientEntity.builder()
                .id(123)
                .name("name")
                .email("email")
                .identificationNumber("123")
                .identificationType("idType")
                .fechaCreacion(LocalDate.now())
                .build();

        AccountEntity accountEntity = AccountEntity
                .builder()
                .accountType(AccountType.AHORROS)
                .numberAccount("123")
                .GMF(true)
                .balance(0.0)
                .fechaCreacion(LocalDate.now())
                .status(Status.CANCELADA)
                .fechaModificacion(LocalDateTime.now())
                .client(client)
                .build();

        Mockito.when(accountRepository.findByNumberAccount(Mockito.anyString()))
                .thenReturn(accountEntity);

        Assertions.assertNull(accountService.changeStatus(Status.ACTIVA, accountEntity.getNumberAccount()));

    }

}
