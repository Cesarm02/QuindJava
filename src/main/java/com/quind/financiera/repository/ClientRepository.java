package com.quind.financiera.repository;

import com.quind.financiera.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    ClientEntity findByIdentificationNumber(String identificationNumber);


}
