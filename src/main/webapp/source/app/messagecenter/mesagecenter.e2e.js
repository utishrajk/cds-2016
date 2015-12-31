/**
 * Created by tomson.ngassa on 6/17/14.
 */

'use strict';

describe('BHAM > Message Center', function() {
    var messagecenterPath = 'messagecenter';

    it('should route to message center after login', function() {
        browser.get('#/' + messagecenterPath);
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + messagecenterPath);
    });

});

