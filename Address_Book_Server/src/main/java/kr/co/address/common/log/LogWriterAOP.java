package kr.co.address.common.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description Log 처리 관련 AOP		
 */
@Aspect
@Component
public class LogWriterAOP {
	Logger logger = LoggerFactory.getLogger(LogWriterAOP.class);
	
	/**
	* @doc 				kr.co.amano.common.log.LogWriterAOP
	* @description		Controller Logging Process     
	* @param 			ProceedingJoinPoint 
	* @return			Object
	* @develop    		minjae_kang, 2019. 5. 21. 
	*/
	@Around("execution(* @org.springframework.stereotype.Controller *..*.*(..))")
	public Object controllerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		Object res = null;
		
		try {
			logger.info("********** before controller ************");
			res = joinPoint.proceed();
			logger.info("********** after controller ************");
		} catch (Exception e) {

		}

		return res;
	}

}
