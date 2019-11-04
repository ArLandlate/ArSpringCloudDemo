package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.*;

public abstract class SimpleLoadBalancer implements LoadBalancer {

    /**
     * @author ArLandlate
     * function: simple random load balancer
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * container declaration
     */
    protected LoadBalancerComponent.HostContainer container;

    /**
     * constructor declaration
     */
    protected SimpleLoadBalancer(LoadBalancerComponent.HostContainer container){
        this.container = container;
    }

    public SimpleLoadBalancer(String... hosts){
        this.container = new LoadBalancerComponent.HostContainer();

        synchronized (this){
            for (String host : hosts){
                container.put(host, 1);
            }
        }
    }

    /**
     * simple loading balance
     * 简单负载均衡算法
     * @return random host
     */
    @Override
    public abstract String get();

    public static void main(String[] args) {
        System.out.println(101%100);
    }

}
