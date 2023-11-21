package com.org.makgol.stores.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreRequestMenuVo {
	
	int store_id;
	//식당 메뉴
	String menu;
	//식당 메뉴 가격
	String price;
	
	
	@Builder
	public StoreRequestMenuVo (String menu, String price ,String category) {
		this.menu = menu;
		this.price = price;
	}

}
