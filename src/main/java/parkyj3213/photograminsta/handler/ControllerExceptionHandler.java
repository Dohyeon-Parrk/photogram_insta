package parkyj3213.photograminsta.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import parkyj3213.photograminsta.handler.ex.CustomApiException;
import parkyj3213.photograminsta.handler.ex.CustomException;
import parkyj3213.photograminsta.handler.ex.CustomValidationException;
import parkyj3213.photograminsta.util.Script;
import parkyj3213.photograminsta.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	// js
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		} else {
			return Script.back(e.getErrorMap().toString());			
		}
	}
	
	@ExceptionHandler(CustomException.class)
	public String Exception(CustomException e) {
		return Script.back(e.getMessage());
	}

	// java
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

}
