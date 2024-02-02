package com.quind.financiera.service;

import com.quind.financiera.entity.dto.ClientRequestDto;
import com.quind.financiera.entity.dto.ClientResponseDto;

public interface ClientService {

    ClientResponseDto saveClient(ClientRequestDto clientRequestDto);

    ClientResponseDto updateClient(ClientRequestDto clientRequestDto);

    String deleteClient(String cedula);

}
