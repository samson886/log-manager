package com.yucong.log.dto;


import javax.validation.constraints.NotNull;

import com.yucong.log.utils.FastJsonUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListHttpRequestLogDTO extends PageInfo {

	private String clientIP;
	private String requestUrl;
	// 2全部，1成功，0异常，-1业务失败，-2登陆失效，-3签名失败
	@NotNull
	private Integer code;
	private String method;
	private String userId;
	private String deviceType;
	
	public String toString() {
		return this.getClass().getSimpleName() + FastJsonUtil.toJson(this);
	}
}