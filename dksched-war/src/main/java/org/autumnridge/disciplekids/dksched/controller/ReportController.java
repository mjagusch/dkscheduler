package org.autumnridge.disciplekids.dksched.controller;

import java.util.List;

import org.autumnridge.disciplekids.dksched.report.Report;
import org.autumnridge.disciplekids.dksched.report.data.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {

  private ReportDao reportDao;
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/reports", method = RequestMethod.GET)
  public ResponseEntity<List<Report>> query(){
    List<Report> reports = reportDao.listReports();
    return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
  }	
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/reports/{reportId}", method = RequestMethod.GET)
  public ResponseEntity<Report> getReport(@PathVariable long reportId){
    Report report = null;
    if ( reportId > 0 ) {
    	report = reportDao.idReport(reportId);
    } else {
    	report = new Report();
    }
    return new ResponseEntity<Report>(report, HttpStatus.OK);
  }
  
  @Autowired
  public void setReportDao(ReportDao reportDao) {
	  this.reportDao = reportDao;
  }
}
