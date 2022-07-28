//HelloController.java
//사용자 정의 컨트롤러 클래스


package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//※ Spring 의 Controller 인터페이스를 구현하는 방법을 통해
//사용자 정의 컨트롤러 클래스를 구성한다.
public class HelloController implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		
		//DispatcherServlet 에서 
		//<bean name="/hello.action" class="com.test.mvc.HelloController"></bean>
		//을 설정해준다
		
		/*
		- Controller
		액션 처리를 담당하는 컨트롤러 클래스 작성.
		Annotation 을 사용하지 않으면, Controller 인터페이스를 구현하여 작성.

		- View
		결과 출력용 JSP 페이지 작성.

		- web.xml
		서블릿 주소와 DispatcherServlet 클래스 등록

		- dispatcher-servlet.xml
		우리가 작성한 Controller 객체 등록.
		URL 매핑 주소 등록.
		*/
		
		String message = "Hello, SpringMVC World~";
		
		mav.addObject("message", message);
		mav.setViewName("/WEB-INF/view/Hello.jsp");
		
		
		return mav;
	}
 
}












