package dao;

import java.util.List;

import util.JDBCUtil;
import vo.ClassClsVo;
import vo.ClassSearchVo;
import vo.ClassVo;

public class ClassDao {
	private static ClassDao instance = null;

	private ClassDao() {
	}

	public static ClassDao getInstance() {
		if (instance == null) {
			instance = new ClassDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	/*
	 * 지금 이거 안 쓰고 있음
	 */
	public void rgstrClass(List<Object> param) {
		String sql = " INSERT INTO RGSTR_CLASS(RGSTR_NO, MEM_ID, CLASS_NO)\r\n" + 
				     " VALUES((SELECT NVL(MAX(RGSTR_NO),0)+1 FROM RGSTR_CLASS), ? , ?)";
		jdbc.update(sql, param);
	}
	
		
	
	/*
	 * 검색 옵션 입력받아 수업 한 개 출력
	 * 옵션 : 수업일시, 강사명, 수업구분(요가종류)명, 지점명
	 *   => 수업, 강사, 수업구분, 지점 테이블로 뷰 생성
	 */
	public List<ClassSearchVo> classSearch(List<Object> param, int sel3) {
		String sql = " SELECT CLASS_NO, CLASS_DATE, CLS_NAME, CLASS_NAME, ACPTB_PRS, INSTR_NAME, CENTER_NAME\r\n" + 
				     " FROM CLASS_SEARCH\r\n" + 
				     " WHERE ";
		if(sel3 == 1) {
			sql += " CLASS_DATE= ? ";
		}
		else if (sel3 == 2) {
			sql += " INSTR_NAME= ? ";
		}
		else if (sel3 == 3) {
			sql += " CLS_NAME LIKE ? % ";
		}
		else if (sel3 == 4) {
			sql += " CENTER_NAME LIKE ? % ";
		}
		return jdbc.selectList(sql, param, ClassSearchVo.class);
	}
	
	
	public List<ClassSearchVo> classList(List<Object> param) {
		String sql = " SELECT * \r\n" + 
				     "   FROM (SELECT ROWNUM RN, A.*\r\n" + 
				     "           FROM(SELECT CLASS_NO, CLASS_DATE, CLS_NAME, CLASS_NAME, ACPTB_PRS, INSTR_NAME, CENTER_NAME\r\n" + 
				     "                  FROM CLASS_SEARCH \r\n" + 
				     "                 ORDER BY CLASS_NO)A)\r\n" + 
				     "         WHERE RN BETWEEN ? AND ? ";
		return jdbc.selectList(sql, param, ClassSearchVo.class);
	}

	
	/*
	 * 어드민 수업구분 조회
	 */
	public List<ClassClsVo> classCls(){
		String sql = "SELECT *\r\n" + 
				" FROM CLASS_CLS\r\n"
				+ "WHERE USEYN IS NULL";
		
		return jdbc.selectList(sql, ClassClsVo.class);
	}
	public void classUpdate(List<Object> param) {
		String sql = "  UPDATE CLASS_CLS\r\n" + 
				"  SET CLS_NO = ?,\r\n" + 
				"  CLS_NAME = ?\r\n" + 
				"  WHERE CLS_NO = ? ";
		
		jdbc.update(sql,param);
		
	}
	public void classInsert(List<Object> param) {
		String sql = "INSERT INTO CLASS_CLS(CLS_NO, CLS_NAME)\r\n" + 
				"        VALUES(?, ?)";
		
		jdbc.update(sql, param);
	}
	/*
	 * 어드민 수업 삭제
	 */
	public void classDelete(List<Object> param) {
		String sql =  "UPDATE CLASS_CLS\r\n" + 
				"   SET USEYN = 'Y'\r\n" + 
				"  WHERE CLS_NO = ?";
		
		jdbc.update(sql, param);
	}	
	

}
