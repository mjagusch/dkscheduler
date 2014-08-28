mainApp.controller("VolunteerCtrl", function($scope, $log, $http, Notification, Week){

	$scope.week = Week.current();
	$scope.displayVolunteer = function(volunteer){
	    $scope.volunteer = volunteer;
	    $scope.volunteer = "x";
	};
});