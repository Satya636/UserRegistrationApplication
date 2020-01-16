package com.sit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.entity.UserInfoEntity;
import com.sit.mail.EmailUtils;
import com.sit.model.UserInfo;
import com.sit.model.UserUnlockInfo;
import com.sit.repo.UserRegistrationRepo;

/**
 * This Service class implements all the methods to perform different business
 * opertions such as storing userdetails, generating random numbers,sending
 * account unlock email to users,verifying user details, unlocking user
 * accounts,sending password to user emails,and validating emails before
 * refistration.
 * 
 * @author SATYASACHI
 *
 */
@Service
public class UserRegistrationManagementImpl implements UserRegistrationManagementService {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Autowired
	private UserRegistrationRepo userRepo;

	@Autowired
	private EmailUtils emailUtil;

	/**
	 * This method is used for inserting user details to db post registration
	 * 
	 * @return boolean
	 */
	@Override
	public boolean saveUserDetails(UserInfo userInfo) throws IOException, MessagingException, Exception {
		UserInfoEntity userEntity = new UserInfoEntity();
		BeanUtils.copyProperties(userInfo, userEntity);
		userEntity.setStatus("LOCKED");
		userEntity.setPassword(randomAlphaNumeric(6));
		UserInfoEntity isSaved = userRepo.save(userEntity);
		if (isSaved.getUserId() > 0) {
			sendAccUnlockEmail(userEntity);
		}
		return userEntity.getUserId() > 0;
	}

	/**
	 * This Method is used for generating a random alphanumeric number
	 * 
	 * @param count
	 * @return String
	 */
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/**
	 * This Method is used for sending user tempPassword to user through mail for
	 * unlocking their account.
	 * 
	 * @param entity
	 * @throws Exception
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void sendAccUnlockEmail(UserInfoEntity entity) throws Exception, IOException, MessagingException {
		String to = entity.getEmail();
		String subject = "Unlock Account";
		String file = "EmailBodyTemplate.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder body = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains("${NAME}")) {
				line = line.replace("${NAME}", entity.getUserName());
			}
			if (line.contains("${PWD}")) {
				line = line.replace("${PWD}", String.valueOf(entity.getPassword()));
			}
			if (line.contains("${EMAIL}")) {
				line = line.replace("${EMAIL}", entity.getEmail());
			}
			body.append(line);
		}
		reader.close();
		emailUtil.sendEmailWithAttachment(to, subject, String.valueOf(body));
	}

	/**
	 * This method is used for verifying tempPwd given by enduser
	 * 
	 * @return boolean
	 */
	@Override
	public boolean verifyTempPwd(String email, String tempPwd) {
		UserInfoEntity userInfo = userRepo.findByemail(email);
		if (tempPwd.equals(userInfo.getPassword()) && userInfo.getStatus().equals("LOCKED")) {
			return true;
		} else
			return false;
	}

	/**
	 * This method is used for unlocking a user account
	 * 
	 * @return boolean
	 */
	@Override
	public boolean unlockUser(UserUnlockInfo info) {
		userRepo.unlockUserAccount("UNLOCKED", info.getNewPwd(), info.getEmail());
		return true;
	}

	/**
	 * This Method is used for verifying user through email and password for login
	 * 
	 * @return UserInfoEntity
	 */
	@Override
	public UserInfoEntity verifyAccount(String email, String pwd) {
		UserInfoEntity savedInfoEntity = userRepo.verifyEmailAndPassword(email, pwd);
		return savedInfoEntity;
	}

	/**
	 * This method is used for getting password based on the user email id
	 * 
	 * @return String
	 */
	@Override
	public String fetchUserPassword(String email) throws IOException, MessagingException, Exception {
		UserInfoEntity savedInfoEntity = userRepo.findByemail(email);
		StringBuilder status = new StringBuilder();
		if (savedInfoEntity == null) {
			status.append("NA");
		} else {
			if (savedInfoEntity.getStatus().equals("LOCKED"))
				status.append("LOCKED");
			else
				status.append("EMAIL_SENT");
			sendUserPwdByEmail(savedInfoEntity);

		}
		return String.valueOf(status);
	}

	/**
	 * This method is used for sending password to the user email id if user
	 * requests for forgot password
	 * 
	 * @param savedInfoEntity
	 * @throws Exception
	 * @throws IOException
	 * @throws MessagingException
	 */
	private void sendUserPwdByEmail(UserInfoEntity savedInfoEntity) throws Exception, IOException, MessagingException {
		String to = savedInfoEntity.getEmail();
		String subject = "Password Recovery";
		String file = "RetrievePasswordEmailTemplate.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder body = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains("${NAME}")) {
				line = line.replace("${NAME}", savedInfoEntity.getUserName());
			}
			if (line.contains("${EMAIL}")) {
				line = line.replace("${EMAIL}", savedInfoEntity.getEmail());
			}
			if (line.contains("${PASSWORD}")) {
				line = line.replace("${PASSWORD}", String.valueOf(savedInfoEntity.getPassword()));
			}

			body.append(line);
		}
		reader.close();
		emailUtil.sendEmailWithAttachment(to, subject, String.valueOf(body));
	}

	/**
	 * This Method is used for validating user based on the email id
	 * 
	 * @return String
	 */
	@Override
	public String findByUserEmail(String email) {
		UserInfoEntity savedEntity = userRepo.findByemail(email);
		return (savedEntity == null) ? "Unique" : "Duplicate";
	}

}
