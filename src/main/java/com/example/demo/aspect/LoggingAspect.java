// LoggingAspect.java
package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 * @author Y7993
 */
@Aspect
@Component
public class LoggingAspect {

	// 前置通知
	@Before("execution(* com.example.demo.service.UserService.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		// 访问控制示例：假设所有方法执行前都需要检查权限
		// 这里简单打印日志，实际可根据需求添加权限检查逻辑
		System.out.println("前置通知：准备执行方法 " + joinPoint.getSignature().getName());
	}

	// 后置通知
	@After("execution(* com.example.demo.service.UserService.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		// 日志记录示例：记录方法执行后的信息
		System.out.println("后置通知：方法 " + joinPoint.getSignature().getName() + " 执行完毕");
	}

	// 返回通知
	@AfterReturning(pointcut = "execution(* com.example.demo.service.UserService.get*(..))", returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
		// 记录返回值
		System.out.println("返回通知：方法 " + joinPoint.getSignature().getName() + " 返回值为 " + result);
	}

	// 异常通知
	@AfterThrowing(pointcut = "execution(* com.example.demo.service.UserService.*(..))", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
		// 记录异常信息
		System.out.println("异常通知：方法 " + joinPoint.getSignature().getName() + " 抛出异常 " + ex.getMessage());
	}

	// 环绕通知
	@Around("execution(* com.example.demo.service.UserService.getUserInfo(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("环绕通知 - 前置：准备执行方法 " + pjp.getSignature().getName());
		Object result;
		try {
			result = pjp.proceed(); // 执行目标方法
			System.out.println("环绕通知 - 返回：方法 " + pjp.getSignature().getName() + " 返回值为 " + result);
		} catch (Throwable throwable) {
			System.out.println("环绕通知 - 异常：方法 " + pjp.getSignature().getName() + " 抛出异常 " + throwable.getMessage());
			throw throwable;
		}
		System.out.println("环绕通知 - 后置：方法 " + pjp.getSignature().getName() + " 执行完毕");
		return result;
	}
}
