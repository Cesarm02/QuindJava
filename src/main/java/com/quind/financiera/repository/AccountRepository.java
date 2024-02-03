package com.quind.financiera.repository;

import com.quind.financiera.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByNumberAccount(String numberAccount);

}
