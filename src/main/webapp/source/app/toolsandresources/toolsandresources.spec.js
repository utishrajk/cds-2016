/**
 * Created by tomson.ngassa on 3/10/14.
 */

'use strict';

describe("bham.toolsandresourcesModule:", function() {
    var $route, $location, $rootScope;

    beforeEach(module('ngRoute'));
    beforeEach(module('treeControl'));
    beforeEach(module('bham.toolsandresourcesModule'));

    var scope;

    beforeEach(inject(function(_$route_, $rootScope, $controller){
        $route = _$route_;
        scope = $rootScope.$new();

       var  ToolsAndResourcesCtrl = $controller('ToolsAndResourcesCtrl', {
            $scope: scope
        });
    }));

    it('should have default values', function(){
        expect(scope.treedata).toBeDefined();
        expect(scope.option).toBeDefined();
    });
});