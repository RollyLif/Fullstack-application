package com.learning.Learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.Learning.dto.UserInfo;

@Repository
public interface UserInfoJpaRepository extends JpaRepository <UserInfo, Long>{
	public UserInfo findByUsername(String username);
}
