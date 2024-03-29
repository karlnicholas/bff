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
            return $http.get('/answer?user='+user.uid, options).then(function (response) {
                // console.log(response.data);
                return response.data;
            });

            // return "{'accounts': ['checking', 'saving']}";
        }
    }


})();