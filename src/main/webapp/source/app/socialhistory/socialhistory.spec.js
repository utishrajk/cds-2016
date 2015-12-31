'use strict';

describe('bham.socialhistoryModule', function () {
    var module;

    beforeEach(function () {
        module = angular.module("bham.socialhistoryModule");
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
            dependencies = module.value('bham.socialhistoryModule').requires;
        });

        it("should have ngResource as a dependency", function () {
            expect(hasModule('ngResource')).toEqual(true);
        });

        it("should have bham.socialhistoryService as a dependency", function () {
            expect(hasModule('bham.socialhistoryService')).toEqual(true);
        });

        it("should have bham.socialhistoryDirectives as a dependency", function () {
            expect(hasModule('bham.socialhistoryDirectives')).toEqual(true);
        });

        it("should have bham.filters as a dependency", function () {
            expect(hasModule('bham.filters')).toEqual(true);
        });

        it("should have bham.naturalSort as a dependency", function () {
            expect(hasModule('bham.naturalSort')).toEqual(true);
        });
    });

});

describe("bham.socialhistoryModule SocialhistoryListCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.socialhistoryModule'));

    var scope, $controller, mockSocialhistoryService, socialhistoryListCtrl, $route, $compile, mockLoadedSocialhistories, socialhistorys, socialhistoryCodes, statusCodes;

    beforeEach(inject(function (_$compile_, $rootScope, _$controller_, _$route_) {
        scope = $rootScope.$new();
        $controller = _$controller_;
        $route = _$route_;
        $compile = _$compile_;

        socialhistorys = [
            {id: "1", name: "c1"},
            {id: "2", name: "c2"},
            {id: "4", name: "c4"} //to be deleted
        ];

        socialhistoryCodes = [
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

        mockSocialhistoryService = {
            query: function (patientId, successCb, errorCb) {
                return socialhistorys;
            },

            create: function (patientId, socialhistory, successCb, errorCb) {
                return {status: 201};
            },

            get: function (id, successCb, errorCb) {
                for (var i = 0; i < socialhistorys.length; i++) {
                    if (socialhistorys[i].id === id) {
                        return socialhistorys[i];
                    }
                }
            },

            update: function (id, socialhistory, successCb, errorCb) {
                return {status: 200};
            },

            delete: function (id, successCb, errorCb) {
                socialhistorys.splice(id, 1);
                return {status: 200};
            },

            getSocialhistoryCodes: function (successCb, errorCb) {
                return socialhistoryCodes;
            },

            getSocialhistoryStatusCodes: function (successCb, errorCb) {
                return statusCodes;
            }
        };

        mockLoadedSocialhistories = mockSocialhistoryService.query(213, successCb, errorCb);

        scope.selectedPatientId = 213;

        socialhistoryListCtrl = $controller('socialhistoryListCtrl', {
            $scope: scope,
            SocialhistoryService: mockSocialhistoryService,
            loadedSocialHistories: mockLoadedSocialhistories
        });
    }));

    it('should retrieve a list of socialhistorys', function () {
        expect(scope.socialhistories.length).toBeGreaterThan(0);
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
        scope.sort('socialHistoryTypeName');
        expect(scope.reverse).toBeFalsy();
        scope.sort('socialHistoryTypeName');
        expect(scope.reverse).toBeTruthy();
    });

    it('should sort by status column', function () {
        scope.sort('socialHistoryStatusCode');
        expect(scope.reverse).toBeFalsy();
        scope.sort('socialHistoryStatusCode');
        expect(scope.reverse).toBeTruthy();
    });

    it('should sort by startDate column', function () {
        scope.sort('startDate');
        expect(scope.reverse).toBeFalsy();
        scope.sort('startDate');
        expect(scope.reverse).toBeTruthy();
    });

    it('should sort by endDate column', function () {
        scope.sort('endDate');
        expect(scope.reverse).toBeFalsy();
        scope.sort('endDate');
        expect(scope.reverse).toBeTruthy();
    });

    it('should delete a socialhistory', function () {
        scope.deleteSocialhistory(4);
        var socialhistory = mockSocialhistoryService.get(4);
        expect(socialhistory).toEqual(undefined);
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
        scope.filteredsocialhistories =  [ {id: "1", name: "p1"}, {id: "1", name: "p2"},];

        scope.$apply();
        expect(scope.startRecord ).toEqual(1);
        expect(scope.showPageSize ).toEqual(2);

        //In case the filter size is more than the page size
        scope.filteredsocialhistories =  [{id: "1", name: "p1"},{id: "2", name: "p2"},{id: "3", name: "p3"},{id: "4", name: "p4"},{id: "5", name: "p5"},
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

describe("bham.socialhistoryModule SocialhistoryEditCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.socialhistoryModule'));

    var scope, $controller, mockSocialhistoryService, socialhistoryEditCtrl, $route, $compile, form, socialhistorys, socialhistoryCodes, statusCodes, socialhistory, loadedData;

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

        socialhistorys = [
            {id: "1", name: "c1"},
            {id: "2", name: "c2"}
        ];

        socialhistoryCodes = [
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

        mockSocialhistoryService = {
            query: function (patientId, successCb, errorCb) {
                return socialhistorys;
            },

            create: function (patientId, socialhistory, successCb, errorCb) {
                return {status: 201};
            },

            get: function (id, successCb, errorCb) {
                for (var i = 0; i < socialhistorys.length; i++) {
                    if (socialhistorys[i].id === id) {
                        return socialhistorys[i];
                    }
                }
            },

            update: function (id, socialhistory, successCb, errorCb) {
                return {status: 200};
            },

            delete: function (id, successCb, errorCb) {
                socialhistorys.splice(id, 1);
                return {status: 200};
            },

            getSocialhistoryCodes: function (successCb, errorCb) {
                return socialhistoryCodes;
            },

            getSocialhistoryStatusCodes: function (successCb, errorCb) {
                return statusCodes;
            }
        };

        //loadedData is pre-populated when SocialhistoryEditCtrl is called.
        loadedData = [socialhistoryCodes, statusCodes, socialhistorys[0]];

        socialhistoryEditCtrl = $controller('socialhistoryEditCtrl', {
            $scope: scope,
            SocialhistoryService: mockSocialhistoryService,
            loadedData: loadedData
        });

    }));

    it('Should contain default values for dropdown and the selected socialhistory', function () {
        expect(scope.socialhistoryTypeCodes).toEqualData(socialhistoryCodes);
        expect(scope.problemStatusCodes).toEqualData(statusCodes);
        expect(scope.socialhistory).toEqualData(socialhistorys[0]);
    });

    it('Should edit a socialhistory', function () {
        scope.save(socialhistorys[0]);
        var editedSocialhistory = mockSocialhistoryService.get(socialhistorys[0].id);
        expect(editedSocialhistory).toEqualData(socialhistorys[0]);
    });


});

describe("bham.socialhistoryModule SocialhistoryCreateCtrl", function () {
    beforeEach(module('ngRoute'));
    beforeEach(module('bham.socialhistoryModule'));

    var scope, $controller, mockSocialhistoryService, socialhistoryCreateCtrl, $route, $compile, form, socialhistorys, socialhistoryCodes, statusCodes, socialhistory, loadedData;

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

        socialhistorys = [
            {id: "1", name: "c1"},
            {id: "2", name: "c2"}
        ];

        socialhistoryCodes = [
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

        mockSocialhistoryService = {
            query: function (socialhistoryId, successCb, errorCb) {
                return socialhistorys;
            },

            create: function (socialhistoryId, socialhistory, successCb, errorCb) {
                socialhistorys.push(socialhistory);
                return {status: 201};
            },

            get: function (id, successCb, errorCb) {
                for (var i = 0; i < socialhistorys.length; i++) {
                    if (socialhistorys[i].id === id) {
                        return socialhistorys[i];
                    }
                }
            },

            update: function (id, socialhistory, successCb, errorCb) {
                return {status: 200};
            },

            delete: function (id, successCb, errorCb) {
                socialhistorys.splice(id, 1);
                return {status: 200};
            },

            getSocialhistoryCodes: function (successCb, errorCb) {
                return socialhistoryCodes;
            },

            getSocialhistoryStatusCodes: function (successCb, errorCb) {
                return statusCodes;
            }
        };

        //loadedData is pre-populated when SocialhistoryEditCtrl is called.
        loadedData = [socialhistoryCodes, statusCodes];

        scope.selectedPatientId = 213;

        socialhistoryCreateCtrl = $controller('socialhistoryCreateCtrl', {
            $scope: scope,
            SocialhistoryService: mockSocialhistoryService,
            loadedData: loadedData
        });

    }));

    it('Should contain default values for dropdown and the selected socialhistory', function () {
        expect(scope.socialhistoryTypeCodes).toEqualData(socialhistoryCodes);
        expect(scope.problemStatusCodes).toEqualData(statusCodes);
    });

    it('Should create a socialhistory', function () {
        var newSocialhistory = {id: "3", name: "c3"};
        scope.save(newSocialhistory);
        var retrievedSocialhistory = mockSocialhistoryService.get(newSocialhistory.id);
        expect(newSocialhistory).toEqualData(retrievedSocialhistory);
    });


});
