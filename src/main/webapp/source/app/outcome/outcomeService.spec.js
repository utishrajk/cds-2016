/**
 * Created by tomson.ngassa on 6/12/14.
 */

'use strict';


describe('bham.outcomeService', function () {
    var module;

    beforeEach(function () {
        module = angular.module("bham.outcomeService");
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
            dependencies = module.value('bham.outcomeService').requires;
        });

        it("should have ngResource as a dependency", function () {
            expect(hasModule('ngResource')).toEqual(true);
        });
    });
});

describe('OutcomeService', function(){
    var mockOutcomeService, $httpBackend;

    beforeEach(module('bham.outcomeService'));

    beforeEach(function () {
        angular.mock.inject(function ($injector) {
            $httpBackend = $injector.get('$httpBackend');
            mockOutcomeService = $injector.get('OutcomeService');
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

    it('should query outcomes for a given patient', inject(function () {
        var outcomes = [{id: 2,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"},
                        {id: 1,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"}];
        $httpBackend.whenGET("outcomes/patient/213").respond(outcomes);
        var result = mockOutcomeService.getOutcomeResource().query({patientId:213},
                        function(data ){},
                        function(error){});
        $httpBackend.flush();
        expect(result).toEqualData(outcomes);
    }));

    it('should query CgiICode', inject(function () {
        var cgiICodes = [{code: "1",codeSystem: "2.16.840.1.113883.6.12",codeSystemName: "CPT", displayName: "Very much improved since the initiation of treatment", originalText: null },
                         {code: "2",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Much improved", originalText: null }];
        $httpBackend.whenGET("cgiicodes").respond(cgiICodes);
        var result = mockOutcomeService.getCgiICodeResource().query();
        $httpBackend.flush();
        expect(result).toEqualData(cgiICodes);
    }));

    it('should query CgiSCode', inject(function () {
        var cgiscodes = [{code: "1",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Normal, not at all ill", originalText: null },
                         {code: "2",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Borderline mentally ill", originalText: null }];
        $httpBackend.whenGET("cgiscodes").respond(cgiscodes);
        var result = mockOutcomeService.getCgiSCodeResource().query();
        $httpBackend.flush();
        expect(result).toEqualData(cgiscodes);
    }));

    it('should query ProcedureCode', inject(function () {
        var procedureCodes =  [{code: "H0018", displayName: "Behavioral health; short-term residential" },
                                {code: "H2034", displayName: "Alcohol and/or drug abuse halfway house services, per diem" }  ];
        $httpBackend.whenGET("proceduretypecodes").respond(procedureCodes);
        var result = mockOutcomeService.getProcedureCodeResource().query();
        $httpBackend.flush();
        expect(result).toEqualData(procedureCodes);
    }));


    it('should create outcome for a given patient', inject(function () {
        var outcome = {id:"1"};
        $httpBackend.expectPOST("outcomes/patient/1").respond({status: 201});
        var status = mockOutcomeService.create(
            1,
            outcome,
            function(data ){
                status = data.status;
            },
            function(error){});
        $httpBackend.flush();
        expect(status).toEqualData(201);
    }));


    it('should update outcome only ', inject(function () {
        var outcomeonly = {id:"1"};
        $httpBackend.expectPUT("outcomes/1").respond({status: 200});
        var status = mockOutcomeService.update(1, outcomeonly,
            function(data ){
                status = data.status;
            },
            function(error){}
        );
        $httpBackend.flush();
        expect(status).toEqual(200);
    }));

    it('should delete outcome only ', inject(function () {
        $httpBackend.expectDELETE("outcomes/1").respond({status: 200});
        var status = mockOutcomeService.delete(1,
            function(data ){
                status = data.status;
            },
            function(error){}
        );
        $httpBackend.flush();
        expect(status).toEqual(200);
    }));
});
