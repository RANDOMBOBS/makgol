package com.org.makgol.global.error;

import org.springframework.dao.DataAccessException;

public class NoDeliveryException extends DataAccessException {
	
	//생성자의 인자로 예외메세지를 전달받아서 
	public NoDeliveryException(String msg) {
		//부모생성자에 전달하면
		super(msg);
		//message 필드에 저장된다.
	}

}