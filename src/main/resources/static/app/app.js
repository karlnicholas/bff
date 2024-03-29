(function () {

    var app = angular.module('qiqApp', ['ui.bootstrap', 'qiqAuth', 'qiqAccounts']);

    app.run(['$rootScope', '$location', function ($rootScope, $location) {

        $rootScope.$on("$routeChangeError", function (event, next, previous, error) {
            if (error === "AUTH_REQUIRED") {
                $location.path("/");
            }
        });

    }]);
})();