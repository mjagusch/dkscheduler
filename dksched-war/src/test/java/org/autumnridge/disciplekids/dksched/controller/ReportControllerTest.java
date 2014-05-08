package org.autumnridge.disciplekids.dksched.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.autumnridge.disciplekids.dksched.report.Report;
import org.autumnridge.disciplekids.dksched.report.data.ReportDao;
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
public class ReportControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ReportDao dao;
    
	private ReportController controller = new ReportController();

	@Before
	public void setupTests() {
		controller.setReportDao(dao);		
	}
	
	@Test
	public void testList() {
		ResponseEntity<List<Report>> list = controller.query();
		assertEquals(HttpStatus.OK, list.getStatusCode());
		assertEquals(2, list.getBody().size());
	}
	
	@Test
	public void testGet() {
		ResponseEntity<Report> o1 = controller.getReport(1L);
		ResponseEntity<Report> o2 = controller.getReport(0L);
		ResponseEntity<Report> o3 = controller.getReport(-1L);
		assertEquals(HttpStatus.OK, o1.getStatusCode());
		assertEquals(HttpStatus.OK, o2.getStatusCode());
		assertEquals(HttpStatus.OK, o3.getStatusCode());
		assertEquals(1L, (long) o1.getBody().getId());
		assertNull(o2.getBody().getId());
		assertNull(o3.getBody().getId());
	}
}
