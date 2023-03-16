package com.example.demo.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Distributeur;


@RepositoryRestResource
public interface DistributeurRep  extends JpaRepository<Distributeur,String>{

	
	
	@Query(value = "select distinct adresse from distributeur order by adresse", nativeQuery = true)
	List<String>  listeadresse();

	@Query(value = "select distinct source  from livraison_en_cours order by source", nativeQuery = true)
	List<String>  listsource();
	
	@Query(value = "select distinct ville from distributeur order by ville ", nativeQuery = true)
	List<String>  listeville();
	
	@Query(value = "select distinct nom_dist from distributeur WHERE VILLE=:ville order by nom_dist ", nativeQuery = true)
	List<String>  listenomdist(@Param("ville") String ville);
	
	@Query(value = "select distinct des_prod from produit order by des_prod", nativeQuery = true)
	List<String>  listeproddes();

	//distribution par region
	@Query(value = "select count(*) as count , p.des_prod  as libelle from produit p , kit_pack k ,distributeur d WHERE p.cod_prod = k.cod_prod and k.cod_dist=d.cd_dist and adresse =:adresse group by p.des_prod ", nativeQuery = true)
	List<Object[]>  query(@Param("adresse") String adresse); 
	
	@Query(value = "select count(e.libelle),e.libelle from kit_pack k , etat_kit e  , distributeur d  where etat_kit=e.code_etat and cod_prod =(select cod_prod  from produit where des_prod=:proddes ) and k.cod_dist=d.cd_dist and  d.ville =:ville and d.nom_dist=:nomdist group  by etat_kit , e.libelle , d.nom_dist", nativeQuery = true)
	List<Object[]>  etatsproduit(@Param("proddes") String proddes,@Param("ville") String ville,@Param("nomdist") String nomdist); 

	/*top 5 boutiques qui ont un produit  enstock etat_kit 1 ou 5 */
	@Query(value = "select count(*) , d.nom_dist  from distributeur d , kit_pack k  where cod_prod=(select cod_prod  from produit where des_prod=:prod ) and d.cd_dist=k.cod_dist and   k.etat_kit in ('1','5') group by d.nom_dist order by  count desc limit 5", nativeQuery = true)
	List<Object[]>  enstock(@Param("prod") String prod);

/*top 5 boutiques qui ont un produit  vendu  (etat_kit= 3) */
	@Query(value = "select count(*) , d.nom_dist  from distributeur d , kit_pack k  where cod_prod=(select cod_prod  from produit where des_prod=:prod ) and d.cd_dist=k.cod_dist and   k.etat_kit ='3' group by d.nom_dist order by  count desc limit 5", nativeQuery = true)
	List<Object[]> vendu(@Param("prod") String prod);

	/*distribution par source et produit */
	@Query(value = "select qte_prod  ,nom_dist ,l.dat_crea from livraison_en_cours l , distributeur d where l.destination=d.cd_dist and cod_prod=(select cod_prod  from produit where des_prod='PORTABLE OPPO A55 (4+128G)' ) and l.source='LAC' ", nativeQuery = true)
	List<Object[]> distribution ();
	//	List<Object[]> distribution (@Param("prodes") String prod , @Param("source") String source);

	/***dist total par date***/
		@Query(value = "select sum (CAST (qte_prod AS INTEGER))  ,   DATE_PART( 'month', dat_crea) as monthy from livraison_en_cours l , distributeur d where l.destination=d.cd_dist and cod_prod=(select cod_prod  from produit where des_prod=:prodes) and l.source=:source and l.dat_crea >=:date1   and l.dat_crea<=:date2 group by monthy order by monthy asc ", nativeQuery = true)
		List<Object[]> distributiontotal  (@Param("prodes") String prodes , @Param("source") String source, @Param("date1") Date date1, @Param("date2") Date date2);


		/****VENT par date ****/
		@Query(value = "select count(distinct(k.order_number) ) ,DATE_PART( 'month', k.dat_crea) as monthy from kit_pack k , livraison_en_cours l   where l.order_number=k.order_number and k.cod_prod=(select cod_prod  from produit where des_prod=:prodes) and k.etat_kit='3' and l.source=:source and k.dat_VENT >=:date1   and k.dat_VENT<=:date2 group by monthy order by monthy asc", nativeQuery = true)
		List<Object[]>ventpardate (@Param("prodes") String prod , @Param("source") String source, @Param("date1") Date date1, @Param("date2") Date date2);


	/***LES PRODUIT HORS STOCK ***/
		@Query(value ="select d.nom_dist ,p.des_prod from distributeur d , produit p ,kit_pack k  where k.cod_prod=p.cod_prod and  d.cd_dist=k.cod_dist and   k.etat_kit='3' ", nativeQuery = true)
		List<Object[]>stockexpose ();

		//*** Qte max des produit par boutique */
		@Query(value ="select count(k.cod_prod), p.des_prod , d.nom_dist  from kit_pack k , distributeur d , produit p  where  d.cd_dist=k.cod_dist and p.cod_prod = k.cod_prod  group by  d.nom_dist ,p.des_prod  having count(k.cod_prod)> 5 order by(count(k.cod_prod))", nativeQuery = true)
		List<Object[]>qtemax ();

		/*quantité minimale des produits par boutique*/
		@Query(value ="select count(k.cod_prod), p.des_prod , d.nom_dist  from kit_pack k , distributeur d , produit p  where  d.cd_dist=k.cod_dist and p.cod_prod = k.cod_prod  group by  d.nom_dist ,p.des_prod  having count(k.cod_prod)< 2", nativeQuery = true)
		List<Object[]>qtemin();

		/***taux de distribution des produits selon boutique **/
		/*@Query(value ="select sum(CAST(qte_prod AS NUMERIC)) , cod_prod from livraison_en_cours  where destination=(select cd_dist from distributeur where nom_dist=:boutique)and dat_crea>=:date1 and dat_crea<=:date2 group by destination,cod_prod ", nativeQuery = true)
		List<Object[]>qtemin();*/
}
