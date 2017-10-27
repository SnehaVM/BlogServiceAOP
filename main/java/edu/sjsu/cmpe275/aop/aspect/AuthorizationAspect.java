package edu.sjsu.cmpe275.aop.aspect;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.BlogService;
import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;

/**
 * Aspect class that handles authorization concerns of blog service.
 * @author sneha
 */

@Aspect
@Order(1)
public class AuthorizationAspect {
	public static HashMap<String, List<String>> sharedBlogsMap = new HashMap<String, List<String>>();

	@Autowired
	BlogService blogService;

	/**
	 * around: before advice for shareBlog() method.
	 * @throws Throwable 
	 */
	@Around("execution(public void edu.sjsu.cmpe275.aop.BlogService.shareBlog(..))")
	public void aroundShareAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		// System.out.println("inside auth aspect");
		List<String> blogList = new ArrayList<String>();
		Object[] args = joinPoint.getArgs();
		if (args.length == 3) {
			String userId = args[0].toString().toLowerCase();
			String targetUser = args[2].toString().toLowerCase();
			String blogOwner = args[1].toString().toLowerCase();
			if (!userId.equals(blogOwner)) {
				if (sharedBlogsMap.containsKey(userId)) {
					blogList = sharedBlogsMap.get(userId);
					if (!blogList.contains(blogOwner)) {
						//System.out.println("Unauthorized opertion1 - @beforeShareAdvice throwing exception!");
						throw new AccessDeniedExeption(userId + " is not authorized to share the blog!\n");
					}
				} else {
					//System.out.println("Unauthorized opertion2 - @beforeShareAdvice throwing exception!");
					throw new AccessDeniedExeption(userId + " is not authorized to share the blog!\n");
				}
			}
			if (userId.equals(blogOwner) && blogOwner.equals(targetUser)) {
				} else {
					joinPoint.proceed();
					if (sharedBlogsMap.containsKey(targetUser))
						blogList = sharedBlogsMap.get(targetUser);
					if(!blogList.contains(blogOwner)) {
						blogList.add(blogOwner);
						sharedBlogsMap.put(targetUser, blogList);
					}
					
				}
			
		} else {
			System.out.println("Invalid no.of arguments for shareBlog method");
		}

	}


	/**
	 * Around: around the execution of unshareBlog() method
	 */
	@Around("execution(public void edu.sjsu.cmpe275.aop.BlogService.unshareBlog(..))")
	public void aroundUnshareAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//System.out.println("inside before unshare");
		List<String> blogList = new ArrayList<String>();
		Object[] args = joinPoint.getArgs();
		if (args.length == 2) {
			String userId = args[0].toString().toLowerCase();
			String targetUser = args[1].toString().toLowerCase();
			if (userId.equals(targetUser)) {
			} else {
				if (!sharedBlogsMap.containsKey(targetUser)) {
					throw new AccessDeniedExeption(
							userId +" Cannot unshare! " + userId+ " 's blog is not shared with " + targetUser + "\n");
				} else {
					blogList = sharedBlogsMap.get(targetUser);
					if (!blogList.contains(userId))
						throw new AccessDeniedExeption(
								userId +" is not authorized to unshare blog from " + targetUser + "\n");
				}
			}
			if (!(userId.equals(targetUser))) {
				joinPoint.proceed();
				blogList = sharedBlogsMap.get(targetUser);
				for (int i = 0; i < blogList.size(); i++) {
					if (blogList.get(i).equals(userId)) {
						// System.out.println(blogList.get(i).toString());
						
						blogList.remove(i);
						if(!blogList.isEmpty()) {
						sharedBlogsMap.put(targetUser, blogList);
						break;
						}
						else {
							sharedBlogsMap.remove(targetUser);
						}
							
					}
				}
			}
			
		}
	}


	/**
	 * Before: before the execution of commentOnBlog() method
	 */
	@Before("execution(public void edu.sjsu.cmpe275.aop.BlogService.commentOnBlog(..))")
	public void beforeCommentAdvice(JoinPoint joinPoint) throws Throwable {
		List<String> blogList = new ArrayList<String>();
		Object[] args = joinPoint.getArgs();
		//System.out.println(sharedBlogsMap);
		if (args.length == 3) {
			String userId = args[0].toString().toLowerCase();
			String blogOwner = args[1].toString().toLowerCase();
			if (userId.equals(blogOwner)) {
			} else {
				if (!sharedBlogsMap.containsKey(userId))
					throw new AccessDeniedExeption(
							"Can't comment! " + blogOwner + "'s blog is not shared with " + userId + "\n");
				else {
					blogList = sharedBlogsMap.get(userId);
					if (!blogList.contains(blogOwner))
						throw new AccessDeniedExeption(
								"Can't comment! " + blogOwner + "'s blog is not shared with " + userId + "\n");

				}
			}
		} else {
			System.out.println("Invalid no.of arguments for commentOnBlogs method");
		}

	}

	/**
	 * Before: before the execution of readBlog() method
	 */
	@Before("execution(public edu.sjsu.cmpe275.aop.Blog edu.sjsu.cmpe275.aop.BlogService.readBlog(..))")
	public void beforeReadAdvice(JoinPoint joinPoint) throws Throwable {
		List<String> blogList = new ArrayList<String>();
		Object[] args = joinPoint.getArgs();
		if (args.length == 2) {
			String userId = args[0].toString().toLowerCase();
			String blogOwner = args[1].toString().toLowerCase();
			// System.out.println(sharedBlogsMap);
			if (!userId.equals(blogOwner)){
				if (!sharedBlogsMap.containsKey(userId))
					throw new AccessDeniedExeption("Can't read! " + blogOwner + "'s blog is not shared with " + userId + "\n");
				else {
					blogList = sharedBlogsMap.get(userId);
					if (!blogList.contains(blogOwner))
						throw new AccessDeniedExeption(
								"Can't read! " + blogOwner + "'s blog is not shared with " + userId + "\n");
				}
			}
		} else {
			System.out.println("Invalid no.of arguments for readBlog() method");
		}

	}
	

}
