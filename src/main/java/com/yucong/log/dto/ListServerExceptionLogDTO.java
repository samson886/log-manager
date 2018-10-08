package com.yucong.log.dto;


import com.yucong.log.utils.FastJsonUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListServerExceptionLogDTO extends PageInfo {

	private String clientIP;
	private String requestUrl;
	private String method;
	private String userId;
	private String deviceType;
	
	
	public String toString() {
		return this.getClass().getSimpleName() + FastJsonUtil.toJson(this);
	}
}
