package org.autumnridge.disciplekids.dksched.report.data;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.autumnridge.disciplekids.dksched.report.Report;
import org.autumnridge.disciplekids.dksched.report.data.ReportDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:app-context/test.xml",
        "classpath:app-context/embeddedDatasource.xml",
        "classpath:app-context/dataAccess.xml"
})
public class ReportHibernateTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ReportDao reportDao;
    
	@Test
	public void testReportSave() {
		Report r = new Report();

		reportDao.saveOrUpdateReport(r);
		
		Report loaded = reportDao.idReport(r.getId());
		
		assertNotNull(loaded);
		assertNotNull(loaded.getId());
	}
	
	@Test
	public void testBadId() {
		assertNull(reportDao.idReport(null));
		assertNull(reportDao.idReport(999L));
	}
	
	@Test
	public void testListReports() {
		assertEquals(2, reportDao.listReports().size());
	}
	
	@Test
	public void testDeleteReport() {
		List<Report> initial = reportDao.listReports();
		int expectedSize = initial.size() - 1;
		
		reportDao.deleteReport(initial.get(0));
		List<Report> updated = reportDao.listReports();
		assertEquals(expectedSize, updated.size());
	}	
}
