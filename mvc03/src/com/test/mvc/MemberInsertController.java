//MemberInsertController.java
//����� ���� ��Ʈ�ѷ� Ŭ����
//ȸ�� ������ �߰� �׼� ó�� Ŭ����
//DAO ��ü�� ���� ������ ����
//�غ� -> �������̽� ������ �Ӽ� ���� 
//   -> setter �޼ҵ� �߰�


package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


//�� ��implements Controller�� Ȥ�� ��extends AbstractController��
//-- �������� HttpServlet �� ��ӹ��� ���� ��ü ����

//�� Spring �� Controller �������̽��� �����ϴ� ����� ����
//����� ���� ��Ʈ�ѷ� Ŭ������ �����Ѵ�. 
public class MemberInsertController implements Controller
{
	//dao ���� �Ӽ� ���� -> �������̽� ����
	private IMemberDAO dao;
	
	
	//setter �޼ҵ� ����
	public void setDao(IMemberDAO dao)
	{
		this.dao = dao;
	}



	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//�׼� �ڵ�
		
		ModelAndView mav = new ModelAndView();
		
		//������ �޾ƿ��� ����
		
		//ĳ���� ���ڵ� ����
		request.setCharacterEncoding("UTF-8");
		
		//���� �������κ��� �Ѿ�� ������ ����
		String name = request.getParameter("name");
		String tel = request.getParameter("telephone");
		
		try
		{
			MemberDTO member = new MemberDTO();
			member.setName(name);
			member.setTelephone(tel);
			
			dao.add(member);
			
			
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		//sendRedirect()  ->  memberlist.do
		
		//mav.setViewName("/WEB-INF/view/MemberList.jsp);
		//�ƹ��͵� �ǳٰ� �����Ƿ� ���� �� �ƴ�!!!!
		
		//�ٸ� �信�� ������ �ѱ�
		//Ŭ���̾�Ʈ���� �ൿ�ϵ��� �ȳ�
		mav.setViewName("redirect:memberlist.do");
		
		
		return mav;
	}
 
}












