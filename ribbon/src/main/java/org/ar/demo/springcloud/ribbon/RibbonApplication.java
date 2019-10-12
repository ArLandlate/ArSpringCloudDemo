package org.ar.demo.springcloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RibbonApplication {

	/**
	 * @author ArLandlate
	 * useful: spring boot 启动
	 */

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}

}
