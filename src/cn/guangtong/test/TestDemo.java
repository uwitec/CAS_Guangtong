package cn.guangtong.test;

import org.springframework.beans.factory.annotation.Autowired;

import cn.guangtong.service.cms.AdminService;


public class TestDemo {
	
	@Autowired
	private AdminService adminService;
	
	public void testD(){
		TestDemo2 td = new TestDemo2();
		
		Class A = td.getClass();
	}
	
}
