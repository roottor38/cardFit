package com.cardfit.project.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardfit.project.service.CardDBService;
import com.cardfit.project.service.CardService;

import model.dto.Summation;

@CrossOrigin
@RestController
@Component
public class CardRestController {

	public CardRestController() {
		System.out.println("*** Start CardRestController ***");
	}
	
	@Autowired
	private CardService service;
	
	@DeleteMapping("deleteCard/{cardname}")
	public void deleteCard(@PathVariable String cardname) {
		service.deleteCard("cardname", cardname);
	}
	
	@PostMapping("/histogram/{subject}")
	   public void histogram(@PathVariable String subject) {
	      if(subject.equals("total")) {
	         Summation data =  null;
	         try {
	            data = CardDBService.getSummation();
	         }catch(SQLException e) {
	            e.printStackTrace();
	         }
	           RConnection connection = null;
	   
	           String value1 = String.valueOf(data.getMovie())+","+
	                 String.valueOf(data.getCafe())+","+
	                       String.valueOf(data.getTelecom())+","+
	                             String.valueOf(data.getTransportation())+","+
	                                   String.valueOf(data.getOnshop())+","+
	                                         String.valueOf(data.getOffshop())+","+
	                                               String.valueOf(data.getFood())+","+
	                                                     String.valueOf(data.getOthers());
	           try {
	               connection = new RConnection();
	               connection.eval("value<-c("+value1+")");
	               connection.eval("name<-c('movie','cafe','telecom','transportation','onshop','offshop','food','others')");
	               connection.eval("result = data.frame(name,value)");
	               connection.eval("library(ggplot2)");
	               connection.eval("png(\"histogram.png\")");
	               connection.eval("print(ggplot(result, aes(name, value)) + geom_bar(stat = \"identity\" , fill = \"blue\"))");
	               connection.eval("dev.off()");
	               connection.close();
	               File oldFile = new File("C:\\0.encore\\09.Spring\\step13_cardFit\\src\\main\\webapp\\images\\histogram.png");
	               oldFile.delete();
	               Path temp = Files.move 
	                       (Paths.get("C:\\Users\\Playdata\\Documents\\histogram.png"),  
	                       Paths.get("C:\\0.encore\\09.Spring\\step13_cardFit\\src\\main\\webapp\\images\\histogram.png"));
	           } catch (Exception e) {
	              e.printStackTrace();
	           }
	      }else if(subject.equals("gender")) {
	         Summation men =  null;
	         Summation women =  null;
	         try {
	            men = CardDBService.getMenSummation();
	            women = CardDBService.getWomenummation();
	         }catch(SQLException e) {
	            e.printStackTrace();
	         }
	           RConnection connection = null;

	           String menValue = String.valueOf(men.getMovie())+","+
	                 String.valueOf(men.getCafe())+","+
	                       String.valueOf(men.getTelecom())+","+
	                             String.valueOf(men.getTransportation())+","+
	                                   String.valueOf(men.getOnshop())+","+
	                                         String.valueOf(men.getOffshop())+","+
	                                               String.valueOf(men.getFood())+","+
	                                                     String.valueOf(men.getOthers());
	           
	           String wowomenValue = String.valueOf(women.getMovie())+","+
	                 String.valueOf(women.getCafe())+","+
	                       String.valueOf(women.getTelecom())+","+
	                             String.valueOf(women.getTransportation())+","+
	                                   String.valueOf(women.getOnshop())+","+
	                                         String.valueOf(women.getOffshop())+","+
	                                               String.valueOf(women.getFood())+","+
	                                                     String.valueOf(women.getOthers());
	          
	           try {
	               connection = new RConnection();
	               connection.eval("name<-c('movie','cafe','telecom','transportation','onshop','offshop','food','others')");
	               connection.eval("men<-c("+menValue+")");
	               connection.eval("women<-c("+wowomenValue+")");
	               connection.eval("result <- data.frame(name, men, women)");
	               connection.eval("library(reshape2)");
	               connection.eval("result<-melt(result)");
	               connection.eval("library(ggplot2)");
	               connection.eval("png(\"Genderhistogram.png\")");
	               connection.eval("print(ggplot(result,aes(x=name, y=value ,fill=variable))+geom_bar(position=\"dodge\",stat=\"identity\"))");
	               connection.eval("dev.off()");
	               connection.close();
	               
	               File oldFile = new File("C:\\0.encore\\09.Spring\\step13_cardFit\\src\\main\\webapp\\images\\Genderhistogram.png");
	               oldFile.delete();
	               Path temp = Files.move 
	                       (Paths.get("C:\\Users\\Playdata\\Documents\\Genderhistogram.png"),  
	                       Paths.get("C:\\0.encore\\09.Spring\\step13_cardFit\\src\\main\\webapp\\images\\Genderhistogram.png"));
	           } catch (Exception e) {
	              e.printStackTrace();
	           }
	      }else {
	         Summation get20 =  null;
	         Summation get30 =  null;
	         Summation get40 =  null;
	         try {
	            get20 = CardDBService.get20();
	            get30 = CardDBService.get30();
	            get40 = CardDBService.get40();
	         }catch(SQLException e) {
	            e.printStackTrace();
	         }
	           RConnection connection = null;

	           String value20 = String.valueOf(get20.getMovie())+","+
	                 String.valueOf(get20.getCafe())+","+
	                       String.valueOf(get20.getTelecom())+","+
	                             String.valueOf(get20.getTransportation())+","+
	                                   String.valueOf(get20.getOnshop())+","+
	                                         String.valueOf(get20.getOffshop())+","+
	                                               String.valueOf(get20.getFood())+","+
	                                                     String.valueOf(get20.getOthers());
	           
	           String value30 = String.valueOf(get30.getMovie())+","+
	                 String.valueOf(get30.getCafe())+","+
	                       String.valueOf(get30.getTelecom())+","+
	                             String.valueOf(get30.getTransportation())+","+
	                                   String.valueOf(get30.getOnshop())+","+
	                                         String.valueOf(get30.getOffshop())+","+
	                                               String.valueOf(get30.getFood())+","+
	                                                     String.valueOf(get30.getOthers());
	           
	           String value40 = String.valueOf(get40.getMovie())+","+
	                 String.valueOf(get40.getCafe())+","+
	                       String.valueOf(get40.getTelecom())+","+
	                             String.valueOf(get40.getTransportation())+","+
	                                   String.valueOf(get40.getOnshop())+","+
	                                         String.valueOf(get40.getOffshop())+","+
	                                               String.valueOf(get40.getFood())+","+
	                                                     String.valueOf(get40.getOthers());
	           
	           try {
	               connection = new RConnection();
	               connection.eval("name<-c('movie','cafe','telecom','transportation','onshop','offshop','food','others')");
	               connection.eval("v20<-c("+value20+")");
	               connection.eval("v30<-c("+value30+")");
	               connection.eval("v40<-c("+value40+")");
	               connection.eval("result <- data.frame(name, v20, v30, v40)");
	               connection.eval("library(reshape2)");
	               connection.eval("result<-melt(result)");
	               connection.eval("library(ggplot2)");
	               connection.eval("png(\"Agehistogram.png\")");
	               connection.eval("print(ggplot(result,aes(x=name, y=value ,fill=variable))+geom_bar(position=\"dodge\",stat=\"identity\"))");
	               connection.eval("dev.off()");
	               connection.close();
	               
	               File oldFile = new File("C:\\0.encore\\09.Spring\\step13_cardFit\\src\\main\\webapp\\images\\Agehistogram.png");
	               oldFile.delete();
	               Path temp = Files.move 
	                     (Paths.get("C:\\Users\\Playdata\\Documents\\Agehistogram.png"),  
	                           Paths.get("C:\\0.encore\\09.Spring\\step13_cardFit\\src\\main\\webapp\\images\\Agehistogram.png"));
	           } catch (Exception e) {
	              e.printStackTrace();
	           }
	      }
	   }
}
	
