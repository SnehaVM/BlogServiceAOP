package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.JoinPoint;
import java.util.Arrays;

/**
 * Aspect class that handles validation concerns of blog service.
 * @author sneha
 */
@Aspect
@Order(0)
public class ValidationAspect{
	/**
	 * Before: before the execution of all the methods in blog service
	 */
	@Before("execution(public * edu.sjsu.cmpe275.aop.BlogService.*(..))")
	public void validateAllAdvice(JoinPoint joinPoint) {
		//System.out.println("inside validation aspect");
		String userId = "";
		Object[] args = joinPoint.getArgs();
		if (joinPoint.getSignature().getName().equals("commentOnBlog")) {
			if(args[0] == null || args[1] == null)
			{
				throw new IllegalArgumentException("UserId cannot be null!\n");
			}
			if(args[2] == null)
			{
				throw new IllegalArgumentException("Comment cannot have null value!\n");
			}
			int userLength = args[0].toString().trim().length();
			int ownerLength = args[1].toString().trim().length();
			String comment = args[2].toString();
			if(comment.trim().length() == 0)
			{
				throw new IllegalArgumentException("Comment cannot be empty!\n");
			}
			if (userLength < 3 || userLength > 16 || ownerLength < 3 || ownerLength > 16) {
				throw new IllegalArgumentException("User Id must be 3 to 16 characters in length!\n");
			} else if (comment.length() > 100) {
				throw new IllegalArgumentException("comments must not exceed 100 characters!");
			} else {
				for (Object obj : Arrays.copyOf(args, args.length - 1)) {
					userId = obj.toString();
					for (int i = 0; i < userId.length(); i++) {
						if (Character.isLetter(userId.charAt(i)) == false) {
							throw new IllegalArgumentException(
									"Illegal character in User Id:" + "(" + userId + ") " + userId.charAt(i) + "!\n");
						}
					}
				}
			}

		} else {
			for (Object obj : args) {
				if(obj == null)
				{
					throw new IllegalArgumentException("UserId cannot be null!\n");
				}
				else
				{
				userId = obj.toString();
				if (userId.length() < 3 || userId.length() > 16) {
					throw new IllegalArgumentException("User Id must be 3 to 16 characters in length!\n");
				} else {
					for (int i = 0; i < userId.length(); i++) {
						if (Character.isLetter(userId.charAt(i)) == false) {
							throw new IllegalArgumentException(
									"Illegal character in User Id:" + "(" + userId + ") " + userId.charAt(i) + "!\n");
						}
					}
				}

			}
			}
		}
	}

	

}