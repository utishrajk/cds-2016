///**
// * Created by tomson.ngassa on 3/10/14.
// */

'use strict';


describe('bham.caremanagerModule', function(){
    var module;

    beforeEach(function() {
        module = angular.module("bham.caremanagerModule");
    });

    it("should be registered", function() {
        expect(module).not.toEqual(null);
    });

    describe("Dependencies:", function() {

        var dependencies;

        var hasModule = function(m) {
            return dependencies.indexOf(m) >= 0;
        };

        beforeEach(function() {
            dependencies = module.value('bham.caremanagerModule').requires;
        });

        it("should have ngResource as a dependency", function() {
            expect(hasModule('ngResource')).toEqual(true);
        });

        it("should have bham.security as a dependency", function() {
            expect(hasModule('bham.security')).toEqual(true);
        });

        it("should have bhma.caremanagerService as a dependency", function() {
            expect(hasModule('bham.caremanagerService')).toEqual(true);
        });

    });
});


describe("bham.caremanagerModule CaremanagerCtrl", function() {

    beforeEach(module('ngRoute'));
    beforeEach(module('bham.caremanagerModule'));

    beforeEach(function(){
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    var scope, MockCareManagerService, caremanager, loadedData;

    beforeEach(inject(function($rootScope, $controller){
        caremanager = {lastName: "Ngassa",firstName: "Tomson", id:2, userName: "tmngassa", website: "http://bham.com"};

        scope = $rootScope.$new();

        var successCb = function(){console.log('Success');};
        var errorCb =  function (){ console.log('Error');};

        MockCareManagerService = {
            update : function(id, newcaremanager, successCb, errorCb) {
                caremanager = newcaremanager;
            },
            get : function(id, successCb, errorCb) {
                return caremanager ;
            }
        };

        loadedData = [{}, {}, caremanager];
        scope.states = loadedData[0];
        scope.specialities = loadedData[1];
        scope.caremanager = loadedData[2];

        $controller('CaremanagerCtrl', {
            $scope: scope,
            CareManagerService: MockCareManagerService,
            loadedData: loadedData
        });
    }));

    it("should have default values", function() {
        expect(scope.activeTab).toEqual('basic');
        expect(scope.states).toEqual({});
        expect(scope.specialities).toEqual({});
        expect(scope.caremanager).toEqual(caremanager);
    });

    it("should switch tab", function() {
        expect(scope.activeTab).toEqual('basic');
        scope.switchTabTo('tab1');
        expect(scope.activeTab).toEqual('tab1');
    });

    it("should update caremanager", function() {
        caremanager.lastName = "Ngassa1";
        scope.save(caremanager.id, caremanager, function(){console.log('Success');},  function (){ console.log('Error');});
        var updatedCarmanager = MockCareManagerService.get(2);
        expect(caremanager.lastName).toEqual(updatedCarmanager.lastName);
    });
});

