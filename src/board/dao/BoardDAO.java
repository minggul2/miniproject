package board.dao;

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
		SqlSession sqlSession = sqlSessionFactory .openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.getList", map);
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
		Map<String, String> map = new HashMap<>();
		map.put("subject", subject);
		map.put("content", content);
		map.put("seq", seq+"");
		sqlSession.update("boardSQL.board_Modify", map);
		sqlSession.commit();
		sqlSession.close();
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
		pDTO.setPseq(pDTO.getSeq());
		sqlSession.insert("boardreplySQL.boardReply_insert", pDTO);
		sqlSession.update("boardSQL.boardReply_reply", pDTO.getSeq());
		sqlSession.commit();
		sqlSession.close();
		
	}

	//삭제
	public void boardDelete(int seq, int pseq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("boardSQL.boardDelete_reply", seq);
		sqlSession.commit();

		Map<String, String> map = new HashMap<>();
		map.put("subject_add", "[원글이 삭제된 답글]");
		map.put("pseq", seq+"");
		sqlSession.update("boardSQL.boardDelete_subejct", map);
		sqlSession.commit();
		
		sqlSession.delete("boardSQL.boardDelete_board", seq);
		sqlSession.commit();
		sqlSession.close();
		
	}

	public List<BoardDTO> boardSearch(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BoardDTO> list = sqlSession.selectList("boardSQL.boardSearch", map);
		sqlSession.close();
		return list;
	}

	public int getBoardSearchTotalA(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su = sqlSession.selectOne("boardSQL.getBoardSearchTotalA", map);
		sqlSession.close();
		return su;
	}
	
	
}











