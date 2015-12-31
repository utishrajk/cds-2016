'use strict';

angular.module('bham.organizationService', ['ngResource'])
    .constant('ORGANIZATION_RESOURCE_URL', 'organizationalproviders/:id' )
    .constant('STATE_RESOURCE_URL', 'statecodes' )
    .constant('COUNTRY_RESOURCE_URL', 'countrycodes' )

    .factory('OrganizationService', ['$resource','ORGANIZATION_RESOURCE_URL','STATE_RESOURCE_URL','COUNTRY_RESOURCE_URL', '$location',
        function($resource, ORGANIZATION_RESOURCE_URL, STATE_RESOURCE_URL, COUNTRY_RESOURCE_URL, $location){

            var OrganizationResource = $resource( ORGANIZATION_RESOURCE_URL,{id: '@id'},{'update': { method:'PUT' }} );
            var StateResource = $resource(STATE_RESOURCE_URL);
            var CountryResource = $resource(COUNTRY_RESOURCE_URL);

            return {
                getOrganizationResource : function(){
                    return OrganizationResource;
                },

                getStateResource : function(){
                    return StateResource;
                },

                getCountryResource : function(){
                    return CountryResource;
                },

                update : function(organizationProfile, successCb, errorCb) {
                    OrganizationResource.update({id: organizationProfile.id},organizationProfile,successCb, errorCb );
                },
            };
    }]);