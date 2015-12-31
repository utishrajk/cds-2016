'use strict';
angular.module('bham', [
        'templates-app',
        'templates-common',
        'ngRoute',
        'bham.dashboarModule',
        'bham.caremanagerModule',
        'bham.organizationModule',
        'bham.patientModule',
        'bham.conditionModule',
        'bham.socialhistoryModule',
        'bham.visualanalyticsModule',
        'bham.messagecenterModule',
        'bham.reportsModule',
        'bham.toolsandresourcesModule',
        'bham.directives',
        'bham.breadcrumbsModule',
        'bham.procedureModule',
        'bham.outcomeModule',
        'bham.reminderModule',
        'http-auth-interceptor',
//        'spring-security-csrf-token-interceptor',
//        'tmh.dynamicLocale',
        'ngCookies',
//        'pascalprecht.translate',
        'bham.security'
    ])

    .config(['$routeProvider', '$locationProvider', '$compileProvider', 'USER_ROLES', '$httpProvider',
        function ($routeProvider, $locationProvider, $compileProvider, USER_ROLES, $httpProvider) {

            $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|tel):/);

//        $locationProvider.html5Mode(true).hashPrefix('!');

//        $locationProvider.html5Mode(true).hashPrefix('!');

            //TODO: Enable html5mMode
            //$locationProvider.html5Mode(true);
            $routeProvider
                .when('/error', {
                    templateUrl: 'error.tpl.html',
                    controller: 'ErrorCtrl',
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
                .when('/login', {
                    templateUrl: 'login.tpl.html',
                    controller: 'LoginController',
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
                .when('/logout', {
                    templateUrl: 'login.tpl.html',
                    controller: 'LogoutController',
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
                .when('/register', {
                    templateUrl: 'register.tpl.html',
                    controller: 'RegisterController',
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
                .otherwise({
                    redirectTo: '/login',
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                });

//	        // Initialize angular-translate
//           $translateProvider.useStaticFilesLoader({
//	            prefix: 'i18n/',
//	            suffix: '.json'
//	        });
//	
//	        $translateProvider.preferredLanguage('en');
//	
//	        $translateProvider.useCookieStorage();
//	
//	        tmhDynamicLocaleProvider.localeLocationPattern('../vendor/angular-i18n/angular-locale_{{locale}}.js');
//	        tmhDynamicLocaleProvider.useCookieStorage('NG_TRANSLATE_LANG_KEY');

        }])

    .config(['$httpProvider', function ($httpProvider) {
        // Enable cross domain calls
        $httpProvider.defaults.useXDomain = true;

        // Remove the header used to identify ajax call  that would prevent CORS from working
        // The 'X-Request-With' header is no longer present by default as of Angular 1.2
        //delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
    ])

    .run(['$rootScope', '$location', '$http', 'AuthenticationSharedService', 'Session', 'USER_ROLES', '$cookieStore',
        function ($rootScope, $location, $http, AuthenticationSharedService, Session, USER_ROLES, $cookieStore) {
            $rootScope.$on('$routeChangeStart', function (event, next) {
                $rootScope.authenticated = AuthenticationSharedService.isAuthenticated();
                $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
                $rootScope.userRoles = USER_ROLES;
                $rootScope.account = Session;

                var authorizedRoles = (angular.isDefined(next.access) && angular.isDefined(next.access.authorizedRoles) ) ? next.access.authorizedRoles : "";
                if (!AuthenticationSharedService.isAuthorized(authorizedRoles)) {
                    event.preventDefault();
                    if (AuthenticationSharedService.isAuthenticated()) {
                        // user is not allowed
                        $rootScope.$broadcast("event:auth-notAuthorized");
                    } else {
                        // user is not logged in
                        $rootScope.$broadcast("event:auth-loginRequired");
                    }
                } else {
                    // Check if the customer is still authenticated on the server
                    // Try to load a protected 1 pixel image.
                    if (!$rootScope.authenticated && $location.path() === "/register") {
                        $location.path('/register').replace();
                    }
                }
            });

            // Call when the the client is confirmed
            $rootScope.$on('event:auth-loginConfirmed', function (data) {
                if ($location.path() === "/login") {
                    $location.path('/dashboard').replace();
                }
            });

            // Call when the 401 response is returned by the server
            $rootScope.$on('event:auth-loginRequired', function (rejection) {
                Session.destroy();
                $rootScope.authenticated = false;
                if ($location.path() !== "/" && $location.path() !== "") {
                    $location.path('/login').replace();
                }
            });

            // Call when the 403 response is returned by the server
            $rootScope.$on('event:auth-notAuthorized', function (rejection) {
                $rootScope.errorMessage = 'Access deny';
                $rootScope.errorCode = '403';
                $location.path('/error').replace();
            });

            // Call when the user logs out
            $rootScope.$on('event:auth-loginCancelled', function () {
                $location.path('/login');
            });
        }])

    .controller('ErrorCtrl', [ '$scope', '$routeParams', '$location', function ($scope, $routeParams, $location) {

        //Back from back button
        $scope.goBack = function () {
            window.history.back();
        };

        //Back from browser back button
        $scope.$back = function () {
            window.history.back();
        };
    }])
    .controller('AppCtrl', [ '$scope', '$location', 'BreadcrumbsService', 'SocialhistoryService', 'ConditionService', '$route', '$cookieStore','$anchorScroll',
        function ($scope, $location, BreadcrumbsService, SocialhistoryService, ConditionService, $route, $cookieStore, $anchorScroll) {

            $scope.headnavbar = '../head-navbar.tpl.html';
            $scope.breadcrums = '../breadcrums.tpl.html';
            $scope.sidenavbar = '../side-navbar.tpl.html';

            //Date pattern used by all the forms to check the format of the date
            $scope.datePattern = /(0[1-9]|1[012])[ \/.](0[1-9]|[12][0-9]|3[01])[ \/.](19|20)\d\d/;

            $scope.breadcrumbs = function () {
                var breadcrumbs = BreadcrumbsService.getAll();

                //updateing the current page
                if (breadcrumbs.length >= 1) {
                    // Update the current page
                    $scope.currentPage = breadcrumbs[0].name;
                }
                return breadcrumbs;
            };

            $scope.redirect = function (path) {
                $location.path(path);
                $route.reload();
            };

            $scope.getEntityById = function (entityList, entityId) {
                for (var i = 0; i < entityList.length; i++) {
                    if (entityList[i].id === parseInt(entityId)) {
                        return entityList[i];
                    }
                }
            };

            $scope.deleteEntityById = function (entityList, entityId) {
                for (var i = 0; i < entityList.length; i++) {
                    if (entityList[i].id === parseInt(entityId)) {
                        entityList.splice(i, 1);
                        break;
                    }
                }
            };

            $scope.openCustomMenu = false;

            $scope.populateCustomPatientMenu = function (selectedPatient) {
                $scope.selectedPatient = selectedPatient;
                $scope.selectedPatientId = selectedPatient.id;
                $scope.selectedPatientFullName = angular.isDefined(selectedPatient) ? selectedPatient.fullName:'';

                $scope.collapseDemographicsAccordion = "";
                $scope.toggleDemographicsAccordionClass = false;

                $scope.collapseConditionsAccordion = "";
                $scope.toggleConditionsAccordionClass = false;

                $scope.collapseSocialhistoryAccordion = "";
                $scope.toggleSocialhistoryAccordionClass = false;

                $scope.collapseProcedureAccordion = "";
                $scope.toggleProcedureAccordionClass = false;

                $scope.enableCustomPatientMenu();
                $scope.removeActiveClassInSideNavBar();
            };

            $scope.selectTreatmentPlanMenu = function () {
                $scope.treatmentPlanMenuitem = true;
            };

            $scope.selectSummaryRecordMenu = function () {
                $scope.summaryCareRecordMenuitem = true;
            };

            $scope.onToggledDemographicsAccordion = function () {
                $scope.collapseDemographicsAccordion = $scope.collapseDemographicsAccordion === "collapse" ? '' : 'collapse';
                $scope.toggleDemographicsAccordionClass = !$scope.toggleDemographicsAccordionClass;
            };

            $scope.onToggledConditionsAccordion = function () {
                $scope.collapseConditionsAccordion = $scope.collapseConditionsAccordion === "collapse" ? '' : 'collapse';
                $scope.toggleConditionsAccordionClass = !$scope.toggleConditionsAccordionClass;
            };

            $scope.onToggledSocialHistoryAccordion = function () {
                $scope.collapseSocialhistoryAccordion = $scope.collapseSocialhistoryAccordion === "collapse" ? '' : 'collapse';
                $scope.toggleSocialhistoryAccordionClass = !$scope.toggleSocialhistoryAccordionClass;
            };

            $scope.onToggledProcedureAccordion = function () {
                $scope.collapseProcedureAccordion = $scope.collapseProcedureAccordion === "collapse" ? '' : 'collapse';
                $scope.toggleProcedureAccordionClass = !$scope.toggleProcedureAccordionClass;
            };

            $scope.removeActiveClassInSideNavBar = function () {
                $scope.dashboardMenuitem = false;
                $scope.careManagerMenuitem = false;
                $scope.organizationMenuitem = false;
                $scope.patientListMenuitem = false;
                $scope.visualAnalyticsMenuitem = false;
                $scope.messageCenterMenuitem = false;
                $scope.reportsMenuitem = false;
                $scope.toolsResourceMenuitem = false;
                $scope.conditionsMenuitem = false;
                $scope.socialHistoryMenuitem = false;
                $scope.procedureMenuitem = false;
                $scope.treatmentPlanMenuitem = false;
                $scope.demographicsMenuitem = false;
                $scope.summaryCareRecordMenuitem = false;
                $scope.remindersMenuitem = false;
            };

            $scope.addActiveClassInSideNavBar = function (menuItem) {
                if (menuItem === 'dashboard') {
                    $scope.dashboardMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'careManager') {
                    $scope.careManagerMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'organization') {
                    $scope.organizationMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'patientList') {
                    $scope.patientListMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'visualAnalytics') {
                    $scope.visualAnalyticsMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'messageCenter') {
                    $scope.messageCenterMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'reports') {
                    $scope.reportsMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'toolsAndResource') {
                    $scope.toolsResourceMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'reminders') {
                    $scope.remindersMenuitem = true;
                    $scope.disableCustomPatientMenu();
                } else if (menuItem === 'conditions') {
                    $scope.enableCustomPatientMenu();
                    $scope.conditionsMenuitem = true;
                } else if (menuItem === 'socialHistory') {
                    $scope.enableCustomPatientMenu();
                    $scope.socialHistoryMenuitem = true;
                } else if (menuItem === 'procedure') {
                    $scope.enableCustomPatientMenu();
                    $scope.procedureMenuitem = true;
                } else if (menuItem === 'treatmentPlan') {
                    $scope.enableCustomPatientMenu();
                    $scope.treatmentPlanMenuitem = true;
                } else if (menuItem === 'demographics') {
                    $scope.enableCustomPatientMenu();
                    $scope.demographicsMenuitem = true;
                }
                else if (menuItem === 'summaryCareRecord') {
                    $scope.enableCustomPatientMenu();
                    $scope.summaryCareRecordMenuitem = true;
                }
            };

            $scope.onSelectmenu = function (menuItem) {
                $scope.enableDropDownMenu = false;
                $scope.removeActiveClassInSideNavBar();
                $scope.addActiveClassInSideNavBar(menuItem);
            };

            $scope.enableCustomPatientMenu = function () {
                $scope.openCustomMenu = true;
            };


            $scope.disableCustomPatientMenu = function () {
                $scope.openCustomMenu = false;
            };

            //For debugging
            $scope.toJSON = function (obj) {
                return JSON.stringify(obj, null, 2);
            };

            //hide or show elements use in demographics table
            //Determine whether to route to demographics or patientlist page
            $scope.isDemographics = false;

            $scope.toggleDemographicMode = function (isDemographics) {
                $scope.isDemographics = isDemographics;
            };

            $scope.showPatientProfile = function () {
                $location.path("/patient/" + $scope.selectedPatientId + "/patientprofile");
            };

            $scope.isFutureDate = function (currentDate) {
                var result = false;
                if (currentDate) {
                    var today = new Date().getTime();
                    var newDate = new Date(currentDate).getTime();

                    if (newDate > today) {
                        result = true;
                    }
                }
                return result;

            };

            $scope.isEndDateBeforeStartDate = function (startDate, endDate) {
                var result = false;
                if (startDate && endDate) {
                    var start = new Date(startDate);
                    var end = new Date(endDate);
                    if (end < start) {
                        result = true;
                    }
                }
                return result;
            };

            $scope.enableDropDownMenu = false;

            $scope.toggleDropDownMenu = function(){
                $scope.enableDropDownMenu = !$scope.enableDropDownMenu;
            };

            $scope.gotoTop = function (){
                //Id of the element you want to scroll to
                $location.hash('top');
                // call $anchorScroll()
                $anchorScroll();
            };
        }])
    .controller('LoginController', ['$scope', '$location', 'AuthenticationSharedService', function ($scope, $location, AuthenticationSharedService) {
        $scope.rememberMe = true;

        $scope.login = function () {
            AuthenticationSharedService.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe,
                success: function () {
                }
            });
        };
    }])

    .controller('LogoutController', ['$scope', '$location', 'AuthenticationSharedService', function ($scope, $location, AuthenticationSharedService) {
        AuthenticationSharedService.logout({
            success: function () {
                $location.path('');
            }
        });
    }])
    .controller('RegisterController', ['$rootScope', '$scope', '$location', 'CareManagerService', function ($rootScope, $scope, $location, CareManagerService) {
        var successCallback = function (data) {
            $rootScope.registererror = false;
            $location.path("/login");
            $rootScope.validateMessage = true;
        };

        var errorCallback = function (error) {
            console.log(error.data.message);
            $rootScope.registerErrorMsg = "Somebody already has that username";
            $rootScope.registererror = true;
        };

        $scope.register = function () {

            var user = $scope.user;

            if (user === undefined || isEmpty(user.firstName) || isEmpty(user.lastName) || isEmpty(user.email) || isEmpty(user.credential) || isEmpty(user.confirmPassword)) {
                $rootScope.registerErrorMsg = 'All fields are required';
                $rootScope.registererror = true;

            } else if (user.credential !== user.confirmPassword) {
                $rootScope.registerErrorMsg = "Your passwords don't match";
                $rootScope.registererror = true;

            } else if (!validateEmail(user.email)) {
                $rootScope.registerErrorMsg = "Your email is not valid";
                $rootScope.registererror = true;

            } else {
                CareManagerService.create(user, successCallback, errorCallback);
            }

            function validateEmail(email) {
                var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }

            function isEmpty(str) {
                return str === undefined || str.trim() === '';
            }

        };


    }])
;
