(function () {

    var module = angular.module('qiqAnswer');

    module.factory('answerFactory', answerFactory);

    answerFactory.$inject = ['$http']


    function answerFactory($http) {

        return {
            getAnswer: getAnswer
        };

        function getAnswer(user) {
            const options = {
                headers: {
                    'Authorization': 'Bearer ' + user.ya
                }
            }
            return $http.get('/answer?user='+user.uid, options).then(function (response) {
                // console.log(response.data);
                return response.data;
            });

            // return "{'answer': ['checking', 'saving']}";
        }
    }


})();