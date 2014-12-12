/**
 * Created by pgiessler on 11.12.2014.
 */

qa82analyzer.controller('QA82_ResultController', function ($scope) {
    $scope.resultAvailable = false;
    $scope.columns = [];
    $scope.rows = [];

    $scope.$on('resultAvailableEvent', function(event, args) {
        $scope.resultAvailable = true;
        if (args.information.length > 0) {
            $scope.columns.push(args.information[0].name);
            for (index in args.information) {
                $scope.rows.push(args.information[index]);
            }
            ;
        }
        console.log($scope.columns);
        console.log($scope.rows);
    });
});