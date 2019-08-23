package com.rochatambelini.services.exceptions;

public class DateIntegrityExeption extends RuntimeException {
        	private static final long serialVersionUID = 1L;
        	
   public  DateIntegrityExeption(String msg){
	   super (msg);
   } 
   
   public  DateIntegrityExeption(String msg, Throwable cause){
	   super (msg,cause);
   }

}
