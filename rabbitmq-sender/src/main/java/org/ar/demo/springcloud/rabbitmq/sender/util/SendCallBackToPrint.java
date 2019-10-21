package org.ar.demo.springcloud.rabbitmq.sender.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SendCallBackToPrint implements RabbitTemplate.ReturnCallback {

    /**
     * @author ArLandlate
     * function: handle send failure return call back (in process from exchange to message queue)
     */

    /**
     * return call back to print logs
     * @param message: message
     * @param replyCode: reply code
     * @param replyText: reply text
     * @param exchange: exchange
     * @param routingKey: routing key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("路由消息队列失败");
        System.out.println("reply code: \t\t" + replyCode);
        System.out.println("reply text: \t\t" + replyText);
        System.out.println("exchange: \t\t\t" + exchange);
        System.out.println("routing key: \t\t" + routingKey);
    }

}
