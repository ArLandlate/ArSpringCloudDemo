package org.ar.demo.springcloud.order.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScans({
        @ComponentScan("org.ar.demo.springcloud.core.configuration"),
        @ComponentScan("org.ar.demo.springcloud.order.controller"),
        @ComponentScan("org.ar.demo.springcloud.order.service")
})
public class BaseConfiguration {

    /**
     * @author ArLandlate
     * function: spring boot base config
     */

    @Bean           //ribbon服务发现
    @LoadBalanced   //ribbon开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
