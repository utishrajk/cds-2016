angular.module("bham.reportsModule", ['bham.security'])

.config(['$routeProvider', 'USER_ROLES', function($routeProvider, USER_ROLES) {
		'use strict';
		
		$routeProvider			
		.when('/reports', {				
				templateUrl: "reports/reports.tpl.html",
                access: {
                    authorizedRoles: [USER_ROLES.admin, USER_ROLES.user]
                }
			});
}]);