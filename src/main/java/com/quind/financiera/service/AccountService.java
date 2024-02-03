package com.quind.financiera.service;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.dto.AccountRequestDto;
import com.quind.financiera.entity.dto.AccountResponseDto;
import com.quind.financiera.entity.enums.Status;

public interface AccountService {

    AccountResponseDto saveAccount(AccountRequestDto responseDto);

    AccountResponseDto changeStatus(Status status, String numberAccount);



}
