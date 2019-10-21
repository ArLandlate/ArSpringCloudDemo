package org.ar.demo.springcloud.rabbitmq.sender.configuration;

public class MQConstant {

    /**
     * @author ArLandlate
     * function: rabbitmq constants
     */

    public static final String EXCHANGE_DEFAULT = "topicExchange";
    public static final String QUEUE_TEST = "testQueue";
    public static final String ROUTINGKEY_TEST = "test.message";

    public static final String RBMQ_HOSTNAME = "192.168.177.128";
    public static final int RBMQ_PORT = 5672;
    public static final String RBMQ_USERNAME = "sender";
    public static final String RBMQ_PASSWORD = "nevermind";
    public static final String RBMQ_VIRTUALHOST = "arrb00v";

}
