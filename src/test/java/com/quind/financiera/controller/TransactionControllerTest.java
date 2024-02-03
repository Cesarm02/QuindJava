package com.quind.financiera.controller;

import com.quind.financiera.entity.dto.TransactionRequestDto;
import com.quind.financiera.entity.dto.TransactionResponseDto;
import com.quind.financiera.entity.enums.TransactionType;
import com.quind.financiera.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void generateTransaction(){
        TransactionResponseDto transactionResponseDto = TransactionResponseDto
                .builder()
                .description("description")
                .receive("receive")
                .send("send")
                .status("status")
                .value(200.00)
                .build();

        TransactionRequestDto transactionRequestDto = TransactionRequestDto
                .builder()
                .transactionType(TransactionType.CONSIGNACION)
                .numberAccount("numberAccount")
                .value(200.00)
                .send("send")
                .build();

        Mockito.when(transactionService.saveTransaction(transactionRequestDto))
                .thenReturn(transactionResponseDto);

        Assertions.assertNotNull(transactionController.generateTransaction(transactionRequestDto));

    }

}
