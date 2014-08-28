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

