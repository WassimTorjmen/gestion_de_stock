package com.example.demo.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.dao.DistributeurRep;
import com.example.demo.model.produithorsstock;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class mailservice {
	@Autowired
	private DistributeurRep  disQ ;
	String path ="C:\\Users\\Karoui Faten\\Downloads";
	public void exportmail() throws FileNotFoundException, JRException {
		//data 
  List<Object[]> result =disQ.stockexpose() ;
		  
		  List<Object> resultList = result.stream()
			    .map(x -> {

			    	produithorsstock data= new  produithorsstock();
			
			        data.nom_dist=x[0].toString(); 
			        data.des_prod=x[1].toString(); 

			        return data;
			    })
			    .collect(Collectors.toList());
		  System.out.print(result);
		  //load jrxml file & compile it 
		  File file =ResourceUtils.getFile("classpath:email.jrxml"); 
		  JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath()); 
		  JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(resultList);
		  Map<String,Object> parameters = new HashMap<>(); 
		  parameters.put("created  by ", "M");
		  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
		  JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\email.pdf");
	
		
	}
}
