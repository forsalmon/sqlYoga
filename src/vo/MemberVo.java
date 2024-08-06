package vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVo {
	
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private Date mem_bir;
	private String mem_delyn;
	private String admin_yn;

}
