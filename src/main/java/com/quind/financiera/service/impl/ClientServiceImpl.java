package com.quind.financiera.service.impl;

import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.ClientRequestDto;
import com.quind.financiera.entity.dto.ClientResponseDto;
import com.quind.financiera.repository.ClientRepository;
import com.quind.financiera.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDto saveClient(ClientRequestDto clientRequestDto) {
        ClientEntity client = buildClient(clientRequestDto, true);
        log.info(client.toString() +  " ---- Cliente ---- ");
        clientRepository.save(client);
        log.info("---- Cliente guardado --- ");
        ClientResponseDto clientResponseDto = entityToResponse(client);
        return clientResponseDto;
    }


    ClientEntity buildClient(ClientRequestDto clientRequestDto, boolean isNew){
        ClientEntity response = new ClientEntity();
        if(isNew){
            response.setFechaCreacion(LocalDate.now());
            response.setFechaModificacion(LocalDate.now());
        }else{
            response.setFechaModificacion(LocalDate.now());
        }
        response.setEmail(clientRequestDto.getEmail());
        response.setFechaNacimiento(clientRequestDto.getFechaNacimiento());
        response.setIdentificationNumber(clientRequestDto.getIdNumber());
        response.setIdentificationType(clientRequestDto.getIdType());
        response.setLastname(clientRequestDto.getLastname());
        response.setName(clientRequestDto.getName());
        return response;
    }

    ClientResponseDto entityToResponse (ClientEntity clientEntity){
        ClientResponseDto response = ClientResponseDto.builder()
                .email(clientEntity.getEmail())
                .name(clientEntity.getName())
                .fechaNacimiento(clientEntity.getFechaNacimiento())
                .idNumber(clientEntity.getIdentificationNumber())
                .idType(clientEntity.getIdentificationType())
                .lastname(clientEntity.getLastname())
                .build();
        return response;
    }

}
