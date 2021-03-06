package org.autumnridge.disciplekids.dksched.schedule.data;

import java.sql.Time;
import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.joda.time.LocalDate;

public interface ScheduleDao {
	
	/**
	 * find a room instance with a given id
	 */
	ScheduledRoom idScheduledRoom(Long id);

	/**
	 * Load scheduled room from date and title
	 */
	ScheduledRoom loadScheduledRoom(ScheduledDate scheduledDate, Room room);
	
	/**
	 * save an idable or update it
	 */
	void saveOrUpdateScheduledRoom(ScheduledRoom scheduledRoom);
	
	/**
	 * List the available room instances
	 * @return
	 */
	List<ScheduledRoom> listScheduledRooms(ScheduledDate scheduledDate, Room room);

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
	List<VolunteerInstance> listVolunteerInstances(ScheduledRoom scheduledRoom, Volunteer volunteer);

	void deleteScheduledRoom(ScheduledRoom scheduledRoom);

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
	
	ScheduledDate loadScheduledDate(LocalDate date, Time timeStart, Time timeEnd);

	void deleteScheduledDate(ScheduledDate scheduledDate);

	void saveOrUpdateScheduledDate(ScheduledDate scheduledDate);
	
}
