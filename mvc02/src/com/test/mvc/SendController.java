//SendController.java
//����� ���� ��Ʈ�ѷ� Ŭ����


package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//�� Spring �� Controller �������̽��� �����ϴ� ����� ����
//����� ���� ��Ʈ�ѷ� Ŭ������ �����Ѵ�.
public class SendController implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//�׼� �ڵ�
		
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("/WEB-INF/view/Send.jsp");
		
		//DispatcherServlet
		
		return mav;
	}
 
}












