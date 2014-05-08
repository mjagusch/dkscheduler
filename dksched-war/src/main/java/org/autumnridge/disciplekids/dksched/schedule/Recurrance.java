package org.autumnridge.disciplekids.dksched.schedule;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "Recurrance", schema = "dbo")
public class Recurrance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version = 0;

	@NotNull
	private int dayOfWeek;
	
	@NotNull
	private Time timeStart;
	
	@NotNull
	private Time timeEnd;
	
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
	
	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public Recurrance setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
		return this;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public Recurrance setTimeStart(Time timeOfDay) {
		this.timeStart = timeOfDay;
		return this;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public Recurrance setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
		return this;
	}

	public int getVolunteerSlots() {
		return volunteerSlots;
	}

	public Recurrance setVolunteerSlots(int volunteerSlots) {
		this.volunteerSlots = volunteerSlots;
		return this;
	}

	public void merge(Recurrance merged) {
		this.dayOfWeek = merged.getDayOfWeek();
		this.timeStart = merged.getTimeStart();
		this.timeEnd = merged.getTimeEnd();
		this.volunteerSlots = merged.volunteerSlots;
	}
}
