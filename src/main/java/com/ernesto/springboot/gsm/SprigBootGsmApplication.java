package com.ernesto.springboot.gsm;

import java.lang.reflect.Method;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprigBootGsmApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SprigBootGsmApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SprigBootGsmApplication.class, args);
	}

	//Calendar
	@Override
	public void run(String... args) throws Exception {
		//@SuppressWarnings("rawtypes")
		Class<?> c = Class.forName("org.slf4j.Logger");
		//System.out.println("Class: getName()" + c.getName());
		//System.out.println("Class: toString()" + c.toString());
		
		//for (Method method : Calendar.class.getMethods() ) {
		for (Method method : c.getMethods() ) {			
			System.out.println("Class: getMethods() Class: " + c.getName() + " Methods: " +  method.getName() + " Args? " + method.isVarArgs()) ;
		}
		
		Method m = c.getMethod("info", String.class);
		
		Object o = m.invoke(logger, "HOLA MUNDO");

		//System.out.println(o);
		
		logger.info("Inet Adress localHost: " + InetAddress.getLocalHost().toString());
		logger.info("Inet Adress loopbackadress: " + InetAddress.getLoopbackAddress().toString());
		logger.info("Inet Adress: getByname. " + InetAddress.getByName("www.google.com"));
		
	}

}
