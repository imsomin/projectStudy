//IMemberDAO.java
//�������̽�

package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

//import java.sql.ResultSet;

public interface IMemberDAO
{

	//ȸ�� ������ �߰� �޼ҵ� ����
	public int add(MemberDTO member) throws SQLException;
	
	//��ü �ο� �� Ȯ�� �޼ҵ� ����
	public int count() throws SQLException;
	
	//ȸ�� ����Ʈ Ȯ�� �޼ҵ� ����
	public ArrayList<MemberDTO> list() throws SQLException;
				
		
		
		
}
	
	
	
	
	
	

