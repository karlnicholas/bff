(function () {

    var module = angular.module('qiqAnswer');

    module.factory('answerFactory', answerFactory);

    answerFactory.$inject = ['$http']


    function answerFactory($http) {

        return {
            getAnswer: getAnswer
        };

        function getAnswer(user) {
            if ( user.isLoggedIn ) {
                const options = {
                    headers: {
                        'Authorization': 'Bearer ' + user.ya
                    }
                }
                return $http.get('/answer?uid=' + user.uid, options).then(function (response) {
                    // console.log(response.data);
                    return response.data;
                });
            } else {
                return Promise.reject();
            }
        }
    }
})();