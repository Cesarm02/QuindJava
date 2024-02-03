package com.quind.financiera.service.impl;

import com.quind.financiera.entity.AccountEntity;
import com.quind.financiera.entity.TransactionEntity;
import com.quind.financiera.entity.dto.TransactionRequestDto;
import com.quind.financiera.entity.dto.TransactionResponseDto;
import com.quind.financiera.entity.enums.TransactionType;
import com.quind.financiera.repository.AccountRepository;
import com.quind.financiera.repository.TransactionRepository;
import com.quind.financiera.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransactionResponseDto saveTransaction(TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto responseDto = new TransactionResponseDto();
        switch (transactionRequestDto.getTransactionType()){
            case CONSIGNACION:
                responseDto = transactionConsignacion(transactionRequestDto);
                return responseDto;
            case RETIRO:
                responseDto = transactionRetiro(transactionRequestDto);
                return responseDto;
            case TRANSFERENCIA:
                responseDto = transactionTransferencia(transactionRequestDto);
                return responseDto;
            default:
                log.info("No se encuentra el tipo de tramite");
                return null;
        }

    }

    TransactionResponseDto transactionConsignacion(TransactionRequestDto transactionRequestDto){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        AccountEntity entity = accountRepository.findByNumberAccount(transactionRequestDto.getNumberAccount());
        if(entity != null){
            //Transaction
            saveTransaction(TransactionType.CONSIGNACION, transactionRequestDto);
            //Add value
            entity.setBalance( entity.getBalance() + transactionRequestDto.getValue());
            accountRepository.save(entity);
            //Transaction
            transactionResponseDto = transactionStatus("Realizada", "Transacción realizada", transactionRequestDto.getValue(), transactionRequestDto.getNumberAccount(), null);
        }else{
            transactionResponseDto = transactionStatus("Fallida", "No existe la cuenta, no se puedo realizar", 0,
                    entity != null ? transactionRequestDto.getNumberAccount() : null, null);
        }
        return transactionResponseDto;
    }

    TransactionResponseDto transactionRetiro(TransactionRequestDto requestDto){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        AccountEntity entity = accountRepository.findByNumberAccount(requestDto.getNumberAccount());
        if(entity != null){
            saveTransaction(TransactionType.RETIRO, requestDto);
            //remove
            entity.setBalance( entity.getBalance() - requestDto.getValue());
            if(entity.getBalance() < 0){
                transactionResponseDto = transactionStatus("Fallida", "No se cuenta con el dinero para la operación", 0, null, null);
            }else{
                accountRepository.save(entity);
                //Transaction
                transactionResponseDto = transactionStatus("Realizada", "Transacción realizada", requestDto.getValue(), requestDto.getNumberAccount(), null);
            }
        }else{
        transactionResponseDto = transactionStatus("Fallida", "No existe la cuenta, no se puedo realizar", 0,
                entity != null ? requestDto.getNumberAccount() : null, null);
    }
        return transactionResponseDto;
    }

    TransactionResponseDto transactionTransferencia(TransactionRequestDto transactionRequestDto){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        AccountEntity entity = accountRepository.findByNumberAccount(transactionRequestDto.getNumberAccount());
        AccountEntity entitySend = accountRepository.findByNumberAccount(transactionRequestDto.getSend());
        if(entity != null && entitySend != null){
            saveTransaction(TransactionType.TRANSFERENCIA, transactionRequestDto);
            //add and remove
            entity.setBalance( entity.getBalance() + transactionRequestDto.getValue());
            entitySend.setBalance( entitySend.getBalance() - transactionRequestDto.getValue());
            if(entitySend.getBalance() < 0) {
                transactionResponseDto = transactionStatus("Fallida", "No se cuenta con el dinero para la operación", 0, transactionRequestDto.getNumberAccount(), transactionRequestDto.getSend());
            }else{
                accountRepository.save(entity);
                accountRepository.save(entitySend);
                //Transaction
                transactionResponseDto = transactionStatus("Realizada", "Transacción realizada",
                        transactionRequestDto.getValue(), transactionRequestDto.getNumberAccount(), transactionRequestDto.getSend());
            }
        }else{
            transactionResponseDto = transactionStatus("Fallida", "No existe la cuenta, no se puedo realizar", 0,
                    entity != null ? transactionRequestDto.getNumberAccount() : null, entitySend != null ? transactionRequestDto.getSend() : null);
        }
        return transactionResponseDto;
    }

    TransactionResponseDto transactionStatus(String status, String description, double value, String receive, String send){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setDescription(description);
        transactionResponseDto.setStatus(status);
        transactionResponseDto.setValue(value != 0 ? value : 0);
        transactionResponseDto.setReceive(receive);
        transactionResponseDto.setSend(send != null ? send : null);
        return transactionResponseDto;
    }

    void saveTransaction(TransactionType transactionType, TransactionRequestDto transactionRequestDto ){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionType(transactionType);
        transactionEntity.setValue(transactionRequestDto.getValue());
        transactionEntity.setAccountReceive(transactionRequestDto.getNumberAccount());
        transactionEntity.setAccountSend(transactionRequestDto.getSend() != null ? transactionRequestDto.getSend() : "");
        transactionRepository.save(transactionEntity);

    }

}
