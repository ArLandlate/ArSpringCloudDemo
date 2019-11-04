package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.List;

public class SimpleRoundRobinLoadBalancer extends SimpleLoadBalancer {

    /**
     * @author ArLandlate
     * function: simple round-robin load balancer
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * container declaration
     */
    private int point;

    /**
     * constructor declaration
     */
    protected SimpleRoundRobinLoadBalancer(LoadBalancerComponent.HostContainer container){
        super(container);
    }

    public SimpleRoundRobinLoadBalancer(String... hosts){
        super(hosts);
    }

    /**
     * simple round-robin loading balance
     * 简单轮询负载均衡算法
     * @return random host
     */
    @Override
    public synchronized String get() {
        if (point>=container.size()){
            point = 0;
        }
        String ret = container.get(point).getHost();
        point++;
        return ret;
    }

}
