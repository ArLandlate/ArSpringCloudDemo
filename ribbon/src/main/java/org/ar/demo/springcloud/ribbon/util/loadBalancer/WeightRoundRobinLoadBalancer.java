package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.Map;

public class WeightRoundRobinLoadBalancer extends WeightLoadBalancer {

    /**
     * @author ArLandlate
     * function: weight round-robin load balancer
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * container declaration
     */
    private int position;

    /**
     * I don't want sb. on the outside get a balancer by this constructor
     * if he still has reference to the parameter list, it will be unsafe to alter list structure
     * @param container
     */
    protected WeightRoundRobinLoadBalancer(LoadBalancerComponent.HostContainer container){
        super(container);
    }

    public WeightRoundRobinLoadBalancer(Map<String, Integer> hostMap){
        super(hostMap);
    }

    /**
     * weight round-robin loading balance
     * 加权轮询负载均衡算法
     * @return random host
     */
    @Override
    public synchronized String get() {
        position++;
        return container.getByPoint(position-1).getHost();
    }

}
