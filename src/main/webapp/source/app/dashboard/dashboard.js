'use strict';

angular.module("bham.dashboarModule", ['bham.security'])

.config(['$routeProvider', 'USER_ROLES', function($routeProvider, USER_ROLES) {

		$routeProvider
		.when('/dashboard', {				
				templateUrl: "dashboard/dashboard.tpl.html",
                access: {
                    authorizedRoles: [USER_ROLES.admin, USER_ROLES.user]
                },
                controller:'DashboardCtrl'
			});
}])
.controller('DashboardCtrl', ['$scope',function($scope){
        $scope.openCustomMenu = false;
        $scope.onSelectmenu('dashboard');
}]);