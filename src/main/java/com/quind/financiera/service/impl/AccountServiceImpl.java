package com.quind.financiera.service.impl;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.AccountRequestDto;
import com.quind.financiera.entity.dto.AccountResponseDto;
import com.quind.financiera.entity.enums.AccountType;
import com.quind.financiera.entity.enums.Status;
import com.quind.financiera.repository.AccountRepository;
import com.quind.financiera.repository.ClientRepository;
import com.quind.financiera.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public AccountResponseDto saveAccount(AccountRequestDto responseDto) {
        ClientEntity client = clientRepository.findByIdentificationNumber(responseDto.getCedula());
        if(client == null){
            log.info("No existe un cliente para la cuenta");
            return null;
        }
        if(responseDto.getBalance() <= 0 && responseDto.getAccountType().equals(AccountType.AHORROS)){
            log.info("No puedes crear una cuenta con 0 pesos");
            return null;
        }
        AccountEntity entity = buildAccount(responseDto, true);
        log.info(entity.toString() + " ------- Cuenta ----- ");
        entity = accountRepository.save(entity);
        AccountResponseDto accountResponseDto = entityToResponse(entity);
        return accountResponseDto;
    }

    @Override
    public AccountResponseDto changeStatus(Status status, String numberAccount) {
        AccountEntity accountEntity = accountRepository.findByNumberAccount(numberAccount);
        if(status.equals(Status.CANCELADA) && accountEntity.getBalance() == 0){
            accountEntity.setStatus(status);
        }else{
            log.info("No se puede cancelar por no tener un saldo en 0");
            return null;
        }
        return entityToResponse(accountEntity);
    }

    AccountEntity buildAccount(AccountRequestDto accountRequestDto, boolean isNew){
        AccountEntity response = new AccountEntity();
        if(isNew){
            response.setFechaCreacion(LocalDate.now());
            response.setFechaModificacion(LocalDateTime.now());
        }else{
            response.setFechaModificacion(LocalDateTime.now());
        }
        response.setId(accountRequestDto.getId() != null ? accountRequestDto.getId() : 0);
        ClientEntity client = clientRepository.findByIdentificationNumber(accountRequestDto.getCedula());
        //response.setClient(client);
        response.setGMF(accountRequestDto.getGmf());
        response.setBalance(accountRequestDto.getBalance());
        response.setStatus(Status.ACTIVA);
        response.setNumberAccount(generateNumberAccount(accountRequestDto.getAccountType().name()));
        response.setAccountType(accountRequestDto.getAccountType());
        return response;
    }

    private String generateNumberAccount(String accountType) {
        String tipoCuenta = accountType.equals("AHORROS") ? "53" : "33";
        int numeroAleatorio = (int) (Math.random() * 100000000);
        return tipoCuenta + numeroAleatorio;
    }

    AccountResponseDto entityToResponse(AccountEntity accountEntity){
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccountType(accountEntity.getAccountType().name());
        accountResponseDto.setGMF(accountEntity.getGMF());
        accountResponseDto.setBalance(accountEntity.getBalance());
        accountResponseDto.setClientResponseDto(accountEntity.getClient());
        accountResponseDto.setNumberAccount(accountEntity.getNumberAccount());
        accountResponseDto.setFechaCreacion(accountEntity.getFechaCreacion());
        accountResponseDto.setFechaModificacion(accountEntity.getFechaModificacion());
        accountResponseDto.setStatus(accountEntity.getStatus());
        return accountResponseDto;
    }

}
