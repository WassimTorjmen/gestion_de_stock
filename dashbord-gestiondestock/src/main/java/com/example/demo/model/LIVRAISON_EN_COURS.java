package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class LIVRAISON_EN_COURS {
	@Id 
	private String ID ; 
	private String SOURCE ; 
	private String DESTINATION ;
	  @Column( columnDefinition = "  VARCHAR default 'Non' ")
	private String ACCEPTED ; 
	private Date  DAT_CREA ; 
	private Date DAT_MAJ ; 
	private String TYPE_STOCK_LIV ;
	private Long ORDER_NUMBER ; 
	private String COD_PROD ;
	private String PACK_3G ; 
	private String DES_PROD ; 
	private  String QTE_PROD ; 
	 
}
/**/