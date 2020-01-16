package com.sit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sit.constants.AppConstants;
import com.sit.model.UserUnlockInfo;
import com.sit.service.UserRegistrationManagementService;

/**
 * This Class is used for Handling Requests from user_unlock page
 * 
 * @author SATYASACHI
 *
 */
@Controller
public class UserUnlockController {

	@Autowired
	private UserRegistrationManagementService userRegService;

	/**
	 * This Method is used for displaying user_unlock page
	 * @param model
	 * @param req
	 * @return String
	 */
	@RequestMapping(value = "unlockForm")
	public String displayAccountUnlockForm(Model model, HttpServletRequest req) {
		String email = req.getParameter("email");
		UserUnlockInfo userUnlockInfo = new UserUnlockInfo();
		userUnlockInfo.setEmail(email);
		model.addAttribute("userUnlockInfo", userUnlockInfo);
		return "user_unlock";
	}

	/**
	 * This Method is used for unlock the User Account
	 * @param unlockInfo
	 * @param model
	 * @return String 
	 */
	@RequestMapping("unlockUser")
	public String unlockUserAccount(@ModelAttribute("userUnlockInfo") UserUnlockInfo unlockInfo, Model model) {
		String email = unlockInfo.getEmail();
		String tempPwd = unlockInfo.getTempPwd();
		boolean isValid = userRegService.verifyTempPwd(email, tempPwd);
		if (isValid) {
			boolean isUnlocked = userRegService.unlockUser(unlockInfo);
			if (isUnlocked)
				model.addAttribute(AppConstants.SUCCESS_MSG, "Your Account Is Unlocked..Please Continue With Login.");
		} else {
			model.addAttribute(AppConstants.FAILURE_MSG, "Pwd Not Matched");
		}
		return "user_unlock";
	}
}
