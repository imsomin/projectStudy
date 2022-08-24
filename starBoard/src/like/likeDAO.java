package like;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class likeDAO {
	
	
	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public likeDAO() {
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
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	public int write(int bbsID, String userID,int likeStar, int badStar) {
		String SQL = "INSERT INTO evaluation VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			pstmt.setInt(3, likeStar);
			pstmt.setInt(4, badStar);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	public ArrayList<likeDTO> whereList(int bbsID, String userID){
		String SQL = "SELECT * FROM evaluation WHERE bbsID = ? AND userID =?"; 
		ArrayList<likeDTO> list = new ArrayList<likeDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				likeDTO like = new likeDTO();
				like.setBbsID(rs.getInt(1));
				like.setUserID(rs.getString(2));
				like.setLikeStar(rs.getInt(3));
				like.setBadStar(rs.getInt(4));
				list.add(like);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public ArrayList<likeDTO> getList(int bbsID){
		String SQL = "SELECT * FROM evaluation WHERE bbsID = ?"; 
		ArrayList<likeDTO> list = new ArrayList<likeDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				likeDTO like = new likeDTO();
				like.setBbsID(rs.getInt(1));
				like.setUserID(rs.getString(2));
				like.setLikeStar(rs.getInt(3));
				like.setBadStar(rs.getInt(4));
				list.add(like);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	public likeDTO getEvaluation(int bbsID) {
		String SQL = "SELECT * FROM evaluation WHERE bbsID = ?";
		ArrayList<likeDTO>list = new ArrayList<likeDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				likeDTO like = new likeDTO();
				like.setBbsID(rs.getInt(1));
				like.setUserID(rs.getString(2));
				like.setLikeStar(rs.getInt(3));
				like.setBadStar(rs.getInt(4));
				list.add(like);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int update(int bbsID, String userID,int likeStar, int badStar) {
		String SQL = "UPDATE evaluation SET likeEat=?, badEat=? WHERE bbsID = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, likeStar);
			pstmt.setInt(2, badStar);
			pstmt.setInt(3, bbsID);
			pstmt.setString(4, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int delete(int bbsID, String userID) {
		String SQL = "DELETE FROM evaluation WHERE bbsID = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setString(2, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

}
