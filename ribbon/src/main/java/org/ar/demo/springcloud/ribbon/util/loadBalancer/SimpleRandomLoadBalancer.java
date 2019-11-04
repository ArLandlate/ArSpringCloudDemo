package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.*;

public class SimpleRandomLoadBalancer extends SimpleLoadBalancer {

    /**
     * @author ArLandlate
     * function: simple random load balancer
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * container declaration
     */

    /**
     * constructor declaration
     */
    protected SimpleRandomLoadBalancer(LoadBalancerComponent.HostContainer container){
        super(container);
    }

    public SimpleRandomLoadBalancer(String... hosts){
        super(hosts);
    }

    /**
     * simple random loading balance
     * 简单随机负载均衡算法
     * @return random host
     */
    @Override
    public synchronized String get() {
        int i = (int) (container.size() * Math.random());
        return container.get(i).getHost();
    }

}
