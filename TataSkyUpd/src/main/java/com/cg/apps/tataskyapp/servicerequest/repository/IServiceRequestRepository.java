package com.cg.apps.tataskyapp.servicerequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.servicerequest.entities.ServiceRequest;

@Repository
public interface IServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

	
	@Query("FROM ServiceRequest WHERE statusOpened = 1 and account =:account")
	ServiceRequest findOpenedServiceRequest(@Param("account") Account account);
	
	
}
