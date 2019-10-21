package org.ar.demo.springcloud.rabbitmq.sender.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SendConfirmCallBackToPrint implements RabbitTemplate.ConfirmCallback {

    /**
     * @author ArLandlate
     * function: handle send failure confirm call back (in process from server to exchange)
     */

    /**
     * confirm call back to print logs
     * @param correlationData: you can also set a data like business-id when you send your message
     * @param ack: just a flag
     * @param cause: the fail reason string
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("发送消息到交换机" + (ack?"成功":"失败"));
        System.out.println("data: \t\t" + correlationData);
        System.out.println("ack: \t\t" + ack);
        System.out.println("cause: \t\t" + cause);
    }

}
