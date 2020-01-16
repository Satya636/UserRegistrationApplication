package com.sit.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sit.model.UserPassword;
import com.sit.service.UserRegistrationManagementService;

/**
 * This class is used for handling requests from forgot_password page
 * 
 * @author SATYASACHI
 *
 */
@Controller
public class UserPasswordRetrieveController {

	@Autowired
	private UserRegistrationManagementService userRegService;

	/**
	 * This method is used displaying forgot_password page
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "showForgotPwdForm")
	private String showForgotPasswordForm(Model model) {
		UserPassword userPwd = new UserPassword();
		model.addAttribute("userPwd", userPwd);
		return "forgot_password";
	}

	/**
	 * This method is used for handling forgot password button
	 * 
	 * @param userPwd
	 * @param model
	 * @return String
	 * @throws IOException
	 * @throws MessagingException
	 * @throws Exception
	 */
	@RequestMapping(value = "forgotPwd", method = RequestMethod.POST)
	private String retrieveUserPassword(@ModelAttribute("userPwd") UserPassword userPwd, Model model)
			throws IOException, MessagingException, Exception {
		String accStatus = userRegService.fetchUserPassword(userPwd.getEmail());
		if (accStatus.equals("NA")) {
			model.addAttribute("NA", "Please Enter Correct Email Id");
		} else if (accStatus.equals("EMAIL_SENT")) {
			model.addAttribute("EMAIL_SENT", "Check Your Email To Get The Password");
		} else {
			model.addAttribute("LOCKED", "Your Account Is Locked");
		}
		return "forgot_password";
	}
}
