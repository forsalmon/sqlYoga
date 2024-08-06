package dao;

import java.util.List;

import util.JDBCUtil;
import vo.MemberVo;

public class MemberDao {
	private static MemberDao instance = null;

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void memberJoin(List<Object> param) {
		String sql = " INSERT INTO MEMBER(MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_BIR)\r\n" 
					+" VALUES(?, ?, ?, ?, ?)";
		jdbc.update(sql, param);
	}
	
	public MemberVo memberLogin(List<Object> param) {
		String sql =  "SELECT *\r\n" + 
				"     FROM MEMBER\r\n" + 
				"    WHERE MEM_ID = ?\r\n" + 
				"      AND MEM_PASS = ?\r\n" + 
				"      AND MEM_DELYN IS NULL";
		 
		return jdbc.selectOne(sql, param, MemberVo.class);
		
	}
	
	
	public void memberDelete(List<Object> param) {
		String sql = "UPDATE MEMBER\r\n" + 
				"   SET MEM_DELYN = 'Y'\r\n" + 
				"  WHERE MEM_ID = ?" ;
		
		jdbc.update(sql, param);
	}

}