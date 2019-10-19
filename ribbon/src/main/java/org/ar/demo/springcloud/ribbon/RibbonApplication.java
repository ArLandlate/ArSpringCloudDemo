package org.ar.demo.springcloud.ribbon;

import org.ar.demo.springcloud.ribbon.policy.IRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.PropertySource;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"org.ar.demo.springcloud.ribbon.configuration"})
@PropertySource(value= {"${config.path}/serviceInfo.properties"}, ignoreResourceNotFound=true, encoding="utf-8")
@RibbonClients({
		@RibbonClient(name = "${service.power.info.name}", configuration = IRuleConfig.RetryRuleConfig.class),
		@RibbonClient(name = "${service.order.info.name}", configuration = IRuleConfig.RandomRuleConfig.class)
})
public class RibbonApplication {

	/**
	 * @author ArLandlate
	 * function: spring boot 启动
	 */

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}

}
