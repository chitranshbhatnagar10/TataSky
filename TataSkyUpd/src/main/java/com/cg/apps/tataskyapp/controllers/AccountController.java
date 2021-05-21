package com.cg.apps.tataskyapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.account.dto.AccountTo;
import com.cg.apps.tataskyapp.account.dto.RechargeToForAcc;
import com.cg.apps.tataskyapp.account.dto.UserTo;
import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.account.service.IAccountService;
import com.cg.apps.tataskyapp.exception.AccountNotFoundException;
import com.cg.apps.tataskyapp.recharge.entities.Recharge;
import com.cg.apps.tataskyapp.servicerequest.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.servicerequest.entities.ServiceRequest;
import com.cg.apps.tataskyapp.user.entities.User;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {
	@Autowired
	private IAccountService aService;

//to test controller	
	@RequestMapping("/hello")
	public String sayHello() {
		return "saying hello";
	}

//to add a new account
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public Account addAccount(@RequestBody @Valid Account account) {
		return aService.add(account);
	}
/*to find account details using account id 
 * this uses AccountTo and UserTo data transfer objects
 * to remove cylic dependency because user and account
 *  have bidirectional one to one mapping 
 */
	@GetMapping("/find/id/{accountId}")
	public AccountTo findById(@PathVariable("accountId") Long accountId) throws AccountNotFoundException {
		Account acc = aService.findById(accountId);
		AccountTo accountTo = new AccountTo(acc);
		User usr = acc.getUser();
		UserTo uto = new UserTo(usr);
		accountTo.setUser(uto);
		List<Recharge> rech = acc.getRecharges();
		List<RechargeToForAcc> rechList = new ArrayList<RechargeToForAcc>();
		for (Recharge recharge : rech) {
			rechList.add(new RechargeToForAcc(recharge));
		}
		List<ServiceRequestTo> serReqTo = new ArrayList<ServiceRequestTo>();
		List<ServiceRequest> sReq = acc.getRequests();
		for (ServiceRequest serviceRequest : sReq) {
			serReqTo.add(new ServiceRequestTo(serviceRequest));
		}
		accountTo.setRequests(serReqTo);
		accountTo.setRech(rechList);
		return accountTo;
	}

/*to update an account
 * this also uses data transfer objects AccountTo and UserTo
 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PutMapping("/update")
	public AccountTo update(@RequestBody Account account) {
		Account acc = aService.update(account);
		AccountTo accountTo = new AccountTo(acc);
		User usr = acc.getUser();
		UserTo uto = new UserTo(usr);
		accountTo.setUser(uto);
		List<Recharge> rech = acc.getRecharges();
		List<RechargeToForAcc> rechList = new ArrayList<RechargeToForAcc>();
		for (Recharge recharge : rech) {
			rechList.add(new RechargeToForAcc(recharge));
		}
		List<ServiceRequestTo> serReqTo = new ArrayList<ServiceRequestTo>();
		List<ServiceRequest> sReq = acc.getRequests();
		for (ServiceRequest serviceRequest : sReq) {
			serReqTo.add(new ServiceRequestTo(serviceRequest));
		}
		accountTo.setRequests(serReqTo);
		accountTo.setRech(rechList);
		return accountTo;
	}

//to delete a user's account by account id  	
	@DeleteMapping("delete/id/{accountId}")
	public void deleteByAccountId(@PathVariable("accountId") Long accountId) {
		aService.deleteByAccountId(accountId);
	}

//returns the number of accounts created in a given period using client entered start and end date 
	@GetMapping("/count/in/period/{startDate}/{endDate}")
	public int countInPeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {
		return aService.countCreatedAccountsInPeriod(startDate, endDate);
	}


//returns total number of accounts created 
	@GetMapping("/count/accounts")
	public int countAccounts() {
		 aService.countAccounts();
		return aService.countAccounts();
	}

}
