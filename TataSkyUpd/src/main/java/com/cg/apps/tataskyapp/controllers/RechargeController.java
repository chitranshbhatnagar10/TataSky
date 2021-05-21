package com.cg.apps.tataskyapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.pack.entities.Pack;
import com.cg.apps.tataskyapp.recharge.dto.AccountToForRec;
import com.cg.apps.tataskyapp.recharge.dto.AddRequest;
import com.cg.apps.tataskyapp.recharge.dto.RechargeTo;
import com.cg.apps.tataskyapp.recharge.dto.UserToForRech;
import com.cg.apps.tataskyapp.recharge.entities.Recharge;
import com.cg.apps.tataskyapp.recharge.service.RechargeServiceImpl;
import com.cg.apps.tataskyapp.servicerequest.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.servicerequest.entities.ServiceRequest;
import com.cg.apps.tataskyapp.user.entities.User;

@RestController
@RequestMapping("/recharge")
public class RechargeController {

	@Autowired
	private RechargeServiceImpl rService;

//to test controller
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello! from Spring Rest server";
	}

//to list out all the existing recharges
	@GetMapping("/listall")
	public List<RechargeTo> listall() {
		List<Recharge> rechList = rService.listall();
		List<RechargeTo> rechToList = new ArrayList<RechargeTo>();
		for (Recharge rl : rechList) {
			RechargeTo rechTo = new RechargeTo(rl);
			Account acc = rl.getAccount();
			AccountToForRec accTo = new AccountToForRec(acc);
			User usr = acc.getUser();
			UserToForRech uto = new UserToForRech(usr);
			accTo.setUser(uto);
			List<ServiceRequestTo> serList = new ArrayList<ServiceRequestTo>();
			List<ServiceRequest> serListCurr = acc.getRequests();
			for (ServiceRequest serviceRequest : serListCurr) {
				ServiceRequestTo newSerReq = new ServiceRequestTo(serviceRequest);
				serList.add(newSerReq);
			}
			accTo.setRequests(serList);
			rechTo.setAccount(accTo);
			rechToList.add(rechTo);
		}
		return rechToList;
	}

	/*
	 * to create a new recharge, it takes both Account and Pack as parameters to
	 * recharge an account using xyz pack
	 */
	@PostMapping("/add")
	public RechargeTo createRecharge(@RequestBody AddRequest request) {

		Pack pck = new Pack(request.getPackId(), request.getCost(), request.getDaysValidity(), request.getDescription(),
				request.getPlanName(), request.getChannels());
		Account acc = new Account(request.getAccountId(), request.getUser(), request.getRecharges(),
				request.getRegisteredDate(), request.getRequests(), request.getCurrentPack());
		Recharge rech = rService.createRecharge(pck, acc);
		RechargeTo rechTo = new RechargeTo(rech);
		Account acc1 = rech.getAccount();
		AccountToForRec accTo = new AccountToForRec(acc1);
		User usr = acc1.getUser();
		UserToForRech uto = new UserToForRech(usr);
		accTo.setUser(uto);
		List<ServiceRequestTo> serList = new ArrayList<ServiceRequestTo>();
		List<ServiceRequest> serListCurr = acc.getRequests();
		for (ServiceRequest serviceRequest : serListCurr) {
			serList.add(new ServiceRequestTo(serviceRequest));
		}
		accTo.setRequests(serList);
		rechTo.setAccount(accTo);
		return rechTo;
	}

//to update an existing recharge
	@PutMapping("/update")
	public RechargeTo updateRecharge(@RequestBody Recharge recharge) {
		Recharge rech = rService.update(recharge);
		RechargeTo rechTo = new RechargeTo(rech);
		Account acc = rech.getAccount();
		AccountToForRec accTo = new AccountToForRec(acc);
		User usr = acc.getUser();
		UserToForRech uto = new UserToForRech(usr);
		accTo.setUser(uto);
		List<ServiceRequestTo> serList = new ArrayList<ServiceRequestTo>();
		List<ServiceRequest> serListCurr = acc.getRequests();
		for (ServiceRequest serviceRequest : serListCurr) {
			serList.add(new ServiceRequestTo(serviceRequest));
		}
		accTo.setRequests(serList);
		rechTo.setAccount(accTo);
		return rechTo;
	}

//lists user recharges from latest to oldest 
	@PostMapping("/rechargeDesc")
	public List<RechargeTo> findRechargesForUserInDescendingOrderByPurchasedDate(@RequestBody Account account) {
		List<Recharge> rechList = rService.findRechargesForUserInDescendingOrderByPurchasedDate(account);
		List<RechargeTo> rechToList = new ArrayList<RechargeTo>();
		for (Recharge rl : rechList) {
			RechargeTo rechTo = new RechargeTo(rl);
			Account acc = rl.getAccount();
			AccountToForRec accTo = new AccountToForRec(acc);
			User usr = acc.getUser();
			UserToForRech uto = new UserToForRech(usr);
			accTo.setUser(uto);
			List<ServiceRequestTo> serList = new ArrayList<ServiceRequestTo>();
			List<ServiceRequest> serListCurr = acc.getRequests();
			for (ServiceRequest serviceRequest : serListCurr) {
				serList.add(new ServiceRequestTo(serviceRequest));
			}
			accTo.setRequests(serList);
			rechTo.setAccount(accTo);
			rechToList.add(rechTo);
		}
		return rechToList;
	}

//returns number of recharges done on an account	
	@PostMapping("/userRechCount")
	public int rechargesForUserCount(@RequestBody Account account) {
		int rechCount = rService.rechargesForUserCount(account);
		return rechCount;
	}

//list of recharges done in a specific period
	@GetMapping("/rechargesInPeriod/{startDate}/{endDate}")
	public List<RechargeTo> findAllRechargesInPeriod(
			@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {
		List<Recharge> rechList = rService.findAllRechargesInPeriod(startDate, endDate);
		List<RechargeTo> rechToList = new ArrayList<RechargeTo>();
		for (Recharge rl : rechList) {
			RechargeTo rechTo = new RechargeTo(rl);
			Account acc = rl.getAccount();
			AccountToForRec accTo = new AccountToForRec(acc);
			User usr = acc.getUser();
			UserToForRech uto = new UserToForRech(usr);
			accTo.setUser(uto);
			List<ServiceRequestTo> serList = new ArrayList<ServiceRequestTo>();
			List<ServiceRequest> serListCurr = acc.getRequests();
			for (ServiceRequest serviceRequest : serListCurr) {
				serList.add(new ServiceRequestTo(serviceRequest));
			}
			accTo.setRequests(serList);
			rechTo.setAccount(accTo);
			rechToList.add(rechTo);
		}
		return rechToList;
	}

//returns the number of recharges done in a specific period mentioned at the client end
	@GetMapping("/countRechInPeriod/{startDate}/{endDate}")
	public int countRechargesInPeriod(
			@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {

		return rService.countRechargesInPeriod(startDate, endDate);
	}

//returns the total amount generated in a specific time period
	@GetMapping("/totalrevenue/{startDate}/{endDate}")
	public double totalRevenueInPeriod(
			@PathVariable("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {
		return rService.totalRevenueInPeriod(startDate, endDate);
	}

//returns the no. of recharges done on a specific pack
	@PostMapping("/countrecharge")
	public int rechargesCount(@RequestBody Pack pack) {
		return  rService.rechargesCount(pack);
	}

}
