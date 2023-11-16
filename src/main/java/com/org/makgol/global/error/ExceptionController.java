package com.org.makgol.global.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	//custom예외처리
	@ExceptionHandler(NoDeliveryException.class)
	public ModelAndView noDelivery(NoDeliveryException nde) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("exception", nde);
		mView.setViewName("jsp/error/data_access");

		return mView;
	}
}
    