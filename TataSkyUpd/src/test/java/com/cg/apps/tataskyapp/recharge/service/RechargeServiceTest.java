package com.cg.apps.tataskyapp.recharge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.apps.tataskyapp.account.entities.Account;
import com.cg.apps.tataskyapp.pack.entities.Pack;
import com.cg.apps.tataskyapp.recharge.entities.Recharge;



@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(RechargeServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RechargeServiceTest {

	@Autowired
	RechargeServiceImpl service;
	
	@Autowired
	EntityManager em;
	@Test
	public void testListall(){
		Recharge r1=new Recharge(399.0,31,"plan for a month", "my plan 1",LocalDate.now(),Arrays.asList("hungama","nick"),true);
		Recharge r2=new Recharge(345.0,34,"Family pack","Family pack north",LocalDate.now(),Arrays.asList( "Star Bharat","Star Plus"),true);
		em.persist(r1);
		em.persist(r2);
		Recharge[] arr=new Recharge[2];
		arr[0]=r1;
		arr[1]=r2;
		List<Recharge> rlist=new ArrayList<Recharge>();
		rlist=service.listall();
		Assertions.assertEquals(Arrays.asList(r1,r2),rlist);
	}
	
	@Test
	public void testCreateRecharge(Pack pack, Account account) {
		Pack p=new Pack(399.0, 34, "Family pack","Entertainment",Arrays.asList("StarPlus","Star1"));
		Account a=new Account();
	}
}
