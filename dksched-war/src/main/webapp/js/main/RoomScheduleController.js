mainApp.controller("RoomScheduleCtrl", function($scope, $log, $http, $window, Rooms, ScheduledRooms, Notification){
	$scope.rooms = Rooms.query();

	$scope.displayRoomSchedule = function(room) {
		$scope.room = room;
		$scope.scheduledRooms = [{scheduledDate:{dateScheduled:"2014-01-01", timeStart:"09:45:00", timeEnd:"11:15:00"}}];
	};
	
	$scope.displayScheduledRoom = function(scheduledRoom) {
		$scope.scheduledRoom = scheduledRoom;
	};
});

