package parkyj3213.photograminsta.handler.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import parkyj3213.photograminsta.handler.ex.CustomValidationApiException;
import parkyj3213.photograminsta.handler.ex.CustomValidationException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class ValidationAdvice {

	// procedingJoinPoint -> profile 함수의 모든 곳에 접근 할 수 있는 변수
	// profile 보다 먼저 실행
	@Around("execution(* parkyj3213.photograminsta.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();

					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						log.info("=========================");
						log.info(error.getDefaultMessage());
						log.info("=========================");
					}
					throw new CustomValidationApiException("============= validation error", errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
	
	@Around("execution(* parkyj3213.photograminsta.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				if (bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();

					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						log.info("===================");
						log.info(error.getDefaultMessage());
						log.info("===================");
					}
					throw new CustomValidationException("============= validation error", errorMap);
				} 
			}
		}
		return proceedingJoinPoint.proceed();
	}
}





