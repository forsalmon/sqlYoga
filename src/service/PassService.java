package service;

import java.util.List;

import dao.PassDao;
import vo.ClassPassVo;
import vo.MemPassMgmtVo;
import vo.PassMgMtVO;

public class PassService {
	
	private static PassService instance = null;

	private PassService() {
	}

	public static PassService getInstance() {
		if (instance == null) {
			instance = new PassService();
		}
		return instance;
	}
	
	PassDao passDao = PassDao.getInstance();
	
	/*
	 * 수강권관리 - 해당 회원의 수강권 정보출력
	 */
	public List<MemPassMgmtVo> passMgmt(List<Object> param){
		return passDao.passMgmt(param);
	}
	
	/*
	 * 수강신청 성공 후 수강권관리 업데이트
	 */
	public void passUpdate(List<Object> param) {
		passDao.passUpdate(param);
	}
	
	/*
	 * 수강권관리 업데이트 후 잔여수강횟수 출력
	 */
	public PassMgMtVO passUpdateList(List<Object> param) {
		return passDao.passUpdateList(param);
	}
	
	/*
	 * 수강권 잔여수강횟수 체크
	 */
	public MemPassMgmtVo passCntChk(List<Object> param) {
		return passDao.passCntChk(param);
	}
	
	/*
	 * 수강권 리스트 출력
	 */
	public List<ClassPassVo> passList() {
		return passDao.passList();
	}
	
	/*
	 * 지난 수강권 보기
	 */
	public List<MemPassMgmtVo> expiredPass(List<Object> param){
		return passDao.expiredPass(param);
	}
	
	/*
	 * 수강권 구매
	 */
	public void passPurchase(List<Object> param) {
		passDao.passPurchase(param);
	}
}
