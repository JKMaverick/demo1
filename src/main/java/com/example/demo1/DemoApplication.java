package com.example.demo1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	// TODO zgrac pozostale litery z githuba
	public static void main(String[] args) {
		XmlParser xmlParser = new XmlParser();
		xmlParser.start();
		System.out.println();
//		SpringApplication.run(DemoApplication.class, args);
	}


}
