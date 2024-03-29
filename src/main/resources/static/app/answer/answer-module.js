(function () {

    var module = angular.module('qiqAnswer', ['ngRoute']);

    module.config(['$routeProvider', config]);


    function config($routeProvider) {
        $routeProvider.when('/answer', {
            controller: 'AnswerController',
            controllerAs: 'qc',
            templateUrl: 'app/answer/answer-template.html',
            // resolve: {
            //     "currentAuth": ["authFactory", function (authFactory) {
            //         return authFactory.auth.$requireSignIn();
            //     }]
            // }
        });
    }


})();