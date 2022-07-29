package com.escandell.bank2.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escandell.bank2.persistence.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	

}
