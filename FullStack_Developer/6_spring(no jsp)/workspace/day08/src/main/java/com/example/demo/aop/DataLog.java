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
	//com.example.demo.controller 패키지의 하위 클래스들의 경우 cut() 당함을 설정
	@Pointcut("execution(* com.example.demo.controller..*.*(..))")
	private void cut() {}
	
	//cut() 메소드가 호출되기 전에 호출
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		//흐름이 짤린 메소드의 상징(ID라고 생각하면 됨)을 받아오기
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		//받은 상징을 통해 메소드 객체화
		Method method = signature.getMethod();
		//그 메소드의 이름 추출
		System.out.println(method.getName()+" 호출!");
		
		//실행되는 메소드의 매개변수를 배열에 담아 가져오기
		Object[] args = joinPoint.getArgs();
		//각 매개값들을 하나씩 반복하여 꺼내면서
		for(Object obj : args) {
			//값과 타입을 출력
			System.out.println(obj+"(type : "+obj.getClass().getSimpleName()+")");
		}
	}
	
	@AfterReturning(value = "cut()", returning = "obj")
	public void afterReturning(Object obj) {
		System.out.println("return : "+obj);
	}
	
}















