package org.autumnridge.disciplekids.dksched.schedule.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.RoomInstance;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;

public interface ScheduleDao {
	
	/**
	 * find a room instance with a given id
	 */
	RoomInstance idRoomInstance(Long id);

	/**
	 * save an idable or update it
	 */
	void saveOrUpdateRoomInstance(RoomInstance roomInstance);
	
	/**
	 * List the available room instances
	 * @return
	 */
	List<RoomInstance> listRoomInstances();

	/**
	 * find a volunteer instance with a given id
	 */
	VolunteerInstance idVolunteerInstance(Long id);

	/**
	 * save an idable or update it
	 */
	void saveOrUpdateVolunteerInstance(VolunteerInstance volunteerInstance);
	
	/**
	 * List the available volunteer instances
	 * @return
	 */
	List<VolunteerInstance> listVolunteerInstances();

	void deleteRoomInstance(RoomInstance roomInstance);

	void deleteVolunteerInstance(VolunteerInstance volunteerInstance);

	void deleteRecurrance(Recurrance recurrance);
	
	/**
	 * List the available room recurrances
	 * @return
	 */
	List<Recurrance> listRecurrances();

	/**
	 * find a room recurrance with a given id
	 */
	Recurrance idRecurrance(Long id);

	/**
	 * save an idable or update it
	 */
	void saveOrUpdateRecurrance(Recurrance recurrance);

	List<ScheduledDate> listScheduledDates();

	ScheduledDate idScheduledDate(Long id);

	void deleteScheduledDate(ScheduledDate scheduledDate);

	void saveOrUpdateScheduledDate(ScheduledDate scheduledDate);
	
}
