package com.org.makgol.stores.vo;

import lombok.Data;

@Data
public class KakaoLocalResponseVo<T> {
	public boolean success;
	public String message;
	public T result;

	public KakaoLocalResponseVo(boolean success, String message, T result) {
	        this.success = success;
	        this.message = message;
	        this.result = result;
	    }

}
