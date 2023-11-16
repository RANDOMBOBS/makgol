package com.org.makgol.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionController {
	//custom예외처리
	@ExceptionHandler(value = CustomException.class)
	public ModelAndView noDelivery(CustomException e) {
		log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception",e);
		mView.setViewName("error/data_access");

		return mView;
	}
    
}