package org.autumnridge.disciplekids.dksched.schedule.data;

import java.sql.Date;
import java.util.List;

import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ScheduleHibernate implements ScheduleDao {

	private SessionFactory sf;

	@Override
	public ScheduledRoom idScheduledRoom(Long id) {
		return (ScheduledRoom) (id == null ? null : sf.getCurrentSession().get(ScheduledRoom.class, id));
	}

	@Override
	public void saveOrUpdateScheduledRoom(ScheduledRoom scheduledRoom) {
		sf.getCurrentSession().saveOrUpdate(scheduledRoom);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduledRoom> listScheduledRooms(Date date) {
		return sf.getCurrentSession().createCriteria(ScheduledRoom.class)
				.list();
	}

	@Override
	public ScheduledDate idScheduledDate(Long id) {
		return (ScheduledDate) (id == null ? null : sf.getCurrentSession().get(ScheduledDate.class, id));
	}

	@Override
	public void saveOrUpdateScheduledDate(ScheduledDate scheduledDate) {
		sf.getCurrentSession().saveOrUpdate(scheduledDate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduledDate> listScheduledDates() {
		return sf.getCurrentSession().createCriteria(ScheduledDate.class)
				.list();
	}

	@Override
	public VolunteerInstance idVolunteerInstance(Long id) {
		return (VolunteerInstance) (id == null ? null : sf.getCurrentSession().get(VolunteerInstance.class, id));
	}

	@Override
	public void saveOrUpdateVolunteerInstance(
			VolunteerInstance volunteerInstance) {
		sf.getCurrentSession().saveOrUpdate(volunteerInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VolunteerInstance> listVolunteerInstances() {
		return sf.getCurrentSession().createCriteria(VolunteerInstance.class)
				.list();
	}

	@Override
	public void deleteScheduledRoom(ScheduledRoom scheduledRoom) {
		sf.getCurrentSession().delete(scheduledRoom);
	}

	@Override
	public void deleteScheduledDate(ScheduledDate scheduledDate) {
		sf.getCurrentSession().delete(scheduledDate);
	}

	@Override
	public void deleteVolunteerInstance(VolunteerInstance volunteerInstance) {
		sf.getCurrentSession().delete(volunteerInstance);
	}

    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sf = sf;
    }
    
	@Override
	public Recurrance idRecurrance(Long id) {
		return (Recurrance) (id == null ? null : sf.getCurrentSession().get(Recurrance.class, id));
	}
	
	@Override
	public void saveOrUpdateRecurrance(Recurrance recurrance) {
		sf.getCurrentSession().saveOrUpdate(recurrance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recurrance> listRecurrances() {
		return sf.getCurrentSession().createCriteria(Recurrance.class)
				.list();
	}

	@Override
	public void deleteRecurrance(Recurrance recurrance) {
		sf.getCurrentSession().delete(recurrance);
	}
}
