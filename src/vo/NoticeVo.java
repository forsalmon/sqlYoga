package vo;


import lombok.Data;

@Data
public class NoticeVo {
	private int ntc_no;
	private String ntc_cont;
	private String ntc_date;
	private String ntc_delyn;
	private String mem_id;
	private String ntc_title;
}