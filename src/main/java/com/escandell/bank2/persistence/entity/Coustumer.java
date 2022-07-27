package com.escandell.bank2.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class Coustumer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Debe ingresar su nombre")
	private String name;

	@NotBlank(message = "Debe ingresar su apellido")
    private String last_name;

	@Length(min = 11, max = 11, message = "El carnet de identidad tiene que tener 11 dígitos")
	private String identification;

	@NotBlank
    private String address;
    
    /*one to many union con account*/
//    @OneToMany(mappedBy = "coustumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Account> accounts;

}
