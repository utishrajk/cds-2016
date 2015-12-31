
'use strict';

angular.module("bham.messagecenterModule", ['bham.security'])

.config(['$routeProvider','USER_ROLES', function($routeProvider, USER_ROLES) {
		
		$routeProvider			
		.when('/messagecenter', {				
				templateUrl: "messagecenter/messagecenter.tpl.html",
                access: {
                    authorizedRoles: [USER_ROLES.admin, USER_ROLES.user]
                }
			});
}]);