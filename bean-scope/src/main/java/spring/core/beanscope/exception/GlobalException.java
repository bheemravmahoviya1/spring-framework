package spring.core.beanscope.exception;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler({HttpMessageNotWritableException.class,HttpMessageNotWritableException.class})
	public String httpMediaTypeNotSupportedException(){
		return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
	}

}
