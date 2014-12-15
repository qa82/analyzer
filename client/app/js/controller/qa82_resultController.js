/**
 * Created by pgiessler on 11.12.2014.
 */

qa82analyzer.controller('QA82_ResultController', function ($scope) {

    var information = {};

    $scope.resultAvailable = false;
    $scope.columns = [];
    $scope.rows = [];

    $scope.$on('resultAvailableEvent', function(event, args) {
        this.clearPreviousResult();
        $scope.resultAvailable = true;
        $scope.columns.push(args.informationType);
        $scope.rows = args.information;
    });

    clearPreviousResult = function() {
        $scope.resultAvailable = false;
        $scope.columns = [];
        $scope.rows = [];
    }
});