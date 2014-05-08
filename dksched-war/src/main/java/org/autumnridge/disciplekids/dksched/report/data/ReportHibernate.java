package org.autumnridge.disciplekids.dksched.report.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.report.Report;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReportHibernate implements ReportDao {

	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> listReports() {
		return sf.getCurrentSession().createCriteria(Report.class)
				.list();
	}
	
	@Override
	public Report idReport(Long id) {
		return (Report) (id == null ? null : sf.getCurrentSession().get(Report.class, id));
	}

	@Override
	public void saveOrUpdateReport(Report report) {
		sf.getCurrentSession().saveOrUpdate(report);
	}

	@Override
	public void deleteReport(Report report) {
		sf.getCurrentSession().delete(report);
	}
	
    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sf = sf;
    }
}
