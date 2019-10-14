package org.ar.demo.springcloud.feign.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScans({
        @ComponentScan("org.ar.demo.springcloud.core.configuration"),
        @ComponentScan("org.ar.demo.springcloud.feign.controller"),
        @ComponentScan("org.ar.demo.springcloud.feign.client"),
        @ComponentScan("org.ar.demo.springcloud.feign.service")
})
public class BaseConfiguration {

    /**
     * @author ArLandlate
     * useful: spring boot base config
     */

    @Bean           //ribbon服务发现
    @LoadBalanced   //ribbon开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
