package com.escandell.bank2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.escandell.bank2.persistence.entity.Coustumer;
import com.escandell.bank2.persistence.repository.CoustumerRepository;

@Service
public class CoustumerService {
	
     private final CoustumerRepository repository;

	public CoustumerService(CoustumerRepository repository) {
		this.repository = repository;
	}
     
	public Coustumer CreatedCoustumer(Coustumer coustumer) {

		return this.repository.save(coustumer);
	}
    
	public List<Coustumer> ListAllCoustumer(){
		return this.repository.findAll();
	}
	
	public void DeleteCoustumerByID(Integer id) {
		 this.repository.deleteById(id);
	}
	
	public Coustumer GetCoustumerById(Integer id) {
		return this.repository.findById(id).get();
		
	}
}
