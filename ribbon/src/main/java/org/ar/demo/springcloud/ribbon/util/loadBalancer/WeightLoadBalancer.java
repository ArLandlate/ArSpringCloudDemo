package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.*;

public abstract class WeightLoadBalancer implements LoadBalancer {

    /**
     * @author ArLandlate
     * function: weight load balancer super class
     * 手写实现几种基本的软件负载均衡算法
     */

    /**
     * container declaration
     */
    protected LoadBalancerComponent.HostContainer container;

    /**
     * constructor declaration
     */
//    protected synchronized void init(List<String> hostList, List<Integer> weightList){
//        // initial lists
//        this.hostList = hostList;
//        this.weightList = weightList;
//
//        // check parameters
//        if (hostList==null || weightList==null || hostList.size()!=weightList.size()){
//            throw new ExceptionInInitializerError("initializer exception, please check parameters");
//        }
//
//        // accumulate total weight and construct host weight offset tree
//        for (int i = 0; i < weightList.size(); i++) {
//            int weight = weightList.get(i);
//            String host = hostList.get(i);
//            hostWeightOffsetTree.put(host, totalWeight);
//            totalWeight += weight;
//        }
//    }

    /**
     * I don't want sb. on the outside get a balancer by this constructor
     * if he still has reference to the parameter list, it will be unsafe to alter list structure
     * @param container
     */
    protected WeightLoadBalancer(LoadBalancerComponent.HostContainer container){
        this.container = container;
    }

    protected WeightLoadBalancer(Map<String, Integer> hostMap){
        container = new LoadBalancerComponent.HostContainer();

        // check parameters
        if (hostMap==null){
            hostMap = new HashMap<>();
        }

        // init variables
        for (String host : hostMap.keySet()){
            container.put(host, hostMap.get(host));
        }
    }

    /**
     * weight loading balance
     * @return random host
     */
    @Override
    public abstract String get();

}
