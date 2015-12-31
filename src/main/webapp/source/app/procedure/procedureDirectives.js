'use strict';

angular.module("bham.procedureDirectives", [])
    .directive("deleteProcedure", function () {
        return {
            restrict: 'E',
            scope: {
                procedureid: '@',
                name: '@',
                ondelete: '&'
            },
            template: '<a class="red" href="#">' +
                '<i class="icon-trash bigger-130"></i>' +
                '</a>',
            link: function (scope, element, attrs) {
                element.on('click', function (e) {
                    e.preventDefault();
                    var msg = 'Procedure <b>' + scope.name + '</b> will be permanently deleted and cannot be recovered.';
                    $('#dialog-confirm-msg').html(msg);
                    $("#dialog-confirm").removeClass('hide').dialog({
                        resizable: false,
                        modal: true,
                        title: 'Delete procedure',
                        title_html: true,
                        buttons: [
                            {
                                html: "<i class='icon-trash bigger-110'></i>&nbsp; Delete",
                                "class": "btn btn-danger btn-xs",
                                click: function () {
                                    scope.ondelete({procedureId: scope.procedureid});
                                    $(this).dialog("close");
                                }
                            },
                            {
                                html: "<i class='icon-remove bigger-110'></i>&nbsp; Cancel",
                                "class": "btn btn-xs",
                                click: function () {
                                    $(this).dialog("close");
                                }
                            }
                        ]
                    });
                });
            }
        };
    })
    .directive("showProcedure", function () {
        return {
            restrict: 'E',
            scope: {
                procedure: '='
            },
            template: '<a class="blue" href="#">' +
                '<i class="icon-zoom-in bigger-130"></i>' +
                '</a>',
            link: function (scope, element, attrs) {

                element.on('click', function (e) {
                    e.preventDefault();

                    var selectedProcedure = scope.procedure;
                    var procedureTypeName = selectedProcedure.procedureTypeName;
                    var procedureStatusCode = selectedProcedure.procedureStatusCode;
                    var procedureStartDate = selectedProcedure.procedureStartDate;
                    var procedureEndDate = selectedProcedure.procedureEndDate;
                    var bodySiteCode = selectedProcedure.bodySiteCode;

                    var procedureDetails = '<div class="row">' +
                        '<div class="col-xs-12">' +
                        '<div class="table-responsive">' +
                        '<div class="modal-body no-padding" >' +
                        ' <table  class="table table-striped table-bordered table-hover no-bottom-margin">' +
                        '  <tbody>' +
                        ' <tr > <td  class="captionID" >  <b>Name</b> </td> <td class="dataID"> ' + procedureTypeName + ' </td></tr>' +
                        ' <tr > <td  class="captionID" >  <b>Status</b> </td> <td class="dataID"> ' + procedureStatusCode + ' </td></tr>' +
                        ' <tr > <td  class="captionID" >  <b>Start Date</b> </td> <td class="dataID"> ' + procedureStartDate + ' </td></tr>' +
                        ' <tr > <td  class="captionID" >  <b>End Date</b> </td> <td class="dataID"> ' + procedureEndDate + ' </td></tr>' +
//                        ' <tr > <td  class="captionID" >  <b>Body Site Code</b> </td> <td class="dataID"> ' + bodySiteCode + ' </td></tr>' +   TODO
                        '</tbody>' +
                        ' </table>' +
                        ' </div>' +
                        ' </div>' +
                        ' </div>' +
                        ' </div>';
                    $('#dialog-procedure-details').html(procedureDetails);
                    $("#dialog-procedure-details").removeClass('hide').dialog({
                        resizable: false,
                        modal: true,
                        title: 'Procedure details',
                        title_html: true,
                        width: 400
                    });
                });
            }
        };
    });

