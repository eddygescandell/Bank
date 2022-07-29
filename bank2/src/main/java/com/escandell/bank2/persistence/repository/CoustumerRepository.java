package com.escandell.bank2.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escandell.bank2.persistence.entity.Coustumer;

@Repository
public interface CoustumerRepository extends JpaRepository<Coustumer, Integer>{

}
