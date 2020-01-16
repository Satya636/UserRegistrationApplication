package com.sit.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sit.entity.UserInfoEntity;

/**
 * This Interface is used for performing peristance operations
 * @author SATYASACHI
 *
 */
@Repository
public interface UserRegistrationRepo extends JpaRepository<UserInfoEntity, Integer> {

	/**
	 * This method is used for fetching userDetails based on email
	 * @param email
	 * @return UserInfoEntity
	 */
	public UserInfoEntity findByemail(String email);
	
	/**
	 * This method is used for updating the userRecords
	 * @param status
	 * @param pwd
	 * @param email
	 */
	@Transactional
	@Modifying
	@Query("update UserInfoEntity set  status=:status,password=:pwd where email=:email")
	public void unlockUserAccount(String status,String pwd,String email);
	
	/**
	 * This method is used for checking user account availability
	 * @param email
	 * @param pwd
	 * @return UserInfoEntity
	 */
	@Query(value = "from UserInfoEntity where email=:email and password=:pwd")
	public UserInfoEntity verifyEmailAndPassword(String email,String pwd);
	
	
	
	
}
