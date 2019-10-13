package org.ar.demo.springcloud.ribbon.policy;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class IRuleConfig {

    /**
     * @author ArLandlate
     * useful: IRule config
     * 不同服务定义不同的负载均衡策略
     * notice: 这一配置文件必须放在spring组件扫描范围之外，否则spring会让所有服务都遵循这一策略
     */

    /**
     * retry 策略
     * 轮询+timeout熔断
     */
    @Configuration
    public static class RetryRuleConfig {
        @Bean
        public IRule iRule() {
            return new RetryRule();
        }
    }

    /**
     * random 策略
     * 随机匹配
     */
    @Configuration
    public static class RandomRuleConfig {
        @Bean
        public IRule iRule() {
            return new RandomRule();
        }
    }

}
