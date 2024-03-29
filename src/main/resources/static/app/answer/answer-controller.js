(function () {

    var module = angular.module('qiqAnswer');

    module.controller('AnswerController', AnswerController);

    AnswerController.$inject = ['$routeParams', 'answerFactory', 'authFactory'];

    function AnswerController($routeParams, answerFactory, authFactory) {
        var qc = this;

        qc.user = authFactory.user;

        init();

        function init() {
            answerFactory.getAnswer(qc.user).then(function (answer) {
                qc.answer = answer;
            });
        }

    }
})();