package com.quind.financiera.service;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.TransactionRequestDto;
import com.quind.financiera.entity.dto.TransactionResponseDto;
import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import com.quind.financiera.entity.enums.TransactionType;
import com.quind.financiera.repository.AccountRepository;
import com.quind.financiera.repository.TransactionRepository;
import com.quind.financiera.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void saveTransactionTest(){
        TransactionResponseDto transactionResponseDto = TransactionResponseDto
                .builder()
                .value(200.00)
                .status("status")
                .send("send")
                .receive("receive")
                .description("description")
                .build();

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
                .balance(200.00)
                .fechaCreacion(LocalDate.now())
                .status(Status.ACTIVA)
                .fechaModificacion(LocalDateTime.now())
                .client(client)
                .build();

        TransactionRequestDto transactionRequestDto = TransactionRequestDto
                .builder()
                .send("send")
                .transactionType(TransactionType.CONSIGNACION)
                .value(200.00)
                .numberAccount("numberAccount")
                .build();

        Mockito.when(accountRepository.findByNumberAccount(Mockito.anyString()))
                .thenReturn(accountEntity);

        Assertions.assertNotNull(transactionService.saveTransaction(transactionRequestDto));

    }

    @Test
    void saveTransactionRetiroTest(){
        TransactionResponseDto transactionResponseDto = TransactionResponseDto
                .builder()
                .value(200.00)
                .status("status")
                .send("send")
                .receive("receive")
                .description("description")
                .build();

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
                .balance(200.00)
                .fechaCreacion(LocalDate.now())
                .status(Status.ACTIVA)
                .fechaModificacion(LocalDateTime.now())
                .client(client)
                .build();

        TransactionRequestDto transactionRequestDto = TransactionRequestDto
                .builder()
                .send("send")
                .transactionType(TransactionType.RETIRO)
                .value(200.00)
                .numberAccount("numberAccount")
                .build();

        Mockito.when(accountRepository.findByNumberAccount(Mockito.anyString()))
                .thenReturn(accountEntity);

        Assertions.assertNotNull(transactionService.saveTransaction(transactionRequestDto));

    }

    @Test
    void saveTransactionTransferenciaTest(){
        TransactionResponseDto transactionResponseDto = TransactionResponseDto
                .builder()
                .value(200.00)
                .status("status")
                .send("send")
                .receive("receive")
                .description("description")
                .build();

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
                .balance(200.00)
                .fechaCreacion(LocalDate.now())
                .status(Status.ACTIVA)
                .fechaModificacion(LocalDateTime.now())
                .client(client)
                .build();

        TransactionRequestDto transactionRequestDto = TransactionRequestDto
                .builder()
                .send("send")
                .transactionType(TransactionType.TRANSFERENCIA)
                .value(200.00)
                .numberAccount("numberAccount")
                .build();

        Mockito.when(accountRepository.findByNumberAccount(Mockito.anyString()))
                .thenReturn(accountEntity);

        Assertions.assertNotNull(transactionService.saveTransaction(transactionRequestDto));

    }


}
