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
		console.log("TODO: $scope.scheduleRecurring");
	};
});

