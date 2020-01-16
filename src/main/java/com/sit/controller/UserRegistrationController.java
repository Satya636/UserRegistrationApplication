package com.sit.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sit.model.UserInfo;
import com.sit.service.UserRegistrationManagementService;

/**
 * This Class is used for handling requests from user_registration page
 * 
 * @author SATYASACHI
 *
 */
@Controller
public class UserRegistrationController {

	@Autowired
	private UserRegistrationManagementService regService;

	/**
	 * This Method is used for displaying registration page
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "registerForm")
	public String displayRegistrationForm(Model model) {
		UserInfo info = new UserInfo();
		model.addAttribute("userInfo", info);
		return "user_registration";
	}

	/**
	 * This Method is used for handling register button
	 * 
	 * @param info
	 * @param attributes
	 * @return String
	 * @throws IOException
	 * @throws MessagingException
	 * @throws Exception
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String handleRegisterButton(@ModelAttribute("userInfo") UserInfo info, RedirectAttributes attributes)
			throws IOException, MessagingException, Exception {
		boolean isSaved = regService.saveUserDetails(info);
		if (isSaved) {
			System.out.println(isSaved);
			attributes.addFlashAttribute("sucessMsg",
					"Registration Is Almost Completed..Check Your Email To Unlock The Account");

		} else {
			attributes.addFlashAttribute("failureMsg", "Registration Failed");
		}

		return "redirect:/userRegistrationSucess";
	}

	/**
	 * This Method is used for Handling Double Posting Problem
	 * 
	 * @param info
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/userRegistrationSucess", method = RequestMethod.GET)
	public String contactDetailsSaveSucess(UserInfo info, Model model) {
		model.addAttribute("userInfo", info);
		return "user_registration";

	}

	/**
	 * This Method is used for validating EmailId
	 * 
	 * @param req
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/validateEmail")
	public @ResponseBody String validateEmail(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		return regService.findByUserEmail(email);
	}

}
