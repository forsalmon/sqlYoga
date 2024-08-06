package service;

import java.util.List;

import dao.ClassDao;
import vo.ClassClsVo;
import vo.ClassSearchVo;
import vo.ClassVo;

public class ClassService {
	private static ClassService instance = null;

	private ClassService() {
	}

	public static ClassService getInstance() {
		if (instance == null) {
			instance = new ClassService();
		}
		return instance;
	}
	
	ClassDao classDao = ClassDao.getInstance();
	
	// 지금 수강신청에서 쓰고 있는 메소드
	public void rstrClass(List<Object> param) {
		classDao.rgstrClass(param);		
	}
	
	public List<ClassSearchVo> classSearch(List<Object> param, int sel3) {
		return classDao.classSearch(param, sel3);
		
	}
		
	public List<ClassSearchVo> classList(List<Object> param) {
		return classDao.classList(param);
	}
	
	/*
	 * 어드민 수업구분 조회
	 */
	public List<ClassClsVo> classCls() {
		return classDao.classCls();
	}
	public void classInsert(List<Object> param) {
		classDao.classInsert(param);
	}
	public void classUpdate(List<Object> param) {
		classDao.classUpdate(param);
	}
	
	/*
	 * 어드민 수업숙제
	 */
	public void classDelete(List<Object> param){
		classDao.classDelete(param);
	}
	
}
