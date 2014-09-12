
insert into dbo.Room(version,name,description,defaultVolunteerSlots,changeusername,changetime)
values(0,'Giraffe','',2,'mhj',CURRENT_TIMESTAMP),
  (0,'Zebra','',2,'mhj',CURRENT_TIMESTAMP),
  (0,'Lion','',2,'mhj',CURRENT_TIMESTAMP),
  (0,'Ladybug','',2,'mhj',CURRENT_TIMESTAMP),
  (0,'Salamander','',2,'mhj',CURRENT_TIMESTAMP),
  (0,'Nest','',2,'mhj',CURRENT_TIMESTAMP);


insert into dbo.Volunteer(version,lastname,firstname,email,changeusername,changetime)
values(0,'Johnson','Markl','mj@gmail.com','mhj',CURRENT_TIMESTAMP),
(0,'Johnson','Sue','sj@gmail.com','mhj',CURRENT_TIMESTAMP);

insert into dbo.Recurrance(version, dayOfWeek, timeStart,timeEnd,changeusername,changetime)
values(0,1,'17:15:00','18:45:00','mhj',CURRENT_TIMESTAMP),
(0,2,'08:45:00','10:15:00','mhj',CURRENT_TIMESTAMP),
(0,2,'10:15:00','11:45:00','mhj',CURRENT_TIMESTAMP);

insert into dbo.ScheduledDate(version,dateScheduled,timeStart,timeEnd,changeusername,changetime)
values(0,'2014-04-19','17:15:00','18:45:00','mhj',CURRENT_TIMESTAMP),
(0,'2014-04-20','08:15:00','09:45:00','mhj',CURRENT_TIMESTAMP),
(0,'2014-04-20','09:45:00','11:15:00','mhj',CURRENT_TIMESTAMP),
(0,'2014-04-20','11:15:00','12:45:00','mhj',CURRENT_TIMESTAMP);

insert into dbo.ScheduledRoom(version,room_id,scheduledDate_id,volunteerSlots,changeusername,changetime)
values(0,1,1,2,'mhj',CURRENT_TIMESTAMP),
(0,1,2,2,'mhj',CURRENT_TIMESTAMP),
(0,1,3,2,'mhj',CURRENT_TIMESTAMP),
(0,1,4,2,'mhj',CURRENT_TIMESTAMP),
(0,2,1,2,'mhj',CURRENT_TIMESTAMP),
(0,2,2,2,'mhj',CURRENT_TIMESTAMP),
(0,2,3,2,'mhj',CURRENT_TIMESTAMP),
(0,2,4,2,'mhj',CURRENT_TIMESTAMP),
(0,3,1,2,'mhj',CURRENT_TIMESTAMP),
(0,3,2,2,'mhj',CURRENT_TIMESTAMP),
(0,3,3,2,'mhj',CURRENT_TIMESTAMP),
(0,3,4,2,'mhj',CURRENT_TIMESTAMP);

insert into dbo.VolunteerInstance(version,scheduledRoom_id,volunteer_id,changeusername,changetime)
values(0,1,1,'mhj',CURRENT_TIMESTAMP),
(0,2,2,'mhj',CURRENT_TIMESTAMP),
(0,3,2,'mhj',CURRENT_TIMESTAMP),
(0,4,1,'mhj',CURRENT_TIMESTAMP);

insert into dbo.Report(version,changeusername,changetime)
values(0,'mhj',CURRENT_TIMESTAMP),
(0,'mhj',CURRENT_TIMESTAMP);
