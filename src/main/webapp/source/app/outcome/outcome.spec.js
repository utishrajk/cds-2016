/**
 * Created by tomson.ngassa on 3/3/14.
 */

'use strict';

describe('bham.outcomeModule', function(){
    var module;

    beforeEach(function() {
        module = angular.module("bham.outcomeModule");
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
            dependencies = module.value('bham.outcomeModule').requires;
        });

        it("should have ngResource as a dependency", function() {
            expect(hasModule('ngResource')).toEqual(true);
        });

        it("should have bham.outcomeService as a dependency", function() {
            expect(hasModule('bham.outcomeService')).toEqual(true);
        });

        it("should have bham.outcomeDirectives as a dependency", function() {
            expect(hasModule('bham.outcomeDirectives')).toEqual(true);
        });

        it("should have bham.naturalSort as a dependency", function() {
            expect(hasModule('bham.naturalSort')).toEqual(true);
        });

        it("should have nvd3ChartDirectives as a dependency", function() {
            expect(hasModule('nvd3ChartDirectives')).toEqual(true);
        });
    });
});

describe("bham.outcomeModule OutcomeListCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.outcomeModule'));
    beforeEach(function(){
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    var scope, $controller, mockOutcomeService, outcomeListCtrl, $compile, mockLoadedOutcomes, outcomes, conditionCodes, statusCodes;

    beforeEach(inject(function (_$compile_, $rootScope, _$controller_, _$route_) {
        scope = $rootScope.$new();
        $controller = _$controller_;
//        $route = _$route_;
        $compile = _$compile_;

        var outcomes = [{id: 0,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", startDate:"05/21/2014", endDate: "05/28/2014"},
            {id: 1,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill",startDate:"05/21/2014", endDate: "05/28/2014"},
            {id: 2,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill",startDate:"05/21/2014", endDate: "05/28/2014"},
            {id: 3,patientId: 213, cgiICode: "7", cgiIName: "ok", cgiSCode: "5", cgiSName: "Markedly ill",startDate:"05/21/2014", endDate: "05/28/2014"}];

        var successCb = function () {
            console.log('Success');
        };

        var errorCb = function () {
            console.log('Error');
        };

        mockOutcomeService = {
            get: function (id, successCb, errorCb) {
                for (var i = 0; i < outcomes.length; i++) {
                    if (outcomes[i].id === id) {
                        return outcomes[i];
                    }
                }
            },
            delete: function (id, successCb, errorCb) {
                outcomes.splice(id, 1);
                return {status: 200};
            }
        };
        scope.selectedPatientId = 213;
        outcomeListCtrl = $controller('OutcomeListCtrl', {
            $scope: scope,
            OutcomeService: mockOutcomeService,
            loadedOutcomes: outcomes
        });
    }));

    it('should retrieve a list of conditions', function () {
        expect(scope.outcomes.length).toBeGreaterThan(0);
        expect(scope.pageSize).toEqual(10);
        expect(scope.reverse).toBeFalsy();
        expect(scope.sortField).toBeUndefined();
        expect(scope.pageNo).toEqual(0);
        expect(scope.lastPage).toEqual(0);
        expect(scope.firstPage).toEqual(0);
        expect(scope.pages).toEqual([]);
        expect(scope.pages.length).toEqual(0);
    });

    it('should sort by column', function () {
        scope.sort('cgiSName');
        expect(scope.reverse).toBeFalsy();
        scope.sort('cgiSName');
        expect(scope.reverse).toBeTruthy();
    });

    it('should delete an outcome', function () {
        scope.deleteOutcome(3);
        var outcome = mockOutcomeService.get(3);
        expect(outcome).toEqual(undefined);
    });

    it('should search outcome table', function () {
        scope.criteria = 'ok';
        scope.onSearch();
        expect(scope.composedCriteria).toEqual(scope.criteria);
    });

    it('should sort up', function(){
        scope.sortField = 'cgiIName';
        expect(scope.isSortUp(scope.sortField)).toBeTruthy();
        scope.sort(scope.sortField);
        expect(scope.isSortUp(scope.sortField)).toBeFalsy();
    });

    it('should sort down', function(){
        scope.sortField = 'cgiIName';
        expect(scope.isSortDown(scope.sortField)).toBeFalsy();
        scope.sort(scope.sortField);
        expect(scope.isSortDown(scope.sortField)).toBeTruthy();
    });

    it('should calculate pagination pages', function(){
        scope.pages = [];
        //Create 5 pages
        for (var i=0; i<5; i++) {
            scope.pages.push(i);
        }
        scope.setActivePage(2);
        expect(scope.pageNo).toEqual(2);
    });

    it('should change showPageSize on page size change', function(){
        scope.pageSize = 20;
        scope.onPageSizeChange();
        expect(scope.showPageSize).toEqual(4);  // Since the list of condition is 4
        expect(scope.startRecord).toEqual(1);
        expect(scope.pageNo).toEqual(0);

        scope.pageSize = 2;
        scope.onPageSizeChange();
        expect(scope.showPageSize).toEqual(2);  // Since the list of patients is 4
        expect(scope.startRecord).toEqual(1);
        expect(scope.pageNo).toEqual(0);
    });

    it("should update the showpage variable when the filter condition length changes", function(){
        expect(scope.pages).toEqual([]);
        //In case the filter size is less than the page size
        scope.filteredOutcomes =  [{id: 0,patientId: 213},{id: 1,patientId: 213}];

        scope.$apply();
        expect(scope.startRecord ).toEqual(1);
        expect(scope.showPageSize ).toEqual(2);

        //In case the filter size is more than the page size
        scope.filteredOutcomes =  [{id: 0,patientId: 213},{id: 1,patientId: 213},{id: 2,patientId: 213},{id: 3,patientId: 213},{id: 4,patientId: 213},
            {id: 5,patientId: 213},{id: 6,patientId: 213},{id: 7,patientId: 213},{id: 8,patientId: 213},{id: 9,patientId: 213},
            {id: 10,patientId: 213},{id: 11,patientId: 213},{id: 12,patientId: 213},{id: 13,patientId: 213},{id: 14,patientId: 213}];

        scope.$apply();
        expect(scope.startRecord ).toEqual(1);
        expect(scope.showPageSize ).toEqual(10);
        expect(scope.pages).toEqual([0,1]);
    });

    it("should update pagination pages when page size changes", function(){
        //If page size is greater than total outcomes
        expect(scope.pageSize).toEqual(10);
        scope.pageSize = 25;
        scope.$apply();
        expect(scope.pages).toEqual([0]);

        //If page size is less than total patients
        scope.pageSize = 2;
        scope.$apply();
        expect(scope.pages).toEqual([0, 1]);

        scope.totalRecords = 12;
        scope.pageSize = 10;
        scope.$apply();
        expect(scope.pages).toEqual([0]);
    });

    it("should prepare example controller", function(){
        var cgii = [], cgis = [];
        var series1 = {"key" : "CGI-I","values": cgii};
        var series2 = {"key" : "CGI-S","area": true,"values": cgis};

        scope.ExampleCtrl(scope);
        expect(scope.exampleData[0]).toBeDefined();
        expect(scope.exampleData[1]).toBeDefined();
    });

});

describe("bham.outcomeModule OutcomeEditCtrl ", function() {

    beforeEach(module('ngRoute'));
    beforeEach(module('bham.outcomeModule'));

    beforeEach(function(){
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    var scope, $controller, MockOutcomeService, OutcomeEditCtrl, loadedData, $location, outcomes, cgiICodes, cgiSCodes,procedureTypeCodes ;

    beforeEach(inject(function( $rootScope, _$controller_, $compile, _$location_){
        scope = $rootScope.$new();
        $controller = _$controller_;
        $location=_$location_;


        outcomes = [{id: 2,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"},
            {id: 1,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"}];

        cgiICodes = [{code: "1",codeSystem: "2.16.840.1.113883.6.12",codeSystemName: "CPT", displayName: "Very much improved since the initiation of treatment", originalText: null },
            {code: "2",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Much improved", originalText: null }];

        cgiSCodes = [{code: "1",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Normal, not at all ill", originalText: null },
            {code: "2",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Borderline mentally ill", originalText: null }];

        procedureTypeCodes =  [{code: "H0018", displayName: "Behavioral health; short-term residential" },
            {code: "H2034", displayName: "Alcohol and/or drug abuse halfway house services, per diem" } ];

        var successCb = function(){console.log('Success');};

        var errorCb =  function (){console.log('Error');};

        MockOutcomeService = {
            get : function(id, successCb, errorCb) {
                for(var i = 0; i < outcomes.length ; i++ ){
                    if(outcomes[i].id === id){
                        return outcomes[i];
                    }
                }
            },

            update : function(id, outcome, successCb, errorCb) {
                for(var i = 0; i < outcomes.length ; i++ ){
                    if(outcomes[i].id === outcome.id){
                        outcomes.splice(i, 1, outcome);
                        break;
                    }
                }
            }
        };

        loadedData = [procedureTypeCodes, cgiICodes, cgiSCodes, outcomes];

        OutcomeEditCtrl = $controller('OutcomeEditCtrl', {
            $scope: scope,
            OutcomeService:MockOutcomeService,
            loadedData: loadedData
        });

    }));

    it('Should contain default values', function(){
        expect(scope.procedureTypeCodes).toEqualData(procedureTypeCodes);
        expect(scope.cgiICodes).toEqualData(cgiICodes);
        expect(scope.cgiSCodes).toEqualData(cgiSCodes);
        expect(scope.outcome).toEqualData(outcomes);
    });

    it('Should update edit outcome', function(){
        var editOutcome =  {id: 2,patientId: 213, cgiICode: "7", cgiIName: "Ok", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"};
        scope.save(editOutcome,function(){},function(){} );
        var newOutcome = MockOutcomeService.get(editOutcome.id);
        expect(newOutcome).toEqualData(editOutcome);
    });

//    xit('outcome form should be invalid initially', function(){
//        expect(form.$valid).toBeFalsy(); //because patient.firstname is required
//        expect(form.patientFirstName.$dirty).toBeFalsy();
//    });

//    xit('Should be able to save coutcome form', function(){
//        expect(scope.canSave()).toBeFalsy();
//        setPatientFirstName("Tomson");
//        expect(scope.canSave()).toBeTruthy();
//    });
});

describe("bham.outcomeModule OutcomeCreateCtrl ", function() {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.outcomeModule'));

    var scope, $controller, MockOutcomeService, OutcomeCreateCtrl, cgiICodes, cgiSCodes, procedureTypeCodes, outcomes;

    beforeEach(function(){
        this.addMatchers({
            toEqualData: function(expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    beforeEach(inject(function( $rootScope, _$controller_){
        scope = $rootScope.$new();
        $controller = _$controller_;

        cgiICodes = [{code: "1",codeSystem: "2.16.840.1.113883.6.12",codeSystemName: "CPT", displayName: "Very much improved since the initiation of treatment", originalText: null },
            {code: "2",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Much improved", originalText: null }];

        cgiSCodes = [{code: "1",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Normal, not at all ill", originalText: null },
            {code: "2",codeSystem: "2.16.840.1.113883.6.12", codeSystemName: "CPT", displayName: "Borderline mentally ill", originalText: null }];

        procedureTypeCodes =  [{code: "H0018", displayName: "Behavioral health; short-term residential" },
            {code: "H2034", displayName: "Alcohol and/or drug abuse halfway house services, per diem" } ];

        outcomes = [{id: 2,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"},
            {id: 1,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"}];

        var successCb = function(){console.log('Success');};

        var errorCb =  function (){console.log('Error');};

        MockOutcomeService = {
            query: function(){
              return outcomes;
            },
            create : function(id, outcome, successCb, errorCb) {
                outcomes.push(outcome);
            }
        };

        var loadedData = [procedureTypeCodes, cgiICodes, cgiSCodes];

        scope.selectedPatientId = 213;

        OutcomeCreateCtrl = $controller('OutcomeCreateCtrl', {
            $scope: scope,
            OutcomeService:MockOutcomeService,
            loadedData: loadedData
        });

    }));

    it('Should contain default values', function(){
        expect(scope.procedureTypeCodes).toEqualData(procedureTypeCodes);
        expect(scope.cgiICodes).toEqualData(cgiICodes);
        expect(scope.cgiSCodes).toEqualData(cgiSCodes);
    });

    it('Should save outcome', function(){
        var outcome =  {id: 3,patientId: 213, cgiICode: "7", cgiIName: "Very much worse since the initiation of treatment", cgiSCode: "5", cgiSName: "Markedly ill", endDate: "05/28/2014"};
        expect(MockOutcomeService.query().length).toEqual(2);
        scope.save(outcome);
        expect(MockOutcomeService.query().length).toEqual(3);
    });

    xit('Should be a future date', function(){
        expect(scope.showCompareDateError).toBeFalsy();
        console.log(scope.patient);
        scope.patient.birthDate = "04/20/2014";
        scope.$apply();
        expect(scope.showCompareDateError).toBeTruthy();
    });
});
