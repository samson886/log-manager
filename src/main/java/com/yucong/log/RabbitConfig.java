package com.yucong.log;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yucong.log.constants.Env;

@Configuration
@AutoConfigureAfter(Env.class)
public class RabbitConfig {

	public static final String EXCHANGE = "log-exchange";
	public static final String ROUTING_KEY_REQUEST = "request";
	public static final String ROUTING_KEY_EXCEPTION = "exception";
	public static final String ROUTING_KEY_ABNORMAL = "abnormal";
	public static final String ROUTING_KEY_CALLBACK = "callback";
	public static final String ROUTING_KEY_SMS = "sms";

	@Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }
	
	@Bean
    Queue requestLogQueue() {
		 return new Queue(ROUTING_KEY_REQUEST, false);
    }
	
	@Bean
    Queue exceptionLogQueue() {
        return new Queue(ROUTING_KEY_EXCEPTION, false);
    }
	
	@Bean
    Queue abnormalLogQueue() {
        return new Queue(ROUTING_KEY_ABNORMAL, false);
    }
	
	@Bean
    Queue smsLogQueue() {
        return new Queue(ROUTING_KEY_SMS, false);
    }

	@Bean
    Binding bindingRequestLogQueue(Queue requestLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(requestLogQueue)
                             .to(exchange)
                             .with(ROUTING_KEY_REQUEST);
    }

    @Bean
    Binding bindingExceptionLogQueue(Queue exceptionLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(exceptionLogQueue)
                             .to(exchange)
                             .with(ROUTING_KEY_EXCEPTION);
    }
    
    @Bean
    Binding bindingAbnormalLogQueue(Queue abnormalLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(abnormalLogQueue)
                             .to(exchange)
                             .with(ROUTING_KEY_ABNORMAL);
    }
    
    @Bean
    Binding bindingSmsLogQueue(Queue smsLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(smsLogQueue)
                             .to(exchange)
                             .with(ROUTING_KEY_SMS);
    }
	
}
