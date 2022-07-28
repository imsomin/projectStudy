//MemberListController.java
//����� ���� ��Ʈ�ѷ�
//����Ʈ ��� �׼�
//DAO ��ü�� ���� ������ ����
//�غ� -> setter �޼ҵ� �߰�

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MemberListController implements Controller
{
	
	//�������̽� �ڷ����� ���ϴ� �Ӽ� ����
	private IMemberDAO dao;
	
	
	
    /*
	public IMemberDAO getDao()
	{
		return dao;
	}
	*/



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
		
		
		int count = 0;
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		try
		{
			count = dao.count();
			memberList = dao.list();
			
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		mav.addObject("count", count);
		mav.addObject("memberList", memberList);
		mav.setViewName("/WEB-INF/view/MemberList.jsp");
		
		
		//SimpleDriverDataSource
		
		return mav;
	}
 
}

