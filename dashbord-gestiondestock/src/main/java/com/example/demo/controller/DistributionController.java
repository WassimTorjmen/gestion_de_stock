package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DistributeurRep;
import com.example.demo.metier.distribution;
import com.example.demo.metier.mailservice;
import com.example.demo.model.Distributeur;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/distri")
@CrossOrigin(origins = "http://localhost:4200")
public class DistributionController {

	   @Autowired
		private DistributeurRep  disQ ;
	   @Autowired
		private distribution dis ; 
	 
	 @GetMapping("/parAdresse")
	    public List<Object> parAdresse( @RequestParam String adresse) throws JsonProcessingException {
	    	
	    	  return dis.finddis(adresse) ; 
	    	  
	 }
	  @GetMapping("/etats")
	    public List<Object> etatsproduit(@RequestParam String proddes , @RequestParam String ville ,@RequestParam String nomdist) throws UnsupportedEncodingException{ 
	    	 String prodd = java.net.URLDecoder.decode(proddes, "UTF-8");
	    	 System.out.print(ville); 
	    	 return dis.etatsproduit(prodd,ville,nomdist); 
	    
	    	 
	 }


	   @GetMapping("/adresse")
	   public List<String>listeadresse() {
			  return disQ.listeadresse(); 
	   
		  }
	   @GetMapping("/ville")
	   public List<String>listeville() {
			  return disQ.listeville(); 
	   
		  }
	  
	   @GetMapping("/nomdist")
	   public List<String>listenomdist(@RequestParam String ville) {
			  return disQ.listenomdist(ville); 
	   
		  }
	   
	   @GetMapping("/proddes")
	   public List<String>listeproddes() {
			  return disQ.listeproddes(); 
	   
		  }
	   @GetMapping("/source")
	   public List<String>listesource() {
			  return disQ.listsource(); 
	   
		  }
	    @GetMapping("/enstock")
	    public List<Object> enstock(@RequestParam String prod) throws UnsupportedEncodingException{   
	    	 String prodd= java.net.URLDecoder.decode(prod, "UTF-8");
	    	 return dis.enstock(prodd); 
	 }
	    @GetMapping("/vendu")
	    public List<Object> vendu(@RequestParam String prod) throws UnsupportedEncodingException{   
	
	        String prodd= java.net.URLDecoder.decode(prod, "UTF-8");
	          
	    	 return dis.vendu(prodd); 
	 }
	    
	    @GetMapping("/distribution")
	    public List<Object> distribution() throws UnsupportedEncodingException, ParseException{
	    	String prod ="PORTABLE OPPO A55 (4+128G)";
	    	String source="BEN";
	    	String date1="02-02-2022";
	    	String date2="02-04-2022";
	    String prodd = java.net.URLDecoder.decode(prod, "UTF-8");
	    	 return dis.distribution();
	 }

	    
	    @GetMapping("/distributiontotal")
	    public List<Object> distributionTotal(@RequestParam String prod,@RequestParam String source,@RequestParam String date1,@RequestParam String date2) throws UnsupportedEncodingException, ParseException{   
	    	 String prodd = java.net.URLDecoder.decode(prod, "UTF-8");
	    	 return dis.distributionTotal(prodd, source,date1,date2); 
	 }
	    @GetMapping("/ventpardate")
	    public List<Object> ventpardate(@RequestParam String prod,@RequestParam String source,@RequestParam String date1,@RequestParam String date2) throws UnsupportedEncodingException, ParseException{   
	    	 String prodd = java.net.URLDecoder.decode(prod, "UTF-8");
	    	
	    	 return dis.ventpardate(prodd, source,date1,date2); 
	 }
	    @GetMapping("/qteMax")
	    public List<Object> qtemax() {
			return dis.qtemax();
	    	
	    }
	    @GetMapping("/qteMin")
	    public List<Object> qtemin() {
			return dis.qtemin();
	    	
	    }
}
