

app={
	contextPath:"/dksched",
		
		giveFocusToFirstTextInput : function($container) {
			var foundInput = $container.find(':input').first();

			if($(foundInput).is(":visible")){
				foundInput[0].focus();
			}
			return foundInput;
		}
};

var dkSched = angular.module('dkSched', [
  'ngRoute',
  'ngResource',
  'ui.bootstrap',
  'ui.bootstrap.modal',
  'ui.utils',
  'checklist-model']);

dkSched.config(['$routeProvider', function($routeProvider) {
		$routeProvider
			.when('/main/', {
				templateUrl : 'partials/main.tpl.html',
				controller : 'MainCtrl'
			})
			.when('/manage/volunteers/', {
				templateUrl : 'partials/volunteers.tpl.html',
				controller : 'VolunteerCtrl'
			})
			.when('/manage/rooms/', {
				templateUrl : 'partials/rooms.tpl.html',
				controller : 'RoomCtrl'
			})
			.when('/manage/schedule/', {
				templateUrl : 'partials/schedule.tpl.html',
				controller : 'ScheduleCtrl'
			})
			.when('/manage/reports/', {
				templateUrl : 'partials/reports.tpl.html',
				controller : 'ReportsCtrl'
			})
			.otherwise({redirectTo : '/main/'});
	}]);