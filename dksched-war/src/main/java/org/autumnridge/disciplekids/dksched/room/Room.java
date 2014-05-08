package org.autumnridge.disciplekids.dksched.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.base.Strings;

@Entity
@Table(name = "Room", schema = "dbo")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version = 0;

	@NotNull
	private String name;
	
	private String description;
	
	private int defaultVolunteerSlots;
	
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
	
	public String getName() {
		return name;
	}

	public Room setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Room setDescription(String description) {
		this.description = description;
		return this;
	}

	public int getDefaultVolunteerSlots() {
		return defaultVolunteerSlots;
	}

	public Room setDefaultVolunteerSlots(int defaultVolunteerSlots) {
		this.defaultVolunteerSlots = defaultVolunteerSlots;
		return this;
	}
	
	public void merge(Room merged) {
		this.name = (Strings.isNullOrEmpty(merged.getName()) ? this.name : merged.getName());
		this.description = (Strings.isNullOrEmpty(merged.getDescription()) ? this.description : merged.getDescription());
		this.defaultVolunteerSlots = (merged.getDefaultVolunteerSlots() <= 0 ? this.defaultVolunteerSlots : merged.getDefaultVolunteerSlots());
	}
}
