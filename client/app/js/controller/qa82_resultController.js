/**
 * Created by pgiessler on 11.12.2014.
 */

qa82analyzer.controller('QA82_ResultController', function ($scope) {
    $scope.resultAvailable = false;

    $scope.$on('resultAvailableEvent', function(event, args) {
        $scope.resultAvailable = true;
        console.log(args);
    });
});