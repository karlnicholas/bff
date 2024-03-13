(function () {

    var module = angular.module('qiqAccounts');

    module.controller('AccountsController', AccountsController);

    AccountsController.$inject = ['$routeParams', 'accountsFactory', 'authFactory'];

    function AccountsController($routeParams, accountsFactory, authFactory) {
        var qc = this;

        qc.user = authFactory.user;

        init();

        function init() {
            accountsFactory.getAccounts(ac.user).then(function (accounts) {
                qc.accounts = accounts;
            });
        }

    }
})();