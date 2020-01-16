package com.sit.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.sit.entity.UserInfoEntity;
import com.sit.model.UserInfo;
import com.sit.model.UserUnlockInfo;

public interface UserRegistrationManagementService {

	public boolean saveUserDetails(UserInfo userInfo) throws IOException, MessagingException, Exception;
	
	public boolean verifyTempPwd(String email,String tempPwd);
	
	public boolean unlockUser(UserUnlockInfo info);
	
	public UserInfoEntity verifyAccount(String email,String pwd);
	
	public String fetchUserPassword(String email) throws IOException, MessagingException, Exception;
	
	public String findByUserEmail(String email);
}
