/**
 * Created by tomson.ngassa on 3/10/14.
 */

'use strict';

describe("bham.dashboarModule:", function() {
    var $route, $location, $rootScope;

    beforeEach(module('ngRoute'));
    beforeEach(module('bham.dashboarModule'));

    beforeEach(inject(function(_$route_, _$location_, _$rootScope_){
        $route = _$route_;
        $location = _$location_;
        $rootScope = _$rootScope_;
    }));

    xit('should route to the dashboard page', function(){
        expect($route.current).toBeUndefined();
        $location.path('/dashboard');
        $rootScope.$digest();

        expect($location.path()).toBe('/dashboard');
        expect($route.current.template).toBe('<h3>Dashboard</h3>');
    });
});