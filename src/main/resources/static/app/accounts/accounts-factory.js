(function () {

    var module = angular.module('qiqAccounts');

    module.factory('accountsFactory', accountsFactory);

    accountsFactory.$inject = ['$http']


    function accountsFactory($http) {

        return {
            getAccounts: getAccounts
        };

        function getAccounts() {
            return $http.get('/accounts').then(function (response) {
                console.log(response.data);
                return response.data.accounts;
            });

            // return "{'accounts': ['checking', 'saving']}";
        }
    }


})();