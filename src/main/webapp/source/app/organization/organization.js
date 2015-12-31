'use strict';

angular.module("bham.organizationModule", ['bham.security', 'bham.organizationService'])

.config(['$routeProvider', 'USER_ROLES', function($routeProvider, USER_ROLES) {

		$routeProvider			
		.when('/organization/:id', {
                controller: 'OrganizationProfileCtrl',
                access: {
                    authorizedRoles: [USER_ROLES.admin]
                },
                templateUrl: "organization/organization.tpl.html",
                resolve: {
                    loadedData: ['OrganizationService', '$log', '$q','$route', function(OrganizationService, $log, $q, $route){
                        var userId = $route.current.params.id;
                        if( angular.isDefined(userId)){
                            // Set up a promise to return
                            var deferred = $q.defer();
                            // Set up our resource calls
                            var organizationResource = OrganizationService.getOrganizationResource();
                            var organizationData = organizationResource.get({id:userId},
                                function(response){},
                                function(error){$log.error("Error message: " + error.data.message );}
                            );

                            var stateResource = OrganizationService.getStateResource();
                            var stateData = stateResource.query(
                                function(response){},
                                function(error){$log.error("Error message: " + error.data.message );}
                            );

                            var countryResource = OrganizationService.getCountryResource();
                            var countryData = countryResource.query(
                                function(response){},
                                function(error){$log.error("Error message: " + error.data.message );}
                            );

                            // Wait until both resources have resolved their promises, then resolve this promise
                            $q.all([organizationData.$promise, stateData.$promise, countryData.$promise]).then(function(response) {
                                deferred.resolve(response);
                            });
                            return deferred.promise;

                        } else {
                            $log.error("CareManager Get Resolve: Care manager id not defined.");
                        }
                    }]
                }
			});
}])
.controller('OrganizationProfileCtrl', ['$scope','loadedData', 'OrganizationService', '$log', function ($scope, loadedData, OrganizationService, $log){
        // Switch Tabs
        $scope.activeTab  = 'organizationProfile';
        $scope.switchTabTo = function (tabId) {
            $scope.activeTab = tabId;
        };

        $scope.organizationProfile = loadedData[0];
        $scope.states = loadedData[1];
        $scope.countries = loadedData[2];


        $scope.save = function(organizationProfile){
            OrganizationService.update(
                organizationProfile,
                function(response){
                    $log.info('Success increating organization');
                },
                function(error){
                    $log.error(error.data.message );
                    $log.error("Error message: " + error.data.message );
                }
            );
        };
}]);