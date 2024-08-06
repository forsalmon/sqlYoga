package service;

import java.util.List;

import dao.ReviewDao;
import vo.ReviewVo;
import vo.RgstrMgmtVo;

public class ReviewService {
private static ReviewService instance = null;

private ReviewService() {
}

public static ReviewService getInstance() {
	if (instance == null) {
		instance = new ReviewService();
	}
	return instance;
}
	ReviewDao reviewDao = ReviewDao.getInstance();
 
	 public List<ReviewVo> reviewList(){
		 return reviewDao.reviewList();
	 }
	
	public void reviewInsert(List<Object> param) {
		reviewDao.reviewInsert(param);
	}
	public ReviewVo reviewDetail(int reviewNo) {
		return reviewDao.reviewDetail(reviewNo);
	}
	
	public void reviewDelete(List<Object> param) {
		reviewDao.reviewDelete(param);
	}
	
	public void adminReviewDel(List<Object> param) {
		reviewDao.adminReviewDel(param);
	}
	public boolean reviewChek(List<Object> param) {
		ReviewVo rgstr = reviewDao.check(param);
		if(rgstr == null) return false;
		else return true;
	}
}