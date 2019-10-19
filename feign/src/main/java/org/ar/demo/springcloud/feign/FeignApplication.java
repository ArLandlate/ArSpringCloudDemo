package org.ar.demo.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"org.ar.demo.springcloud.feign.configuration"})
public class FeignApplication {

	/**
	 * @author ArLandlate
	 * function: spring boot 启动
	 */

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}

}
