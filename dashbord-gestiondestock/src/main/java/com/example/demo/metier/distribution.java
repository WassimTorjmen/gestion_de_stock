package com.example.demo.metier;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import freemarker.template.Configuration;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DistributeurRep;
import com.example.demo.dto.DisAdrr;
import com.example.demo.dto.distributionTotal;
import com.example.demo.dto.Qtemax_min;
import com.example.demo.dto.desetat;
import com.example.demo.dto.venteParDist;
import com.fasterxml.jackson.core.JsonProcessingException;
import freemarker.template.TemplateException;

import java.io.IOException;
import net.sf.jasperreports.engine.JRException;

@Service
public class distribution {
	   @Autowired
	   public JavaMailSender emailSender;
	   @Autowired
		private DistributeurRep  disRep ;
	   @Autowired
			private mailservice mails ;
	  public List<Object> finddis(String adresse) throws JsonProcessingException {
		  List<Object[]> result =disRep.RegionDist(adresse);
		  List<Object> resultList = result.stream()
			    .map(x -> {

			        desetat data= new desetat();
			        data.libelle =x[1].toString();
			        data.count=Long.parseLong(x[0].toString()); 

			        return data;
			    })
			    .collect(Collectors.toList());
		  System.out.println(resultList);
		return resultList ;
		 
   
	    }
	  public List<Object> etatsproduit(String proddes ,String ville ,String nomdist) {
		  List<Object[]> result =disRep.etatsproduit(proddes,ville,nomdist);
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
		  List<Object[]> result =disRep.enstock(prod);
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
		  
		  List<Object[]> result =disRep.vendu(prod);
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
		  List<Object[]> result =disRep.distribution();
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
		  List<Object[]> result =disRep.distributiontotal(prod ,source,convertedCurrentDate,convertedCurrentDate2);
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
		  List<Object[]> result =disRep.ventpardate(prod ,source,convertedCurrentDate,convertedCurrentDate2);
		  
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
		  List<Object[]> result =disRep.qtemax();
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
		  List<Object[]> result =disRep.qtemin();
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
public List<Object>venteParDist(){
		  List<Object[]>result=disRep.ventepardist();
		  List<Object> resultList = result.stream()
				  .map(x->{
					venteParDist data=new venteParDist();
					data.destination=(String) x[0];
					data.adresse=(String) x[1];
					data.nom_dist=(String)x[2];
					data.nom_produit=(String) x[3];
					data.cod_prod=(String) x[4];
					data.last_delivary_date=(String) x[5];
					data.last_sell_date=(String) x[6];
					data.derniere_qte_livree=(Integer) x[7];
					return data;
				  })
				  .collect(Collectors.toList());
		  return resultList;
}

}
