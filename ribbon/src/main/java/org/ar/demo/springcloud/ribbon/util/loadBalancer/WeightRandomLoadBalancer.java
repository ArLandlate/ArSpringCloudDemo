package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.*;

public class WeightRandomLoadBalancer extends WeightLoadBalancer {

    /**
     * @author ArLandlate
     * function: weight random load balancer
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * container declaration
     */

    /**
     * constructor declaration
     */

    /**
     * I don't want sb. on the outside get a balancer by this constructor
     * if he still has reference to the parameter list, it will be unsafe to alter list structure
     * @param container
     */
    protected WeightRandomLoadBalancer(LoadBalancerComponent.HostContainer container){
        super(container);
    }

    public WeightRandomLoadBalancer(Map<String, Integer> hostMap){
        super(hostMap);
    }

    /**
     * weight random loading balance
     * 加权随机负载均衡算法
     * @return random host
     */
    @Override
    public synchronized String get() {
        int posCount = (int) (container.getTotalWeight() * Math.random());
        return container.getByPoint(posCount).getHost();
    }

}
