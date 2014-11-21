//var qa82analyzer = angular.module('qa82analyzer', ['http']);

qa82analyzer.controller('QA82_QueryController', function ($scope, httpService) {
    $scope.qualityAnalysisList = [
        {'name' : 'analysis1 name'},
        {'name' : 'analysis2 name'},
    ];
    $scope.resultAvailable = false;
    $scope.selectedQualityAnalysis = $scope.qualityAnalysisList[0];
    $scope.analyze = function() {
        console.log("Sorry not yet implemented")
    };
});

