package com.sit.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * This Entity class is binded with UserRegistrationRepo
 * @author SATYASACHI
 *
 */
@Data
@Entity
@Table(name = "USER_DTLS_MASTER")
public class UserInfoEntity {

	@GeneratedValue
	@Id
	@Column(name = "USER_ID", length = 5)
	private Integer userId;

	@Column(name = "USER_NAME", length = 25)
	private String userName;

	@Column(name = "EMAIL", length = 25)
	private String email;

	@Column(name = "PASSWORD", length = 6)
	private String password;

	@Column(name = "STATUS", length = 1)
	private String status;

}
