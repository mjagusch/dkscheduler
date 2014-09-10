package org.autumnridge.disciplekids.dksched.schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "ScheduledRoom", schema = "dbo")
public class ScheduledRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version = 0;

	@ManyToOne
	@NotNull
	private Room room;
	
	@OneToOne
	@JoinColumn(name="scheduledDate_id")
	@NotNull
	private ScheduledDate scheduledDate;
	
	@NotNull
	private int volunteerSlots;
	
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

	public void setId(Long id) {
		this.id = id;
	}
	
	public Room getRoom() {
		return room;
	}

	public ScheduledRoom setRoom(Room room) {
		this.room = room;
		return this;
	}

	public ScheduledDate getScheduledDate() {
		return scheduledDate;
	}

	public ScheduledRoom setScheduledDate(ScheduledDate scheduledDate) {
		this.scheduledDate = scheduledDate;
		return this;
	}

	public int getVolunteerSlots() {
		return volunteerSlots;
	}

	public void setVolunteerSlots(int volunteerSlots) {
		this.volunteerSlots = volunteerSlots;
	}

	public void merge(ScheduledRoom merged) {
		this.scheduledDate = merged.getScheduledDate();
		this.room = merged.getRoom();
		this.volunteerSlots = merged.volunteerSlots;
	}
}
