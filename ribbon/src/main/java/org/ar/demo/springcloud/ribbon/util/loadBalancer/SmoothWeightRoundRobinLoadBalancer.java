package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmoothWeightRoundRobinLoadBalancer extends WeightLoadBalancer {

    /**
     * @author ArLandlate
     * function: smooth weight round-robin load balancer
     * 手写实现几种基本的软件负载均衡算法
     *
     * 平滑加权轮询
     * 思路: [A, B, C]设立一个动态的权重数组currentWeights，初始值都为0，例如[0, 0, 0]，每次选择时做以下操作：
     *  1、让每个节点的currentWeight + weight，如[0+5, 0+3, 0+1]
     *  2、选择currentWeight数值最大的节点返回回去，如A
     *  3、该节点的currentWeight -= totalWeight，如[5-9, 3, 1]
     *  重复以上步骤
     *
     * 理解: 将所有节点看作运动员，在一条长度为totalWeight的跑道上奔跑，weight为他们的速度，
     *  先到终点的拿到当前任务，然后退回原点重新起跑，依此实现负载的平滑
     */

    /**
     * inner class
     */
    private class HostCurrentWeightComparator implements Comparable<HostCurrentWeightComparator>{
        private LoadBalancerComponent.HostEntity entity;
        private int currentWeight;

        private HostCurrentWeightComparator(LoadBalancerComponent.HostEntity entity, int currentWeight){
            this.entity = entity;
            this.currentWeight = currentWeight;
        }

        private HostCurrentWeightComparator(LoadBalancerComponent.HostEntity entity){
            this(entity, 0);
        }

        private void addCurrentWeight(int val){
            currentWeight += val;
        }

        private HostCurrentWeightComparator addCurrentWeightAnd(int val){
            currentWeight += val;
            return this;
        }

        private void addSelfWeight(){
            addCurrentWeight(entity.getWeight());
        }

        @Override
        public int compareTo(HostCurrentWeightComparator o) {
            return o.currentWeight - this.currentWeight;
        }
    }

    /**
     * container declaration
     */
    private List<HostCurrentWeightComparator> comparators;
    private int comparatorsVersion = -1;

    /**
     * I don't want sb. on the outside get a balancer by this constructor
     * if he still has reference to the parameter list, it will be unsafe to alter list structure
     * @param container
     */
    protected SmoothWeightRoundRobinLoadBalancer(LoadBalancerComponent.HostContainer container){
        super(container);
    }

    public SmoothWeightRoundRobinLoadBalancer(Map<String, Integer> hostMap){
        super(hostMap);
    }

    /**
     * weight round-robin loading balance
     * 平滑加权轮询负载均衡算法
     * @return random host
     */
    @Override
    public synchronized String get() {
        // if need reset
        if (container.getVersion()!=comparatorsVersion){
            comparators = new ArrayList<>();
            for (LoadBalancerComponent.HostEntity entity : container.getHosts()){
                comparators.add(new HostCurrentWeightComparator(entity));
            }
            comparatorsVersion = container.getVersion();
        }

        if (comparators.size()==0){
            return null;
        }

        // first step
        for (HostCurrentWeightComparator comparator : comparators){
            comparator.addSelfWeight();
        }

        // second step & third step
        comparators.sort(HostCurrentWeightComparator::compareTo);
        return comparators.get(0).addCurrentWeightAnd(-container.getTotalWeight()).entity.getHost();
    }

}
