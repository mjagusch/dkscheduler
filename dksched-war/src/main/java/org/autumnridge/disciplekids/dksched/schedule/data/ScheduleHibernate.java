package org.autumnridge.disciplekids.dksched.schedule.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.RoomInstance;
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
	public RoomInstance idRoomInstance(Long id) {
		return (RoomInstance) (id == null ? null : sf.getCurrentSession().get(RoomInstance.class, id));
	}

	@Override
	public void saveOrUpdateRoomInstance(RoomInstance roomInstance) {
		sf.getCurrentSession().saveOrUpdate(roomInstance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomInstance> listRoomInstances() {
		return sf.getCurrentSession().createCriteria(RoomInstance.class)
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
	public void deleteRoomInstance(RoomInstance roomInstance) {
		sf.getCurrentSession().delete(roomInstance);
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
