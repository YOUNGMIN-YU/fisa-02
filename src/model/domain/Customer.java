package model.domain;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.MyUtil;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class Customer {
	private int cno ;
	private char[] cname = new char[3];
	private Integer cmoney;
	private Integer mno;
	
	public String getRecord() {
		return this.cno + "\t  |"
				+ MyUtil.strMapping(this.cname) + "  |"
				+ this.cmoney + "\t  |"
				+ this.mno + "\t  |" ;
	}
	
}
