package vo;

import lombok.Data;

@Data
public class ReviewVo {
	private String rvw_title;
	private String rvw_cont;
	private int rvw_rated;
	private String rvw_date;
	private String rvw_delyn;
	private int rgstr_no;

}