package org.ar.demo.springcloud.feign.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value= {"${config.path}/serviceInfo.properties"}, ignoreResourceNotFound=true, encoding="utf-8")
public class ServiceInfo {

    /**
     * @author ArLandlate
     * function: 配置层：服务信息常量
     */

    public ServiceInfo(
            @Value("${service.power.info.name}") String POWER_NAME,
            @Value("${service.power.info.name}") String RIBBON_NAME,
            @Value("${service.power.info.name}") String FEIGN_NAME,
            @Value("${service.order.info.name}") String ORDER_NAME
    ){

        this.POWER_NAME = POWER_NAME;
        this.RIBBON_NAME = RIBBON_NAME;
        this.FEIGN_NAME = FEIGN_NAME;
        this.ORDER_NAME = ORDER_NAME;
    }

    public final String POWER_NAME;
    public final String RIBBON_NAME;
    public final String FEIGN_NAME;
    public final String ORDER_NAME;

}
