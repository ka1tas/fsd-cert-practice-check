package com.cts.signup.Dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.signup.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(String id);
	

}
