package imageboard.dao;

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
	
	DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ImageboardDAO(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
										
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeImageboard(ImageboardDTO imageboardDTO) {
		
		//ÔøΩÔøΩÔøΩÔøΩ ref = seq
		String sql = "insert into imageboard values(seq_imageboard.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, imageboardDTO.getImageId());
			pstmt.setString(2, imageboardDTO.getImageName());
			pstmt.setInt(3, imageboardDTO.getImagePrice());
			pstmt.setInt(4, imageboardDTO.getImageQty());
			pstmt.setString(5, imageboardDTO.getImageContent());
			pstmt.setString(6, imageboardDTO.getImage1());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public List<ImageboardDTO> getList(int startNum, int endNum){
		List<ImageboardDTO> list = new ArrayList<>();
		ImageboardDTO imageboardDTO = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		String sql = "select * from "
				+ "(select rownum rn, tt.* from "
				+ "(select * from imageboard order by seq desc) tt)"
				+ " where rn >= ? and rn <= ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				imageboardDTO = new ImageboardDTO();
				
				imageboardDTO.setSeq(rs.getInt("seq"));
				imageboardDTO.setImageId(rs.getString("imageId"));
				imageboardDTO.setImageName(rs.getString("imageName"));
				imageboardDTO.setImagePrice(rs.getInt("imagePrice"));
				imageboardDTO.setImageQty(rs.getInt("imageQty"));
				imageboardDTO.setImageContent(rs.getString("imageContent"));
				imageboardDTO.setImage1(rs.getString("image1"));
				imageboardDTO.setLogtime(rs.getString("logtime"));
				list.add(imageboardDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int getBoardTotalA() {
		int totalA = 0;
		String sql = "select count(*) from imageboard";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totalA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalA;
		
	}
	
	public BoardDTO boardView(int seq) {
		BoardDTO boardDTO = null;
		String sql = "select * from board where seq = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getString("logtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardDTO;
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

	public void boardReply(Map<String, String> map) {
		BoardDTO pDTO = boardView(Integer.parseInt(map.get("pseq")));
		
		
		try {
			//step update
			String sql = "update board set step = step + 1 where ref = ? and step > ?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pDTO.getRef());
			pstmt.setInt(2, pDTO.getStep());
			pstmt.executeUpdate();
			pstmt.close();
			
			
			//µ•¿Ã≈Õ ≥÷±‚
			sql = "insert into board values(seq_board.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			pstmt.setInt(6, pDTO.getRef());
			pstmt.setInt(7, pDTO.getLev()+1);
			pstmt.setInt(8, pDTO.getStep()+1);
			pstmt.setInt(9, pDTO.getSeq());
			pstmt.executeUpdate();
			pstmt.close();
			
			
			sql = "update board set reply = reply + 1 where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pDTO.getSeq());
			pstmt.executeQuery();
			
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

	//ªË¡¶
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
			pstmt.setString(1, "[ø¯±€¿Ã ªË¡¶µ» ¥‰±€]" );
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











