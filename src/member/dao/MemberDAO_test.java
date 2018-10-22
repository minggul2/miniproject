package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO_test extends MemberDAO{
	public static MemberDAO_test instance;
	
	public static MemberDAO_test getInstance() {
		if(MemberDAO_test.instance == null) {
			synchronized(MemberDAO_test.class) {
				instance = new MemberDAO_test();
				System.out.println("객체생성");
			}
		}
		return instance;
	}
	
	private SqlSessionFactory sqlSessionFactory;
	
	
	public MemberDAO_test() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			System.out.println(sqlSessionFactory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isExitsId(String id) {
		boolean check = false;
		System.out.println("아이디는 : " + id);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int num = (sqlSession.selectOne("memberSQL.isExitsId", id));
		if(num == 1) {
			check = true;
		}	
		
		sqlSession.close();
		
		System.out.println("아이디있냐 " + check);
			
		return check;
	}	
	
	
	public List<ZipcodeDTO> getZipcodeList(Map<String, String> map){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<ZipcodeDTO> list = sqlSession.selectList("memberSQL.getZipcodeList", map);
		sqlSession.close();
		
		return list;	
	}
	
	public MemberDTO getMember(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.getMember", id);
		sqlSession.close();
		
		return memberDTO;
	}
	
	public MemberDTO login(Map<String, String> map) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
		sqlSession.close();
		return memberDTO;
	}	
	
	public int write(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int su = sqlSession.insert("memberSQL.write", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		return su;
	}
	
	
}
