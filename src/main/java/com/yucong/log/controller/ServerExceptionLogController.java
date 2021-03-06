package com.yucong.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.common.log.model.ServerExceptionLog;
import com.yucong.log.constants.GlobalLog;
import com.yucong.log.dto.ListServerExceptionLogDTO;
import com.yucong.log.service.ServerExceptionLogService;
import com.yucong.log.vo.common.CommonVO;
import com.yucong.log.vo.common.DataTableVO;

/**
 * 服务异常日志管理
 * 
 * @author 喻聪
 * @date   2018-09-13
 * 
 */
@RestController
@RequestMapping(value = "serverExceptionLog")
public class ServerExceptionLogController  {

	@Autowired
	private ServerExceptionLogService serverExceptionLogService;
	
	/**
	 * 分页查询服务异常日志
	 *  
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public CommonVO<DataTableVO<ServerExceptionLog>> listHttpRequest(ListServerExceptionLogDTO dto) {
		GlobalLog.MY_LOGGER.info("分页查询服务异常日志:" + dto);
		return new CommonVO<DataTableVO<ServerExceptionLog>>( serverExceptionLogService.listAll(dto) );
	}
	

}
