package com.xd.shiro;

import com.xd.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;


public class ShiroLoginUtil
{
	 private static PasswordService passwordService = new DefaultPasswordService();
	 
	 public static boolean login(User user)
	 {
		 UsernamePasswordToken token=new UsernamePasswordToken(user.getName(),user.getPassword());
		 try{
			 SecurityUtils.getSubject().login(token);
			 SecurityUtils.getSubject().getSession().setTimeout(9000000);
		 }
		 catch(AuthenticationException e){
			 System.out.println("出错了..SecurityUtils.getSubject().login(token)");
			 return false;
		 }
		 return true;
	 }
	 public static User getCurrentUser()
	 {
		 Subject subject=SecurityUtils.getSubject();
		 User user=(User)subject.getPrincipal();
		 return user;
	 }
	 public static void logout()
	 {
		 SecurityUtils.getSubject().logout();
	 }
	 public static boolean passwordsMatch(String submittPassword,String encryptedPassword)
	 {
		 //return passwordService.passwordsMatch(submittPassword, encryptedPassword);
		 return submittPassword.equals(encryptedPassword);
	 }
	 public static String encryptPassword(String password)
	 {
		 return passwordService.encryptPassword(password);
	 }

	public static boolean isPermitted(String perm)
	{
		Subject currentUser = SecurityUtils.getSubject();
		return (currentUser.hasRole(perm));
	}

//	public static boolean isPermitted(Permission perm)
//	{
//		Subject currentUser = SecurityUtils.getSubject();
//		return (currentUser.hasRole(perm.toString()));
//	}
//
//	public static void checkPermission(Permission perm)
//	{
//		Subject currentUser = SecurityUtils.getSubject();
//		currentUser.checkPermission(perm.toString());
//	}

	public static void checkPermission(String perm)
	{
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.checkPermission(perm);
	}
}
