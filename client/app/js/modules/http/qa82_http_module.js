var httpModule = angular.module('qa82analyzer.http', []).
    value('base_url','http://localhost:8080/');

httpModule.service('httpService', function($http, base_url) {

    this.httpGet = function(path, successCallback, errorCallback) {
        $http.get(base_url + path).
            success(function(data, status) {
                if(successCallback != undefined) {
                    successCallback(data, status);
                }
            }).
            error(function(data, status) {
                if(errorCallback != undefined) {
                    errorCallback(data, status);
                }
            });
    }
});




