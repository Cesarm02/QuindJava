package com.quind.financiera.controller;

import com.quind.financiera.entity.dto.ClientRequestDto;
import com.quind.financiera.entity.dto.ClientResponseDto;
import com.quind.financiera.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(path = "save")
    ResponseEntity<ClientResponseDto> saveClient(@RequestBody ClientRequestDto clientRequestDto){
        ClientResponseDto responseDto = clientService.saveClient(clientRequestDto);
        return ResponseEntity.ok(responseDto);
    }

}
