package org.autumnridge.disciplekids.dksched.volunteer.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;

public interface VolunteerDao {
	
	/**
	 * find a volunteer with a given id
	 */
	Volunteer idVolunteer(Long id);

	/**
	 * save an idable or update it
	 */
	void saveOrUpdateVolunteer(Volunteer volunteer);

	/**
	 * List the available volunteers
	 * @return
	 */
	List<Volunteer> listVolunteers();
	
	void deleteVolunteer(Volunteer volunteer);
}
