package com.quind.financiera.controller;


import com.quind.financiera.entity.dto.TransactionRequestDto;
import com.quind.financiera.entity.dto.TransactionResponseDto;
import com.quind.financiera.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    ResponseEntity<TransactionResponseDto> generateTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        TransactionResponseDto response = transactionService.saveTransaction(transactionRequestDto);
        return ResponseEntity.ok(response);
    }


}
