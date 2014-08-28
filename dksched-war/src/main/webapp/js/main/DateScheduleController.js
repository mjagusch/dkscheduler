mainApp.controller("DateScheduleCtrl", function($scope, $log, $http, $window, $modal, ScheduledDates, ScheduledRooms, Notification){

	$scope.scheduledDates = ScheduledDates.query();

	$scope.displayScheduledDate = function(selectedDate) {
		$scope.selectedDate = selectedDate;
		$scope.scheduledRooms = ScheduledRooms.query({scheduledDate_id: selectedDate.id});
	};
	
	$scope.voidScheduledDate = function() {
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
	    		$scope.voidScheduledDate();
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
	
	$scope.addScheduledRoom = function() {
		$log.info('addRoom');
		$scope.editRoom({});
	};
		
	$scope.editScheduledRoom = function(selectedRoom) {
		$log.info('editRoom');
		$scope.editRoom(selectedRoom);
	};
		
	$scope.editRoom = function (room, size) {
		$log.info('popup: ' + JSON.stringify(room));
		var modalInstance = $modal.open({
			templateUrl: 'scheduledRoomDialog.html',
		    controller: RoomEditCtrl,
		    size: size,
		    resolve: {
		        room: function() {
		        	$log.info('ROOM PROVIDER');
		        	return room;
		        }
		    }
		});

		modalInstance.result.then(function (editedRoom) {
			$log.info('RETURN: ' + JSON.stringify(editedRoom));
			$scope.editedRoom = editedRoom;
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	var RoomEditCtrl = function ($scope, $modalInstance, room) {
        $scope.room = room;

		$scope.ok = function () {
		    $modalInstance.close($scope.room);
		};

		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};
	};	
});
