package com.org.makgol.stores.vo;

import lombok.Data;

@Data
public class KakaoLocalRequestVo {
	public String x;
	public String y;
	public String keyword="";
	public int radius = 10000;
	public int size = 1;
	public int page = 1;
	
}
