package com.escandell.bank2.persistence.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;


import lombok.Data;

@Data
@Entity
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Digits(integer = 8, fraction = 0)
	private Long id;


	@Min(value = 50, message = "El saldo debe ser mayor o igual a 50")
	private Long balance;


	@PastOrPresent
	private LocalDateTime creationDate;
	
	/*many to one union con coustumer*/
	@ManyToOne
	@JoinColumn(name = "coustumer_id")
	private Coustumer coustumer;

	//Asignar fecha de creacion a las cuentas automaticamente
	@PrePersist
	public void AddCreationDate(){
		creationDate = LocalDateTime.now();
	}

}
