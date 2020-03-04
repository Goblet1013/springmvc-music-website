package org.fkit.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fkit.domain.User;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

@Controller
public class UserController {
	
	@RequestMapping(value="/loginForm")
	public String loginForm(
//不需要加：	@PathVariable String formName,
			String request_locale,
			HttpServletResponse response,
			Model model,
			HttpServletRequest request
			){
		//基于HttpSission的国际化实现
//		System.out.println("request_locale = "+request_locale);
//		if(request_locale != null) {
//			//设置中文环境
//			if(request_locale.equals("zh_CN")) {
		//Locale对象中封装了许多国家的语言，第一个参数对应语言，第二个参数对应国家
//			Locale locale = new Locale("zh","CN");
//			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
//			}else if(request_locale.equals("en_US")) {
//				Locale locale = new Locale("en","US");
//				request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
//				
//			}else {
//				request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
//			}
//			
//		}
		//基于Cookie的国际化实现
		System.out.println("request_locale = "+request_locale);
		if(request_locale != null) {
			if(request_locale.equals("zh_CN")) {
				Locale locale = new Locale("zh","CN");
				(new CookieLocaleResolver()).setLocale (request,response,locale);
			}else if(request_locale.equals("en_US")) {
				Locale locale = new Locale("en","US");
				(new CookieLocaleResolver()).setLocale (request,response,locale);
			}else {
				(new CookieLocaleResolver()).setLocale (request,response,LocaleContextHolder.getLocale());
			}
		}
		User user = new User();
		model.addAttribute("user",user);
		return "loginForm";
	}
	@PostMapping(value = "/login")
	public String login(
			//参数标注了modelAttribute注解后，可以将前台的传值对象放到数据模型中
			@ModelAttribute User user,
			Model model,
			//HTTP请求头中所有的信息都封装在这个对象中
			HttpServletRequest request) {
		//如果登录名事fkit，密码是123456，则验证通过
		if(user.getLoginname()!=null && user.getLoginname().equals("fkit")
				&&user.getPassword()!=null && user.getPassword().equals("123456")) {
			//从后台代码获取国际化信息username
			RequestContext requestContext = new RequestContext(request);
			String username = requestContext.getMessage("username");
			//将获取的username信息设置到User对象中，并设置到Model当中
			user.setLoginname(username);
			model.addAttribute("user",user);
			return "success";
		}
		return "error";
		
	}
	
	
	
	
	
}
