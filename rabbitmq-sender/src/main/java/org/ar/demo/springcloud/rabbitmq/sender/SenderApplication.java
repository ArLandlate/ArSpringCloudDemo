package org.ar.demo.springcloud.rabbitmq.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"org.ar.demo.springcloud.rabbitmq.sender.configuration"})
public class SenderApplication {

	/**
	 * @author ArLandlate
	 * function: spring boot 启动
	 *
	 * note: there are two ways in spring boot to configure rabbitmq
	 *  application configuration file, spring configuration bean
	 *  This demo use spring configuration bean, and also have anotated codes in application.properties
	 */

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

}
