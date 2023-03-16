package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@IdClass(PKclass.class)
public class Famille {
	@Id 
	private String COD_FAM ; 
	@ManyToOne 
	@Id
	@JoinColumn(name = "COD_GROUPE" , nullable = false)
	private Groupe COD_GROUPE; 
	@NonNull 
	private String LIB_FAM ; 
	private Date DAT_MAJ  ; 
	private String COD_USER ; 
	private String ACTIF ; 
	@OneToMany(mappedBy = "codeF" ,fetch=FetchType.EAGER ,cascade=CascadeType.ALL)
	private Collection<SousFamille> Sfamille ;
}
