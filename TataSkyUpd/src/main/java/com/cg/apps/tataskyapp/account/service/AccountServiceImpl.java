package com.cg.apps.tataskyapp.account.service;
/*
 Account Service functions
 */

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.account.repository.IAccountRepository;
import com.cg.apps.tataskyapp.exception.AccountNotFoundException;
import com.cg.apps.tataskyapp.pack.entities.Pack;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	IAccountRepository aDao;
	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override
	public Account add(Account account) {
		logger.info("******** Account Created*****");
		Account acc = new Account(account);
		aDao.save(acc);
		return acc;
	}

	@Override
	public Account findById(Long accountId) throws AccountNotFoundException {
		logger.info("******** Account found for id *******" + accountId);
		Optional<Account> opt = aDao.findById(accountId);
		if(!opt.isPresent()) {
		throw new AccountNotFoundException("Account not found for id: "+accountId);
	} 
		return  opt.get();
	}

	public Account update(Account account) {
		logger.info("******** Updating Account**** ");
		Optional<Account> opt = aDao.findById(account.getAccountId());
		Account acc = opt.get();
		acc.copy(account);
		return aDao.save(acc);
	}

	@Override
	public void deleteByAccountId(Long accountId) {
		logger.info("******** Account Deleted for id *******" + accountId);
		aDao.deleteById(accountId);

	}

	@Override
	public int countCreatedAccountsInPeriod(LocalDate startDate, LocalDate endDate) {
		logger.info("******** countAccountsInPeriod  **** ");
		return  aDao.countCreatedAccountsInPeriod(startDate, endDate);
	}

	@Override
	public int countAccounts() {
		logger.info("******** countAccounts **** ");
		return aDao.countAccounts();
	}

	@Override
	public void removePackForAccount(Account account, Pack pack) {
		Account acc = new Account(account);
		acc.setCurrentPack(null);
		update(acc);
	}


}
