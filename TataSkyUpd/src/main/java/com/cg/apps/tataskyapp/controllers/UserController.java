package com.cg.apps.tataskyapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cg.apps.tataskyapp.account.dto.RechargeToForAcc;
import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.recharge.entities.Recharge;
import com.cg.apps.tataskyapp.servicerequest.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.servicerequest.entities.ServiceRequest;
import com.cg.apps.tataskyapp.user.dto.AccountToUser;
import com.cg.apps.tataskyapp.user.dto.UserDto;
import com.cg.apps.tataskyapp.user.entities.User;
import com.cg.apps.tataskyapp.user.service.IUserService;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	@Autowired
	private IUserService uService;

	// to test user controller
	@RequestMapping("/test")
	public String test() {
		return "working fine";
	}

	// to find user details by entering username
	@GetMapping("/by/username/{uname}")
	public UserDto findByUsername(@PathVariable("uname") String username) {
		User usr = uService.findByUsername(username);
		UserDto userTo1 = new UserDto(usr);
		Account acc = usr.getAccount();
		AccountToUser accountTo1 = new AccountToUser(acc);
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
		accountTo1.setRequests(serReqTo);
		accountTo1.setRecharges(rechList);
		userTo1.setAccount(accountTo1);

		return userTo1;

	}

	// to add or to register a new user into database
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public UserDto register(@RequestBody @Valid User user) {
		User usr = uService.register(user);
		UserDto userTo1 = new UserDto(usr);
		Account acc = usr.getAccount();
		AccountToUser accountTo1 = new AccountToUser(acc);
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
		accountTo1.setRequests(serReqTo);
		accountTo1.setRecharges(rechList);
		userTo1.setAccount(accountTo1);
		return userTo1;
	}

	// to find user details by entering user's id
	@GetMapping("/by/id/{id}")
	public UserDto findById(@PathVariable("id") Long id) {
		User usr = uService.findById(id);
		UserDto userTo1 = new UserDto(usr);
		Account acc = usr.getAccount();
		AccountToUser accountTo1 = new AccountToUser(acc);
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
		accountTo1.setRequests(serReqTo);
		accountTo1.setRecharges(rechList);
		userTo1.setAccount(accountTo1);
		return userTo1;
	}

	// to delete user's information from the database by entering just the id
	@DeleteMapping("delete/by/id/{id}")
	public void deleteByUserId(@PathVariable("id") Long userId) {
		uService.deleteByUserId(userId);
	}
	@PutMapping("/update")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDto update(@RequestBody @Valid User user) {
		User usr= uService.update(user);
		UserDto userTo1 = new UserDto(usr);
		
		Account acc = usr.getAccount();
		AccountToUser accountTo1 = new AccountToUser(acc);
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
		accountTo1.setRequests(serReqTo);
		accountTo1.setRecharges(rechList);
		userTo1.setAccount(accountTo1);
		return userTo1;
		
	}
}