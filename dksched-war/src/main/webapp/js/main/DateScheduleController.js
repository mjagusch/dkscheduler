mainApp.controller("DateScheduleCtrl", function($scope, $log, $http, $window, $modal, ScheduledDates, ScheduledRooms, Rooms, Notification){

	$scope.rooms = Rooms.query();
	$scope.scheduledDates = ScheduledDates.query();

	$scope.displayScheduledDate = function(selectedDate) {
		$scope.selectedDate = selectedDate;
		$scope.scheduledRooms = ScheduledRooms.query({scheduledDate_id: selectedDate.id});
		$scope.voidSelectedRoom();
	};
	
	$scope.voidSelectedDate = function() {
		$scope.selectedDate = undefined;
	};
	
	$scope.saveScheduledDate = function(selectedDate) {
	    if($scope.scheduledDateEditForm.$dirty) {
	        if($scope.scheduledDateEditForm.$valid) {
	          if (selectedDate.id) {
	            ScheduledDates.update(selectedDate, 
	            function() {
		            Notification.send({type:'success', title:'Scheduled date updated'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error updating scheduled date'});
	            });
	          } else {
		        var saved = ScheduledDates.save(selectedDate,
		        function() {
		            Notification.send({type:'success', title:'Scheduled date saved'});
	            },
	            function() {
		            Notification.send({type:'error', title:'Error saving scheduled date'});
	            });
	            _.extend($scope.selectedDate, saved);
	            $scope.scheduledDates.push($scope.selectedDate);
	          }
	        }else{
	          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the scheduled date and try again.'});
	        }
	    }
	};
	
	$scope.addScheduledDate = function() {
	    $scope.selectedDate = {id: undefined, dateScheduled: undefined, timeStart: undefined, timeEnd: undefined};
	    $scope.scheduledRooms = [];
	};
	
	$scope.deleteScheduledDate = function(selectedDate) {
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
	
	$scope.selectScheduledRoom = function(selectedRoom) {
		$scope.selectedRoom = selectedRoom;
	};
	
	$scope.voidSelectedRoom = function() {
		$scope.selectedRoom = undefined;
	};

	$scope.addScheduledRoom = function() {
		$scope.editRoom({scheduledDate: $scope.selectedDate});
	};
		
	$scope.editScheduledRoom = function(selectedRoom) {
		$scope.editRoom(selectedRoom);
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
				}
		    }
		});

		modalInstance.result.then(function (editedRoom) {
			$scope.scheduledRooms.push(editedRoom);
		}, function () {
			// TODO: do we need to do anything on exit?
		});
	};

	var RoomEditCtrl = function ($scope, $modalInstance, ScheduledRooms, room, rooms) {
		$scope.form = {}; // Work-around
        $scope.room = room;
        $scope.rooms = rooms;
        
		$scope.ok = function () {
			$scope.saveScheduledRoom($scope.room);			
		    $modalInstance.close($scope.room);
		};

		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};

		$scope.saveScheduledRoom = function(room) {
		    if($scope.form.scheduledRoomEditForm.$dirty) {
		        if($scope.form.scheduledRoomEditForm.$valid) {
		          if (room.id) {
		            ScheduledRooms.update(room, 
		            function(saved) {
			            _.extend($scope.selectedRoom, saved);
			            Notification.send({type:'success', title:'Scheduled room updated'});
		            },
		            function() {
			            Notification.send({type:'error', title:'Error updating scheduled room'});
		            });
		          } else {
			        ScheduledRooms.save(room,
			        function(saved) {
			            _.extend($scope.selectedRoom, saved);
			            Notification.send({type:'success', title:'Scheduled room saved'});
		            },
		            function() {
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