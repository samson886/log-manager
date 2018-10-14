package com.yucong.log.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.java.common.log.constant.LogConstants;

@Configuration
public class Env {

	
	public static Boolean isProduct;
	
	/**发送请求日志队列的名称*/
	public static String requestLogQueueName;
	
	/**业务处理队列的名称*/
	public static String exceptionLogQueueName;

	@Value("${isProduct}")
	public void setProduct(Boolean isProduct){
		Env.isProduct = isProduct;
		if(isProduct) {
			Env.requestLogQueueName = LogConstants.Routing.REQUEST ;
			Env.exceptionLogQueueName = LogConstants.Routing.EXCEPTION ;
		} else {
			Env.requestLogQueueName =  LogConstants.Routing.REQUEST + LogConstants.Routing.TEST_QUEUE_SUFFIX;
			Env.exceptionLogQueueName =  LogConstants.Routing.EXCEPTION + LogConstants.Routing.TEST_QUEUE_SUFFIX;
		}
		
		
		
	}



}
