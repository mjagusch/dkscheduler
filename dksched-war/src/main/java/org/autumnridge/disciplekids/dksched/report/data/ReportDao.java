package org.autumnridge.disciplekids.dksched.report.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.report.Report;

public interface ReportDao {

	List<Report> listReports();
	
	Report idReport(Long id);
	
	void saveOrUpdateReport(Report report);
	
	void deleteReport(Report report);
}
