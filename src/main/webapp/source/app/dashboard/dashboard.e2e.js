/**
 * Created by tomson.ngassa on 6/17/14.
 */

'use strict';


describe('BHAM > Dashboard', function() {
    var dashboardPath = 'dashboard';

    it('should route to dashborard page', function() {
        browser.get('#/' + dashboardPath);
        expect(browser.getCurrentUrl()).toEqual( browser.baseUrl + dashboardPath);
    });

});
