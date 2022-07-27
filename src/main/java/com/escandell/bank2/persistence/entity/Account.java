package com.escandell.bank2.persistence.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.ManyToAny;

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
	private Date creationDate;
	
//	/*many to one union con coustumer*/
//	@ManyToOne(fetch =FetchType.LAZY, optional = false)
//	@JoinColumn(name = "coustumer_id", nullable = false)
//	private Coustumer coustumer;


}
