package com.example.demo.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;
import freemarker.template.Configuration;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DistributeurRep;
import com.example.demo.model.DisAdrr;
import com.example.demo.model.distributionTotal;
import com.example.demo.model.produithorsstock;
import com.example.demo.model.Distributeur;
import com.example.demo.model.Qtemax_min;
import com.example.demo.model.desetat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import net.sf.jasperreports.engine.JRException;

@Service
public class distribution {
	   @Autowired
	   public JavaMailSender emailSender;
	   @Autowired
		private DistributeurRep  disQ ;
	   @Autowired
			private mailservice mails ;
	  public List<Object> finddis(String adresse) throws JsonProcessingException {
		  List<Object[]> result =disQ.query(adresse);
		  List<Object> resultList = result.stream()
			    .map(x -> {

			        desetat data= new desetat();
			        data.libelle =x[1].toString();
			        data.count=Long.parseLong(x[0].toString()); 

			        return data;
			    })
			    .collect(Collectors.toList());
		return resultList ;
		 
   
	    }
	  public List<Object> etatsproduit(String proddes ,String ville ,String nomdist) {
		  List<Object[]> result =disQ.etatsproduit(proddes,ville,nomdist);
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	desetat data= new desetat();
			        data.libelle =x[1].toString();
			        data.count=Long.parseLong(x[0].toString()); 

			        return data;
			    })
			    .collect(Collectors.toList());
		return resultList ;
		 
	    }
	  
	 public List<Object> enstock(String prod) {
		  List<Object[]> result =disQ.enstock(prod);
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	desetat data= new desetat();
			        data.libelle =x[1].toString();
			        data.count=Long.parseLong(x[0].toString()); 

			        return data;
			    })
			    .collect(Collectors.toList());
		return resultList ;
		 
	    }

	  
	  
	  public List<Object> vendu(String prod) {
		  
		  List<Object[]> result =disQ.vendu(prod);
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	desetat data= new desetat();
			        data.libelle =x[1].toString();
			        data.count=Long.parseLong(x[0].toString()); 

			        return data;
			    })
			    .collect(Collectors.toList());
		return resultList ;
		 
	    }
	  public List<Object> distribution( ) throws ParseException{
		 Calendar calendar = Calendar.getInstance();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  //Date convertedCurrentDate = sdf.parse(date1);
		  //Date convertedCurrentDate2 = sdf.parse(date2);
		  List<Object[]> result =disQ.distribution();
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	DisAdrr data= new DisAdrr();
			    	 data.date=(Date) x[2]; 
			        data.type=x[1].toString();
			        data.total=Long.parseLong(x[0].toString()); 
			        
			        calendar.setTime((Date) x[2]);
			      //  data.dat_crea=calendar.get(Calendar.MONTH);
                   
			        return data;
			    })
			    .collect(Collectors.toList());

		 return resultList ; 
	    }
	  public List<Object> distributionTotal(String prod,String source,String date1, String date2 ) throws ParseException {
		
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date convertedCurrentDate = sdf.parse(date1);
		  Date convertedCurrentDate2 = sdf.parse(date2);
		
		  System.out.print( convertedCurrentDate2);
		  List<Object[]> result =disQ.distributiontotal(prod ,source,convertedCurrentDate,convertedCurrentDate2);
		  System.out.print(result);

		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	 distributionTotal data= new  distributionTotal();
			
			        data.count=Long.parseLong(x[0].toString()); 
			      
			        Double n =(Double) x[1];
			        String monthname=monthName1(n);
		
			        data.libelle = monthname ;

			        return data;
			    })
			    .collect(Collectors.toList());
		  System.out.print(resultList);
		  return resultList ;

		
		 
	    }
	public String monthName1(Double n) {
		 String[] s = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		if(n>=1 && n<=12)	
			   return s[(int) (n-1)] ; 
		return "invlide"; 
	}
	  public List<Object> ventpardate(String prod,String source,String date1, String date2 ) throws ParseException {
			
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date convertedCurrentDate = sdf.parse(date1);
		  Date convertedCurrentDate2 = sdf.parse(date2);
		  
	 
//	Sytem.out.print(date_1+""+date_2);
		  List<Object[]> result =disQ.ventpardate(prod ,source,convertedCurrentDate,convertedCurrentDate2);
		  
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	 distributionTotal data= new  distributionTotal();
			
			        data.count=Long.parseLong(x[0].toString()); 
			      
			        Double n =(Double) x[1];
			        String monthname=monthName1(n);
		
			        data.libelle = monthname ;

			        return data;
			    })
			    .collect(Collectors.toList());
		  System.out.print(resultList);
		  return resultList ;

		
		 
	    }
	  
// send mail daily hors stock 
	  @Autowired
	    private JavaMailSender mailSender;
	  @Autowired
	    private Configuration configuration;
	  @Scheduled(cron = "0 011 16 * * ?")

	  public String mailsender() throws MessagingException, JRException, IOException, TemplateException {
	
		     mails.exportmail();
		       MimeMessage mimeMessage = emailSender.createMimeMessage();
		        MimeMessageHelper mimeMessageHelper
		                = new MimeMessageHelper(mimeMessage, true);
		        mimeMessageHelper.setFrom("torjmane521@gmail.com");
		        mimeMessageHelper.setTo("torjmen.wassim@gmail.com");
		        mimeMessageHelper.setText("ci-joint vous trouvez la liste des produits qui sont en répture de stock par boutique ");
		        mimeMessageHelper.setSubject("Produit en répture de stock ");
		        FileSystemResource fileSystem
		                = new FileSystemResource(new File("Documents\\gestion_de_stock\\email.pdf"));
		        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
		                fileSystem);
		 
		        emailSender.send(mimeMessage);
		       
         return"ok"; 
	  }
	  
	  public List<Object> qtemax()  {
		  List<Object[]> result =disQ.qtemax();
		  System.out.print(result );
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	 Qtemax_min data= new   Qtemax_min();
			    	 
			          data.qte=Long.parseLong(x[0].toString());
			          
			          data.produit=(String) x[1];
			          data.boutique=(String) x[2];
			    	
			        return data;
			    })
			    .collect(Collectors.toList());
		  //System.out.print(resultList);
		  return resultList ;

		
		 
	    }
	  public List<Object> qtemin()  {
		  List<Object[]> result =disQ.qtemin();
		  System.out.print(result );
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	 Qtemax_min data= new   Qtemax_min();
			    	
			          data.qte=Long.parseLong(x[0].toString());
			          
			          data.produit=(String) x[1];
			          data.boutique=(String) x[2];
			    	  
			        return data;
			    })
			    .collect(Collectors.toList());
		  //System.out.print(resultList);
		  return resultList ;

		
		 
	    }
}
