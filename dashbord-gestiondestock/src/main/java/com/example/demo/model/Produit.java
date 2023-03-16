package com.example.demo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produit {
	@Id
	private String COD_PROD ; 
	private String DES_PROD   ; 
	@NonNull 
	private String COMPOSER  ; 
	private Double PA_HT ; 
	private Double PA_TTC ; 
	private Double VAL_MARGE ; 
	private Double TX_MARGE ;
	private Double PV_HT ; 
	private Double TX_TVA  ; 
	private Double MNT_TVA  ; 
	private Double PV_TTC ; 
	private String SITUATION ; 
	private Long QTE_MIN ; 
	private Long QTE_MAX ; 
	@NonNull
	private String VENDABLE  ; 
	private String LIVRABLE ; 
	private String TYPE_PROD ; 
	private String VALORISABLE ; 
	private String LIB_FACT ; 
	private Double REDEVANCE ; 
	private Double TX_REMISE ; 
	private String NUM_COMPT ;
	private String NUM_COMPT_REM ; 
	private String  SERIALISABLE ; 
	private String DEPOT_VENTE ; 
	private String   FICHE_VENTE   ; 
	private String  ID_CLIENT     ; 
	private String   NUM_APPEL   ; 
    private Date DAT_MAJ ; 
    private String   COD_USER   ; 
    private String   VALORISATION   ; 
    
    private Double TX_TIMBRE_RECHARGE = 0.0 ;
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String    FLAG_LP     ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String   INTERNE    ; 
    private String   DES_WEB    ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String FLAG_ESHOP ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String LP_CORPO ; 
    @Column( columnDefinition="Decimal default '0.0'")
    private Double PV_HT_ESHOP ; 
  
    private String FLAG_3G ; 
    @Column( columnDefinition = "  VARCHAR default 'o' ")
    private String ACTIF ; 
    private String  GAMME ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String FLAG_4G ; 
    private Long  GAMME_DASH_ID ; 
    private Long  FAMILLE_DASH_ID  ; 
    @Column( columnDefinition = "  VARCHAR default 'N' ")
    private String   OUTLET    ; 
    private Long   FLAG_AFFICHER_RESERV  ; 
    private Date  DAT_OUTLET  ; 
    private Long    ORDRE    ;
    private String    INFO_TIMBRE      ; 
    @ManyToOne 
	private SousFamille codeSF ;
    @OneToMany(mappedBy = "codep" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Collection<KitPack> kitpack;
}

