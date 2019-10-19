package org.ar.demo.springcloud.rabbitmq.sender.util;

import org.ar.demo.springcloud.rabbitmq.sender.configuration.MQConstant;
import org.springframework.amqp.core.Exchange;
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
    private RabbitTemplate template;

    /**
     * simple sender demo
     * @param message
     */
    public void sendMessage(String message){
        template.convertAndSend(MQConstant.EXCHANGE_DEFAULT, MQConstant.ROUTINGKEY_TEST, message);
    }

}
