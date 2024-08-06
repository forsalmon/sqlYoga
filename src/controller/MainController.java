package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.ClassService;
import service.MemberService;
import service.NoticeService;
import service.PassService;
import service.ReviewService;
import service.RgstrService;
import util.ScanUtil;
import util.View;
import vo.ClassAcptbPrsChkVo;
import vo.ClassClsVo;
import vo.ClassDateChkVo;
import vo.ClassPassVo;
import vo.ClassSearchVo;
import vo.MemPassMgmtVo;
import vo.MemberVo;
import vo.NoticeVo;
import vo.PassMgMtVO;
import vo.PurPassMgmtVo;
import vo.ReviewVo;
import vo.RgstrMgmtVo;


public class MainController {
	static public Map<String, Object> sessionStorage = new HashMap<>();	
	ClassService classService = ClassService.getInstance();
	MemberService memberService = MemberService.getInstance();
	PassService passService = PassService.getInstance();
	RgstrService rgstrService = RgstrService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
	ReviewService reviewService = ReviewService.getInstance();
	
	
	public static void main(String[] args) {
		
      System.out.println();
      System.out.println(" ##  ##    ## ##    ## ##     ##");
      System.out.println(" ##  ##   ##   ##  ##   ##     ##");
      System.out.println(" ##  ##   ##   ##  ##        ## ##");
      System.out.println("  ## ##   ##   ##  ##  ###   ##  ##");
      System.out.println("   ##     ##   ##  ##   ##   ## ###");
      System.out.println("   ##     ##   ##  ##   ##   ##  ##");
      System.out.println("   ##      ## ##    ## ##   ###  ##");

        System.out.println();
        System.out.println("===========================================");
        System.out.println("===========================================");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠀⠛⠀⠀⠀⣠⡶⠿⠛⠛⠻⢶⣄⠀⠀⠀⠀⠀⠀⣤⠀⣤⠛⣤ ");
        System.out.println("        ⣤⠛⣤⠀⠀⠀⠀⠀⠀⠀⣾⠋⠀⢀⣠⣶⠀⠀⠙⣷⡀⠀⠀⠀⠀⠀⠀⠀⠛⠀");
        System.out.println("        ⠀⠛⠀⠀⠀⠀⠀⠀⠀⢸⣯⣴⠶⠟⠉⠹⣷⣄⣀⣸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢸⣿⣧ ⠀⠀⠀⠀   ⠉⣹⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⢀⡀⢀⡰⢆⡀⠀ ⢸⡇⠙⢷⣤⣀⣀⣤⡾⠋⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⠈⠁⠈⠱⠎⠁⠀⠀⢸⡇⠀⠀⣿⠉⠉⣿⠀⠀⢸⡇⠀⠀⠀⣤⠛⣤⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠀⢠⡾⣿⡟⢻⣟⠛⠀⠀⠛⣻⡟⢻⣿⢷⡄⠀⠀⠛⠀⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⣧⠈⠙⠛⠷⠾⠛⠋⠁⣸⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⢿⡄⠀⠀⠀⠀⠀ ⠀⢀⣿⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⠀⣸⡇⠀⢸⣧⠀⠀⠀⠀⠀⠀⣼⡇⠀⢸⣇⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠀⠀⣰⡟⠀⢀⣴⣿⡄⠀⠀⠀⠀⢠⣿⣦⡀⠀⢻⣆⠀⠀⠀⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⣴⠶⠶⠟⠀⣠⡿⣣⡾⠛⠛⠛⠛⠛⠛⢷⣜⢿⣆⠀⠻⠷⠶⣦⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⢻⣶⣶⣶⣾⣯⣴⣟⣁⣀⡀⠀⠀⢀⣀⣈⣻⣧⣽⣷⣶⣶⣶⡟⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⢸⣧⣀⠀⠀⠀⠉⠉⠉⠙⠛⠛⠿⣿⣏⠉⠉⠉⠀⠀⠀  ⣀⣼⡇⠀⠀⠀");
        System.out.println("        ⠀⠀⠀⠀⠉⠙⠛⠛⠛⠿⠶⠶⢶⣦⣤⣤⣼⣿⠿⠶⠿⠛⠛⠛⠋⠉⠀⠀⠀⠀");
		
		
		
		new MainController().start();
	}
	
	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case MEMBER_JOIN:
				view = memberJoin();
				break;		
			case MEMBER_LOGIN:
				view = memberLogin();
				break;
			case MEMBER_DELETE:
				view = memberDelete();
				break;
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case MEMBER_HOME:
				view = memberHome();
				break;
			case ADMIN_HOME:
				view = adminHome();
				break;				
			case NOTICE_HOME:
				view = noticeHome();
				break;	
			case NOTICE_DETAIL:
				view = noticeDetail();
				break;		
			case ADMIN_NOTICE:
				view = adminNotice();
				break;
			case ADMIN_NOTICEDT:
				view = adminNoticeDt();
				break;		
			case NOTICE_INSERT:
				view = noticeInsert();
				break;					
			case NOTICE_UPDATE:
				view = noticeUpdate();
				break;		
			case NOTICE_DELETE:
				view = noticeDelete();
				break;						
			case REVIEW_HOME:
				view = reviewHome();
				break;				
			case REVIEW_DETAIL:
				view = reviewDetail();
				break;	
			case REVIEW_INSERT:
				view = reviewInsert();
				break;		
			case REVIEW_DELETE:
				view = reviewDelete();
				break;		
			case ADMIN_REVIEW_DELETE:
				view = adminReviewDel();
				break;				
			case ADMIN_REVIEW:
				view = adminReview();
				break;	
			case ADMIN_REVIEWDT:
				view = adminReviewDt();
				break;
			case CLASS_MGMT:
				view = classMgmt();
				break;	
			case CLASS_INSERT:
				view = classInsert();
				break;	
			case CLASS_UPDATE:
				view = classUpdate();
				break;	
			case ADMIN_RGSTR:
				view = classDelete();
				break;			
			case CLASS_LIST:
				view = classList();
				break;
			case RGSTR_CLASS:
				view = rgstrClass();
				break;
			case RGSTR_CLASS_LIST:
				view = rgstrClassList();
				break;
			case RGSTR_LIST:
				view = rgstrList();
				break;
			case RGSTR_DETAIL:
				view = rgstrDetail();
				break;
			case RGSTR_DELETE:
				view = rgstrDelete();
				break;
			case CLASS_SEARCH:
				view = classSearch();
				break;
			case PASS_MGMT:
				view = passMgmt();
				break;
			case EXPIRED_PASS:
				view = expiredPass();
				break;
			case PASS_PURCHASE:
				view = passPurchase();
				break;
			default:
				break;
			}
		}
	}
				


	/*
	 * @수강권 구매
	 */
	private View passPurchase() {
		
		String id = (String) sessionStorage.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		MemPassMgmtVo p = passService.passCntChk(param);
		// 수강종료일이 남아있는 수강권 중에 잔여수강횟수가 1회라도 남아있으면 수강권 구매 불가
		if (p == null || p != null && p.getRmn_cnt() == 0) {
			// 판매 수강권 리스트 출력
			List<ClassPassVo> passList = passService.passList();
			System.out.println("=============== [수강권 구매] =================");
			for (ClassPassVo c : passList) {
				System.out.println("|"+"수강권번호"+"|"+c.getPass_no()+"\t"+
						           "|"+"수강가능횟수"+"|"+c.getClass_cnt()+"\t"+
						           "|"+"가격"+"|"+c.getPass_price());
				System.out.println("-------------------------------------------");
			}
			String passNo = ScanUtil.nextLine("▶  구매하고 싶은 수강권번호 입력: ");
			List<Object> param2 = new ArrayList<Object>();
			param2.add(id);
			param2.add(passNo);
			passService.passPurchase(param2);
			System.out.println("["+id+"]님! 수강권 구매가 완료되었습니다 !");
		}
		else if (p.getRmn_cnt() >= 1) {
			System.out.println("⚠ 잔여수강횟수가 남아있는 수강권이 있습니다!");
			System.out.println("⚠ 보유한 수강권을 소진 후 구매하세요 ");
			return View.MEMBER_HOME;
		}
		return View.CLASS_LIST;	
	}
	
	/*
	 * @지난 수강권 조회 
	 */
	private View expiredPass() {
		String id = (String) sessionStorage.get("MEM_ID");
		System.out.println(" [ "+id+" ] 님의 지난 수강권 정보 "+" ==============");				
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		List<MemPassMgmtVo> expiredList = passService.expiredPass(param);
		if (expiredList.isEmpty()) {
			System.out.println("⚠ 지난 수강권이 없습니다 !! ");
			System.out.println("=========================================================================");
			return View.MEMBER_HOME;
		}
		else {
			for (MemPassMgmtVo e : expiredList) {
				System.out.println("구매일(수강시작일): "+e.getPass_str()+"\n"+
						           "수  강   종   료   일   : "+e.getPass_end()+"\n"+	
						           "수강권옵션(횟수)  : "+e.getClass_cnt());	
				System.out.println("======================================");
			}
		}
		return View.MEMBER_HOME;
	}
	
	/*
	 * @수강권 관리 
	 */
	private View passMgmt() {
		
		// 보유한 수강권 없으면 보유한 수강권이 없습니다 ! 출력
		String id = (String) sessionStorage.get("MEM_ID");					
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		List<MemPassMgmtVo> passMgmtList = passService.passMgmt(param);
		if (passMgmtList.size() == 0) {
			System.out.println("⚠ 보유한 수강권이 없습니다 !");
			return View.PASS_PURCHASE;
		}
		else {
			System.out.println("[ "+id+" ] 님의 유효 수강권 정보 "+" ==============");
			for (MemPassMgmtVo p : passMgmtList) {
				System.out.println("수강종료일   : "+p.getPass_end()+"\n"+	
						           "수강시작일   : "+p.getPass_str()+"\n"+
						           "잔여수강횟수: "+p.getRmn_cnt()+"/"+p.getClass_cnt());					
				System.out.println("--------------------------------------");
			}
			System.out.println("======================================");
			System.out.println("1. 지난 수강권 조회");
			System.out.println("2. 수강권 구매");
			System.out.println("3. 홈");
			int sel = ScanUtil.menu();
			switch (sel) {
			case 1:
				return View.EXPIRED_PASS;
			case 2:
				return View.PASS_PURCHASE;
			default:
				return View.MEMBER_HOME;
			}
		}	
	}	


	/*
	 * @수업 검색
	 */
	private View classSearch() {
		List<Object> param = new ArrayList<Object>();
		System.out.println("검색할 옵션을 선택하세요.");
		System.out.println("1. 수업일시");
		System.out.println("2. 강사명");
		System.out.println("3. 수업구분(요가종류)");
		System.out.println("4. 지점");
		int sel3 = ScanUtil.menu();
		if (sel3 == 1) {
			String time = ScanUtil.nextLine("▶  수강을 원하는 수업일시(0000/00/00 00:00): ");
			param.add(time);
		}
		else if (sel3 == 2) {
			String instrName = ScanUtil.nextLine("▶  수강을 원하는 강사명 : ");
			param.add(instrName);
		}
		else if (sel3 == 3) {
			String clsName = ScanUtil.nextLine("▶  수강을 원하는 수업구분(요가종류) : ");
			param.add(clsName);
		}
		else if (sel3 == 4) {
			String centerName = ScanUtil.nextLine("▶  수강을 원하는 지점 : ");
			param.add(centerName);
		}
		List<ClassSearchVo> cl = classService.classSearch(param, sel3);
		for (ClassSearchVo c : cl) {
			System.out.println("|"+"번호"+"|"+c.getClass_no()+"\t"+"|"+"일시"+"|"+c.getClass_date()+"|"+"분류"+"|"+c.getCls_name()+"\t"
			+"|"+"수업명"+"|"+c.getClass_name()+"\t"+"|"+"수용인원"+"|"+c.getAcptb_prs()+"\t"
			+"|"+"강사"+"|"+c.getInstr_name()+"\t"+"|"+"센터"+"|"+c.getCenter_name());
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
		return View.MEMBER_HOME;
	}
	
	
	/*
	 * @수강신청 취소
	 */
	private View rgstrDelete() {
		List<Object> param = new ArrayList<Object>();
		
		String id = (String) sessionStorage.get("MEM_ID");
		int rgstrNo = (int) sessionStorage.get("rgstrNo");
		param.add(id);
		param.add(rgstrNo);
		
		rgstrService.rgstrDelete(param);
		System.out.println("["+rgstrNo+"]번 수업의 수강신청이 취소되었습니다 !!");
		
		return View.RGSTR_LIST;
	}

	/*
	 * @수강신청 상세조회
	 */
	private View rgstrDetail() {
		
		List<Object> param = new ArrayList<Object>();
		String id = (String) sessionStorage.get("MEM_ID");
		param.add(id);
				
		int rgstrNo = ScanUtil.nextInt("▶  상세조회할 신청번호 입력 : ");
		param.add(rgstrNo);
		System.out.println("=========================================================================");	
		List<RgstrMgmtVo> list3 = rgstrService.rgstrDetail(param);
		
		for (RgstrMgmtVo r : list3) {
			System.out.println("신청번호:["+r.getRgstr_no()+"]\n"+
		
							   "수업일시:["+r.getClass_date()+"]\n"+
					           "수업정보:["+r.getClass_name()+"(1/"+r.getAcptb_prs()+")]\n"+
					           "수업진행:["+r.getInstr_name()+" 강사\t"+"위치:"+r.getCenter_name()+"]\n");
		}
		System.out.println("=========================================================================");
		
		System.out.println("1. 수강신청 취소");
		System.out.println("2. 수강신청 리스트로 돌아가기");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			sessionStorage.put("rgstrNo", rgstrNo);
			return View.RGSTR_DELETE;
		case 2:
			return View.RGSTR_LIST;
		default:
			return View.MEMBER_HOME;
		}
	}
	
	/*
	 * @수강신청 리스트 출력
	 */
	private View rgstrList() {
		
		String id = (String) sessionStorage.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		System.out.println("======================================[ "+id+" ] 님의 수강신청 리스트 "+" ======================================");
		System.out.println();
		List<RgstrMgmtVo> list2 = rgstrService.rgstrList(param);
		for (RgstrMgmtVo r : list2) {
			System.out.println("|"+"신청번호"+"|"+r.getRgstr_no()+"\t"+"|"+"수업일시"+"|"+r.getClass_date()+"\t"+
					"|"+"수업명"+"|"+r.getClass_name()+"\t"+"|"+"수용인원"+"|"+r.getAcptb_prs()+"\t"+
					"|"+"강사명"+"|"+r.getInstr_name()+"\t"+"|"+"지점명"+"|"+r.getCenter_name());
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
						
		System.out.println("1. 상세 조회");
		System.out.println("2. 홈");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.RGSTR_DETAIL;
		case 2:
			return View.MEMBER_HOME;
		default:
			return View.MEMBER_HOME;
		}
	}
	
	/*
	 * @수강신청 성공 후 업데이트 된 수강신청리스트 출력 및 변경된 수강권 잔여수강횟수 출력 메소드
	 */
	private View rgstrClassList() {
		
		String id = (String) sessionStorage.get("MEM_ID");
		System.out.println("["+id+"]님의 수강신청 리스트입니다.");
		System.out.println("=========================================================================");					
		// 1. 업데이트 된 수강신청리스트 출력
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		List<RgstrMgmtVo> list = rgstrService.rgstrList(param);
		for (RgstrMgmtVo r : list) {
			System.out.println("|"+"신청번호"+"|"+r.getRgstr_no()+"\t"+"|"+"수업일시"+"|"+r.getClass_date()+"\t"+
					"|"+"수업명"+"|"+r.getClass_name()+"\t"+"|"+"수용인원"+"|"+r.getAcptb_prs()+"\t"+
					"|"+"강사명"+"|"+r.getInstr_name()+"\t"+"|"+"지점명"+"|"+r.getCenter_name());
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
		// 2. 회원의 수강권관리 업데이트 - 수강횟수(RGSTR_CNT)+1,수강권의 잔여수강횟수(RMN_CNT)-1
		passService.passUpdate(param);
		System.out.println("=========================================================================");					
		System.out.println("잔여수강횟수가 변경되었습니다 ★!");
		// 3. 변경된 잔여횟수 출력
		PassMgMtVO p = passService.passUpdateList(param);
		System.out.println(p.getMem_id()+"님의 잔여수강횟수는 ["+p.getRmn_cnt()+"] 회입니다.");
		System.out.println("=========================================================================");					
		
		return View.MEMBER_HOME;
	}

	/*
	 * @수강신청
	 *  : 조건 1, 2, 3 만족하면 수강신청 성공. 
	 *  : 성공 후 수강신청 업데이트 => 수강권 업데이트
	 *  : 업데이트 된 수강권 잔여횟수 출력
	 */
	private View rgstrClass() {
		List<Object> param = new ArrayList<Object>();
		String id = (String) sessionStorage.get("MEM_ID");
		// 조건 1. 해당 회원이 보유한 수강권의 잔여수강횟수가 1이상인지 체크
		param.add(id);
		PurPassMgmtVo purPassMgmt = rgstrService.remainCnt(param);
		// => 수강권 자체가 없으면 수강권 구매 유도
		if (purPassMgmt == null) {
			System.out.println("⚠ 보유한 수강권이 없습니다 ! 수강권을 구매하세요 !");
			return View.PASS_PURCHASE;
		}
		// => 보유한 수강권의 잔여 수강횟수가 1회 이상이면 진행
		else if (purPassMgmt.getRmn_cnt() >= 1) {
			// 조건 2. 해당 수업에 자리 남아있는지 체크
			int classNo = ScanUtil.nextInt("▶  수강신청할 수업번호 입력: ");		
			param.remove(id);
			param.add(classNo);
			ClassAcptbPrsChkVo prsChk = rgstrService.acptbPrs(param);			
			if (prsChk.getCount_rgstr_no() < prsChk.getAcptb_prs()) {
				// 조건 3. 해당 회원이 해당 수업과 동일한 수업일시의 수업을 이미 신청했는지 체크
				param.clear();
				param.add(id);
				param.add(classNo);
				ClassDateChkVo dateChk = rgstrService.classDatechk(param);
				if (dateChk == null || dateChk.getClass_date().isEmpty()) {				
					rgstrService.rgstrClass(param);
					System.out.println("수강신청에 성공하셨습니다 !");
					System.out.println("========================================================================="); 					
					return View.RGSTR_CLASS_LIST;
				} 
				else {
					System.out.println("⚠ 수강신청 실패! 동일한 시간의 다른 수업을 이미 신청했습니다.");
					return View.CLASS_LIST; 
				}	
				} else {
					System.out.println("⚠ 수강신청 실패 ! 해당 수업의 수용인원을 초과했습니다.");
					return View.CLASS_LIST;
				}				
		} 
		else {
			System.out.println("⚠ 수강신청 실패! 수강권의 수강횟수를 모두 소진했습니다.");
			return View.PASS_PURCHASE;
		}
	}
	
	/*
	 * @개설 수업 리스트
	 *   : 5개 목록씩 페이징 처리
	 */
	private View classList() {
		
		int page = 1;
		if(sessionStorage.containsKey("page")) {
			page = (int) sessionStorage.get("page");
		}
		int startNum = 1+5*(page-1);
		int endNum = 5*page;	
		List<Object> param = new ArrayList<Object>();
		param.add(startNum);
		param.add(endNum);
		System.out.println("========================================== [ 개설 수업 목록 ] ==========================================");
		System.out.println();
		List<ClassSearchVo> list = classService.classList(param);
		for (ClassSearchVo c : list) {
			System.out.println("|"+"번호"+"|"+c.getClass_no()+"\t"+"|"+"일시"+"|"+c.getClass_date()+"\t"
			+"|"+"수업명"+"|"+c.getClass_name()+"\t"+"|"+"수용인원"+"|"+c.getAcptb_prs()+"\t"
			+"|"+"강사"+"|"+c.getInstr_name()+"\t"+"|"+"센터"+"|"+c.getCenter_name());
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
		System.out.println("1. 다음페이지");
		System.out.println("2. 이전페이지");
		System.out.println("3. 수강신청");
		System.out.println("4. 수업 검색");
		System.out.println("5. 홈");
	
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			page++;
			sessionStorage.put("page", page);
			return View.CLASS_LIST;
		case 2:
			if(page != 1) page--;
			sessionStorage.put("page", page);
			return View.CLASS_LIST;
		case 3:
			return View.RGSTR_CLASS;			
		case 4:
			return View.CLASS_SEARCH;
		default:
			return View.MEMBER_HOME;
		}
	}
		
		
	private View classDelete() {
		String clsNo = (String) sessionStorage.get("clsNo");
	    List<Object> param = new ArrayList<Object>();
	    param.add(clsNo);
	    
	    classService.classDelete(param);
	    System.out.println("수업이 삭제되었습니다.");
	    
	    return View.CLASS_MGMT;
	}
	
		
	private View classUpdate() {
		String clsNo = ScanUtil.nextLine("▶ 수정할 수업구분번호를 입력하세요 : ");
		sessionStorage.put("clsNo", clsNo);
	
		String clsNoUp = ScanUtil.nextLine("▶ 수업구분번호 : ");
		String clsName = ScanUtil.nextLine("▶ 수업구분: ");
		String clsNoAdd = (String) sessionStorage.get("clsNo");
		
		
		List<Object> param = new ArrayList<Object>();
		param.add(clsNoUp);
		param.add(clsName);
		param.add(clsNoAdd);
		
		classService.classUpdate(param);
		return View.CLASS_MGMT;
	}
	
	private View classInsert() {
		String clsNo = ScanUtil.nextLine("▶ 수업구분번호 : ");
		String clsName = ScanUtil.nextLine("▶ 수업구분: ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(clsNo);
		param.add(clsName);
		
		classService.classInsert(param);;
		
		return View.CLASS_MGMT;
	}

	private View classMgmt() {
		System.out.println("================================================= [ 수업관리  ] =================================================");
		System.out.println();
		List<ClassClsVo> ClassCls = classService.classCls();
		for (ClassClsVo Cvo : ClassCls) {
			System.out.println("|"+"수업구분번호"+"|"+Cvo.getCls_no()+"\t"+"|"+"수업구분"+"|"+Cvo.getCls_name()+"\t"+"|"+"사용여부"+"|"+Cvo.getUseyns());
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		}
		System.out.println("1. 등록");
		System.out.println("2. 수정");
		System.out.println("3. 삭제");
		System.out.println("4. 홈");
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.CLASS_INSERT;
		case 2:
			return View.CLASS_UPDATE;
		case 3:
			String clsNo = ScanUtil.nextLine("▶  수업구분번호를 입력해주세요: ");
			sessionStorage.put("clsNo", clsNo);
			String confirm = ScanUtil.nextLine("▶  삭제하시겠습니까?(Y/N) : ");
			if(confirm.equals("y")) {
				return View.ADMIN_RGSTR;
			}else if(confirm.equals("n")) {
				return View.CLASS_MGMT;
			}
		default:
			return View.CLASS_MGMT;
		}
	}
	
	private View adminReviewDel() {
		int reviewNo = (int) sessionStorage.get("reviewNo");
		List<Object> param = new ArrayList<Object>();
		param.add(reviewNo);
		
        reviewService.adminReviewDel(param);
        System.out.println("글이 삭제되었습니다.");
		return View.ADMIN_REVIEW;
	}
	
	
	private View adminReviewDt() {
		System.out.println("[ 후기 상세 ] ==============");
		int reviewNo = (int) sessionStorage.get("reviewNo");
		ReviewVo review = reviewService.reviewDetail(reviewNo);
		System.out.println("제목 : "+ review.getRvw_title());
		System.out.println(review.getRvw_cont());
		System.out.println("=========================================");
		System.out.println("1. 삭제");
		System.out.println("2. 리스트로 돌아가기");
		
		int sel = ScanUtil.nextInt("");
		switch (sel) {
		case 1:
			String confirm = ScanUtil.nextLine("▶  삭제하시겠습니까?(Y/N) : ");
			if(confirm.equals("y")) {
				return View.ADMIN_REVIEW_DELETE;
			}else if(confirm.equals("n")) {
				return View.ADMIN_REVIEWDT;
			}
			
		case 2:
			return View.ADMIN_REVIEW;
		default:
			return View.ADMIN_REVIEW;
		}
	
	}
	
	private View adminReview() {
		List<ReviewVo> reviewList = reviewService.reviewList();
		System.out.println("================================================= [ 후기  ] =================================================");
		System.out.println();
		for (ReviewVo RVo : reviewList) {
			System.out.println("|"+"수강번호"+"|"+RVo.getRgstr_no()+"\t"+"|"+"제목"+"|"+RVo.getRvw_title()+"\t"+"|"+"내용"+"|"+RVo.getRvw_cont()+"\t"+"|"+"별점"+"|"+RVo.getRvw_rated()+"\t"+"|"+"작성일자"+"|"+RVo.getRvw_date());
			System.out.println("-------------------------------------------------------------------------------------------------------");
		}
		System.out.println("1. 상세 조회");
		System.out.println("2. 홈");
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			int reviewNo = ScanUtil.nextInt("▶  게시판 번호를 입력해주세요: ");
			sessionStorage.put("reviewNo", reviewNo);
			return View.ADMIN_REVIEWDT;
		case 2:
			return View.ADMIN_HOME;
		default:
			return View.ADMIN_HOME;
		}
	}
	
	

	private View reviewDelete() {
		int reviewNo = (int) sessionStorage.get("reviewNo");
		List<Object> param = new ArrayList<Object>();
		param.add(reviewNo);
		
        reviewService.reviewDelete(param);
        System.out.println("글이 삭제되었습니다.");
		return View.REVIEW_HOME;
	}
	
	private View reviewInsert() {
		String id = (String) sessionStorage.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		
		 
		List<RgstrMgmtVo> list = rgstrService.rgstrRvwList(param);
		System.out.println("##수강하지 않은 수업 후기는 작성할 수 없습니다.");
		System.out.println("================================================= [ 나의 수강이력  ] =================================================");
		for (RgstrMgmtVo r : list) {
			System.out.println("|"+"신청번호"+"|"+r.getRgstr_no()+"\t"+"|"+"수업일시"+"|"+r.getClass_date()+"\t"+
					"|"+"수업명"+"|"+r.getClass_name()+"\t"+"|"+"수용인원"+"|"+r.getAcptb_prs()+"\t"+
					"|"+"강사명"+"|"+r.getInstr_name()+"\t"+"|"+"지점명"+"|"+r.getCenter_name());
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
		int rgstrNo = ScanUtil.nextInt("▶ 신청번호 : ");
		List<Object> param2 = new ArrayList<Object>();
		param2.add(rgstrNo);
		boolean chk = reviewService.reviewChek(param2); 
		if(chk) {
			System.out.println("⚠ 이미 후기를 작성한 수업입니다.");
			return View.REVIEW_HOME;
		} else if(!chk) {
			String rvwTIt = ScanUtil.nextLine("▶ 제목 : ");
			String rvwCont = ScanUtil.nextLine("▶ 내용 : ");
			String rvwRate = ScanUtil.nextLine("▶ 별점 : ");
			
			param.clear();
			param.add(rvwTIt);
			param.add(rvwCont);
			param.add(rvwRate);
			param.add(rgstrNo);
			
			reviewService.reviewInsert(param);
		}
		return View.REVIEW_HOME;
		
		
	}
	
	private View reviewDetail() {
		System.out.println("[ 후기 상세 ] ===============");
		int reviewNo = (int) sessionStorage.get("reviewNo");
		ReviewVo review = reviewService.reviewDetail(reviewNo);
		System.out.println("제목 : "+ review.getRvw_title());
		System.out.println(review.getRvw_cont());
		
		System.out.println("1. 삭제");
		System.out.println("2. 리스트로 돌아가기");
	
		int sel = ScanUtil.nextInt("메뉴 : ");
		switch (sel) {
		case 1:
			return View.REVIEW_DELETE;
		case 2:
			return View.REVIEW_HOME;
		default:
			return View.REVIEW_HOME;
		}
	}
	
	private View reviewHome() {
		List<ReviewVo> reviewList = reviewService.reviewList();
		System.out.println("================================================= [ 후기  ] =================================================");
		System.out.println();
		for (ReviewVo RVo : reviewList) {
			System.out.println("|"+"수강번호"+"|"+RVo.getRgstr_no()+"\t"+"|"+"제목"+"|"+RVo.getRvw_title()+"\t"+"|"+"내용"+"|"+RVo.getRvw_cont()+"\t"+"|"+"별점"+"|"+RVo.getRvw_rated()+"\t"+"|"+"작성일자"+"|"+RVo.getRvw_date());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
		}
		System.out.println("1. 상세 조회");
		System.out.println("2. 등록");
		System.out.println("3. 홈");
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			int reviewNo = ScanUtil.nextInt("▶  수강 번호를 입력해주세요: ");
			sessionStorage.put("reviewNo", reviewNo);
			return View.REVIEW_DETAIL;
		case 2:
			return View.REVIEW_INSERT;
		case 3:
			return View.MEMBER_HOME;
		default:
			return View.MEMBER_HOME;
		}
	}
	
	private View noticeDelete() {
		
		int noticeNo = (int) sessionStorage.get("noticeNo");
		List<Object> param = new ArrayList<Object>();
		param.add(noticeNo);
		
        noticeService.noticeDelete(param);
        System.out.println("삭제되었습니다.");
		return View.ADMIN_NOTICE;
	}
	
    private View noticeUpdate() {
		
		String title = ScanUtil.nextLine("▶ 제목 : ");
		String content = ScanUtil.nextLine("▶ 내용 : ");
		int noticeNo = (int) sessionStorage.get("noticeNo");
		
		
		
		List<Object> param = new ArrayList<Object>();
		param.add(title);
		param.add(content);
		param.add(noticeNo);
		
		noticeService.noticeUpdate(param);
		return View.ADMIN_NOTICEDT;
	}

	private View noticeInsert() {


		String ntcTIt = ScanUtil.nextLine("▶제목 : ");
		String ntcCont = ScanUtil.nextLine("▶내용 : ");
		MemberVo member = (MemberVo) sessionStorage.get("member");
		
		List<Object> param = new ArrayList<Object>();
		param.add(ntcTIt);
		param.add(ntcCont);
		param.add(member.getMem_id());
		
		noticeService.noticeInsert(param);
		
		return View.NOTICE_HOME;
	}
	
	private View adminNoticeDt() {
		System.out.println("[ 공지사항 상세 ] ==============");
		int noticeNo = (int) sessionStorage.get("noticeNo");
		NoticeVo notice = noticeService.noticeDetail(noticeNo);
		System.out.println("제목 : "+ notice.getNtc_title());
		System.out.println(notice.getNtc_cont());
		System.out.println("==========================================");
		System.out.println("1. 수정");
		System.out.println("2. 삭제");
		System.out.println("3. 리스트로 돌아가기");
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.NOTICE_UPDATE;
		case 2:
			String confirm = ScanUtil.nextLine("▶  삭제하시겠습니까?(Y/N) : ");
			if(confirm.equals("y")) {
				return View.NOTICE_DELETE;
			}else if(confirm.equals("n")) {
				return View.ADMIN_NOTICE;
			}
			
		default:
			return View.ADMIN_NOTICE;
		}
	}
	
	private View adminNotice() {
		System.out.println("================================================= [ 공지사항  ] =================================================");
		List<NoticeVo> noticeList = noticeService.noticeList();
		for (NoticeVo NVo : noticeList) {
			System.out.println("글 번호"+"\t"+NVo.getNtc_no()+"  제목"+"\t"+NVo.getNtc_title()+"  내용"+"\t"+NVo.getNtc_cont()+"  작성일자"+"\t"+NVo.getNtc_date()+"  작성자"+"\t"+NVo.getMem_id());
			System.out.println("----------------------------------------------------------------------------------------------------------");
		}
		System.out.println("==============================================================================================================");
		System.out.println("1. 상세 조회");
		System.out.println("2. 등록");
		System.out.println("3. 홈");
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			int noticeNo = ScanUtil.nextInt("▶  게시판 번호를 입력해주세요: ");
			sessionStorage.put("noticeNo", noticeNo);
			return View.ADMIN_NOTICEDT;
		case 2:
			return View.NOTICE_INSERT;
		case 3:
			return View.ADMIN_HOME;
		default:
			return View.ADMIN_HOME;
		}
	}
	
	private View noticeDetail() {

		System.out.println("[ 공지사항 상세 ] ==============");
		int noticeNo = (int) sessionStorage.get("noticeNo");
		NoticeVo notice = noticeService.noticeDetail(noticeNo);
		System.out.println("제목 : "+ notice.getNtc_title());
		System.out.println(notice.getNtc_cont());
		return View.NOTICE_HOME;
	}

	/*
	 * 공지 리스트
	 */
	private View noticeHome() {

		System.out.println("================================================= [ 공지사항  ] =================================================");
		System.out.println();
		List<NoticeVo> noticeList = noticeService.noticeList();
		
		for (NoticeVo NVo : noticeList) {
			System.out.println("|"+"글 번호"+"|"+NVo.getNtc_no()+"\t"+"|"+"제목"+"|"+NVo.getNtc_title()+"\t"+"|"+"내용"+"|"+NVo.getNtc_cont()+"\t"+"|"+"작성일자"+"|"+NVo.getNtc_date()+"\t"+"|"+ "작성자"+"|"+NVo.getMem_id());
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		}
		System.out.println("1. 상세 조회");
		System.out.println("2. 홈");

				
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			int noticeNo = ScanUtil.nextInt("▶  게시판 번호를 입력해주세요: ");
			sessionStorage.put("noticeNo", noticeNo);
			return View.NOTICE_DETAIL;
		case 2:
			return View.MEMBER_HOME;
		default:
			return View.MEMBER_HOME;
		}
	}
	
	private View adminHome() {
		System.out.println("1. 수업 관리");
		System.out.println("2. 공지사항 관리");
		System.out.println("3. 후기 관리");
		System.out.println("4. 로그아웃");
		
		int sel = ScanUtil.menu();
		
		switch (sel) {
		case 1:
			return View.CLASS_MGMT;
		case 2:
			return View.ADMIN_NOTICE;
		case 3:
			return View.ADMIN_REVIEW;
		case 4:
			sessionStorage.remove("member");
			return View.HOME;
		default:
			return View.ADMIN_HOME;
		}
	}
	
	private View memberHome() {
		
		System.out.println("1. 개설 수업리스트 조회");
		System.out.println("2. 수강신청 리스트");
		System.out.println("3. 수강권 관리");
		System.out.println("4. 후기");
		System.out.println("5. 공지사항");
		System.out.println("6. 로그아웃");
		System.out.println("7. 탈퇴");
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.CLASS_LIST;
		case 2:			
			return View.RGSTR_LIST;
		case 3:			
			return View.PASS_MGMT;
		case 4:
			return View.REVIEW_HOME;
		case 5:
			return View.NOTICE_HOME;
		case 6:
			sessionStorage.remove("member");
			return View.HOME;
		case 7:
			return View.MEMBER_DELETE;	
		default:
			return View.MEMBER_HOME;		
		}
	}
	
	private View adminLogin() {
		String id = ScanUtil.nextLine("▶ 아이디 : ");
		String pass = ScanUtil.nextLine("▶비밀번호 : ");
		
		List<Object> param = new ArrayList<Object>();
		if(id.equals("admin1") || id.equals("admin2") || id.equals("admin3")) {
			param.add(id);
			param.add(pass);
			
		} else {
			System.out.println("⚠  로그인 실패 !! 관리자가 아닙니다.");
			return View.ADMIN_LOGIN;
		}
		memberService.memberLogin(param);
		MemberVo member = (MemberVo) sessionStorage.get("member");
		System.out.println("----------------------------------");
		System.out.println("* : +     "+member.getMem_name()+"님 환영합니다!     + : *");
		System.out.println("----------------------------------");
		return View.ADMIN_HOME;
	}
	
	// member delete
    private View memberDelete() {
		
		String id = (String) sessionStorage.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
	    if (id != null) {
	        // 회원 탈퇴 서비스를 호출하여 현재 로그인한 계정을 탈퇴
	        memberService.memberDelete(param);
	        System.out.println("회원 탈퇴가 완료되었습니다.");
	    } else {
	        System.out.println("⚠ 로그인된 계정이 없습니다.");
	    }

	
		return View.HOME;
	}
	
	
	private View memberLogin() {
		System.out.println("=========== [로그인] ===========");
		String id = ScanUtil.nextLine("▶ 아이디 : ");
		String pass = ScanUtil.nextLine("▶ 비밀번호 : ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		
		boolean login = memberService.memberLogin(param);
		if(!login) {
			System.out.println("⚠ 로그인 정보를 다시 입력하세요");
			return View.MEMBER_LOGIN;
		}
		MemberVo member = (MemberVo) sessionStorage.get("member");
		System.out.println("----------------------------------");
		System.out.println("* : +     "+member.getMem_name()+"님 환영합니다!     + : *");
		System.out.println("----------------------------------");
		
		sessionStorage.put("MEM_ID", id);
		return View.MEMBER_HOME;
	}
	
	private View memberJoin() {

		System.out.println("========== [회원 가입] ==========");
		
		// 필수 항목 입력 받기
		String id = ScanUtil.nextLine("▶ 아이디 : ");
		String pass = ScanUtil.nextLine("▶비밀번호 : ");
		String name = ScanUtil.nextLine("▶이름 : ");
		
		while (id.isEmpty() || pass.isEmpty() || name.isEmpty()) {
			System.out.println("##아이디/비밀번호/이름은 필수 항목입니다.다시 입력해주세요.");
			id = ScanUtil.nextLine("▶아이디 : ");
			pass = ScanUtil.nextLine("▶비밀번호 : ");
			name = ScanUtil.nextLine("▶이름 : "); 
		}
		//선택 항목 입력 받기
		String tel = ScanUtil.nextLine("▶ 전화번호(선택) : ");
		String birth = ScanUtil.nextLine("▶생년월일(선택) : ");
		
		// 선택 항목이 비어있는 경우 NULL 처리
		if(tel.isEmpty()) {
			tel = null;
		}
		if(birth.isEmpty()) {
			birth = null;
		}
		System.out.println("회원가입이 완료되었습니다.");
		System.out.println();
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		param.add(name);
		param.add(tel);
		param.add(birth);
		memberService.memberJoin(param);
		 
		return View.MEMBER_LOGIN;
	}
	
	private View home() {
		
		System.out.println("1. 회원 로그인");
		System.out.println("2. 관리자로그인");
		System.out.println("3. 회원가입");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.MEMBER_LOGIN;
		case 2:
			return View.ADMIN_LOGIN;
		case 3:
			return View.MEMBER_JOIN;	
		default:
			return View.HOME;
		}
	}
}
