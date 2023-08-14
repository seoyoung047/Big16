package com.google.phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.google.phonebook.dto.PhoneBookDto;

@Repository
public class PhonebookDao {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// DB 연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"ora_user","hong");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	1. 전체 사원 조회 => searchAll() => return 여러명 사원 => Arraylist 반환
	public ArrayList<PhoneBookDto> searchAll(String userid) throws Exception{
		Connection conn = open();
		ArrayList<PhoneBookDto> phonebookList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();	
		sql.append("select a.userid 				");
		sql.append("	  ,a.membernm 				");
		sql.append("	  ,a.phonenumber 			");
		sql.append("	  ,a.address 				");
		sql.append("	  ,a.groupno				");
		sql.append("	  ,b.groupnm				");
		sql.append("  from phonebook a				");
	    sql.append(" 	  ,phonegroup b				");
		sql.append(" where a.groupno = b.groupno	");	
		sql.append(" and a.userid = ?				");	
		sql.append(" order by a.membernm			");	

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, userid);
		ResultSet rs = pstmt.executeQuery();
	
		try(conn; pstmt; rs) {
			while(rs.next()) {
				
				PhoneBookDto phonebookDto = new PhoneBookDto();
				
				phonebookDto.setUserid(rs.getString("userid"));
				phonebookDto.setMembernm(rs.getString("membernm"));
				phonebookDto.setPhonenumber(rs.getString("phonenumber"));
				phonebookDto.setAddress(rs.getString("address"));
				phonebookDto.setGroupno(rs.getString("groupno"));
				phonebookDto.setGroupnm(rs.getString("groupnm"));
				
				phonebookList.add(phonebookDto);
			}
			return phonebookList;			
		}
		

	}//searchAll end
	
//	2. 이름 검색 사원 조회 => searchByName(name) ~검색한결과로 수정, 삭제 응용 => Arraylist 반환
	public ArrayList<PhoneBookDto> searchByName(String searchnm, String userid) throws SQLException{
		
		Connection conn = open();
		
		ArrayList<PhoneBookDto> phonebookList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.userid							");
		sql.append("	  ,a.membernm 						");
		sql.append("	  ,a.phonenumber 					");
		sql.append("	  ,a.address 						");
		sql.append("	  ,a.groupno						");
		sql.append("	  ,b.groupnm						");
		sql.append("  from phonebook a						");
	    sql.append(" 	  ,phonegroup b						");
	    sql.append(" WHERE a.membernm LIKE '%' || ? || '%'	");
	    sql.append("   and a.userid = ?						");
		sql.append("   and a.groupno = b.groupno			");		
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, searchnm);
		pstmt.setString(2, userid);
		
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			
			while (rs.next()) {
				PhoneBookDto phonebookDto = new PhoneBookDto();
				
				phonebookDto.setUserid(rs.getString("userid"));
				phonebookDto.setMembernm(rs.getString("membernm"));
				phonebookDto.setPhonenumber(rs.getString("phonenumber"));
				phonebookDto.setAddress(rs.getString("address"));
				phonebookDto.setGroupno(rs.getString("groupno"));
				phonebookDto.setGroupnm(rs.getString("groupnm"));
				
				phonebookList.add(phonebookDto);
			}
		}
		return phonebookList;
		
	}//searchByName() end
	
//	3. 사원 추가 => insert(추가할 사원의 정보 -> EmpDTO) => return int 
//	=> DML 명령어 => excuteUpdate()사용 ~ return int = 명령에 영향 받은 행 수
	public void insert(PhoneBookDto phonebookDto) throws Exception {
		Connection conn = open();
		
		String sql = "INSERT INTO PHONEBOOK VALUES (?,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, phonebookDto.getUserid());
			pstmt.setString(2, phonebookDto.getMembernm());
			pstmt.setString(3, phonebookDto.getPhonenumber());
			pstmt.setString(4, phonebookDto.getAddress());
			pstmt.setString(5, phonebookDto.getGroupno());
			pstmt.executeUpdate();
		} 
		
	}//insert end
	
//	4. 사원 수정 => update(수정할 사원의 정보 -> PhoneBookDTO) => return int
	public void update(PhoneBookDto phonebookDto) throws Exception {
		Connection conn = open();
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PHONEBOOK a		 ");
		sql.append("   SET a.MEMBERNM = ?	 ");
		sql.append("   	 , a.ADDRESS = ?	 ");
		sql.append("     , a.GROUPNO = ?	 ");
		sql.append(" WHERE a.userid = ?		 ");
		sql.append("  and  a.phonenumber = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		try(conn; pstmt) {
			
			pstmt.setString(1, phonebookDto.getMembernm());
			pstmt.setString(2, phonebookDto.getAddress());
			pstmt.setString(3, phonebookDto.getGroupno());
			pstmt.setString(4, phonebookDto.getUserid());
			pstmt.setString(5, phonebookDto.getPhonenumber());
			pstmt.executeUpdate();
			
		}

	}
//	5. 회원 삭제
	public void delete(String userid, String phonenumber) throws Exception {
		Connection conn 		= open();
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE PhoneBook a			");
		sql.append(" WHERE a.userid = ?  	");
		sql.append("   and a.phonenumber= ?  		");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		try(conn; pstmt) {
			pstmt.setString(1, userid);
			pstmt.setString(2, phonenumber);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//delete end
	
	public ArrayList<PhoneBookDto> addmemberpncheck(String phonenumber, String userid) throws Exception {
		Connection conn = open();
		ArrayList<PhoneBookDto> phonebookpn = new ArrayList<>();

		String sql = "SELECT * FROM phonebook where phonenumber = ? and userid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, phonenumber);
		pstmt.setString(2, userid);
		ResultSet rs = pstmt.executeQuery();
		try (conn; pstmt; rs) {
			while (rs.next()) {
				PhoneBookDto user = new PhoneBookDto();
				user.setUserid(rs.getString("userid"));
				user.setMembernm(rs.getString("membernm"));
				user.setPhonenumber(rs.getString("phonenumber"));
				user.setAddress(rs.getString("address"));
				user.setGroupno(rs.getString("groupno"));

				phonebookpn.add(user);
			}
		}
		return phonebookpn;

	}
	
}