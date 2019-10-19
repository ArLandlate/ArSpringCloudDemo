package org.ar.demo.springcloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrderApplication {

	/**
	 * @author ArLandlate
	 * function: spring boot 启动
	 */

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
