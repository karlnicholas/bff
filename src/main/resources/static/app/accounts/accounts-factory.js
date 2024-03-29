(function () {

    var module = angular.module('qiqAccounts');

    module.factory('accountsFactory', accountsFactory);

    accountsFactory.$inject = ['$http']


    function accountsFactory($http) {

        return {
            getAccounts: getAccounts
        };

        function getAccounts(user) {
            const options = {
                headers: {
                    'Authorization': 'Bearer ' + user.ya
                }
            }
            return $http.get('/accounts?user='+user.uid, options).then(function (response) {
                console.log(response.data);
                return response.data.accounts;
            });

            // return "{'accounts': ['checking', 'saving']}";
        }
    }


})();