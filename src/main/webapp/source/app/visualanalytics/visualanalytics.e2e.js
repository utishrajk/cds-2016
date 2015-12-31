/**
 * Created by tomson.ngassa on 6/17/14.
 */

'use strict';

describe('BHAM > Visualanalytics', function() {
    var visualanalyticsPath =  'visualanalytics';

    it('should route to visualanalytics page', function() {
        browser.get('#/' + visualanalyticsPath);
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + visualanalyticsPath );
    });

});

