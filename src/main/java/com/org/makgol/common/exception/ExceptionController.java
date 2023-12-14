package com.org.makgol.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionController {
	//custom예외처리
	@ExceptionHandler(value = CustomException.class)
	public ModelAndView Error(CustomException e) {
		log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception",e);
		mView.setViewName("jsp/error/error");

		return mView;
	}
    
}