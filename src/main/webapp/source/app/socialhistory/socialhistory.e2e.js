/**
 * Created by tomson.ngassa on 6/24/14.
 */

'use strict';

describe('BHAM > Patients Social history', function() {
    var patientsPath = 'patients';
    var patientSocialhistory = 'patient-socialhistory';

    beforeEach(function(){
        browser.get('#/' + patientsPath );
        element(by.id('patient-treatmentplan-0')).click();
    });

    it('should create social history ', function() {
        element(by.id(patientSocialhistory)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('socialhistory-add-btn')).click();

        element.all(by.css('select[ng-model="socialhistory.socialHistoryTypeCode"] option')).then(function(socialHistoryTypeCode){
            socialHistoryTypeCode[4].click();
        });

        element.all(by.css('select[ng-model="socialhistory.socialHistoryStatusCode"] option')).then(function(socialHistoryStatusCode){
            socialHistoryStatusCode[2].click();
        });

        var startDate = element(by.model('socialhistory.startDate'));
        startDate.sendKeys('05/25/2014');
        startDate.click();

        var endDate = element(by.model('socialhistory.endDate'));
        endDate.sendKeys('05/27/2014');
        endDate.click();

        //Create social history
        element(by.id('socialhistory-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should edit social history ', function() {
        element(by.id(patientSocialhistory)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('socialhistory-edit-0')).click();

        element.all(by.css('select[ng-model="socialhistory.socialHistoryTypeCode"] option')).then(function(socialHistoryTypeCode){
            socialHistoryTypeCode[2].click();
        });

        element.all(by.css('select[ng-model="socialhistory.socialHistoryStatusCode"] option')).then(function(socialHistoryStatusCode){
            socialHistoryStatusCode[3].click();
        });

        var startDate = element(by.model('socialhistory.startDate'));
        startDate.clear();
        startDate.sendKeys('05/25/2014');
        startDate.click();

        var endDate = element(by.model('socialhistory.endDate'));
        endDate.clear();
        endDate.sendKeys('05/27/2014');
        endDate.click();

        //Create social history
        element(by.id('socialhistory-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should navigate back to the social history list using back button', function() {
        element(by.id(patientSocialhistory)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('socialhistory-add-btn')).click();

        element(by.id('socialhistory-back-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

});