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
import org.joda.time.LocalDate;

@Entity
@Table(name = "ScheduledDate", schema = "dbo")
public class ScheduledDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version = 0;

	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateScheduled;
	
	@NotNull
	private Time timeStart;
	
	@NotNull
	private Time timeEnd;
	
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
	
	public LocalDate getDateScheduled() {
		return dateScheduled;
	}

	public ScheduledDate setDateScheduled(LocalDate dateScheduled) {
		this.dateScheduled = dateScheduled;
		return this;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public ScheduledDate setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
		return this;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public ScheduledDate setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
		return this;
	}
	
	public void merge(ScheduledDate merged) {
		this.dateScheduled = merged.getDateScheduled();
		this.timeStart = merged.getTimeStart();
		this.timeEnd = merged.getTimeEnd();
	}
}
