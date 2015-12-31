

'use strict';

describe('BHAM > Side-NavBar', function() {
    var baseUrl = browser.baseUrl;
    var dashboardUrl = baseUrl + 'dashboard';
    var caremanagerUrl = baseUrl +  'caremanager/2';
    var organizationUrl = baseUrl +  'organization';
    var patientsUrl = baseUrl +  'patients';
    var visualanalyticsUrl = baseUrl +  'visualanalytics';
    var messagecenterUrl = baseUrl +  'messagecenter';
    var reportsUrl = baseUrl +  'reports';
    var remindersUrl = baseUrl +  'reminders';
    var toolsandresourcesUrl = baseUrl +  'toolsandresources';


    it('should remain in the dashboard page', function() {
        element(by.id('navbar-dashboard-lnk')) .click()
        expect(browser.getCurrentUrl()).toEqual(dashboardUrl);
    });

    it('should route to the caremanager page', function() {
        element(by.id('navbar-caremanager-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(caremanagerUrl);
    });

    it('should route to the organization page', function() {
        element(by.id('navbar-organization-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(organizationUrl);
    });

    it('should route to the patients page', function() {
        element(by.id('navbar-patients-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(patientsUrl);
    });

    it('should route to the visualanalytics page', function() {
        element(by.id('navbar-visualanalytics-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(visualanalyticsUrl);
    });

    it('should route to the message center page', function() {
        element(by.id('navbar-messagecenter-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(messagecenterUrl);
    });

    it('should route to the reports page', function() {
        element(by.id('navbar-reports-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(reportsUrl);
    });

    it('should route to the reminders page', function() {
        element(by.id('navbar-reminders-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(remindersUrl);
    });

    it('should route to the tools and resources page', function() {
        element(by.id('navbar-toolsandresources-lnk')).click()
        expect(browser.getCurrentUrl()).toEqual(toolsandresourcesUrl);
    });
});