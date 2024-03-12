(function () {

    var module = angular.module('qiqAccounts', ['ngRoute']);

    module.config(['$routeProvider', config]);


    function config($routeProvider) {
        $routeProvider.when('/accounts', {
            controller: 'AccountsController',
            controllerAs: 'qc',
            templateUrl: 'app/accounts/accounts-template.html',
            // resolve: {
            //     "currentAuth": ["authFactory", function (authFactory) {
            //         return authFactory.auth.$requireSignIn();
            //     }]
            // }
        });
    }


})();