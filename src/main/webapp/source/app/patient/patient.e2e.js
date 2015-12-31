/**
 * Created by tomson.ngassa on 6/17/14.
 */

'use strict';

describe('BHAM > Patients', function() {
    var patientsPath = 'patients';

    beforeEach(function(){
        browser.get('#/' + patientsPath );
    });

    it('should show patients page', function() {
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + patientsPath);
    });

    xit('should increase page size', function() {
        element(by.model('pageSize')).then(function(selectItems){
            selectItems[0].getText('').ToBe('10')
        });

//      var pageSizeSelect = element(by.model('pageSize'));
//
//      var patients = element.all(by.repeater('patient in filteredPatients'));
//      expect(patients.count()).toBe(31);
    });

    it('should contain 10 patients for the inital page size', function() {
        var patients = element.all(by.repeater('patient in filteredPatients'));
        expect(patients.count()).toBe(10);
    });

    it('should navigate back to the patient list using back button', function() {
        element(by.id('patient-add-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + 'patients/add');

        element(by.id('patient-back-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + 'patients');
    });


    it('should create a new patient', function() {
        element(by.id('patient-add-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + 'patients/add');

        element(by.model('patient.medicalRecordNumber')).sendKeys('000001');
        element(by.model('patient.firstName')).sendKeys('firstname');
        element(by.model('patient.lastName')).sendKeys('lastname');
        element.all(by.model('patient.administrativeGenderCode')).get(0).click();

        element.all(by.css('select[ng-model="patient.raceCode"] option')).then(function(races){
            races[2].click();
        });

        element(by.model('patient.birthDate')).sendKeys('05/25/2014');
        element(by.model('patient.birthDate')).click();

        element(by.model('patient.addressPostalCode')).sendKeys('21045');

        element.all(by.css('select[ng-model="patient.addressStateCode"] option')).then(function(states){
            states[10].click();
        });

        //Create patient
        element(by.id('patient-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + 'patients');
    });

    it('should edit patient', function() {
        element(by.id('patient-edit-0')).click();
        var patientId = element(by.model('patient.medicalRecordNumber'));
        var firstName = element(by.model('patient.firstName'));
        var lastName = element(by.model('patient.lastName'));
        var zipCode =  element(by.model('patient.addressPostalCode'));
        var dob = element(by.model('patient.birthDate'));

        patientId.clear();
        firstName.clear();
        lastName.clear();
        zipCode.clear();
        dob.clear();

        patientId.sendKeys('000001');
        firstName.sendKeys('Tomson');
        lastName.sendKeys('Ngassa');
        element.all(by.model('patient.administrativeGenderCode')).get(0).click();

        element.all(by.css('select[ng-model="patient.raceCode"] option')).then(function(races){
            races[2].click();
        });

        element(by.model('patient.birthDate')).sendKeys('05/25/2014');
        element(by.model('patient.birthDate')).click();

        zipCode.sendKeys('21045');

        element.all(by.css('select[ng-model="patient.addressStateCode"] option')).then(function(states){
            states[10].click();
        });

        //Create patient
        element(by.id('patient-save-btn')).click();
        expect(browser.getCurrentUrl()).toEqual(browser.baseUrl + 'patients');
    });

    it('should navigate to patient treatment ', function() {
        element(by.id('patient-treatmentplan-0')).click();
    });

    it('should navigate to summary care records ', function() {
        element(by.id('patient-treatmentplan-0')).click();
        element(by.id('patient-summarycarerecord')).click();
    });

    it('should open delete dialogue', function() {
        //Open delete dialogue
        element(by.id('patient-delete-0')).click();
        //Cancel delete dialogue
        element(by.id('patient-cancel-btn')).click();

    });

    //ui-dialog-titlebar-close
});
