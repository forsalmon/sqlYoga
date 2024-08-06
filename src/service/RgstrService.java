package service;

import java.util.List;
import dao.RgstrDao;
import vo.ClassAcptbPrsChkVo;
import vo.ClassDateChkVo;
import vo.PurPassMgmtVo;
import vo.RgstrMgmtVo;

public class RgstrService {
	
	private static RgstrService instance = null;

	private RgstrService() {
	}

	public static RgstrService getInstance() {
		if (instance == null) {
			instance = new RgstrService();
		}
		return instance;
	}
	
	RgstrDao rgstrDao = RgstrDao.getInstance();
	
	
	/*
	 * 수강신청 (수강신청 테이블 행 삽입)
	 */
	public void rgstrClass(List<Object> param) {
		rgstrDao.rgstrClass(param);
	}
	/*
	 * 수강신청 조건 1. 수강권 잔여수강횟수 체크
	 */
	public PurPassMgmtVo remainCnt(List<Object> param) {
		return rgstrDao.remainCnt(param);
	}
	
	/*
	 * 수강신청 조건 2. 수업의 수용인원 체크
	 */
	public ClassAcptbPrsChkVo acptbPrs(List<Object> param) {
		return rgstrDao.acptbPrs(param);
	}
	
	/*
	 * 수강신청 조건 3. 일시 체크
	 */
	public ClassDateChkVo classDatechk(List<Object> param) {
		return rgstrDao.classDateChk(param);
	}
	
	/*
	 * 수강신청 삭제
	 */
	public void rgstrDelete(List<Object> param) {
		rgstrDao.rgstrDelete(param);
	}
	
	/*
	 * 수강신청한 수업 상세조회
	 */
	public List<RgstrMgmtVo> rgstrDetail(List<Object> param){
		return rgstrDao.rgstrDetail(param);
	}
	
	/*
	 * 한 명의 회원이 수강신청한 리스트
	 */
	public List<RgstrMgmtVo> rgstrList(List<Object> param){
		return rgstrDao.rgstrList(param);
	}
	
	
	public List<RgstrMgmtVo> rgstrRvwList(List<Object> param){
		return rgstrDao.rgstrRvwList(param);
	}


}
