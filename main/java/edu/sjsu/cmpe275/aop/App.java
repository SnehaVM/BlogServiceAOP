package edu.sjsu.cmpe275.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
        /***
         * Following is a dummy implementation of App to demonstrate bean creation with Application context.
         * You may make changes to suit your need, but this file is NOT part of the submission.
         */

    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        BlogService blogService = (BlogService) ctx.getBean("blogService");

        try {
       // 	blogService.unshareBlog("Alice", "job");
        	blogService.unshareBlog(null, "alice");
         	blogService.commentOnBlog(null, null,"888");
         	blogService.shareBlog("Alice", "alice","Bob");
         	 blogService.shareBlog("bob", "alice", "alice");
        	 //blogService.unshareBlog("bob", "alice");
        	 blogService.unshareBlog("Alice", "Bob");
        	blogService.commentOnBlog("Bob", "BOB","");
        	blogService.unshareBlog("Bob", "BOB");
    	blogService.commentOnBlog("Bob", "Alice", "123 fftfyff hgjgjgj hgjgjgjgj gjgjgjgjgj ggjgjgjgjgjg ggjgjgjgjgjgjjgjg"
    			+ "bbjbjbjbjbjbjvjhjvygruegyrgfjbfjbgekhgkbnkbnkgbjhn kgbnkbknknb kbhkjbnkrbnkrnbknbknbknb "
   			+ "knbkbnkt nkjhnkrthnkrhnkrnhkrnhkrnh nknhknhkrhnkrnhknhknhkrnhknhknhkrnkrnhknkrhn nkrhnkrnhkrnhknhkrhnykjnhkr"
    			+ "lknhlynhlhjrojhiryjhohjoyjhoyjhyjh lkyjhljhlyjhljyhly"
    			+ "kjtnhrnthkrnhkrnhk");
        
            //blogService.readBlog("Bob", "Alice");
            //blogService.commentOnBlog("Bob", "Alice", "Nice work!");
            //blogService.unshareBlog("Alice", "Bob");
        } catch (Exception e) {
        	e.printStackTrace();
            //System.out.print(e.getMessage());
        }
        ctx.close();
    }
}