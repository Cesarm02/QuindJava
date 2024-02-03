package com.quind.financiera.service.impl;

import com.quind.financiera.entity.ClientEntity;
import com.quind.financiera.entity.dto.ClientRequestDto;
import com.quind.financiera.entity.dto.ClientResponseDto;
import com.quind.financiera.repository.ClientRepository;
import com.quind.financiera.service.ClientService;
import com.quind.financiera.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDto saveClient(ClientRequestDto clientRequestDto) {
        if(!validarEdad(clientRequestDto.getFechaNacimiento())){
            log.info("Cliente menor de edad");
            return null;
        }
        ClientEntity client = clientRepository.findByIdentificationNumber(clientRequestDto.getIdNumber());
        if(client != null){
            log.info("Cliente ya existe");
            //Excepction personalizada no existe user
            return null;
        }
        client = buildClient(clientRequestDto, true);
        log.info(client.toString() +  " ---- Cliente ---- ");
        clientRepository.save(client);
        log.info("---- Cliente guardado --- ");
        ClientResponseDto clientResponseDto = entityToResponse(client);
        return clientResponseDto;
    }

    @Override
    public ClientResponseDto updateClient(ClientRequestDto clientRequestDto) {
        if(!validarEdad(clientRequestDto.getFechaNacimiento())){
            log.info("Cliente menor de edad");
            return null;
        }
        ClientEntity client = clientRepository.findByIdentificationNumber(clientRequestDto.getIdNumber());
        if(client == null){
            log.info("Cliente no existe");
            //Excepction personalizada no existe user
            return null;
        }
        clientRequestDto.setId(client.getId());
        client = buildClient(clientRequestDto, true);
        log.info(client.toString() +  " ---- Cliente ---- ");
        clientRepository.saveAndFlush(client);
        log.info("---- Cliente actualizado --- ");
        ClientResponseDto clientResponseDto = entityToResponse(client);
        return clientResponseDto;
    }

    @Override
    public String deleteClient(String cedula) {
        ClientEntity client = clientRepository.findByIdentificationNumber(cedula);
        if(client == null){
            log.info("El cliente no existe");
            return null;
        }
        if(client.getAccounts().size() >=1 ){
            log.info("El cliente tiene productos vinculados");
            return null;
        }
        clientRepository.delete(client);
        return Constants.DELETED;
    }


    ClientEntity buildClient(ClientRequestDto clientRequestDto, boolean isNew){
        ClientEntity response = new ClientEntity();
        if(isNew){
            response.setFechaCreacion(LocalDate.now());
            response.setFechaModificacion(LocalDateTime.now());
        }else{
            response.setFechaModificacion(LocalDateTime.now());
        }
        response.setEmail(clientRequestDto.getEmail());
        response.setFechaNacimiento(clientRequestDto.getFechaNacimiento());
        response.setIdentificationNumber(clientRequestDto.getIdNumber());
        response.setIdentificationType(clientRequestDto.getIdType());
        response.setLastname(clientRequestDto.getLastname());
        response.setName(clientRequestDto.getName());
        response.setId(clientRequestDto.getId() != null ? clientRequestDto.getId() : 0);
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
                .fechaModificacion(clientEntity.getFechaModificacion())
                .build();
        return response;
    }

    boolean validarEdad(LocalDate fechaNacimiento){
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        return edad >= 18 ? true : false;
    }

}
