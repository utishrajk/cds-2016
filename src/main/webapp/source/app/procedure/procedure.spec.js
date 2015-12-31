'use strict';

describe('bham.procedureModule', function () {
    var module;

    beforeEach(function () {
        module = angular.module("bham.procedureModule");
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
            dependencies = module.value('bham.procedureModule').requires;
        });

        it("should have ngResource as a dependency", function () {
            expect(hasModule('ngResource')).toEqual(true);
        });

        it("should have bham.procedureService as a dependency", function () {
            expect(hasModule('bham.procedureService')).toEqual(true);
        });

        it("should have bham.procedureDirectives as a dependency", function () {
            expect(hasModule('bham.procedureDirectives')).toEqual(true);
        });

        it("should have bham.filters as a dependency", function () {
            expect(hasModule('bham.filters')).toEqual(true);
        });

        it("should have bham.naturalSort as a dependency", function () {
            expect(hasModule('bham.naturalSort')).toEqual(true);
        });
    });

});

describe("bham.procedureModule ProcedureListCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.procedureModule'));

    var scope, $controller, mockProcedureService, procedureListCtrl, $route, $compile, mockLoadedProcedures, procedures, procedureCodes, statusCodes;

    beforeEach(inject(function (_$compile_, $rootScope, _$controller_, _$route_) {
        scope = $rootScope.$new();
        $controller = _$controller_;
        $route = _$route_;
        $compile = _$compile_;

        procedures = [
            {id: "1", name: "c1"},
            {id: "2", name: "c2"},
            {id: "4", name: "c4"}
        ];

        procedureCodes = [
            {"code": "c1", "displayName": "d1"},
            {"code": "c2", "displayName": "d2"}
        ];

        statusCodes = [
            {"code": "s1", "displayName": "d1"},
            {"code": "s2", "displayName": "d2"}
        ];

        var successCb = function () {
            console.log('Success');
        };

        var errorCb = function () {
            console.log('Error');
        };

        mockProcedureService = {
            query: function (patientId, successCb, errorCb) {
                return procedures;
            },

            create: function (patientId, procedure, successCb, errorCb) {
                return {status: 201};
            },

            get: function (id, successCb, errorCb) {
                for (var i = 0; i < procedures.length; i++) {
                    if (procedures[i].id === id) {
                        return procedures[i];
                    }
                }
            },

            update: function (id, procedure, successCb, errorCb) {
                return {status: 200};
            },

            delete: function (id, successCb, errorCb) {
                procedures.splice(id, 1);
                return {status: 200};
            },

            getProcedureCodes: function (successCb, errorCb) {
                return procedureCodes;
            },

            getProcedureStatusCodes: function (successCb, errorCb) {
                return statusCodes;
            }
        };

        mockLoadedProcedures = mockProcedureService.query(213, successCb, errorCb);

        scope.selectedPatientId = 213;

        procedureListCtrl = $controller('ProcedureListCtrl', {
            $scope: scope,
            ProcedureService: mockProcedureService,
            loadedProcedures: mockLoadedProcedures
        });
    }));

    it('should retrieve a list of procedures', function () {
        expect(scope.procedures.length).toBeGreaterThan(0);
        expect(scope.pageSize).toEqual(10);
        expect(scope.reverse).toBeFalsy();
        expect(scope.sortField).toBeUndefined();
        expect(scope.pageNo).toEqual(0);
        expect(scope.lastPage).toEqual(0);
        expect(scope.firstPage).toEqual(0);
        expect(scope.pages).toEqual([]);
        expect(scope.pages.length).toEqual(0);
    });

    it('should sort by name column', function () {
        scope.sort('procedureTypeName');
        expect(scope.reverse).toBeFalsy();
        scope.sort('procedureTypeName');
        expect(scope.reverse).toBeTruthy();
    });

    it('should sort by status column', function () {
        scope.sort('procedureStatusCode');
        expect(scope.reverse).toBeFalsy();
        scope.sort('procedureStatusCode');
        expect(scope.reverse).toBeTruthy();
    });

    it('should sort by startDate column', function () {
        scope.sort('procedureStartDate');
        expect(scope.reverse).toBeFalsy();
        scope.sort('procedureStartDate');
        expect(scope.reverse).toBeTruthy();
    });

    it('should sort by endDate column', function () {
        scope.sort('procedureEndDate');
        expect(scope.reverse).toBeFalsy();
        scope.sort('procedureEndDate');
        expect(scope.reverse).toBeTruthy();
    });

    it('should delete a procedure', function () {
        scope.deleteProcedure(4);
        var procedure = mockProcedureService.get(4);
        expect(procedure).toEqual(undefined);
    });

    it('should search by name', function () {
        scope.searchBy = 'name';
        scope.criteria = 'c1';
        scope.onSearch();
        expect(scope.composedCriteria).toNotEqual(undefined);
    });

    it('should search by status', function () {
        scope.searchBy = 'status';
        scope.criteria = 'c1';
        scope.onSearch();
        expect(scope.composedCriteria).toNotEqual(undefined);
    });

    it('should search by everything', function () {
        scope.searchBy = undefined;
        scope.criteria = 'c1';
        scope.onSearch();
        expect(scope.composedCriteria).toNotEqual(undefined);
    });

    it('should change showPageSize on page size change', function(){
        scope.pageSize = 20;
        scope.onPageSizeChange();
        expect(scope.showPageSize).toEqual(3);  // Since the list of condition is 4
        expect(scope.startRecord).toEqual(1);
        expect(scope.pageNo).toEqual(0);

        scope.pageSize = 2;
        scope.onPageSizeChange();
        expect(scope.showPageSize).toEqual(2);  // Since the list of patients is 4
        expect(scope.startRecord).toEqual(1);
        expect(scope.pageNo).toEqual(0);
    });

    it('should sort up', function(){
        scope.sortField = 'name';
        expect(scope.isSortUp(scope.sortField)).toBeTruthy();
        scope.sort(scope.sortField);
        expect(scope.isSortUp(scope.sortField)).toBeFalsy();
    });

    it('should sort down', function(){
        scope.sortField = 'name';
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

    it("should update the showpage variable when the filter condition length changes", function(){
        expect(scope.pages).toEqual([]);
        //In case the filter size is less than the page size
        scope.filteredProcedures =  [ {id: "1", name: "p1"}, {id: "1", name: "p2"},];

        scope.$apply();
        expect(scope.startRecord ).toEqual(1);
        expect(scope.showPageSize ).toEqual(2);

        //In case the filter size is more than the page size
        scope.filteredProcedures =  [{id: "1", name: "p1"},{id: "2", name: "p2"},{id: "3", name: "p3"},{id: "4", name: "p4"},{id: "5", name: "p5"},
            {id: "6", name: "p6"},{id: "7", name: "p7"},{id: "8", name: "p8"},{id: "9", name: "p9"},{id: "10", name: "p10"},
            {id: "11", name: "p11"},{id: "12", name: "p13"},{id: "14", name: "p14"}];

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

});

describe("bham.procedureModule ProcedureEditCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.procedureModule'));

    var scope, $controller, mockProcedureService, ProcedureEditCtrl, $route, $compile, form, procedures, procedureCodes, statusCodes, procedure, loadedData;

    beforeEach(function () {
        this.addMatchers({
            toEqualData: function (expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    beforeEach(inject(function (_$compile_, $rootScope, _$controller_, _$route_) {
        scope = $rootScope.$new();
        $controller = _$controller_;
        $route = _$route_;
        $compile = _$compile_;

        procedures = [
            {id: "1", name: "c1"},
            {id: "2", name: "c2"}
        ];

        procedureCodes = [
            {"code": "c1", "displayName": "d1"},
            {"code": "c2", "displayName": "d2"}
        ];

        statusCodes = [
            {"code": "s1", "displayName": "d1"},
            {"code": "s2", "displayName": "d2"}
        ];

        var successCb = function () {
            console.log('Success');
        };

        var errorCb = function () {
            console.log('Error');
        };

        mockProcedureService = {
            query: function (procedureId, successCb, errorCb) {
                return procedures;
            },

            create: function (procedureId, procedure, successCb, errorCb) {
                return {status: 201};
            },

            get: function (id, successCb, errorCb) {
                for (var i = 0; i < procedures.length; i++) {
                    if (procedures[i].id === id) {
                        return procedures[i];
                    }
                }
            },

            update: function (id, procedure, successCb, errorCb) {
                return {status: 200};
            },

            delete: function (id, successCb, errorCb) {
                procedures.splice(id, 1);
                return {status: 200};
            },

            getProcedureCodes: function (successCb, errorCb) {
                return procedureCodes;
            },

            getProcedureStatusCodes: function (successCb, errorCb) {
                return statusCodes;
            }
        };

        //loadedData is pre-populated when ProcedureEditCtrl is called.
        loadedData = [procedureCodes, statusCodes, procedures[0]];

        ProcedureEditCtrl = $controller('ProcedureEditCtrl', {
            $scope: scope,
            ProcedureService: mockProcedureService,
            loadedData: loadedData
        });

    }));

    it('Should contain default values for dropdown and the selected procedure', function () {
        expect(scope.procedureCodes).toEqualData(procedureCodes);
        expect(scope.procedureStatusCodes).toEqualData(statusCodes);
        expect(scope.procedure).toEqualData(procedures[0]);
    });

    it('Should edit a procedure', function () {
        scope.save(procedures[0]);
        var editedProcedure = mockProcedureService.get(procedures[0].id);
        expect(editedProcedure).toEqualData(procedures[0]);
    });


});

describe("bham.procedureModule ProcedureCreateCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.procedureModule'));

    var scope, $controller, mockProcedureService, ProcedureCreateCtrl, $route, $compile, form, procedures, procedureCodes, statusCodes, procedure, loadedData;

    beforeEach(function () {
        this.addMatchers({
            toEqualData: function (expected) {
                return angular.equals(this.actual, expected);
            }
        });
    });

    beforeEach(inject(function (_$compile_, $rootScope, _$controller_, _$route_) {
        scope = $rootScope.$new();
        $controller = _$controller_;
        $route = _$route_;
        $compile = _$compile_;

        procedures = [
            {id: "1", name: "c1"},
            {id: "2", name: "c2"}
        ];

        procedureCodes = [
            {"code": "c1", "displayName": "d1"},
            {"code": "c2", "displayName": "d2"}
        ];

        statusCodes = [
            {"code": "s1", "displayName": "d1"},
            {"code": "s2", "displayName": "d2"}
        ];

        var successCb = function () {
            console.log('Success');
        };

        var errorCb = function () {
            console.log('Error');
        };

        mockProcedureService = {
            query: function (procedureId, successCb, errorCb) {
                return procedures;
            },

            create: function (procedureId, procedure, successCb, errorCb) {
                procedures.push(procedure);
                return {status: 201};
            },

            get: function (id, successCb, errorCb) {
                for (var i = 0; i < procedures.length; i++) {
                    if (procedures[i].id === id) {
                        return procedures[i];
                    }
                }
            },

            update: function (id, procedure, successCb, errorCb) {
                return {status: 200};
            },

            delete: function (id, successCb, errorCb) {
                procedures.splice(id, 1);
                return {status: 200};
            },

            getProcedureCodes: function (successCb, errorCb) {
                return procedureCodes;
            },

            getProcedureStatusCodes: function (successCb, errorCb) {
                return statusCodes;
            }
        };

        //loadedData is pre-populated when ProcedureEditCtrl is called.
        loadedData = [procedureCodes, statusCodes];

        scope.selectedPatientId = 213;

        ProcedureCreateCtrl = $controller('ProcedureCreateCtrl', {
            $scope: scope,
            ProcedureService: mockProcedureService,
            loadedData: loadedData
        });

    }));

    it('Should contain default values for dropdown and the selected procedure', function () {
        expect(scope.procedureCodes).toEqualData(procedureCodes);
        expect(scope.procedureStatusCodes).toEqualData(statusCodes);
    });

    it('Should create a procedure', function () {
        var newProcedure = {id: "3", name: "c3"};
        scope.save(newProcedure);
        var retrievedProcedure = mockProcedureService.get(newProcedure.id);
        expect(newProcedure).toEqualData(retrievedProcedure);
    });


});
