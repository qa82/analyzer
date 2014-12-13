//var qa82analyzer = angular.module('qa82analyzer', ['http']);

qa82analyzer.controller('QA82_QueryController', function ($rootScope, $scope, httpService) {

    var BASE_URI = "analyzer-test/";
    var INFORMATION_RESOURCE_URI = BASE_URI + "information/";
    var INFORMATIONNEED_PROVIDED_RESOURCE_URI = BASE_URI + "informationneeds/provided/";


    $scope.qualityAnalysisList = [];
    $scope.selectedQualityAnalysis = undefined;
    $scope.analyze = function() {
        httpService.httpPost(INFORMATION_RESOURCE_URI, $scope.selectedQualityAnalysis, function (data) {
            $rootScope.$broadcast('resultAvailableEvent', data);
        }, function() {
            console.log("Error");
        });
    };

    $scope.getProvidedInformationNeed = function() {
        httpService.httpGet(INFORMATIONNEED_PROVIDED_RESOURCE_URI, function (data) {
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

