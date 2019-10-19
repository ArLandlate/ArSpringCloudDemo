package org.ar.demo.springcloud.rabbitmq.listener.util;

import org.ar.demo.springcloud.rabbitmq.listener.configuration.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenUtil {

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
     * simple listener demo
     * @param message
     */
    @RabbitListener(queues="testQueue")
    public void listenMessage(String message){
        MessageCache.messageList.addFirst(message);
    }

}
