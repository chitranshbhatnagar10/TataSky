package com.cg.apps.tataskyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.servicerequest.dto.ServiceRequestTo;
import com.cg.apps.tataskyapp.servicerequest.service.IServiceRequestService;

@RestController
@RequestMapping("/service")
public class ServiceRequestController {

	@Autowired
	private IServiceRequestService serviceReq;

	//checking controller
	@RequestMapping("/hello")
	public String greet() {
		return "Hello from student controller";
	}
    //adding a request
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/addrequest")
	public ServiceRequestTo createServiceRequestForUser(@RequestBody Account account) {
		return new ServiceRequestTo(serviceReq.createServiceRequestForUser(account));

	}
   // closing request
	@GetMapping("/close/by/id/{id}")
	public ServiceRequestTo close(@PathVariable("id") Long serviceRequestId) {
		return new ServiceRequestTo(serviceReq.close(serviceRequestId));

	}

	//finding opened Request
	@PostMapping("/opened/requests/")
	public ServiceRequestTo openedServiceRequest(@RequestBody Account account) {
		return new ServiceRequestTo(serviceReq.openedServiceRequest(account));

	}

}
