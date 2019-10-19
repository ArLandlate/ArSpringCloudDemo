package org.ar.demo.springcloud.rabbitmq.sender.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan("org.ar.demo.springcloud.core.configuration"),
        @ComponentScan("org.ar.demo.springcloud.rabbitmq.sender.controller"),
        @ComponentScan("org.ar.demo.springcloud.rabbitmq.sender.util"),
        @ComponentScan("org.ar.demo.springcloud.rabbitmq.sender.service")
})
public class BaseConfiguration {

    /**
     * @author ArLandlate
     * function: spring boot base config
     *
     * note: there are two ways in spring boot to configure rabbitmq
     *  application configuration file, spring configuration bean
     *  This demo use spring configuration bean, and also have anotated codes in application.properties
     */

    /**
     * rabbitmq connection factory bean
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory("192.168.177.128", 5672);
//        factory.setHost("192.168.177.128");
//        factory.setPort(5672);
        factory.setUsername("sender");
        factory.setPassword("nevermind");
        factory.setVirtualHost("arrb00v");

        // if using publisher confirms
//        factory.setPublisherConfirms(true);
        return factory;
    }

    /**
     * rabbitmq template
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        /* note: this factory are require for only the way of java config
            if you adopt configuration files, spring will config this part for you automatically
        */
        RabbitTemplate template = new RabbitTemplate(factory);
        return template;
    }

    @Bean("defaultExchange")
    public TopicExchange defaultExchange(){
        return new TopicExchange(MQConstant.EXCHANGE_DEFAULT);
    }

    @Bean
    public Queue queue(){
        //name, is durable
        return new Queue(MQConstant.QUEUE_TEST, true);
    }

    @Bean
    public Binding binding(){
        //binging a queue
        //to: which exchange to binding; with: binding with routing key
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MQConstant.ROUTINGKEY_TEST);
    }

}
