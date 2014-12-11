//var qa82analyzer = angular.module('qa82analyzer', ['http']);

qa82analyzer.controller('QA82_QueryController', function ($rootScope, $scope, httpService) {
    $scope.qualityAnalysisList = [];
    $scope.selectedQualityAnalysis = undefined;
    $scope.analyze = function() {
        httpService.httpPost("analyzer-test/information",$scope.selectedQualityAnalysis, function(data) {
            $rootScope.$broadcast('resultAvailableEvent', data);
        }, function() {
            console.log("Error");
        });
    };

    $scope.getProvidedInformationNeed = function() {
        httpService.httpGet("analyzer-test/informationneeds/provided", function(data) {
            $scope.qualityAnalysisList = data;
            console.log($scope.qualityAnalysisList);
        }, function() {
            console.log("Error");
        });
    }

    $scope.init = function() {
        $scope.getProvidedInformationNeed();
    }
});

