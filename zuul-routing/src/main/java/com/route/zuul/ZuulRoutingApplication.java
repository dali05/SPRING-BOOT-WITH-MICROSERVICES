package com.route.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.route.zuul.filter.ErrorFilter;
import com.route.zuul.filter.PostFilter;
import com.route.zuul.filter.PreFilter;
import com.route.zuul.filter.RouteFilter;

/**
 * Create ZIP file using cmd
 * C:\Users\sarang_korde>powershell Compress-Archive -Path D:\SKWorkspace\springrest -DestinationPath D:\SKWorkspace\springrest.zip
 * 
 * C:\Users\sarang_korde>powershell Compress-Archive -Path 'D:\SKWorkspace\SPRING BOOT WITH MICROSERVICES' -DestinationPath 'D:\SKWorkspace\SPRING_BOOT_WITH_MICROSERVICES.zip'
 * 
 * @author sarang_korde
 *
 */

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaServer
//@EnableDiscoveryClient
//@EnableEurekaClient
public class ZuulRoutingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulRoutingApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
