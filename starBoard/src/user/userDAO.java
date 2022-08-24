package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {
	
	
	private Connection conn;	//db�� �����ϴ� ��ü
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public userDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "scott";
			String dbPassword = "tiger";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�α��� ����
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM TBL_STARUSER WHERE userID = ?"; 
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword))
					return 1; //�α��� ����
				else
					return 0; //��й�ȣ ����ġ
			}
			return -1; //���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //�����ͺ��̽� ����
	}
	
	//ȸ������ ����
	//�Ѹ��� ����ڸ� �Է¹ް� userDTO Ȱ���Ͽ� �ϳ��� �ν��Ͻ� �����
	public int join(userDTO user) {
		String SQL = "INSERT INTO TBL_STARUSER VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	
	
	

}
