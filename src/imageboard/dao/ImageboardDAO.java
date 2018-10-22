package imageboard.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.bean.BoardDTO;
import imageboard.bean.ImageboardDTO;

public class ImageboardDAO {
public static ImageboardDAO instance;
	
	public static ImageboardDAO getInstance() {
		if(ImageboardDAO.instance == null) {
			synchronized(ImageboardDAO.class) {
				instance = new ImageboardDAO();
			}
		}
		return ImageboardDAO.instance;
	}
	
	private SqlSessionFactory sqlSessionFactory;
	
	public ImageboardDAO(){
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeImageboard(ImageboardDTO imageboardDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("imageboardSQL.writeImageboard", imageboardDTO);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public List<ImageboardDTO> getList(int startNum, int endNum){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		List<ImageboardDTO> list = sqlSession.selectList("imageboardSQL.getList", map);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		sqlSession.close();
		return list;
	}
	
	public int getBoardTotalA() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalA = sqlSession.selectOne("imageboardSQL.getBoardTotalA");
		sqlSession.close();
		return totalA;
	}
	
	public ImageboardDTO boardView(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ImageboardDTO imageboardDTO = sqlSession.selectOne("imageboardSQL.boardView", seq);

		return imageboardDTO;
	}	 
	
	public void hitUpdate(int seq) {
		String sql = "update board set hit = hit+1 where seq = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void boardModify(String subject, String content, int seq) {
		String sql = "update board set subject = ?,"
				+ " 					content = ?,"
				+ " 					logtime = sysdate where seq = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, seq);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//삭제
	public void boardDelete(int seq, int pseq) {
		
		try {
			/*String sql = "update board set reply = reply -1 where seq = (select pseq from board where seq = ?)";*/
			
			String sql = "update board set reply = reply - 1 where seq = ?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pseq);
			pstmt.executeUpdate();
			
			sql = "update board set subject = ? || subject where pseq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "[원글이 삭제된 답글]" );
			pstmt.setInt(2, seq);
			pstmt.executeUpdate();
			
			sql = "delete board where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}











