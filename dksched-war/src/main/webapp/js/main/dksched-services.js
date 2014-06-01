/**
 *
 */
var dkschedServices = angular.module('dkSched');

dkschedServices.factory('Week', ['$resource', function($resource){
  var Week = $resource('/dksched/week', null, {
      'current': {method:'GET'},
      'load': {method:'POST'}
  });
  return Week;
}]);

dkschedServices.factory('Rooms', ['$resource', function($resource){
  var Rooms = $resource('/dksched/rooms', null, {
	  'query': {method:'GET', isArray:true},
	  'get': {method:'GET'},
	  'save': {method:'POST'},
	  'update': {method:'PUT'},
	  'remove': {method:'DELETE'}
  });
  return Rooms;
}]);

dkschedServices.factory('Recurrances', ['$resource', function($resource){
  var Recurrances = $resource('/dksched/recurrances', null, {
	  'query': {method:'GET', isArray:true},
	  'get': {method:'GET'},
	  'save': {method:'POST'},
	  'update': {method:'PUT'},
	  'remove': {method:'DELETE'}
  });
  return Recurrances;
}]);

dkschedServices.factory('ScheduledDates', ['$resource', function($resource){
  var ScheduledDates = $resource('/dksched/scheduled-dates', null, {
	  'query': {method:'GET', isArray:true},
	  'get': {method:'GET'},
	  'save': {method:'POST'},
	  'update': {method:'PUT'},
	  'remove': {method:'DELETE'}
  });
  return ScheduledDates;
}]);

dkschedServices.factory('ScheduledRooms', ['$resource', function($resource){
	  var ScheduledRooms = $resource('/dksched/scheduled-rooms', null, {
		  'query': {method:'GET', isArray:true},
		  'get': {method:'GET'},
		  'save': {method:'POST'},
		  'update': {method:'PUT'},
		  'remove': {method:'DELETE'}
	  });
	  return ScheduledRooms;
}]);

dkschedServices.factory('Volunteers', ['$resource', function($resource){
	  var Volunteers = $resource('/dksched/volunteers', null, {
		  'query': {method:'GET', isArray:true},
		  'get': {method:'GET'},
		  'save': {method:'POST'},
		  'update': {method:'PUT'},
		  'remove': {method:'DELETE'}
	  });
	  return Volunteers;
	}]);

dkschedServices.factory('VolunteerInstances', ['$resource', function($resource){
	  var VolunteerInstances = $resource('/dksched/volunteer-instances', null, {
		  'query': {method:'GET', isArray:true},
		  'get': {method:'GET'},
		  'save': {method:'POST'},
		  'update': {method:'PUT'},
		  'remove': {method:'DELETE'}
	  });
	  return VolunteerInstances;
}]);

dkschedServices.factory('Notification', function($http){
  return {
    send: function(pnotifyOptions){
      jQuery.pnotify(pnotifyOptions);
    }
  };
});



