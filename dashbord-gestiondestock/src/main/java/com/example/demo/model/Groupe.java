package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Groupe {
	@Id 
	private String COD_GROUP; 
	@NonNull
	private String LIB_GROUP  ; 
	private Date  DAT_MAJ  ; 
	private String COD_USER ; 
	@OneToMany(mappedBy = "COD_GROUPE" ,fetch=FetchType.EAGER ,cascade=CascadeType.ALL)
	private Collection<Famille> famille ;

}


