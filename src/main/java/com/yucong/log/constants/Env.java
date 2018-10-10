package com.yucong.log.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Env {
	/**版本编号*/
	public static String verCode;
	
	public static String envName;
	
	public static Boolean isProduct;
	/**是否验证签名*/
	public static Boolean isVerifySign;
	
	/**是否保存日志*/
	public static Boolean isSaveLog;
	
	/**是否验证登录*/
	public static Boolean isAuthLogin;
	
	/**是否发送日志到MQ*/
	public static Boolean isSendMQ;
	
	/**发送日志队列的名称*/
	public static String logQueueName;
	
	/**业务处理队列的名称*/
	public static String businessQueueName;

	@Value("${verCode}")
	public void setVerCode(String verCode){
		Env.verCode = verCode;
	}
	
	@Value("${envName}")
	public void setEnvName(String envName){
		Env.envName = envName;
	}
	
	@Value("${isProduct}")
	public void setProduct(Boolean isProduct){
		Env.isProduct = isProduct;
	}
	
	@Value("${isVerifySign}")
	public void setVerifySign(Boolean isVerifySign){
		Env.isVerifySign = isVerifySign;
	}
	
	@Value("${isSaveLog}")
	public void setSaveLog(Boolean isSaveLog){
		Env.isSaveLog = isSaveLog;
	}
	
	@Value("${isAuthLogin}")
	public void setAuthLogin(Boolean isAuthLogin){
		Env.isAuthLogin = isAuthLogin;
	}
	
	@Value("${isSendMQ}")
	public void setSendMQ(Boolean isSendMQ){
		Env.isSendMQ = isSendMQ;
	}
	
	@Value("${logQueueName}")
	public void setLogQueueName(String logQueueName){
		Env.logQueueName = logQueueName;
	}

	@Value("${businessQueueName}")
	public void setBusinessQueueName(String businessQueueName){
		Env.businessQueueName = businessQueueName;
	}

}
