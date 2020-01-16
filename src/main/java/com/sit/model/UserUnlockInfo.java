package com.sit.model;

import lombok.Data;

/**
 * This model class is binded with user_registration.jsp
 * @author SATYASACHI
 *
 */
@Data
public class UserUnlockInfo {

	private Integer userId;

	private String email;

	private String tempPwd;

	private String newPwd;

	private String cnfPwd;
}
