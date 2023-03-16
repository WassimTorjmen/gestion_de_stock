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
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Document {
	@Id
	private String COD_DOC ;
	private String LIB_DOC ; 
	private String NOM_TABLE ; 
	private  Date DAT_CREA ; 
	private Date DAT_MAJ ; 
	private String COD_USER ; 
    @OneToMany(mappedBy = "COD_DOC" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
		private Collection<KitPack> kitpack;
}

