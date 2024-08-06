package vo;

import lombok.Data;

@Data
public class MemPassMgmtVo {
	private String mem_id;
	private String mem_name;
	private int pur_no;
	private String pass_no;
	private int class_cnt;
	private int rgstr_cnt;
	private int rmn_cnt;
	private String pass_str;
	private String pass_end;

}
