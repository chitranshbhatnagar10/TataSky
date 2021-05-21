package com.cg.apps.tataskyapp.user.service;

import com.cg.apps.tataskyapp.user.entities.User;

public interface IUserService {

	// create user and his account here
	User register(User user);

	User update(User user);

	User findById(Long id);

	User findByUsername(String username);

	/**
	 * delete both user and his account
	 */
	void deleteByUserId(Long userId);

}
