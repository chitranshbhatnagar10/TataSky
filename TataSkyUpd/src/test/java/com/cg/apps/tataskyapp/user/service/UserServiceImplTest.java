package com.cg.apps.tataskyapp.user.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cg.apps.tataskyapp.user.entities.User;
import com.cg.apps.tataskyapp.user.service.IUserService;
import com.cg.apps.tataskyapp.user.service.UserServiceImpl;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(UserServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private EntityManager em;
	
	@Test
	public void testRegister() {
		User usr=new User("palkinn", "Palkin", "Arora", "Hello123@", "Customer");
		User usrSaved = service.register(usr);
		Assertions.assertEquals(usrSaved.getFirstName(), usr.getFirstName()); 
	}
	
	@Test
	public void testFindByUsername() {
		User usr=new User("aman123", "Aman", "Aggarwal", "Mypassworddd@1", "Admin");
		em.persist(usr);
		String username=usr.getUsername();
		User usrFound=service.findByUsername(username);
		Assertions.assertEquals("Aman",usrFound.getFirstName());
	}
	
	@Test
	public void testFindById() {
		User usr=new User("ross1", "Ross", "Geller", "Rosspassword@1", "Customer");
		em.persist(usr);
		Long id=usr.getId();
		User usrFound=service.findById(id);
		Assertions.assertEquals("Ross",usrFound.getFirstName());
	}

}
