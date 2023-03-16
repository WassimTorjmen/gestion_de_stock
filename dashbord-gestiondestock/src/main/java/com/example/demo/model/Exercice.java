package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
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
public class Exercice {
	@Id 
	private String COD_EXE ;
	@NonNull
	private Date DEB_EXE ;
	@NonNull
	private Date  FIN_EXE  ;
	private Date   DAT_CLOT    ;
	private Date  DAT_MAJ   ;
	private String  COD_USER ; 
	 @OneToMany(mappedBy = "COD_EXE" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
		private Collection<KitPack> kitpack;

}
