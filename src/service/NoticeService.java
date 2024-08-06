package service;

import java.util.List;

import dao.NoticeDao;
import vo.NoticeVo;

public class NoticeService {
	private static NoticeService instance = null;

	private NoticeService() {
	}

	public static NoticeService getInstance() {
		if (instance == null) {
			instance = new NoticeService();
		}
		return instance;
	}
	
	NoticeDao noticeDao = NoticeDao.getInstance();
	
	public List<NoticeVo> noticeList(){
		return noticeDao.noticeList();
	}

	public NoticeVo noticeDetail(int noticeNo) {
		return noticeDao.noticeDetail(noticeNo);
	}
	
	public void noticeUpdate(List<Object> param) {
		noticeDao.noticeUpdate(param);
	}
	public void noticeDelete(List<Object> param) {
		noticeDao.noticeDelete(param);
	}
	public void noticeInsert(List<Object> param) {
		noticeDao.noticeInsert(param);
	}
	
	

}
	

