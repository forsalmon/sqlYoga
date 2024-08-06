package service;

import java.util.List;

import controller.MainController;
import dao.MemberDao;
import vo.MemberVo;

public class MemberService {
	private static MemberService instance = null;

	private MemberService() {
	}

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	
	MemberDao memberDao = MemberDao.getInstance();
	
	public void memberJoin(List<Object> param) {
		memberDao.memberJoin(param);	
	}
	
	public boolean memberLogin(List<Object> param) {
		MemberVo member = memberDao.memberLogin(param);
		if(member == null) {
			return false;
		} 
		MainController.sessionStorage.put("member", member);
		return true;
	}
	
	public void memberDelete(List<Object> param) {
		memberDao.memberDelete(param);
	}

}
