package org.autumnridge.disciplekids.dksched.volunteer.data;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.schedule.data.ScheduleDao;
import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:app-context/test.xml",
        "classpath:app-context/embeddedDatasource.xml",
        "classpath:app-context/dataAccess.xml"
})
public class VolunteerHibernateTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    VolunteerDao volunteerDao;

    @Autowired ScheduleDao scheduleDao;

    @Test
	public void testVolunteerSave() {
		Volunteer v = new Volunteer();
		v.setEmail("someone@somewhere.com");
		v.setFirstname("Test");
		v.setLastname("Volunteer");
		v.setMiddlename("Q");

		volunteerDao.saveOrUpdateVolunteer(v);
		
		Volunteer loaded = volunteerDao.idVolunteer(v.getId());
		
		assertEquals("someone@somewhere.com", loaded.getEmail());
		assertEquals("Test", loaded.getFirstname());
		assertEquals("Volunteer", loaded.getLastname());
		assertEquals("Q", loaded.getMiddlename());
	}
	
	@Test
	public void testBadId() {
		assertNull(volunteerDao.idVolunteer(null));
		assertNull(volunteerDao.idVolunteer(999L));
	}
	
	@Test
	public void testListVolunteers() {
		assertEquals(2, volunteerDao.listVolunteers().size());
	}

	@Test
	public void testDeleteVolunteer() {
		List<Volunteer> initial = volunteerDao.listVolunteers();
		int expectedSize = initial.size() - 1;
		
		List<VolunteerInstance> instances = scheduleDao.listVolunteerInstances(null, initial.get(0));
		for ( VolunteerInstance v : instances ) {
			scheduleDao.deleteVolunteerInstance(v);
		}
		
		volunteerDao.deleteVolunteer(initial.get(0));
		List<Volunteer> updated = volunteerDao.listVolunteers();
		assertEquals(expectedSize, updated.size());
	}	
}
