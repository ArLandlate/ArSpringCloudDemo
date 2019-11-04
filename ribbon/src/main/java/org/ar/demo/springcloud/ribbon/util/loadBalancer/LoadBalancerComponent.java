package org.ar.demo.springcloud.ribbon.util.loadBalancer;

import java.util.*;

public class LoadBalancerComponent {

    /**
     * @author ArLandlate
     * function: load balancer component
     * 手写实现几种基本的软件负载均衡算法
     *
//     * note: there is no automatically detect duplication in the component, hold it by yourself if you want.
//     * note: 目前没有做排重处理，如果有需求，自己掌握
     * note: 为在加权类中正确构造权重偏移树，已在构造方法中做了排重处理，所以构造过程中会失去传入顺序
     */

    /**
     * inner class
     */
    protected static class HostContainer{
        private List<HostEntity> hosts;
        // index map<host, hosts.index>
        private Map<String, Integer> indexMap;
        private int totalWeight;
        private boolean isSameWeight = true;
        private int sameWeight;

        // note: map's key is not weight but weight's offset
        protected TreeMap<Integer, HostEntity> hostWeightOffsetTree;
        // when loadBalancerComponent alter list, this flag must be opening to note synchronize the weight map when it's used
        private boolean needResetTree = true;
        private int version;

        protected synchronized boolean put(String host, int weight){
            if (hosts==null){
                hosts = new ArrayList<>();
                indexMap = new HashMap<>();
                sameWeight = weight;
                totalWeight = weight;
                indexMap.put(host, 0);
                needResetTree = true;
                version++;
                return hosts.add(new HostEntity(host, weight));
            }

            if (isSameWeight && sameWeight!=weight){
                isSameWeight = false;
            }

            needResetTree = true;
            version++;

            Integer index = indexMap.get(host);
            if (index!=null){
                totalWeight += weight - hosts.get(index).weight;
                hosts.get(index).weight = weight;
                return true;
            }else {
                if (hosts.add(new HostEntity(host, weight))){
                    indexMap.put(host, hosts.size()-1);
                    totalWeight += weight;
                    return true;
                }
                return false;
            }
        }

        protected HostEntity get(int i){
            return hosts.get(i);
        }

        protected List<HostEntity> getHosts(){
            return hosts;
        }

        protected int getVersion(){
            return version;
        }

        protected int getTotalWeight(){
            return totalWeight;
        }

        protected int size(){
            return indexMap.size();
        }

        protected boolean isSameWeight(){
            return isSameWeight;
        }

        protected boolean needResetTree(){
            return needResetTree;
        }

        protected HostEntity getByPoint(int point){
            // reset tree
            if (needResetTree){
                hostWeightOffsetTree = new TreeMap<>();
                int ofs = 0;
                for (HostEntity entity : hosts){
                    hostWeightOffsetTree.put(ofs, entity);
                    ofs += entity.weight;
                }
            }

            int offset = point % totalWeight;
            if (offset==0){
                return (hostWeightOffsetTree==null||hostWeightOffsetTree.size()==0)?null:hostWeightOffsetTree.lastEntry().getValue();
            }

            SortedMap<Integer, HostEntity> headMap = hostWeightOffsetTree.headMap(offset);
            return headMap.get(headMap.lastKey());
        }
    }

    protected static class HostEntity{
        private String host;
        private int weight;
        protected HostEntity(String host, int weight){
            this.host = host;
            this.weight = weight;
        }
        protected String getHost(){
            return host;
        }
        protected int getWeight(){
            return weight;
        }
        protected void setHost(String host){
            this.host = host;
        }
        protected void setWeight(int weight){
            this.weight = weight;
        }
        @Override
        public boolean equals(Object o){
            if (o instanceof HostEntity){
                HostEntity he = (HostEntity) o;
                return (this.host.equals(he.host));
            }
            return false;
        }
    }

    /**
     * container declaration
     */
    private SimpleRandomLoadBalancer srlBalancer;
    private WeightRandomLoadBalancer wrlBalancer;
    private SimpleRoundRobinLoadBalancer srrlBalancer;
    private WeightRoundRobinLoadBalancer wrrlBalancer;
    private SmoothWeightRoundRobinLoadBalancer swrrlBalancer;

    private HostContainer container;

    /**
     * constructor declaration
     */
    public LoadBalancerComponent(String... hosts){
        this.container = new HostContainer();

        synchronized (this){
            for (String host : hosts){
                container.put(host, 1);
            }
        }
    }

//    private synchronized void initWeights(int value, int size){
//        if (weights==null){
//            weights = new ArrayList<>();
//        }else {
//            weights.clear();
//        }
//        for (int i = 0; i < size; i++) {
//            weights.add(value);
//        }
//        sameWeight = value;
//        isSameWeight = true;
//    }

    /**
     * list alter operation
     */
    // check if weights is same
    public boolean isSameWeight(){
        return container.isSameWeight;
    }

//    public synchronized boolean setWeightList(Integer... weights){
//        if (hosts==null || hosts.size()!=weights.length){
//            return false;
//        }
//        boolean isSameWeight = true;
//        if (weights.length!=0){
//            List<Integer> weightList = new ArrayList<>();
//            int sameWeight = weights[0];
//            for (int i = 0; i < weights.length; i++){
//                int weight = weights[i];
//                if (isSameWeight && weight!=sameWeight){
//                    isSameWeight = false;
//                }
//                weightList.add(weight);
//            }
//            this.weights = weightList;
//            this.isSameWeight = isSameWeight;
//        }
//        return true;
//    }

    public boolean putHost(String host, int weight){
        return container.put(host, weight);
    }

    public boolean addHost(String host){
        return container.put(host, 1);
    }

    /**
     * simple random loading balance
     * 简单随机负载均衡算法
     * @return random host
     */
    public String simpleRandomLoadingBalance(){
        // check not null
        if (srlBalancer == null){
            srlBalancer = new SimpleRandomLoadBalancer(container);
        }
        return srlBalancer.get();
    }

    /**
     * weight random loading balance
     * 加权随机负载均衡算法
     * @return random host
     */
    public String weightRandomLoadingBalance(){
        if (isSameWeight()){
            return simpleRandomLoadingBalance();
        }
        // check not null
        if (wrlBalancer == null){
            wrlBalancer = new WeightRandomLoadBalancer(container);
        }
        return wrlBalancer.get();
    }

    /**
     * simple round-robin loading balance
     * 简单轮询负载均衡算法
     * @return round-robin host
     */
    public String simpleRoundRobinLoadingBalance(){
        // check not null
        if (srrlBalancer == null){
            srrlBalancer = new SimpleRoundRobinLoadBalancer(container);
        }
        return srrlBalancer.get();
    }

    /**
     * weight round-robin loading balance
     * 加权轮询负载均衡算法
     * @return round-robin host
     */
    public String weightRoundRobinLoadingBalance(){
        if (isSameWeight()){
            return simpleRoundRobinLoadingBalance();
        }
        // check not null
        if (wrrlBalancer == null){
            wrrlBalancer = new WeightRoundRobinLoadBalancer(container);
        }
        return wrrlBalancer.get();
    }

    /**
     * smooth weight round-robin loading balance
     * 平滑加权轮询负载均衡算法
     * @return round-robin host
     */
    public String smoothWeightRoundRobinLoadingBalance(){
        if (isSameWeight()){
            return simpleRoundRobinLoadingBalance();
        }
        // check not null
        if (swrrlBalancer == null){
            swrrlBalancer = new SmoothWeightRoundRobinLoadBalancer(container);
        }
        return swrrlBalancer.get();
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        // ----------init
        LoadBalancerComponent component = new LoadBalancerComponent();
        component.putHost("192.168.177.128", 0);
        component.putHost("192.168.177.132", 1);
        component.putHost("192.168.177.131", 2);
        component.putHost("192.168.177.133", 3);
        component.putHost("192.168.177.134", 4);
        component.putHost("192.168.177.135", 5);
        component.putHost("192.168.177.136", 6);
        component.putHost("192.168.177.137", 7);
        component.putHost("192.168.177.138", 8);
        component.putHost("192.168.177.139", 9);
        // ----------test simple random loading balance
//        for (int i = 0; i < 5; i++) {
//            System.out.println(component.simpleRandomLoadingBalance());
//        }

        // ----------test weight random loading balance
//        for (int i = 0; i < 45; i++) {
//            System.out.println(component.weightRandomLoadingBalance());
//        }

        // ----------test simple round-robin loading balance
//        for (int i = 0; i < 20; i++) {
//            System.out.println(component.simpleRoundRobinLoadingBalance());
//        }

        // ----------test weight round-robin loading balance
//        for (int i = 0; i < 20; i++) {
//            System.out.println(component.weightRoundRobinLoadingBalance());
//        }

        // ----------test smooth weight round-robin loading balance
        for (int i = 0; i < 50; i++) {
            System.out.println(component.smoothWeightRoundRobinLoadingBalance());
        }
    }

}
