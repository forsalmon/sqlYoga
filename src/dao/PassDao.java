package dao;

import java.util.List;

import util.JDBCUtil;
import vo.ClassPassVo;
import vo.MemPassMgmtVo;
import vo.PassMgMtVO;

public class PassDao {
	private static PassDao instance = null;

	private PassDao() {
	}

	public static PassDao getInstance() {
		if (instance == null) {
			instance = new PassDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	/*
	 * 수강권관리 - 해당 회원의 수강권 정보출력
	 */
	public List<MemPassMgmtVo> passMgmt(List<Object> param) {
		String sql = " SELECT MEM_ID, PUR_NO, PASS_NO, CLASS_CNT, RMN_CNT, TO_CHAR(PASS_STR, 'yyyy/mm/dd') AS PASS_STR, TO_CHAR(PASS_END, 'yyyy/mm/dd') AS PASS_END\r\n" + 
				     "   FROM MEM_PASS_MGMT\r\n" + 
				     "  WHERE MEM_ID = ?\r\n" + 
				     "    AND PASS_END >= SYSDATE";
		return jdbc.selectList(sql, param, MemPassMgmtVo.class);
	}
	
	/*
	 * 수강신청 성공 후 수강권관리 업데이트
	 * 	 => 지금은 모든 수강권이 업데이트 되니까,
	 *   => 하나만 업데이트 되도록
	 */
	public void passUpdate(List<Object> param) {
		String sql = "  UPDATE PASS_MGMT C \r\n" + 
				     "     SET C.RGSTR_CNT = RGSTR_CNT + 1, \r\n" + 
				     "         C.RMN_CNT   = RMN_CNT - 1\r\n" + 
				     "   WHERE C.PUR_NO = (\r\n" + 
				     "                      SELECT A.PUR_NO\r\n" + 
				     "                        FROM  PASS_MGMT A, PURCHASE B\r\n" + 
				     "                       WHERE A.MEM_ID = B.MEM_ID\r\n" + 
				     "                         AND   A.PUR_NO = B.PUR_NO\r\n" + 
				     "                         AND   A.RMN_CNT > 0\r\n" + 
				     "                         AND   A.MEM_ID = ?\r\n" + 
				     "                         AND   B.PASS_END >= SYSDATE\r\n" + 
				     "                                                          )";
		jdbc.update(sql, param);
	}
	
	/*
	 * 수강권관리 업데이트 후 잔여수강횟수 출력
	 */
	public PassMgMtVO passUpdateList(List<Object> param) {
		String sql = "SELECT RGSTR_CNT, RMN_CNT, MEM_ID, PASS_NO, PUR_NO \r\n" + 
				"FROM   PASS_MGMT \r\n" + 
				"WHERE MEM_ID = ? \r\n" + 
				"   AND PASS_NO IN (SELECT B.PASS_NO \r\n" + 
				"                     FROM PURCHASE A, PASS_MGMT B \r\n" + 
				"                    WHERE A.PUR_NO=B.PUR_NO \r\n" + 
				"                      AND A.PASS_END > SYSDATE)";
		
		return jdbc.selectOne(sql, param, PassMgMtVO.class);
	}
	
	
	/*
	 * 수강권 잔여수강횟수 체크
	 */
	public MemPassMgmtVo passCntChk(List<Object> param) {
		String sql = " SELECT MEM_ID, RMN_CNT \r\n" + 
					"  FROM MEM_PASS_MGMT\r\n" + 
					"  WHERE MEM_ID = ?\r\n" + 
					"  AND (PASS_END >= SYSDATE OR (PASS_END < SYSDATE AND RMN_CNT = 0))";
		return jdbc.selectOne(sql, param, MemPassMgmtVo.class);
		
	}
	
	/*
	 * 수강권 리스트 출력
	 */
	public List<ClassPassVo> passList() {
		String sql = " SELECT PASS_NO, CLASS_CNT, PASS_PRICE\r\n" + 
				     " FROM CLASS_PASS\r\n" + 
				     " ORDER BY PASS_NO";
		return jdbc.selectList(sql, ClassPassVo.class);
	}
	
	/*
	 * 지난 수강권 보기
	 */
	public List<MemPassMgmtVo> expiredPass(List<Object> param) {
		String sql = " SELECT A.MEM_ID, B.CLASS_CNT, A.PASS_NO, TO_CHAR(PASS_STR, 'yyyy/mm/dd') AS PASS_STR, TO_CHAR(PASS_END, 'yyyy/mm/dd') AS PASS_END\r\n" + 
				     "   FROM PURCHASE A, CLASS_PASS B\r\n" + 
				     "  WHERE A.PASS_NO=B.PASS_NO\r\n" + 
				     "    AND A.MEM_ID =  ? \r\n" + 
				     "    AND A.PASS_END < SYSDATE";
		return jdbc.selectList(sql, param, MemPassMgmtVo.class);
	}
	
	

	/*
	 * 수강권 구매
	 */
	public void passPurchase(List<Object> param) {
		String sql = " INSERT INTO PURCHASE(PUR_NO, MEM_ID, PASS_NO)\r\n" + 
				     "   VALUES(increate_PUR_NO, ? , ? )";
		
		jdbc.update(sql, param);
				
	}
}
