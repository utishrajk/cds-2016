'use strict';

angular.module('bham.caremanagerService', ['ngResource'])
    .constant('CARE_MANAGER_PUBLIC_URL', 'individualproviders/register')
    .constant('CARE_MANAGER_RESOURCE_URL', 'individualproviders/:id' )
    .constant('CARE_MANAGER_STATE_RESOURCE_URL', 'statecodes' )
    .constant('CARE_MANAGER_PROVIDER_TAXONOMY_RESOURCE_URL', 'providertaxonomycodes' )

    .factory('CareManagerService', ['$resource','CARE_MANAGER_RESOURCE_URL', 'CARE_MANAGER_PUBLIC_URL', 'CARE_MANAGER_STATE_RESOURCE_URL', 'CARE_MANAGER_PROVIDER_TAXONOMY_RESOURCE_URL','$location',
        function($resource, CARE_MANAGER_RESOURCE_URL, CARE_MANAGER_PUBLIC_URL, CARE_MANAGER_STATE_RESOURCE_URL, CARE_MANAGER_PROVIDER_TAXONOMY_RESOURCE_URL, $location){

            var CareManagerResource = $resource( CARE_MANAGER_RESOURCE_URL,{id: '@id'},{'update': { method:'PUT' }} );
            var CareManagerPublicResource = $resource( CARE_MANAGER_PUBLIC_URL );


            var CareManagerStateResource = $resource( CARE_MANAGER_STATE_RESOURCE_URL);

            var CareManagerProviderTaxonomyResource = $resource( CARE_MANAGER_PROVIDER_TAXONOMY_RESOURCE_URL);

            return {
                update : function(id, caremanager, successCb, errorCb) {
                    CareManagerResource.update({id:id},caremanager,successCb, errorCb );
                },
                
                create : function(careManager, successCb, errorCb) {
                    console.log('Ready to make the call.....!!!');
                    CareManagerPublicResource.save(careManager, successCb, errorCb);
                },

                getStateResource: function(successCb, errorCb) {
                    return CareManagerStateResource;
                },

                getProviderTaxonomyResource: function() {
                    return CareManagerProviderTaxonomyResource;
                },

                getCareManagerResource : function(){
                    return CareManagerResource;
                }
            };
        }]);