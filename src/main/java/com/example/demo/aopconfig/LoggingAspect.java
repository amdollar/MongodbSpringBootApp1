package com.example.demo.aopconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	@Pointcut(value = "@annotation(execution(org.springframework.web.bind.annotation.GetMapping)")
	public void controllerAllPointCut() {

	}

	@AfterReturning(value = "controllerAllPointCut()", returning = "returningVal")
	public void afterMethodAspect(JoinPoint jp, Object returningVal) {
		System.out.println("After execution  :" + jp.getSignature().getName());
		System.out.println("Return value : " + returningVal);
	}

	// Around advice for calculating the total execution time...
	@Around(value = "controllerAllPointCut()")
	public Object executionTime(ProceedingJoinPoint pjp) {
		long currtimebx = System.currentTimeMillis();
		Object retValue = null;
		System.out.println("Started method execution : " + pjp.getSignature());
		System.out.println("Methods args :" + pjp.getArgs());
		try {
			retValue = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long currtimeafex = System.currentTimeMillis();
		System.out.println("Total execution time in milly :" + (currtimeafex - currtimebx));

		return retValue;
	}

	// Around advice for logging time of execution and method info
	@Around(value = "controllerAllPointCut()")
	public Object aroundLogging(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("aroundLogging advice executing");
		Object retval = pjp.proceed();
		;
		System.out.println("aroundLogging advice execution completed");
		return retval;
	}

}