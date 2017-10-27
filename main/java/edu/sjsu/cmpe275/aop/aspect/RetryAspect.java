package edu.sjsu.cmpe275.aop.aspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint; 
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

/**
 * Aspect class that handles Network Exceptions and retry concerns of blog service.
 * @author sneha
 */
@Aspect
@Order(2)
public class RetryAspect{
	/**
	 * Around: around advice to handle network exception during any method execution in blog service.
	 */
	@Around("execution(public * edu.sjsu.cmpe275.aop.BlogService.*(..))")
	public void aroundNetworkAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//System.out.println("inside retry aspect");
		try {
			//initial call
			joinPoint.proceed();
		} catch (NetworkException nwexp) {
			//retry 1
			try
			{
			System.out.println("Network failure detected! Retrying....");
			System.out.println("First retry..");
			joinPoint.proceed(); 
			System.out.println("Success!");
			}
			catch (NetworkException nwexp1) {
				//retry 2
				System.out.println("Failed!");
				try {
					System.out.println("Second retry..");
					joinPoint.proceed();
					System.out.println("Success!");
				}
				catch (NetworkException nwexp2) {
					System.out.println("Failed!");
					throw new NetworkException("Network Error! Cannot perform the operation!\n");
					// TODO: handle exception
				}
				// TODO: handle exception
			}
			// TODO: handle exception
		}
		
		
	}

	
}