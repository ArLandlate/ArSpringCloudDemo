package org.ar.demo.springcloud.ribbon.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScans({
        @ComponentScan("org.ar.demo.springcloud.core.configuration"),
        @ComponentScan("org.ar.demo.springcloud.ribbon.controller"),
        @ComponentScan("org.ar.demo.springcloud.ribbon.service")
})
public class BaseConfiguration {

    /**
     * @author ArLandlate
     * function: spring boot base config
     */

    /**
     * ribbon组件
     * loadBalanced注解用于开启负载均衡策略（默认轮询）
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * ribbon负载均衡策略配置
     * --ribbon load balance strategies configuration
     * ribbon提供了七种策略，可以通过实现该接口自定义负载均衡策略
     * --ribbon provide seven strategies, you can also customize the policy by implement this interface
     */
//    @Bean
//    public IRule iRule(){
//        return new RetryRule();
//    }

}
