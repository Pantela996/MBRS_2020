package uns.ac.rs.mbrs.aspects;

import java.util.NoSuchElementException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Before("execution(public * findAll(..))")
	public void findAllMessage() {
		System.out.println("Testing aspect for findAll!");
	}

	@AfterThrowing("execution(* findOne(..))")
	public void afterExceptionMessage() {
		System.out.println("Exception thrown!");
	}

	@Around("execution (* uns.ac.rs.mbrs.repository.*.findAll())")
	public void catchExceptionWithMessage(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Fetching all objects");
		try {
			pjp.proceed();
		} finally {
			// TODO Auto-generated catch block
			System.out.println("Finally done!");
//			e.printStackTrace();
		}
		System.out.println("Method executed");
	}
}
