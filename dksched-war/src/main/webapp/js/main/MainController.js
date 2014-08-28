var mainApp = angular.module('dkSched');

/**
 * Setup the application and the events that the application manages
 */
mainApp.run(function($rootScope){});


mainApp.controller("MainCtrl", function($scope, $log, $http, Notification, Week){

	$scope.week = Week.current();

	$scope.vol = "Open...";
	$scope.foo = function() {
		$scope.vol = "Miles Jagusch";
	};
	$scope.foobar = function() {
		$scope.vol = "Open...";
	};
});