package com.yucong.log.consume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.common.log.model.BusinessAbnormalLog;
import com.java.common.log.model.HttpRequestLog;
import com.java.common.log.model.ServerExceptionLog;
import com.java.common.log.model.SmsSendLog;
import com.yucong.log.constants.GlobalLog;
import com.yucong.log.service.BusinessAbnormalLogService;
import com.yucong.log.service.HttpRequestLogService;
import com.yucong.log.service.ServerExceptionLogService;
import com.yucong.log.service.SmsSendLogService;
import com.yucong.log.utils.FastJsonUtil;
	
@Component
public class DirectReceiver {

	@Autowired
	private HttpRequestLogService httpRequestLogService;
	@Autowired
	private ServerExceptionLogService serverExceptionLogService;
	@Autowired
	private BusinessAbnormalLogService businessAbnormalLogService;
	@Autowired
	private SmsSendLogService smsSendLogService;
	
    @RabbitListener(queues = "request")
    public void processRequestLog(HttpRequestLog log) {
    	GlobalLog.MY_LOGGER.info("消费request请求消息 : " + FastJsonUtil.toJson(log));
    	httpRequestLogService.add(log);
    }
    
    @RabbitListener(queues = "exception")
    public void processExceptionLog(ServerExceptionLog log) {
        GlobalLog.MY_LOGGER.info("消费exception服务异常消息 : " + FastJsonUtil.toJson(log));
        serverExceptionLogService.add(log);
    }
    
    @RabbitListener(queues = "abnormal")
    public void processAbnormal(BusinessAbnormalLog log) {
        GlobalLog.MY_LOGGER.info("消费abnormal业务异常消息 : " + FastJsonUtil.toJson(log));
        businessAbnormalLogService.add(log);
    }
    
    @RabbitListener(queues = "sms")
    public void processAbnormal(SmsSendLog log) {
        GlobalLog.MY_LOGGER.info("消费sms短信发送记录消息 : " + FastJsonUtil.toJson(log));
        smsSendLogService.add(log);
    }
    
    /*@RabbitListener(queues = "callback")
    public void processCallbackLog(String msg) {
        System.out.println("exception  : " + msg);
    }*/


}

