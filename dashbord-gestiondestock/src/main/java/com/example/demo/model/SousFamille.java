package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
@RequiredArgsConstructor
@IdClass(PkSf.class)
public class SousFamille {
	@Id 
	private String COD_SFAM  ; 
	@Id
	@ManyToOne 
	private Famille codeF ;
	@NonNull 
	private String LIB_SFAM ;
	private Date DAT_MAJ ; 
	private String COD_USER ; 
	@OneToMany(mappedBy = "codeSF" ,fetch=FetchType.EAGER ,cascade=CascadeType.ALL)
	private Collection<Produit> produits ;
}



 
