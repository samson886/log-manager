package com.yucong.log.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yucong.log.vo.MenuVO;
import com.yucong.log.vo.common.CommonVO;

@RestController
@RequestMapping(value="menu")
public class MenuController {

	/**
	 * 获取我的所有菜单
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public CommonVO<List<MenuVO>> listMyMenu() {

		List<MenuVO> menus =  new ArrayList<>();
		
		MenuVO appMemuVO = initAppMemo();
		menus.add(appMemuVO);
		
		MenuVO smsMenuVO = initSmsMemoVO();
		menus.add(smsMenuVO);
		
		MenuVO taskVO  = initTaskVO();
		menus.add(taskVO);
		
		CommonVO<List<MenuVO>> commonVO = new CommonVO<>();
		commonVO.setData(menus);
		return commonVO;
	} 
	
	/*初始化APP接口系统菜单  id：1-4*/
	private MenuVO initAppMemo() {
		MenuVO parent = new MenuVO();
		parent.setId(1);
		parent.setMenuName("系统运行日志");
		parent.setParentId(0);
		parent.setIconCls("database");
		
		List<MenuVO> subs = new ArrayList<>();
		MenuVO sub1 = new MenuVO();
		sub1.setId(2);
		sub1.setMenuName("请求接口日志");
		sub1.setMenuPath("module/api/http_request_log.html");
		sub1.setMenuSort(1);
		sub1.setParentId(1);
		subs.add(sub1);
		
		MenuVO sub2 = new MenuVO();
		sub2.setId(3);
		sub2.setMenuName("服务异常日志");
		sub2.setMenuPath("module/api/server_exception_log.html");
		sub2.setMenuSort(2);
		sub2.setParentId(1);
		subs.add(sub2);
		
		
		MenuVO sub3 = new MenuVO();
		sub3.setId(4);
		sub3.setMenuName("业务异常日志");
		sub3.setMenuPath("module/api/business_abnormal_log.html");
		sub3.setMenuSort(3);
		sub3.setParentId(1);
		subs.add(sub3);
		parent.setChildren(subs);
		return parent;
	}
	
	/*初始化APP接口系统菜单  id：5-6*/
	private MenuVO initSmsMemoVO() {
		MenuVO parent = new MenuVO();
		parent.setId(5);
		parent.setMenuName("短信通知");
		parent.setParentId(0);
		parent.setIconCls("database");
		
		List<MenuVO> subs = new ArrayList<>();
		MenuVO sub1 = new MenuVO();
		sub1.setId(6);
		sub1.setMenuName("短信发送记录");
		sub1.setMenuPath("module/sms/sms_send.html");
		sub1.setMenuSort(1);
		sub1.setParentId(1);
		subs.add(sub1);
		
		parent.setChildren(subs);
		return parent;
	}
	
	/*初始化APP接口系统菜单  id：7-8*/
	private MenuVO initTaskVO() {
		MenuVO parent = new MenuVO();
		parent.setId(7);
		parent.setMenuName("定时任务");
		parent.setParentId(0);
		parent.setIconCls("database");
		
		List<MenuVO> subs = new ArrayList<>();
		MenuVO sub1 = new MenuVO();
		sub1.setId(8);
		sub1.setMenuName("任务管理");
		sub1.setMenuPath("module/task/task.html");
		sub1.setMenuSort(1);
		sub1.setParentId(1);
		subs.add(sub1);
		
		parent.setChildren(subs);
		return parent;
	}
	
}
