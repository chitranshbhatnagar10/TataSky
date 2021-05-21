package com.cg.apps.tataskyapp.pack.service;

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

import com.cg.apps.tataskyapp.pack.entities.Pack;
import com.cg.apps.tataskyapp.pack.service.IPackService;
import com.cg.apps.tataskyapp.pack.service.PackServiceImpl;




@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(PackServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PackServiceImplTest {
	

	@Autowired
	private IPackService service;
	@Autowired
	private EntityManager em;
	
	@Test
	public void testAddPack() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("abc","def"));
		Pack pckSaved = service.add(pck);
	    Assertions.assertEquals(pckSaved.getChannels(), pck.getChannels());	
	    
	}

	@Test
	public void testFindPackById() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("star sports","disney"));
		em.persist(pck);
		Long id = pck.getId();
		Pack pckFound = service.findPackById(id);
		Assertions.assertEquals(pckFound.getCost(), pck.getCost());
		
	}
	
	@Test
	 void testUpdatePack() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("star sports","disney"));
		Pack pckUpdated = service.update(pck);
	    Assertions.assertEquals(pckUpdated.getChannels(), pck.getChannels());	
	    
	}
	
	@Test
	void testDeleteByPackId() {
		Pack pck = new Pack(600.0, 29,"Pack for a month","Kid Pack", Arrays.asList("star sports","disney"));
		em.persist(pck);
		Long id = pck.getId();
		service.deleteByPackId(id);
		
	}
	
	@Test
	 void testfindPacksInAscendingOrderByCost() {
		Pack pck1 = new Pack(600.0, 29,"Pack for three month","Entertainment Pack", Arrays.asList("Star Movies","Sony Sab"));
		Pack pck2 = new Pack(290.0, 29,"Pack for a month","Kid Pack", Arrays.asList("Nick","disney"));
		Pack pck3 = new Pack(470.0, 29,"Pack for two month","Sports Pack", Arrays.asList("star sports","Sony Ten HD"));
		em.persist(pck1);
		em.persist(pck2);
		em.persist(pck3);
		List<Pack> pckFound1 = service.findPacksInAscendingOrderByCost();
		List<Pack> pckFound2 = service.findPacksInAscendingOrderByCost();
		Assertions.assertEquals(pckFound1.get(1), pckFound2.get(1));
		
		
	}
	
	@Test
	void testfindPacksInAscendingOrderByDaysValidity() {
		Pack pck4 = new Pack(600.0, 29,"Pack for three month","Entertainment Pack", Arrays.asList("Star Movies","Sony Sab"));
		Pack pck5 = new Pack(290.0, 29,"Pack for a month","Kid Pack", Arrays.asList("Nick","disney"));
		Pack pck6 = new Pack(470.0, 29,"Pack for two month","Sports Pack", Arrays.asList("star sports","Sony Ten HD"));
		em.persist(pck4);
		em.persist(pck5);
		em.persist(pck6);
		List<Pack> pckFound3 = service.findPacksInAscendingOrderByDaysValidity();
		List<Pack> pckFound4 = service.findPacksInAscendingOrderByDaysValidity();
		Assertions.assertEquals(pckFound3.get(2), pckFound4.get(2));
	}

}
