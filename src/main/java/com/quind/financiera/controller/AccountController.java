package com.quind.financiera.controller;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.dto.AccountRequestDto;
import com.quind.financiera.entity.dto.AccountResponseDto;
import com.quind.financiera.entity.enums.Status;
import com.quind.financiera.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "save")
    ResponseEntity<AccountResponseDto> saveAccount(@RequestBody AccountRequestDto accountRequestDto){
        return ResponseEntity.ok(accountService.saveAccount(accountRequestDto));
    }

    @PutMapping(path = "status")
    ResponseEntity<AccountResponseDto> changeStatus(@RequestParam Status status, @RequestParam String numberAccount){
        return ResponseEntity.ok(accountService.changeStatus(status, numberAccount));
    }

}
