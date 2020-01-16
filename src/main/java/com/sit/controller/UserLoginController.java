package com.sit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sit.entity.UserInfoEntity;
import com.sit.model.UserLoginInfo;
import com.sit.service.UserRegistrationManagementService;

/**
 * This class is used for handling requests from user_login page
 * 
 * @author SATYASACHI
 *
 */
@Controller
public class UserLoginController {

	@Autowired
	private UserRegistrationManagementService userMangService;

	/**
	 * This method is used for displaying user_login page
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/")
	private String displayLoginForm(Model model) {
		UserLoginInfo loginInfo = new UserLoginInfo();
		model.addAttribute("loginInfo", loginInfo);
		return "user_login";
	}

	/**
	 * This method is used for handling SignIn button
	 * 
	 * @param loginInfo
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "singIn", method = RequestMethod.POST)
	private String handleSigninBtn(@ModelAttribute("loginInfo") UserLoginInfo loginInfo, Model model) {
		UserInfoEntity userEntity = userMangService.verifyAccount(loginInfo.getEmail(), loginInfo.getPwd());
		if (userEntity == null) {
			model.addAttribute("loginFailed", "Invalid Credentials");
		} else if (userEntity.getStatus().equals("LOCKED")) {
			model.addAttribute("accLocked", "Your Account Is Locked");
		} else {
			return "redirect:/displayDashBoard";
		}

		return "user_login";
	}

}
