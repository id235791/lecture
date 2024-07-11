package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataLog {
	//com.example.demo.controller 패키지의 하위 클래스들에 경우 cut() 당함을 설정
	@Pointcut("execution(* com.example.demo.controller..*.*(..))")
	private void cut() {}
	
	//cut() 메소드가 호출되기 전에 호출
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		//실행되는 메소드의 이름 추출하기
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println(method.getName()+" 호출");
		
		//실행되는 메소드의 매개변수를 배열에 담아 가져오기
		Object[] args = joinPoint.getArgs();
		System.out.println("Parameter ");
		for(Object obj : args) {
			System.out.println(obj + "(type : "+obj.getClass().getSimpleName()+")");
		}
	}
	
	//cut() 메소드 이후에 호출
	//obj 라는 매개변수에 return 하는 값을 담아놓고 호출 
	@AfterReturning(value="cut()", returning = "obj")
	public void afterReturning(JoinPoint joinPoint, Object obj) {
		System.out.println("return : "+obj);
	}
}







