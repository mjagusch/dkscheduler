package org.autumnridge.disciplekids.dksched.volunteer.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VolunteerHibernate implements VolunteerDao {

	private SessionFactory sf;

	@Override
	public Volunteer idVolunteer(Long id) {
		return (Volunteer) (id == null ? null : sf.getCurrentSession().get(Volunteer.class, id));
	}

	@Override
	public void saveOrUpdateVolunteer(Volunteer volunteer) {
		sf.getCurrentSession().saveOrUpdate(volunteer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Volunteer> listVolunteers() {
		return sf.getCurrentSession().createCriteria(Volunteer.class)
				.list();
	}

	@Override
	public void deleteVolunteer(Volunteer volunteer) {
		sf.getCurrentSession().delete(volunteer);
	}

    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sf = sf;
    }
}
