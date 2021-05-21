package com.cg.apps.tataskyapp.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.account.service.IAccountService;
import com.cg.apps.tataskyapp.exception.UserNotFoundException;
import com.cg.apps.tataskyapp.user.entities.User;
import com.cg.apps.tataskyapp.user.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository uDao;
	@Autowired
	private IAccountService aService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User register(User user) {
		logger.info("******** Creating User*****");
		User usr = new User(user);
		Account acc = aService.add(new Account());
		usr.setAccount(acc);
		usr = uDao.save(usr);
		acc.setUser(usr);
		System.out.println(acc);
		aService.update(acc);
		return usr;
	}

	@Override
	public User update(User user) {
		Optional<User> opt = uDao.findById(user.getId());
		User usr = opt.get();
		Account acc = usr.getAccount();
		usr.copy(user);
		usr = uDao.save(user);
		acc.setUser(usr);
		aService.update(acc);
		return usr;
	}
	@Override
	public User findById(Long id) {
		logger.info("******** Finding user by id*****" + id);
		Optional<User> opt = uDao.findById(id);
		if (!opt.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + id);
		}
		return  opt.get();
	}

	@Override
	public User findByUsername(String username) {
		logger.info("******** Finding user by username*****" + username);
		User usr = uDao.findByUsername(username);
		if (usr == null) {
			throw new UserNotFoundException("User Not found for username: " + username);
		}
		return usr;
	}

	@Override
	public void deleteByUserId(Long userId) {
		logger.info("******** delete user by id*****" + userId);
		Optional<User> opt = uDao.findById(userId);
		if (!opt.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + userId);
		}
		uDao.deleteById(userId);

	}

}
