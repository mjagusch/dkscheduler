mainApp.controller("RecurringScheduleCtrl", function($scope, $log, $http, $window, $modal, Recurrances, Notification){

	$scope.recurrances = Recurrances.query();

	$scope.displayRecurrance = function(recurrance) {
		$scope.selectedRecurrance = recurrance;
	};
	
	$scope.voidRecurrance = function() {
		$scope.selectedRecurrance = undefined;
	};
	
	$scope.editRecurrance = function(recurrance) {
		$scope.editRecurring(recurrance);
	};
	
	$scope.addRecurrance = function() {
		$scope.selectedRecurrance = undefined;
		$scope.editRecurrance({});
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
	
	$scope.editRecurring = function (recurrance, size) {
		var modalInstance = $modal.open({
			templateUrl: 'recurranceDialog.html',
		    controller: RecurranceEditCtrl,
		    size: size,
		    resolve: {
		        recurrance: function() {
		        	return recurrance;
		        },
				recurrances: function() {
					return $scope.recurrances;
				}
		    }
		});

		modalInstance.result.then(function (editedRecurrance) {
			if ( $scope.selectedRecurrance ) {
				_.extend($scope.selectedRecurrance, editedRecurrance);
			} else {
				$scope.selectedRecurrance = editedRecurrance;				
			}
		});
	};

	var RecurranceEditCtrl = function ($scope, $modalInstance, Recurrances, recurrance, recurrances) {
		$scope.form = {}; // Work-around
        $scope.recurrance = _.clone(recurrance);
        $scope.recurrances = recurrances;
        
		$scope.ok = function () {
			$scope.saveRecurrance($scope.recurrance);			
		};

		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};

		$scope.saveRecurrance = function(recurrance) {
		    if($scope.form.recurranceEditForm.$dirty) {
		        if($scope.form.recurranceEditForm.$valid) {
		          if (recurrance.id) {
		            Recurrances.update(recurrance, 
    	              function(saved) {
		            	_.extend($scope.recurrance, saved);
			    		Notification.send({type:'success', title:'Recurrance updated'});
					    $modalInstance.close($scope.recurrance);
			    	  },
			    	  function() {
			    		Notification.send({type:'error', title:'Error updating recurrance'});
			    	  });
		          } else {
			        Recurrances.save(recurrance,
			          function(saved) {
			            _.extend($scope.recurrance, saved);
						$scope.recurrances.push($scope.recurrance);				
			            Notification.send({type:'success', title:'Recurrance saved'});
					    $modalInstance.close($scope.recurrance);
		              },
		              function() {
			            Notification.send({type:'error', title:'Error saving recurrance'});
		            });
		          }
		        } else {
		          Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the scheduled date and try again.'});
		        }
		    }
		};
	};
	
	$scope.scheduleRecurring = function (size) {
		var modalInstance = $modal.open({
			templateUrl: 'recurranceScheduleDialog.html',
		    controller: RecurranceScheduleCtrl,
		    size: size
		});
	};

	var RecurranceScheduleCtrl = function ($scope, $modalInstance, $window) {
		$scope.form = {}; // Work-around
        
		$scope.ok = function () {
			$scope.scheduleRecurrance();			
		};

		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};

		$scope.scheduleRecurrance = function() {
	        if($scope.form.recurranceScheduleForm.$valid) {
	        	var toDate = moment($scope.form.recurranceScheduleForm.toDate.$modelValue, "MM-DD-YYYY").format("YYYY-MM-DD");
	        	$http({method:"post", url:"schedule-recurring", params:{toDate: toDate}})
	        		.success(function() {
	    		    	Notification.send({type:'info', title:'Recurring dates scheduled'});
	    		    	$window.location.reload();
					    $modalInstance.close();	    		    		
	        		})
	        		.error(function() {
	    		    	Notification.send({type:'error', title:'Error scheduling recurring dates'});
	        		});
	        } else {
	            Notification.send({type:'error', title:'Validation Error', text:'Fix the issues for the schedule through date and try again.'});
	        }
		};
	};	
});

