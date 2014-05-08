package org.autumnridge.disciplekids.dksched.schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "VolunteerInstance", schema = "dbo")
public class VolunteerInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version = 0;

	@ManyToOne
	@NotNull
	private RoomInstance roomInstance;
	
	@ManyToOne
	private Volunteer volunteer;
	
	@SuppressWarnings("unused")
//	@NotNull
	private String changeUsername;

	@SuppressWarnings("unused")
//	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime changeTime;

	public boolean checkVersion(int version) {
		return this.version == version;
	}

	public Long getId() {
		return id;
	}

	public RoomInstance getRoomInstance() {
		return roomInstance;
	}

	public VolunteerInstance setRoomInstance(RoomInstance roomInstance) {
		this.roomInstance = roomInstance;
		return this;
	}

	public Volunteer getVolunteer() {
		return volunteer;
	}

	public VolunteerInstance setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
		return this;
	}
}
