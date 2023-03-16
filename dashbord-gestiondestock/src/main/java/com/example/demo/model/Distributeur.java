package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
public class Distributeur {
	@Id
	private String CD_DIST ;
	@NonNull
	private String NOM_DIST  ;
	private String  ADRESSE  ;
	private String VILLE   ;
	private String  TEL    ;
	private String FAX    ;
	private String RESPONSABLE    ;
	@NonNull
    @Column( columnDefinition = "  CHAR(1) ")
	private String INTERNE_EXTERNE    ;
	@NonNull
	private String CD_STATUT     ;
	@NonNull
	private Date  DATE_STATUT  ;
	@NonNull
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime   DATE_CREA      ;
	private String OBSERVATIONS   ;
	private String   CODE_BAR_PTV   ;
	private String MAT_FISCAL     ;
	private String NUM_COMPT   ;
	private Double SOLDE ; 
	private Date DAT_MAJ ; 
	private String COD_USER ;
	private String COD_PDV ; 
	private String JOURNAL ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
	private String CLE  ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String   CENT_REP  ; 
    private Long  CODE_ENTITE_PERE ; 
    private Long  CODE_TYPE_ENTITE ; 
    @ManyToOne 
	private SousFamille codeSF ;
    @OneToMany(mappedBy = "codedis" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Collection<KitPack> kitpacks ;
}
	
	
	

