package com.cg.apps.tataskyapp.servicerequest.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.servicerequest.entities.ServiceRequest;
import com.cg.apps.tataskyapp.servicerequest.repository.IServiceRequestRepository;

@Service
@Transactional
public class IServiceRequestServiceImpl implements IServiceRequestService {

	@Autowired
	private IServiceRequestRepository sdao;

	@Override
	public ServiceRequest createServiceRequestForUser(Account account) {
		ServiceRequest request = new ServiceRequest(account,
				"Recharge Pending... Kindly Recharge to Continue Watching.. ");
		return sdao.save(request);
	}

	@Override
	public ServiceRequest openedServiceRequest(Account account) {
		return sdao.findOpenedServiceRequest(account);
	}

	@Override
	public ServiceRequest close(Long serviceRequestId) {
		Optional<ServiceRequest> opt = sdao.findById(serviceRequestId);
//		if (!opt.isPresent()) {
//			throw new RequestNotFoundException("request not found for id: " + serviceRequestId);
//		}
		ServiceRequest req = opt.get();
		req.setStatusOpened(false);
		sdao.save(req);
		return req;

	}

}
