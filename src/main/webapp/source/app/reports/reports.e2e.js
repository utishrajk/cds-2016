/**
 * Created by tomson.ngassa on 6/17/14.
 */

'use strict';

describe('BHAM > Reports', function() {
    var reportsPath = 'reports';

    it('should route to report page', function() {
        browser.get('#/' + reportsPath );
        expect(browser.getCurrentUrl()).toEqual( browser.baseUrl + reportsPath);
    });
});

