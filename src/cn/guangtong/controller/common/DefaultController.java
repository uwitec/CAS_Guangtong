package cn.guangtong.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * shiro机制默认的拦截器
 * 
 * @author sutong
 *
 */
public class DefaultController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		 return new ModelAndView("error1");
	}

}
