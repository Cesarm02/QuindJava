package com.quind.financiera.service;

import com.quind.financiera.entity.dto.TransactionRequestDto;
import com.quind.financiera.entity.dto.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto saveTransaction(TransactionRequestDto transactionResponseDto);

}
