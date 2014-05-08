package org.autumnridge.disciplekids.dksched.volunteer;

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
@Table(name = "Volunteer", schema = "dbo")
public class Volunteer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private int version = 0;

	@NotNull
	private String lastname;
	
	@NotNull
	private String firstname;
	
	private String middlename;
	
	@NotNull
	private String email;
	
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

	public String getLastname() {
		return lastname;
	}

	public Volunteer setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public Volunteer setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getMiddlename() {
		return middlename;
	}

	public Volunteer setMiddlename(String middlename) {
		this.middlename = middlename;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Volunteer setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public void merge(Volunteer volunteer) {
		
	}
}
