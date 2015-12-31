/**
 * Created by tomson.ngassa on 6/24/14.
 */

'use strict';

describe('BHAM > Patients outcome', function() {
    var patientsPath = 'patients';
    var patientOutcome = 'patient-outcomes';

    beforeEach(function(){
        browser.get('#/' + patientsPath );
        element(by.id('patient-treatmentplan-0')).click();
    });

    it('should create outcomes ', function() {
        element(by.id(patientOutcome)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('outcome-add-btn')).click();
        //Creating first outcome
        var startDate = element(by.model('outcome.startDate'));
        startDate.sendKeys('05/15/2014');
        startDate.click();

        var endDate = element(by.model('outcome.endDate'));
        endDate.sendKeys('05/17/2014');
        endDate.click();


        var symptoms = element(by.model('outcome.symptoms'));
        symptoms.sendKeys('Symptoms 1');

        var tolerability = element(by.model('outcome.tolerability'));
        tolerability.sendKeys('tolerability 1');

        element.all(by.css('select[ng-model="outcome.procedureTypeCode"] option')).then(function(procedureTypeCode){
            procedureTypeCode[2].click();
        });

        element.all(by.css('select[ng-model="outcome.cgiSCode"] option')).then(function(cgiSCode){
            cgiSCode[2].click();
        });

        element.all(by.css('select[ng-model="outcome.cgiICode"] option')).then(function(cgiICode){
            cgiICode[2].click();
        });

//        //Create procedure
         element(by.id('outcome-save-btn')).click();
         expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should edit outcome ', function() {
        element(by.id(patientOutcome)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('outcome-edit-0')).click();

        var startDate = element(by.model('outcome.startDate'));
        startDate.clear();
        startDate.sendKeys('06/20/2014');
        startDate.click();

        var endDate = element(by.model('outcome.endDate'));
        endDate.clear();
        endDate.sendKeys('06/20/2014');
        endDate.click();


        var symptoms = element(by.model('outcome.symptoms'));
        symptoms.clear();
        symptoms.sendKeys('Symptoms 2');

        var tolerability = element(by.model('outcome.tolerability'));
        tolerability.clear();
        tolerability.sendKeys('tolerability 2');

        element.all(by.css('select[ng-model="outcome.procedureTypeCode"] option')).then(function(procedureTypeCode){
            procedureTypeCode[5].click();
        });

        element.all(by.css('select[ng-model="outcome.cgiSCode"] option')).then(function(cgiSCode){
            cgiSCode[5].click();
        });

        element.all(by.css('select[ng-model="outcome.cgiICode"] option')).then(function(cgiICode){
            cgiICode[5].click();
        });

        //Create procedure
        element(by.id('outcome-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

    it('should navigate back to the outcome list using back button', function() {
        element(by.id(patientOutcome)).click();
        var currentUrl = browser.getCurrentUrl();

        element(by.id('outcome-add-btn')).click();

        element(by.id('outcome-back-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(currentUrl);
    });

});