package com.ufsm.portaldengue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PortaldengueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortaldengueApplication.class, args);
	}

}
