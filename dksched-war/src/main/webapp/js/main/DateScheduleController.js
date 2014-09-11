mainApp.controller("DateScheduleCtrl", function($scope, $log, $http, $window, $modal, ScheduledDates, ScheduledRooms, Rooms, Notification){

	$scope.rooms = Rooms.query();
	$scope.scheduledDates = ScheduledDates.query();

	$scope.displayScheduledDate = function(selectedDate) {
		$scope.selectedDate = selectedDate;
		$scope.scheduledRooms = ScheduledRooms.query({scheduledDate_id: selectedDate.id});
		$scope.voidSelectedRoom();
	};
	
	$scope.voidSelectedDate = function() {
		// TODO: hook this up
		$scope.selectedDate = undefined;
	};
	
	$scope.editScheduledDate = function(selectedDate) {
		$scope.editDate(selectedDate);
	};
	
	$scope.addScheduledDate = function() {
		$scope.selectedDate = undefined;
		$scope.editDate({});
	    $scope.scheduledRooms = [];
	};
	
	$scope.deleteScheduledDate = function(selectedDate) {
		// TODO: hook this up
	    if ( $window.confirm('Are you sure you want to delete this scheduled date?') ) {
	    	ScheduledDates.remove({id:selectedDate.id},
	    	function() {
	    		$scope.voidSelectedDate();
	    		$scope.scheduledDates.splice($scope.scheduledDates.indexOf(selectedDate), 1);
	    		Notification.send({type:'success', title:'Scheduled date deleted'});
	    	},
	    	function() {
	    		Notification.send({type:'error', title:'Error deleting scheduled date'});
	    	});
	    }
	};
	
	$scope.editDate = function (date, size) {
		var modalInstance = $modal.open({
			templateUrl: 'scheduledDateDialog.html',
		    controller: DateEditCtrl,
		    size: size,
		    resolve: {
		        date: function() {
		        	return date;
		        },
				dates: function() {
					return $scope.scheduledDates;
				}
		    }
		});

		modalInstance.result.then(function (editedDate) {
			if ( $scope.selectedDate ) {
				_.extend($scope.selectedDate, editedDate);
			} else {
				$scope.selectedDate = editedDate;				
			}
		});
	};

	var DateEditCtrl = function ($scope, $modalInstance, ScheduledDates, date, dates) {
		$scope.form = {}; // Work-around
        $scope.date = _.clone(date);
        $scope.dates = dates;
        
		$scope.ok = function () {
			$scope.saveScheduledDate($scope.date);			
		};

		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};

		$scope.saveScheduledDate = function(date) {
		    if($scope.form.scheduledDateEditForm.$dirty) {
		        if($scope.form.scheduledDateEditForm.$valid) {
		          if (date.id) {
		        	var originalDate = date.dateScheduled;
		        	date.dateScheduled = moment(date.dateScheduled, "MM/DD/YYYY").format("YYYY-MM-DD");
		            ScheduledDates.update(date, 
		            function(saved) {
			            _.extend($scope.date, saved);
			            Notification.send({type:'success', title:'Scheduled date updated'});
					    $modalInstance.close($scope.date);
		            },
		            function() {
		            	date.dateScheduled = originalDate;
			            Notification.send({type:'error', title:'Error updating scheduled date'});
		            });
		          } else {
		        	date.dateScheduled = moment(date.dateScheduled, "MM-DD-YYYY").format("YYYY-MM-DD");
			        ScheduledDates.save(date,
			        function(saved) {
			            _.extend($scope.date, saved);
						$scope.dates.push($scope.date);				
			            Notification.send({type:'success', title:'Scheduled date saved'});
					    $modalInstance.close($scope.date);
		            },
		            function() {
			            Notification.send({type:'error', title:'Error saving scheduled date'});
		            });
		          }
		        }else{
		          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the scheduled date and try again.'});
		        }
		    }
		};
	};
	
	$scope.selectScheduledRoom = function(selectedRoom) {
		$scope.selectedRoom = selectedRoom;
	};
	
	$scope.voidSelectedRoom = function() {
		// TODO: hook this up
		$scope.selectedRoom = undefined;
	};

	$scope.addScheduledRoom = function() {
		$scope.selectedRoom = undefined;
		$scope.editRoom({scheduledDate: $scope.selectedDate});
	};
		
	$scope.editScheduledRoom = function(selectedRoom) {
		$scope.editRoom(selectedRoom);
	};
		
	$scope.deleteScheduledRoom = function(selectedRoom) {
		// TODO: hook this up
	};
	
	$scope.editRoom = function (room, size) {
		var modalInstance = $modal.open({
			templateUrl: 'scheduledRoomDialog.html',
		    controller: RoomEditCtrl,
		    size: size,
		    resolve: {
		        room: function() {
		        	return room;
		        },
				rooms: function() {
					return $scope.rooms;
				},
		        scheduledRooms: function() {
		        	return $scope.scheduledRooms;
		        }
		    }
		});

		modalInstance.result.then(function (editedRoom) {
			if ( $scope.selectedRoom ) {
				_.extend($scope.selectedRoom, editedRoom);
			} else {
				$scope.selectedRoom = editedRoom;				
			}
		});
	};

	var RoomEditCtrl = function ($scope, $modalInstance, ScheduledRooms, room, rooms, scheduledRooms) {
		$scope.form = {}; // Work-around
        $scope.room = _.clone(room, true);
        $scope.rooms = rooms;
        $scope.scheduledRooms = scheduledRooms;
        
		$scope.ok = function () {
			$scope.saveScheduledRoom($scope.room);			
		};

		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};

		$scope.saveScheduledRoom = function(room) {
		    if($scope.form.scheduledRoomEditForm.$dirty) {
		        if($scope.form.scheduledRoomEditForm.$valid) {
  	        	  var originalDate = room.scheduledDate.dateScheduled = moment(room.scheduledDate.dateScheduled, "MM/DD/YYYY").format("YYYY-MM-DD");;
		          room.scheduledDate.dateScheduled = undefined;
		          if (room.id) {
		            ScheduledRooms.update(room, 
		            function(saved) {
			            _.extend($scope.room, saved);
			            Notification.send({type:'success', title:'Scheduled room updated'});
					    $modalInstance.close($scope.room);
		            },
		            function() {
		            	room.scheduledDate.dateScheduled = originalDate;
			            Notification.send({type:'error', title:'Error updating scheduled room'});
		            });
		          } else {
			        ScheduledRooms.save(room,
			        function(saved) {
			            _.extend($scope.room, saved);
						$scope.scheduledRooms.push($scope.room);				
			            Notification.send({type:'success', title:'Scheduled room saved'});
					    $modalInstance.close($scope.room);
		            },
		            function() {
		            	room.scheduledDate.dateScheduled = originalDate;
			            Notification.send({type:'error', title:'Error saving scheduled room'});
		            });
		          }
		        }else{
		          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the scheduled date and try again.'});
		        }
		    }
		};
	};	
});