package org.ar.demo.springcloud.ribbon.util.loadBalancer;

public interface LoadBalancer {

    /**
     * @author ArLandlate
     * function: load balancer interface
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * get loading balance result
     * @return host
     */
    public String get();

}
