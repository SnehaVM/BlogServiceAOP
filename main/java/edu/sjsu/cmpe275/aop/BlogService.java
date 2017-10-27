package edu.sjsu.cmpe275.aop;

import edu.sjsu.cmpe275.aop.exceptions.AccessDeniedExeption;
import edu.sjsu.cmpe275.aop.exceptions.NetworkException;

/**
 * @throws AccessDeniedExeption if target blog isn't shared with current user.
 * @throws NetworkException if there is a network failure
 */
public interface BlogService {
	/**
 	* Read the blog of another user or oneself.
 	* @param userId the ID of the current user
 	* @param blogUserId the ID of user, whose blog is being requested
 	* @return the blog for blogUserId  
 	*/
	Blog readBlog(String userId, String blogUserId)  throws AccessDeniedExeption, IllegalArgumentException, NetworkException;
   
	/**
     * Comment on another user’s blog with a message.
     * @param userId the ID of the current user
     * @param blogUserId the ID of user, whose blog is being commented
     */
    void commentOnBlog(String userId, String blogUserId, String message)  throws AccessDeniedExeption, IllegalArgumentException, NetworkException;

	
	/**
 	* Share a blog with another user. The blog may or may not belong to the current user.
 	* @param userId the ID of the current user
 	* @param blogUserId the ID of the user, whose blog is being shared
 	* @param targetUserId the ID of the user to share the blog with
 	*/
	void shareBlog(String userId, String blogUserId, String targetUserId)  throws AccessDeniedExeption, IllegalArgumentException, NetworkException;
    
	/**
 	* Unshare the current user's own blog with another user.
 	* @param userId
 	* @param targetUserId
 	*/
	void unshareBlog(String userId, String targetUserId)  throws AccessDeniedExeption, IllegalArgumentException, NetworkException;
}
