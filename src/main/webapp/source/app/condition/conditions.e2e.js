/**
 * Created by tomson.ngassa on 6/24/14.
 */

'use strict';

describe('BHAM > Patients Conditions', function() {
    var patientsPath = 'patients';
    var patientConditions = 'patient-conditions';
    beforeEach(function(){
        browser.get('#/' + patientsPath );
        element(by.id('patient-treatmentplan-0')).click();
    });

    it('should create conditions ', function() {
        element(by.id(patientConditions)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('condition-add-btn')).click();

        element.all(by.css('select[ng-model="condition.problemCode"] option')).then(function(problemCodes){
            problemCodes[2].click();
        });

        element.all(by.css('select[ng-model="condition.problemStatusCode"] option')).then(function(problemStatusCodes){
            problemStatusCodes[3].click();
        });

        var startDate = element(by.model('condition.startDate'));
        startDate.sendKeys('05/25/2014');
        startDate.click();

        var endDate = element(by.model('condition.endDate'));
        endDate.sendKeys('05/27/2014');
        endDate.click();

        //Create patient
        element(by.id('condition-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should create conditions ', function() {
        element(by.id(patientConditions)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('condition-edit-0')).click();

        element.all(by.css('select[ng-model="condition.problemCode"] option')).then(function(problemCodes){
            problemCodes[1].click();
        });

        element.all(by.css('select[ng-model="condition.problemStatusCode"] option')).then(function(problemStatusCodes){
            problemStatusCodes[2].click();
        });

        var startDate =  element(by.model('condition.startDate'));
        startDate.clear();
        startDate.sendKeys('04/25/2014');
        startDate.click();

        var endDate = element(by.model('condition.endDate'));
        endDate.clear();
        endDate.sendKeys('04/27/2014');
        endDate.click();

        //Create patient
        element(by.id('condition-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should navigate back to the condition list using back button', function() {
        element(by.id(patientConditions)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('condition-add-btn')).click();

        element(by.id('condition-back-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

});