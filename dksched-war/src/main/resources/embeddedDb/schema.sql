CREATE SCHEMA dbo;

SET IGNORECASE TRUE;

CREATE TABLE dbo.Volunteer(
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  lastname varchar(100) not null,
  firstname varchar(100) not null,
  middlename varchar(100) null,
  email varchar(100) not null,
  changeusername varchar(40) null,
  changetime DATETIME null,
  unique(lastname, firstname, middlename)
);

CREATE TABLE dbo.Room (
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  name varchar(50) not null,
  description varchar(100) null,
  defaultVolunteerSlots integer null,
  changeusername varchar(40) null,
  changetime DATETIME null,
  unique(name)
);

CREATE TABLE dbo.Recurrance(
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  dayOfWeek INTEGER not null,
  timeStart TIME not null,
  timeEnd TIME not null,
  changeusername varchar(40) null,
  changetime DATETIME null,
  unique(dayOfWeek,timeStart)
);

CREATE TABLE dbo.ScheduledDate(
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  dateScheduled DATE not null,
  timeStart TIME not null,
  timeEnd TIME not null,
  changeusername varchar(40) null,
  changetime DATETIME null,
  unique(dateScheduled,timeStart,timeEnd)
);

CREATE TABLE dbo.ScheduledRoom(
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  room_id BIGINT not null,
  scheduledDate_id BIGINT not null,
  volunteerSlots integer not null,
  changeusername varchar(40) null,
  changetime DATETIME null,
  unique(room_id,scheduledDate_id),
  CONSTRAINT fk_ScheduledRoomScheduledDate FOREIGN KEY (scheduledDate_id) REFERENCES ScheduledDate(id),
  CONSTRAINT fk_ScheduledRoomRoom FOREIGN KEY (room_id) REFERENCES Room(id)
);

CREATE TABLE dbo.VolunteerInstance(
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  scheduledRoom_id BIGINT not null,
  volunteer_id BIGINT null,
  changeusername varchar(40) null,
  changetime DATETIME null,
  unique(scheduledRoom_id,volunteer_id),
  CONSTRAINT fk_VolunteerInstanceScheduledRoom FOREIGN KEY (scheduledRoom_id) REFERENCES ScheduledRoom(id),
  CONSTRAINT fk_VolunteerInstanceVolunteer FOREIGN KEY (volunteer_id) REFERENCES Volunteer(id)
);

CREATE TABLE dbo.Report(
  id BIGINT generated BY DEFAULT AS IDENTITY,
  version integer not null,
  changeusername varchar(40) null,
  changetime DATETIME null
);
