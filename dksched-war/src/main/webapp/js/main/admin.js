mainApp.controller("RoomCtrl", function($scope, $log, $http, $window, Rooms, Notification){

	$scope.rooms = Rooms.query();

	$scope.displayRoom = function(room) {
		$scope.room = room;
	};
	
	$scope.voidRoom = function() {
		$scope.room = undefined;
	};
	
	$scope.saveRoom = function(room) {
	    if($scope.roomEditForm.$dirty) {
	        if($scope.roomEditForm.$valid) {
	          if (room.id) {
	            Rooms.update(room, 
	            function() {
		            Notification.send({type:'success', title:'Room updated'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error updating room'});
	            });
	          } else {
		        var saved = Rooms.save(room,
		        function() {
		            Notification.send({type:'success', title:'Room saved'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error saving room'});
	            });
	            _.extend($scope.room, saved);
	            $scope.rooms.push($scope.room);
	          }
	        }else{
	          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the room and try again.'});
	        }
	    }
	};
	
	$scope.addRoom = function() {
	    $scope.room = {id: undefined, name: undefined, description: undefined, defaultVolunteerSlots: undefined};
	};
	
	$scope.deleteRoom = function(room) {
	    if ( $window.confirm('Are you sure you want to delete this room?') ) {
	    	Rooms.remove({id:room.id},
	    	function() {
	    		$scope.rooms.splice($scope.rooms.indexOf(room), 1);
	    		Notification.send({type:'success', title:'Room deleted'});
	    		$scope.voidRoom();
	    	},
	    	function() {
	    		Notification.send({type:'error', title:'Error deleting room'});
	    	});
	    }
	};
	
	$scope.incrVolunteers = function() {
		if ( $scope.room ) {
			if ( !$scope.room.defaultVolunteerSlots ) {
				$scope.room.defaultVolunteerSlots = 0;
			}
			$scope.room.defaultVolunteerSlots++;
		}
	};

	$scope.decrVolunteers = function() {
		if ( $scope.room ) {
			if ( !$scope.room.defaultVolunteerSlots ) {
				$scope.room.defaultVolunteerSlots = 2;
			}
			$scope.room.defaultVolunteerSlots--;
		}
	};
});

mainApp.controller("ScheduleCtrl", function($scope, $log, $http, $window, Recurrances, Notification){
});

mainApp.controller("RecurringScheduleCtrl", function($scope, $log, $http, $window, Recurrances, Notification){

	$scope.recurrances = Recurrances.query();

	$scope.displayRecurrance = function(recurrance) {
		$scope.recurrance = recurrance;
	};
	
	$scope.voidRecurrance = function() {
		$scope.recurrance = undefined;
	};
	
	$scope.saveRecurrance = function(recurrance) {
	    if($scope.recurranceEditForm.$dirty) {
	        if($scope.recurranceEditForm.$valid) {
	          if (recurrance.id) {
	            Recurrances.update(recurrance, 
	            function() {
		            Notification.send({type:'success', title:'Recurrance updated'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error updating recurrance'});
	            });
	          } else {
		        var saved = Recurrances.save(recurrance,
		        function() {
		            Notification.send({type:'success', title:'Recurrance saved'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error saving recurrance'});
	            });
	            _.extend($scope.recurrance, saved);
	            $scope.recurrances.push($scope.recurrance);
	          }
	        }else{
	          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the recurrance and try again.'});
	        }
	    }
	};
	
	$scope.addRecurrance = function() {
	    $scope.recurrance = {id: undefined, dayOfWeek: undefined, timeStart: undefined, timeEnd: undefined};
	};
	
	$scope.deleteRecurrance = function(recurrance) {
	    if ( $window.confirm('Are you sure you want to delete this recurrance?') ) {
	    	Recurrances.remove({id:recurrance.id},
	    	function() {
	    		console.log('VOID');
	    		$scope.voidRecurrance();
	    		$scope.recurrances.splice($scope.recurrances.indexOf(recurrance), 1);
	    		Notification.send({type:'success', title:'Recurrance deleted'});
	    	},
	    	function() {
	    		Notification.send({type:'error', title:'Error deleting recurrance'});
	    	});
	    }
	};
	
	$scope.scheduleRecurring = function() {
		// TODO:
	};
});

mainApp.controller("DateScheduleCtrl", function($scope, $log, $http, $window, ScheduledDates, Notification){

	$scope.scheduledDates = ScheduledDates.query();

	$scope.displayScheduledDate = function(scheduledDate) {
		$scope.scheduledDate = scheduledDate;
	};
	
	$scope.voidScheduledDate = function() {
		$scope.scheduledDate = undefined;
	};
	
	$scope.saveScheduledDate = function(scheduledDate) {
	    if($scope.scheduledDateEditForm.$dirty) {
	        if($scope.scheduledDateEditForm.$valid) {
	          if (scheduledDate.id) {
	            ScheduledDates.update(scheduledDate, 
	            function() {
		            Notification.send({type:'success', title:'Scheduled date updated'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error updating scheduled date'});
	            });
	          } else {
		        var saved = ScheduledDates.save(instance,
		        function() {
		            Notification.send({type:'success', title:'Scheduled date saved'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error saving scheduled date'});
	            });
	            _.extend($scope.scheduledDate, saved);
	            $scope.scheduledDates.push($scope.scheduledDate);
	          }
	        }else{
	          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the scheduled date and try again.'});
	        }
	    }
	};
	
	$scope.addScheduledDate = function() {
	    $scope.scheduledDate = {id: undefined, dateScheduled: undefined, timeStart: undefined, timeEnd: undefined};
	};
	
	$scope.deleteScheduledDate = function(scheduledDate) {
	    if ( $window.confirm('Are you sure you want to delete this scheduled date?') ) {
	    	ScheduledDates.remove({id:scheduledDate.id},
	    	function() {
	    		console.log('VOID');
	    		$scope.voidScheduledDate();
	    		$scope.scheduledDates.splice($scope.scheduledDates.indexOf(scheduledDate), 1);
	    		Notification.send({type:'success', title:'Scheduled date deleted'});
	    	},
	    	function() {
	    		Notification.send({type:'error', title:'Error deleting scheduled date'});
	    	});
	    }
	};
});

mainApp.controller("RoomScheduleCtrl", function($scope, $log, $http, $window, Rooms, RoomInstances, Notification){
	$scope.rooms = Rooms.query();

	$scope.displayRoomSchedule = function(room) {
		$scope.room = room;
		$scope.roomInstances = [{scheduledDate:{dateScheduled:"2014-01-01", timeStart:"09:45:00", timeEnd:"11:15:00"}}];
	};
	
	$scope.displayRoomInstance = function(roomInstance) {
		$scope.roomInstance = roomInstance;
	};
});

mainApp.controller("VolunteerScheduleCtrl", function($scope, $log, $http, $window, Volunteers, VolunteerInstances, Notification){
});

