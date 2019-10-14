package org.ar.demo.springcloud.feign.client;

import org.ar.demo.springcloud.core.enums.ResultContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@PropertySource(value= {"${config.path}/basicConstant.properties"}, ignoreResourceNotFound=true, encoding="utf-8")
@FeignClient("${bcst.service.order.name}")
public interface OrderTestClient {

    /**
     * @author ArLandlate
     * useful: order service test controller invacation interface
     */

    @GetMapping("/order/test/doGetOrder?message={message}")
    public ResultContainer doGetOrder(@PathVariable("message") String message);

}
