package com.quind.financiera.controller;

import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.AccountRequestDto;
import com.quind.financiera.entity.dto.AccountResponseDto;
import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import com.quind.financiera.service.AccountService;
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
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void saveAccount(){

        AccountResponseDto accountResponseDto = AccountResponseDto.builder()
                .GMF(true)
                .numberAccount("number")
                .status(Status.ACTIVA)
                .fechaModificacion(LocalDateTime.now())
                .fechaCreacion(LocalDate.now())
                .balance(200.00)
                .accountType("accountType")
                .clientResponseDto(ClientEntity.builder().build())
                .build();

        AccountRequestDto accountRequestDto = AccountRequestDto.builder()
                .accountType(AccountType.AHORROS)
                .balance(200.00)
                .cedula("cedula")
                .gmf(true)
                .status(Status.ACTIVA)
                .build();

        Mockito.when(accountService.saveAccount(Mockito.any()))
                .thenReturn(accountResponseDto);

        Assertions.assertNotNull(accountController.saveAccount(accountRequestDto));


    }

    @Test
    void changeStatus(){
        AccountResponseDto accountResponseDto = AccountResponseDto.builder()
                .GMF(true)
                .numberAccount("number")
                .status(Status.ACTIVA)
                .fechaModificacion(LocalDateTime.now())
                .fechaCreacion(LocalDate.now())
                .balance(200.00)
                .accountType("accountType")
                .clientResponseDto(ClientEntity.builder().build())
                .build();

        AccountRequestDto accountRequestDto = AccountRequestDto.builder()
                .accountType(AccountType.AHORROS)
                .balance(200.00)
                .cedula("cedula")
                .gmf(true)
                .status(Status.ACTIVA)
                .build();

        Mockito.when(accountService.changeStatus(Mockito.any(), Mockito.anyString()))
                .thenReturn(accountResponseDto);

        Assertions.assertNotNull(accountController.changeStatus(Mockito.any(), Mockito.anyString()));

    }





}
