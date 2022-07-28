//HelloController.java
//����� ���� ��Ʈ�ѷ� Ŭ����


package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//�� Spring �� Controller �������̽��� �����ϴ� ����� ����
//����� ���� ��Ʈ�ѷ� Ŭ������ �����Ѵ�.
public class HelloController implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//�׼� �ڵ�
		
		ModelAndView mav = new ModelAndView();
		
		
		//DispatcherServlet ���� 
		//<bean name="/hello.action" class="com.test.mvc.HelloController"></bean>
		//�� �������ش�
		
		/*
		- Controller
		�׼� ó���� ����ϴ� ��Ʈ�ѷ� Ŭ���� �ۼ�.
		Annotation �� ������� ������, Controller �������̽��� �����Ͽ� �ۼ�.

		- View
		��� ��¿� JSP ������ �ۼ�.

		- web.xml
		���� �ּҿ� DispatcherServlet Ŭ���� ���

		- dispatcher-servlet.xml
		�츮�� �ۼ��� Controller ��ü ���.
		URL ���� �ּ� ���.
		*/
		
		String message = "Hello, SpringMVC World~";
		
		mav.addObject("message", message);
		mav.setViewName("/WEB-INF/view/Hello.jsp");
		
		
		return mav;
	}
 
}












