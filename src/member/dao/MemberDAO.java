package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import memberjsp.bean.MemberDTO;
import memberjsp.bean.ZipcodeDTO;

public class MemberDAO {
public static MemberDAO instance;
	
	public static MemberDAO getInstance() {
		if(MemberDAO.instance == null) {
			synchronized(MemberDAO.class) {
				MemberDAO.instance = new MemberDAO();
			}
		}
		return MemberDAO.instance;
	}
	
	private DataSource ds;
	
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public MemberDAO(){
		 try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");		//Tomcat ����
		} catch (NamingException e) {
			e.printStackTrace();
		}
		 
	}
	
	public boolean isExitsId(String id) {
		boolean check = false;
		String sql = "select id from member where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) check = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public ArrayList<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname){
//		String sql = "";
		ArrayList<ZipcodeDTO> list = new ArrayList<>();
		/*
		if(sido.equals("����")) {
			sql = "select * from NEWZIPCODE where sido like '����' and roadname like ?";
		}else {
			sql = "select * from NEWZIPCODE where sido like ? and sigungu like ? and roadname like ?";
		}
		*/
		String sql = "select * from NEWZIPCODE where sido like ? and nvl(sigungu, 0) like ? and roadname like ?";
//		String sql = "select nvl(zipcode, ''),"
//				+ "nvl(sido, ''),"
//				+ "nvl(Sigungu, ''),"
//				+ "nvl(ynbmyundong, ''),"
//				+ "nvl(
		//%||?||% �� ������
		try {
			/*
			if(sido.equals("����")) {
				pstmt.setString(1, "%"+roadname+"%");
			}else {
			*/
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+sido+"%");
				pstmt.setString(2, "%"+sigungu+"%");
				pstmt.setString(3, "%"+roadname+"%");
//			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeDTO zipcodeDTO = new ZipcodeDTO();
				zipcodeDTO.setZipcode(rs.getString("zipcode"));
				zipcodeDTO.setSido(rs.getString("sido"));
				zipcodeDTO.setSigungu(rs.getString("sigungu") == null ? "" : rs.getString("sigungu"));
				zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
				zipcodeDTO.setRi(rs.getString("ri") == null ? "" : rs.getString("ri"));
				zipcodeDTO.setRoadname(rs.getString("roadname"));
				zipcodeDTO.setBuildingname(rs.getString("buildingname") == null ? "" : rs.getString("buildingname"));
				list.add(zipcodeDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = new MemberDTO();
		String sql = "select * from member where id = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			memberDTO = null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberDTO;
	}
	
	public MemberDTO login(String id, String password) {
		MemberDTO memberDTO = null;
		String sql = "select * from member where id = ? and password = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPassword(rs.getString("password"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberDTO;
	}
	
	public int write(MemberDTO memberDTO) {
		int su = 0;
		
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPassword());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}
}











