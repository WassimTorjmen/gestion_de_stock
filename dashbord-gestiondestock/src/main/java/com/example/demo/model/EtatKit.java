package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name="ETAT_KIT")
public class EtatKit {
	@Id
	private String CODE_ETAT ; 
	private String LIBELLE ; 
	private Date DAT_MAJ ; 
	private  String COD_USER ; 
	 @OneToMany(mappedBy = "CODE_ETAT" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
		private Collection<KitPack> kitpack;
}
/* 
  COD_USER   VARCHAR2(4 BYTE)*/