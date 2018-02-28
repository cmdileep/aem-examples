package com.dil.nit.cls;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;

public class ConnectJCR {

 public static void main(String[] args) throws Exception {
  
  Repository repository = JcrUtils.getRepository("http://localhost:4504/crx/server");

  Session session = repository.login( new SimpleCredentials("admin", "admin".toCharArray()),"crx.default");
    
  Node n=session.getNode("/content/slingmodels/jcr:content");
  
  String date=n.getProperty("cq:lastModified").getString();
  System.out.println("JCR Date-->"+date);
  
  //If the date in milliseconds and then convert to ISO date.
  long millis=n.getProperty("cq:lastModified").getDate().getTimeInMillis();
  Instant instant = Instant.ofEpochMilli(millis);
  LocalDateTime isoDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
  System.out.println(isoDate);
  
  
  DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;
  LocalDateTime isoFormatDate = LocalDateTime.parse(date, format);
  
  System.out.println("Formatted ISO DATE : " + isoFormatDate);
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E HH:mm a");
  //REad the parameter used for formatting the date
  //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html?is-external=true

  System.out.println("Final Required Format : " + isoFormatDate.format(formatter));
 

   
   session.save(); 
   session.logout();
  }
 }

