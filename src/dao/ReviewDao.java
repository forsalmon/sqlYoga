package dao;

import java.util.List;

import util.JDBCUtil;
import vo.ReviewVo;

public class ReviewDao {
	private static ReviewDao instance = null;

	private ReviewDao() {
	}

	public static ReviewDao getInstance() {
		if (instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<ReviewVo> reviewList() {
		String sql = "SELECT RGSTR_NO,SUBSTR(RVW_TITLE,0,15) RVW_TITLE,SUBSTR(RVW_CONT,0,25) RVW_CONT,RVW_RATED,TO_CHAR(RVW_DATE,'YYYY/MM/DD') AS RVW_DATE\r\n" + 
				"        FROM REVIEW\r\n" + 
				"       WHERE RVW_DELYN IS NULL"
				+ "  ORDER BY RGSTR_NO ";
		
		return jdbc.selectList(sql, ReviewVo.class); 
	}
	
	public void reviewInsert(List<Object> param) {
		String sql = " INSERT INTO REVIEW(RVW_TITLE, RVW_CONT, RVW_RATED, RVW_DATE, RGSTR_NO)\r\n" + 
				     "  VALUES(? , ?, ? , SYSDATE, ?)" ;
		
		jdbc.update(sql, param);
	}
	
	public ReviewVo check(List<Object> param) {
		String sql = "  SELECT RGSTR_NO\r\n" + 
				" FROM REVIEW\r\n" + 
				"  WHERE RVW_DELYN IS NULL\r\n" + 
				"    AND RGSTR_NO = ?";
		
		return jdbc.selectOne(sql, param, ReviewVo.class);
	}
	public ReviewVo reviewDetail(int reviewNo) {
		String sql = "SELECT RGSTR_NO,RVW_TITLE,RVW_CONT,RVW_RATED,TO_CHAR(RVW_DATE,'YYYY/MM/DD') AS RVW_DATE\r\n" + 
				"        FROM REVIEW\r\n" + 
				"       WHERE RGSTR_NO = " + reviewNo;
		return jdbc.selectOne(sql, ReviewVo.class);
	}
	
	public void reviewDelete(List<Object> param) {
		String sql = "UPDATE REVIEW\r\n" + 
				"   SET RVW_DELYN = 'Y'\r\n" + 
				"  WHERE RGSTR_NO = ?";
		
		jdbc.update(sql, param);
	}

	public void adminReviewDel(List<Object> param) {
		String sql = "UPDATE REVIEW\r\n" + 
				"   SET RVW_DELYN = 'Y'\r\n" + 
				"  WHERE RGSTR_NO = ?";
		
		jdbc.update(sql, param);
	}

}