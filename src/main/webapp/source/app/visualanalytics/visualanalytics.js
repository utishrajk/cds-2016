angular.module("bham.visualanalyticsModule", ['bham.security'])

.config(['$routeProvider', 'USER_ROLES', function($routeProvider, USER_ROLES) {
		'use strict';
		
		$routeProvider			
		.when('/visualanalytics', {				
				templateUrl: "visualanalytics/visualanalytics.tpl.html",
                access: {
                    authorizedRoles: [USER_ROLES.admin, USER_ROLES.user]
                }
			});
}]);