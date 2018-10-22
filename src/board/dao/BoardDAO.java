package board.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class BoardDAO {
public static BoardDAO instance;
	
	public static BoardDAO getInstance() {
		if(BoardDAO.instance == null) {
			synchronized(BoardDAO.class) {
				BoardDAO.instance = new BoardDAO();
			}
		}
		return BoardDAO.instance;
	}
	
	private SqlSessionFactory sqlSessionFactory;
	
	public BoardDAO(){
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int writeBoard(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su = sqlSession.insert("boardSQL.writeBoard", map);
		sqlSession.commit();
		sqlSession.close();
		return su;
	}
	
	public List<BoardDTO> getList(Map<String, Integer> map){
		
		List<BoardDTO> list;
		BoardDTO boardDTO = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		//오라클에서 포메팅필요
		SqlSession sqlSession = sqlSessionFactory .openSession();
		list = sqlSession.selectList("boardSQL.getList", map);
		sqlSession.close();
		return list;
	}
	
	public int getBoardTotalA() {
		int totalA = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		totalA = sqlSession.selectOne("boardSQL.getBoardTotalA");
		sqlSession.close();
		return totalA;
		
	}
	
	public BoardDTO boardView(int seq) {
		BoardDTO boardDTO = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		boardDTO = sqlSession.selectOne("boardSQL.boardView", seq);
		sqlSession.close();
		return boardDTO;
	}	 
	
	public void hitUpdate(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.hitUpdate", seq);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public void boardModify(String subject, String content, int seq) {
	
		SqlSession sqlSession = sqlSessionFactory.openSession();
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

	public void boardReply(Map<String, String> map) {
		BoardDTO pDTO = boardView(Integer.parseInt(map.get("pseq")));
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.boardReply_step", pDTO);
		pDTO.setId(map.get("id"));
		pDTO.setName(map.get("name"));
		pDTO.setEmail(map.get("email"));
		pDTO.setSubject(map.get("subject"));
		pDTO.setContent(map.get("content"));
		//lev, step, pseq 못받아옴
		System.out.println(pDTO.getContent());
		System.out.println(pDTO.getLev());
		sqlSession.insert("boardSQL.boardReply_insert", pDTO);
		sqlSession.update("boardSQL.boardReply_hit", pDTO.getSeq());
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











