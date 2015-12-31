'use strict';


describe('bham.caremanagerService', function () {
    var module;

    beforeEach(function () {
        module = angular.module("bham.caremanagerService");
    });

    it("should be registered", function () {
        expect(module).not.toEqual(null);
    });

    describe("Dependencies:", function () {
        var dependencies;
        var hasModule = function (m) {
            return dependencies.indexOf(m) >= 0;
        };

        beforeEach(function () {
            dependencies = module.value('bham.caremanagerService').requires;
        });

        it("should have ngResource as a dependency", function () {
            expect(hasModule('ngResource')).toEqual(true);
        });
    });
});

describe('CaremanagerService', function(){
    var mockCaremanagerService, $httpBackend, caremanager;

    beforeEach(module('bham.caremanagerService'));

    beforeEach(function () {
        angular.mock.inject(function ($injector) {
            $httpBackend = $injector.get('$httpBackend');
            mockCaremanagerService = $injector.get('CareManagerService');
        });
    });

    beforeEach(function(){
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should get caremanager by account id', inject(function () {
        caremanager = {lastName: "Ngassa",firstName: "Tomson", id:2, userName: "tmngassa", website: "http://bham.com"};
        $httpBackend.whenGET("individualproviders/2").respond(caremanager);
        var result = mockCaremanagerService.getCareManagerResource().get({id:2});
        $httpBackend.flush();
        expect(result).toEqualData(caremanager);
    }));

    it('should query state Resource ', inject(function () {
        var states = [{code:"AL", displayName:"ALABAMA"}, {code:"AK", displayName:"ALASKA"}];
        $httpBackend.whenGET("statecodes").respond(states);
        var result = mockCaremanagerService.getStateResource().query();
        $httpBackend.flush();
        expect(result).toEqualData(states);
    }));

    it('should query Provider Taxonomy Resource ', inject(function () {
        var providersTaxonomy = [{code: "122300000X", displayName: "Dentist"},{code: "122300000X", displayName: "Dentist"}];
        $httpBackend.whenGET("providertaxonomycodes").respond(providersTaxonomy);
        var result = mockCaremanagerService.getProviderTaxonomyResource().query();
        $httpBackend.flush();
        expect(result).toEqualData(providersTaxonomy);
    }));

    it('should update caremanager ', inject(function () {
        caremanager = {lastName: "Ngassa",firstName: "Tomson1", id:2, userName: "tmngassa", website: "http://bham.com"};
        $httpBackend.expectPUT('individualproviders/2').respond({status: 200});
        var status = mockCaremanagerService.update(2, caremanager,
            function(data ){
                status = data.status;
            },
            function(error){});
        $httpBackend.flush();
        expect(status).toEqual(200);
    }));
});
