package dao;

import java.util.List;
import util.JDBCUtil;
import vo.ClassAcptbPrsChkVo;
import vo.ClassDateChkVo;
import vo.PurPassMgmtVo;
import vo.RgstrMgmtVo;

public class RgstrDao {
	
	private static RgstrDao instance = null;

	private RgstrDao() {
	}

	public static RgstrDao getInstance() {
		if (instance == null) {
			instance = new RgstrDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	/*
	 * 수강신청 (수강신청 테이블 행 삽입)
	 */
	public void rgstrClass(List<Object> param) {
		String sql = " INSERT INTO RGSTR_CLASS(RGSTR_NO, MEM_ID, CLASS_NO)\r\n" + 
				     " VALUES((SELECT NVL(MAX(RGSTR_NO),0)+1 FROM RGSTR_CLASS), ? , ?)";
		jdbc.update(sql, param);
	}
	
	/*
	 * 수강신청 조건 1. 수강권 잔여수강횟수 체크
	 */
	public PurPassMgmtVo remainCnt(List<Object> param) {
		String sql = "  SELECT RMN_CNT\r\n" + 
				     "    FROM PUR_PASS_MGMT\r\n" + 
				     "   WHERE MEM_ID = ? \r\n" + 
				     "     AND TRUNC(PASS_END)>=TRUNC(SYSDATE)";
		return jdbc.selectOne(sql, param, PurPassMgmtVo.class);
	}
	
	/*
	 * 수강신청 조건 2. 수업의 수용인원 체크
	 */
	public ClassAcptbPrsChkVo acptbPrs(List<Object> param) {
		String sql = " SELECT A.CLASS_NO, A.ACPTB_PRS,(SELECT COUNT(B.RGSTR_NO)\r\n" + 
				     "                                   FROM RGSTR_CLASS B\r\n" + 
				     "                                  WHERE B.CLASS_NO=A.CLASS_NO) AS COUNT_RGSTR_NO\r\n" + 
				     "   FROM CLASS A\r\n" + 
				     "  WHERE A.CLASS_NO = ?";
		return jdbc.selectOne(sql, param, ClassAcptbPrsChkVo.class);
	}
	
	/*
	 * 수강신청 조건 3. 일시 체크
	 */
	public ClassDateChkVo classDateChk(List<Object> param) {
		String sql = "       SELECT A.MEM_ID AS MEM_ID, \r\n" + 
				     "              A.RGSTR_NO AS RGSTR_NO, \r\n" + 
				     "              A.CLASS_NO AS CLASS_NO, \r\n" + 
				     "              A.CNCL_YN AS CNCL_YN, \r\n" + 
				     "              B.CLASS_DATE AS CLASS_DATE\r\n" + 
				     "         FROM RGSTR_CLASS A\r\n" + 
				     "   INNER JOIN CLASS B ON A.CLASS_NO = B.CLASS_NO\r\n" + 
				     "        WHERE A.MEM_ID =  ? \r\n" + 
				     "          AND A.CNCL_YN IS NULL\r\n" + 
				     "          AND B.CLASS_DATE = (SELECT CLASS_DATE\r\n" + 
				     "                                FROM CLASS\r\n" + 
				     "                               WHERE CLASS_NO = ? )";
		return jdbc.selectOne(sql, param, ClassDateChkVo.class);
	}
	
	/*
	 * 수강신청 삭제
	 */
	public void rgstrDelete(List<Object> param) {
		String sql = " UPDATE RGSTR_CLASS\r\n" + 
				     "    SET CNCL_YN='Y'\r\n" + 
				     "  WHERE MEM_ID =  ? \r\n" + 
				     "    AND RGSTR_NO= ? ";
		jdbc.update(sql, param);
	}
	
	/*
	 * 	수강신청한 수업 상세조회
	 */
	public List<RgstrMgmtVo> rgstrDetail(List<Object> param) {
		String sql = " SELECT RGSTR_NO, MEM_ID, CLASS_NO, CLASS_DATE, CLASS_NAME, ACPTB_PRS, INSTR_NAME, CENTER_NAME \r\n" + 
				     " FROM RGSTR_MGMT\r\n" + 
				     " WHERE MEM_ID = ? \r\n" + 
				     " AND RGSTR_NO = ? ";
		return jdbc.selectList(sql, param, RgstrMgmtVo.class);
	}
	
	/*
	 * 한 명의 회원이 수강신청한 리스트
	 */
	public List<RgstrMgmtVo> rgstrList(List<Object> param) {
		String sql = " SELECT RGSTR_NO, MEM_ID, CLASS_NO, CLASS_DATE, CNCL_YN, " +
				     "        CLASS_NAME, ACPTB_PRS, INSTR_NAME, CENTER_NAME \r\n" + 
				     "   FROM RGSTR_MGMT\r\n" + 
		   		     "  WHERE MEM_ID = ? " +
				     "    AND CNCL_YN IS NULL" +
		   		     "  ORDER BY CLASS_DATE";
		return jdbc.selectList(sql, param, RgstrMgmtVo.class);
	}
	
	public List<RgstrMgmtVo> rgstrRvwList(List<Object> param) {
		String sql = "SELECT A.CLASS_NO, A.CLASS_DATE, A.ACPTB_PRS, A.CLASS_NAME, A.INSTR_NO, A.CENTER_NO, B.RGSTR_NO\r\n" + 
				"FROM CLASS A, RGSTR_CLASS B\r\n" + 
				"WHERE A.CLASS_NO = B.CLASS_NO\r\n" + 
				"AND B.MEM_ID = ?\r\n" + 
				"AND CNCL_YN IS NULL\r\n" + 
				"AND SYSDATE > TO_DATE(CLASS_DATE, 'yyyy/mm/dd hh24:mi')";
		
		return jdbc.selectList(sql, param, RgstrMgmtVo.class);
	}
	
}
