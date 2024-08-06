package dao;

import java.util.List;

import util.JDBCUtil;
import vo.NoticeVo;

public class NoticeDao {
	private static NoticeDao instance = null;

	private NoticeDao() {
	}

	public static NoticeDao getInstance() {
		if (instance == null) {
			instance = new NoticeDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public List<NoticeVo> noticeList() {
		String sql = "   SELECT NTC_NO, SUBSTR(NTC_TITLE, 0, 15) NTC_TITLE, SUBSTR(NTC_CONT,0,25) NTC_CONT, TO_CHAR(NTC_DATE, 'YYYY/MM/DD') AS NTC_DATE, MEM_ID\r\n" + 
				"   FROM NOTICE\r\n" + 
				"  WHERE NTC_DELYN IS NULL";
		
		return jdbc.selectList(sql, NoticeVo.class); 
	}
	

	public NoticeVo noticeDetail(int noticeNo) {
		String sql = "   SELECT NTC_NO, NTC_TITLE, NTC_CONT, TO_CHAR(NTC_DATE, 'YYYY/MM/DD') AS NTC_DATE, MEM_ID\r\n" + 
				"   FROM NOTICE\r\n" + 
				"  WHERE NTC_NO = " + noticeNo;
		return jdbc.selectOne(sql, NoticeVo.class);
	}
	
	public void noticeUpdate(List<Object> param) {
		String sql = "  UPDATE NOTICE\r\n" + 
				"  SET NTC_TITLE = ?,\r\n" + 
				"  NTC_CONT = ?\r\n" + 
				"  WHERE NTC_NO = ? ";
		
		jdbc.update(sql,param);
		
	}
	public void noticeDelete(List<Object> param) {
		String sql = "UPDATE NOTICE\r\n" + 
				"   SET NTC_DELYN = 'Y'\r\n" + 
				"  WHERE NTC_NO = ?" ;
		
		jdbc.update(sql, param);
	}
	
	public void noticeInsert(List<Object> param) {
		String sql = "INSERT INTO NOTICE(NTC_NO, NTC_TITLE, NTC_CONT, NTC_DATE, MEM_ID)\r\n" + 
				"				VALUES((SELECT NVL(MAX(NTC_NO),0)+1 FROM NOTICE), ?, ?, SYSDATE, ?)";
		
		jdbc.update(sql, param);
	}
}