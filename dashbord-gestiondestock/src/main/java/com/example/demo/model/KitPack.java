package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name="KIT_PACK")
public class KitPack {
	@Id 
	private String KIT_CODE  ; 
	@NonNull 
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date DAT_CREA  ;
	private String MISION ; 
	private String COD_DIST  ; 
	private Date  DAT_STOK  ;
	private Date   DAT_RES  ;
	private Date   DAT_SORT   ;
	private Date   DAT_VENT   ;
	private Date   DAT_BAT   ;
	private String  NUM_BAT ; 
	private String  COMPOSER  ; 
	private String  CD_DIST; 
	private String  NUM_DOC  ;
	private String   COD_EXE_REC   ; 
	private String COD_EXE_BE ; 
	private String   NUM_REC   ; 
	private String   NUM_BE   ;
	private String    COD_PERSONNEL  ;
	private Date   DAT_MAJ   ;
	private String COD_USER ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
	private String  INTEGRED   ;
	@Column( columnDefinition="Decimal default '0.0'")
	private Double COD_COUL ;
	private Long  COD_ACTION_ ;
	private String DATA ;
	private String  COD_ACTION ;
	private Long  ORDER_NUMBER;
	private String  IMSI  ;
	private String COD_FRS_A ;
	private String  CGC  ;
	private String   NUM_BR   ;
	private String   COD_EXE_BR   ;
	@ManyToOne 
	@JoinColumn(name="COD_PROD")  
	private Produit codep ;
	@ManyToOne 
	private Distributeur codedis ;
	@ManyToOne 
	@JoinColumn(name="COD_DOC") 
	private Document COD_DOC ;
	@ManyToOne 
	@JoinColumn(name="COD_FRS")
	private Fournisseur  COD_FRS ;
	@ManyToOne
	@JoinColumn(name="COD_EXE")
	private Exercice COD_EXE  ;
	@ManyToOne 
	@JoinColumn(name="ETAT_KIt")
	private EtatKit CODE_ETAT ;
}
/*select count(*) , p.des_prod from produit p , kit_pack k ,distributeur d where p.cod_prod = k.cod_prod and k.cod_dist=d.cd_dist and adresse ='TUNIS' 
group by p.des_prod ;
*/