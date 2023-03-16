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
public class Fournisseur {
	@Id 
	private String  COD_FRS  ;
	@NonNull 
	private String Nom_FRS ;
	private String ADRESSE ; 
	private String VILLE ; 
	private String  TEL ; 
	private String   FAX  ; 
	private String   RESPONSABLE  ; 
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String    DATE_CREA ;
	private String   OBSERVATIONS   ;
	private String   CODE_BAR_PTV ;
	private String   MAT_FISCAL ;
	private String    COD_USER    ;
	private Date DAT_MAJ ;
	  @Column( columnDefinition = "  VARCHAR default 'Non' ")
	private String     SAV ;
	  @OneToMany(mappedBy = "COD_FRS" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
		private Collection<KitPack> kitpack;
}



