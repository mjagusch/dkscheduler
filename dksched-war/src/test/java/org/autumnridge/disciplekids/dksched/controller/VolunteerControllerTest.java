package org.autumnridge.disciplekids.dksched.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.autumnridge.disciplekids.dksched.volunteer.data.VolunteerDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:app-context/test.xml",
        "classpath:app-context/embeddedDatasource.xml",
        "classpath:app-context/dataAccess.xml"
})
public class VolunteerControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    VolunteerDao dao;
    
	private VolunteerController controller = new VolunteerController();

	@Before
	public void setupTests() {
		controller.setVolunteerDao(dao);		
	}
	
	@Test
	public void testList() {
		ResponseEntity<List<Volunteer>> list = controller.query();
		assertEquals(HttpStatus.OK, list.getStatusCode());
		assertEquals(2, list.getBody().size());
	}
	
	@Test
	public void testGet() {
		ResponseEntity<Volunteer> o1 = controller.getVolunteer(1L);
		ResponseEntity<Volunteer> o2 = controller.getVolunteer(0L);
		ResponseEntity<Volunteer> o3 = controller.getVolunteer(-1L);
		assertEquals(HttpStatus.OK, o1.getStatusCode());
		assertEquals(HttpStatus.OK, o2.getStatusCode());
		assertEquals(HttpStatus.OK, o3.getStatusCode());
		assertEquals(1L, (long) o1.getBody().getId());
		assertEquals("Markl", o1.getBody().getFirstname());
		assertNull(o1.getBody().getMiddlename());
		assertEquals("Johnson", o1.getBody().getLastname());
		assertEquals("mj@gmail.com", o1.getBody().getEmail());
		assertNull(o2.getBody().getId());
		assertNull(o2.getBody().getFirstname());
		assertNull(o2.getBody().getMiddlename());
		assertNull(o2.getBody().getLastname());
		assertNull(o2.getBody().getEmail());
		assertNull(o3.getBody().getId());
		assertNull(o3.getBody().getFirstname());
		assertNull(o3.getBody().getMiddlename());
		assertNull(o3.getBody().getLastname());
		assertNull(o3.getBody().getEmail());
	}

	@Test
	public void testUpdate() {
		Volunteer o = dao.idVolunteer(1L);
		o.setFirstname("foo")
			.setMiddlename("XYZ")
			.setLastname("bar")
			.setEmail("foo@bar.com");
		
		ResponseEntity<Volunteer> re = controller.updateVolunteer(o);
		Volunteer updated = dao.idVolunteer(1L);
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals(1L, (long) re.getBody().getId());
		assertEquals("foo", re.getBody().getFirstname());
		assertEquals("XYZ", re.getBody().getMiddlename());
		assertEquals("bar", re.getBody().getLastname());
		assertEquals("foo@bar.com", re.getBody().getEmail());
		assertEquals(1L, (long) updated.getId());
		assertEquals("foo", updated.getFirstname());
		assertEquals("XYZ", updated.getMiddlename());
		assertEquals("bar", updated.getLastname());
		assertEquals("foo@bar.com", updated.getEmail());
	}

	@Test
	public void testSaveRoom() {
		Volunteer o = new Volunteer()
			.setFirstname("first")
			.setMiddlename("middle")
			.setLastname("last")
			.setEmail("x@y.com");
		
		ResponseEntity<Volunteer> re = controller.saveVolunteer(o);
		Volunteer updated = dao.idVolunteer(o.getId());
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals("first", re.getBody().getFirstname());		
		assertEquals("middle", re.getBody().getMiddlename());		
		assertEquals("last", re.getBody().getLastname());		
		assertEquals("x@y.com", re.getBody().getEmail());		
		assertEquals("first", updated.getFirstname());		
		assertEquals("middle", updated.getMiddlename());		
		assertEquals("last", updated.getLastname());		
		assertEquals("x@y.com", updated.getEmail());		
	}
	
	@Test
	public void testDelete() {
		List<Volunteer> initial = controller.query().getBody();
		
		controller.deleteVolunteer(initial.get(0).getId());
		List<Volunteer> updated = controller.query().getBody();
		
		assertEquals(initial.size()-1, updated.size());
		
		assertEquals(HttpStatus.NOT_FOUND, controller.deleteVolunteer(99).getStatusCode());
	}
}
