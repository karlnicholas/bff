(function () {

    var module = angular.module('qiqAccounts');

    module.controller('AccountsController', AccountsController);

    AccountsController.$inject = ['$routeParams', 'accountsFactory', 'authFactory'];

    function AccountsController($routeParams, accountsFactory, authFactory) {
        var qc = this;

        qc.accountsName = $routeParams.name || 'accounts';
        // qc.user = authFactory.user;

        init();

        function init() {
            accountsFactory.getAccounts().then(function (accounts) {
                qc.accounts = accounts;
            });
        }

    }
})();