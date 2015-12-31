/**
 * Created by tomson.ngassa on 6/24/14.
 */

'use strict';

describe('BHAM > Patients Procedure', function() {
    var patientsPath = 'patients';
    var patientProcdure = 'patient-procedures';

    beforeEach(function(){
        browser.get('#/' + patientsPath );
        element(by.id('patient-treatmentplan-0')).click();
    });

    it('should create social history ', function() {
        element(by.id(patientProcdure)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('procedure-add-btn')).click();

        element.all(by.css('select[ng-model="procedure.procedureTypeCode"] option')).then(function(procedureTypeCode){
            procedureTypeCode[2].click();
        });

        element.all(by.css('select[ng-model="procedure.procedureStatusCode"] option')).then(function(procedureStatusCode){
            procedureStatusCode[2].click();
        });

        var startDate = element(by.model('procedure.procedureStartDate'));
        startDate.sendKeys('05/25/2014');
        startDate.click();

        var endDate = element(by.model('procedure.procedureEndDate'));
        endDate.sendKeys('05/27/2014');
        endDate.click();

        //Create procedure
        element(by.id('procedure-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should edit procedure ', function() {
        element(by.id(patientProcdure)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('procedure-edit-0')).click();

        element.all(by.css('select[ng-model="procedure.procedureTypeCode"] option')).then(function(procedureTypeCode){
            procedureTypeCode[1].click();
        });

        element.all(by.css('select[ng-model="procedure.procedureStatusCode"] option')).then(function(procedureStatusCode){
            procedureStatusCode[1].click();
        });

        var startDate = element(by.model('procedure.procedureStartDate'));
        startDate.clear();
        startDate.sendKeys('05/25/2014');
        startDate.click();

        var endDate = element(by.model('procedure.procedureEndDate'));
        endDate.clear();
        endDate.sendKeys('05/27/2014');
        endDate.click();

        //Edit procedure
        element(by.id('procedure-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should navigate back to the procedure list using back button', function() {
        element(by.id(patientProcdure)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('procedure-add-btn')).click();

        element(by.id('procedure-back-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });



});