package edu.sjsu.cmpe275.aop;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

public class BlogServiceImpl implements BlogService {
	int count = 0;

	/***
	 * Following is a dummy implementation. You can tweak the implementation to suit
	 * your need, but this file is NOT part of the submission.
	 */

	public Blog readBlog(String userId, String blogUserId) throws AccessDeniedExeption, NetworkException {
		// TODO Auto-generated method stub
		System.out.printf("User %s requests to read user %s's blog\n", userId, blogUserId);
		return null;
	}

	public void shareBlog(String userId, String blogUserId, String targetUserId)
			throws AccessDeniedExeption, NetworkException {
		System.out.printf("User %s shares user %s's blog with user %s\n", userId, blogUserId, targetUserId);
		// TODO Auto-generated method stub
	}

	public void unshareBlog(String userId, String targetUserId) throws AccessDeniedExeption, NetworkException {
		// TODO Auto-generated method stub
//		count +=1;
//		if(count<3)
//			throw new NetworkException("");	
		System.out.printf("User %s unshares his/her own blog with user %s\n", userId, targetUserId);
		//throw new IllegalArgumentException();
	}

	public void commentOnBlog(String userId, String blogUserId, String message)
			throws AccessDeniedExeption, IllegalArgumentException, NetworkException {
		// TODO Auto-generated method stub
		System.out.printf("User %s commented on %s's blog\n", userId, blogUserId);
	}

}
