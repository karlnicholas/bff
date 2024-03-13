(function () {

    var module = angular.module('qiqAccounts');

    module.controller('AccountsController', AccountsController);

    AccountsController.$inject = ['$routeParams', 'accountsFactory', 'authFactory'];

    function AccountsController($routeParams, accountsFactory, authFactory) {
        var ac = this;

        ac.user = authFactory.user;

        init();

        function init() {
            accountsFactory.getAccounts(ac.user).then(function (accounts) {
                ac.accounts = accounts;
            });
        }

    }
})();