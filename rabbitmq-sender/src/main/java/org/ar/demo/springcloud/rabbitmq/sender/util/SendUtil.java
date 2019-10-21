package org.ar.demo.springcloud.rabbitmq.sender.util;

import org.ar.demo.springcloud.rabbitmq.sender.configuration.MQConstant;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendUtil {

    /**
     * @author ArLandlate
     * function: rabbitmq send message util
     */

    /**
     * container declaration
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate02;

    /**
     * simple sender demo
     * @param message
     */
    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_DEFAULT, MQConstant.ROUTINGKEY_TEST, message);
    }

    /**
     * sender demo using publisher confirms
     * @param message
     */
    public void sendMessageOpenPublisherConfirms(String message, String routingKey, String id){
        rabbitTemplate02.convertAndSend(MQConstant.EXCHANGE_DEFAULT, routingKey, message, new CorrelationData(id));
    }

}
