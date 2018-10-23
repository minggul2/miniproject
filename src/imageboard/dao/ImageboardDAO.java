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
	
	public ImageboardDTO imageboardView(int seq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ImageboardDTO imageboardDTO = sqlSession.selectOne("imageboardSQL.imageboardView", seq);
		
		return imageboardDTO;
		
	}
	
}











