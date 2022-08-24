package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class bbsDAO {
	
	
	private Connection conn;	//db에 접근하는 객체
	private ResultSet rs;
	
	public bbsDAO() {
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
	
	//날짜 구하기
	/*
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//현재의 날짜를 그대로 가져오도록 한다
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	*/

	
	//마지막에 쓰인 글 가져오기
	public int getNext() {
		String SQL = "SELECT bbsID FROM TBL_BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//그 다음 게시글이 나오도록 하는 것
				return rs.getInt(1) + 1;
			}
			return 1; //첫번째 게시물인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	//좋아요 수 세기
	/*
	public int getCount(int bbsID) {
		String SQL = "SELECT COUNT(*) FROM TBL_BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {	
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	*/
	
	//글쓰기 기능
	//public int write(String bbsTitle, String userID, String bbsContent) {
	//public int write(int bbsID, String bbsTitle, String userID, String bbsContent) {
	public int write(bbsDTO bbs) {
		String SQL = "INSERT INTO TBL_BBS(BBSID, BBSTITLE, USERID, BBSDATE, BBSCONTENT, BBSAVAILABLE) VALUES(SEQ_BBS.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			//pstmt.setInt(1, getNext()); //다음번에 쓰이게 될 게시물의 번호
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getUserID());
			pstmt.setString(3, bbs.getBbsDate());
			pstmt.setString(4, bbs.getBbsContent());
			pstmt.setInt(5, 1); //삭제가 안된 글이므로 1을 넣어준다
			
			//pstmt.executeUpdate();
			//return getNext();
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	//페이징처리(1)
	public ArrayList<bbsDTO> getList(int bbsID, int pageNumber){
		ArrayList<bbsDTO> list = new ArrayList<bbsDTO>();
		
		String SQL = "SELECT * FROM TBL_BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"; 
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setInt(2,  getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bbsDTO bbs = new bbsDTO();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	
	//검색(1)
	/*
	public ArrayList<bbsDTO> searchList(int bbsID, int pageNumber, String search){
		String SQL = "SELECT * FROM TBL_BBS WHERE bbsID < ? AND (bbsTitle like ? OR bbsContent like ? ) AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"; 
		ArrayList<bbsDTO> list = new ArrayList<bbsDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 10);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bbsDTO bbs = new bbsDTO();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //데이터베이스 오류
	}
	*/
	
	//만약 게시글이 10개가 넘어간다면 다음 페이지로 넘어가야 한다
	//페이징처리(2)
	public boolean nextPage (int bbsID, int pageNumber) {
		String SQL = "SELECT * FROM TBL_BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setInt(2,  getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; //데이터베이스 오류
	}
	
	//검색(2)
	/*
	public boolean searchNextPage (int bbsID, int pageNumber, String search) {
		String SQL = "SELECT * FROM TBL_BBS WHERE bbsID < ? AND (bbsTitle like ? OR bbsContent like ?) AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			pstmt.setInt(2,  getNext() - (pageNumber - 1) * 10);
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false; //데이터베이스 오류
	}
	*/
	
	//글 가져오기
	public bbsDTO getBbs(int bbsID) {
		String SQL = "SELECT * FROM TBL_BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bbsDTO bbs = new bbsDTO();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				//bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//글 수정하기
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String SQL = "UPDATE TBL_BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	//글 삭제하기
	public int delete(int bbsID) {
		//String SQL = "DELETE FROM TBL_BBS WHERE bbsID = ?";
		String SQL = "UPDATE TBL_BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

}
