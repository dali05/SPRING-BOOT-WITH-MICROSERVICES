package com.rest.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.rest.customerservice.filehandling.FileStorageProperties;

/**
 * This is used to enable the ConfigurationProperties feature, 
 * you need to add @EnableConfigurationProperties annotation to any configuration class.
 * 
 * @EnableConfigurationProperties({
	FileStorageProperties.class
	})
 * 
 * @author sarang_korde
 *
 */


@SpringBootApplication
//@EnableHystrix
//@EnableHystrixDashboard
//@EnableCircuitBreaker
@EnableDiscoveryClient
//@EnableEurekaClient
@EnableConfigurationProperties({FileStorageProperties.class})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
