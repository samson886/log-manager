package com.yucong.log;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.common.log.constant.LogConstants;
import com.yucong.log.constants.Env;

@Configuration
@AutoConfigureAfter(Env.class)
public class RabbitConfig {

	@Bean
    DirectExchange exchange() {
        return new DirectExchange(LogConstants.Routing.EXCHANGE);
    }
	
	@Bean
    Queue requestLogQueue() {
		String queueName = Env.isProduct ?  LogConstants.Routing.REQUEST : LogConstants.Routing.REQUEST + "_test";
		return new Queue(queueName, false);
    }
	
	@Bean
    Queue exceptionLogQueue() {
		String queueName = Env.isProduct ?  LogConstants.Routing.EXCEPTION : LogConstants.Routing.EXCEPTION + "_test";
        return new Queue(queueName, false);
    }
	
	@Bean
    Queue abnormalLogQueue() {
		String queueName = Env.isProduct ?  LogConstants.Routing.ABNORMAL : LogConstants.Routing.ABNORMAL + "_test";
        return new Queue(queueName, false);
    }
	
	@Bean
    Queue smsLogQueue() {
		String queueName = Env.isProduct ?  LogConstants.Routing.SMS : LogConstants.Routing.SMS + "_test";
        return new Queue(queueName, false);
    }

	@Bean
    Binding bindingRequestLogQueue(Queue requestLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(requestLogQueue)
                             .to(exchange)
                             .with(requestLogQueue.getName());
    }

    @Bean
    Binding bindingExceptionLogQueue(Queue exceptionLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(exceptionLogQueue)
                             .to(exchange)
                             .with(exceptionLogQueue.getName());
    }
    
    @Bean
    Binding bindingAbnormalLogQueue(Queue abnormalLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(abnormalLogQueue)
                             .to(exchange)
                             .with(abnormalLogQueue.getName());
    }
    
    @Bean
    Binding bindingSmsLogQueue(Queue smsLogQueue, DirectExchange exchange) {
        return BindingBuilder.bind(smsLogQueue)
                             .to(exchange)
                             .with(smsLogQueue.getName());
    }
	
}
