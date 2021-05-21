package com.cg.apps.tataskyapp.servicerequest.service;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.servicerequest.entities.ServiceRequest;

public interface IServiceRequestService {

	/**
	 * create new service request only if there is not an already existing opened
	 * service request
	 */
	ServiceRequest createServiceRequestForUser(Account account);

	ServiceRequest openedServiceRequest(Account account);

	ServiceRequest close(Long serviceRequestId);
}
