package com.yucong.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bm.common.log.model.HttpRequestLog;
import com.yucong.log.constants.GlobalLog;
import com.yucong.log.dto.ListHttpRequestLogDTO;
import com.yucong.log.service.HttpRequestLogService;
import com.yucong.log.vo.common.CommonVO;
import com.yucong.log.vo.common.DataTableVO;



/**
 * 请求日志管理
 * 
 * @author 喻聪
 * @date   2018-09-13
 * 
 */
@RestController
@RequestMapping(value = "httpRequestLog")
public class HttpRequestLogController  {

	@Autowired
	private HttpRequestLogService httpRequestLogService;
	
	
	/**
	 * 分页查询请求日志
	 *  
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public CommonVO<DataTableVO<HttpRequestLog>> list(ListHttpRequestLogDTO dto) {
		GlobalLog.MY_LOGGER.info("分页查询请求日志:" + dto);
		return new CommonVO<DataTableVO<HttpRequestLog>>( httpRequestLogService.listAll(dto) );
	}
	
	
	

}
