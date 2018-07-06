var app = angular.module("app", ['angularMoment', 'ngSanitize', 'pascalprecht.translate']);

app.config(function($translateProvider) {
	$translateProvider
    .useStaticFilesLoader({ // load our locales
        prefix: 'strings/',
        suffix: '.json'
    })
    .useSanitizeValueStrategy('escape')
    .registerAvailableLanguageKeys(['ja', 'en'])
    .determinePreferredLanguage(function () { // choose the best language based on browser languages
        var translationKeys = $translateProvider.registerAvailableLanguageKeys(),
            browserKeys = navigator.languages,
            preferredLanguage;

        label: for (var i = 0; i < browserKeys.length; i++) {
            for (var j = 0; j < translationKeys.length; j++) {
                if (browserKeys[i] == translationKeys[j]) {
                    preferredLanguage = browserKeys[i];
                    break label;
                }
            }
        }
        return preferredLanguage;
	});
});

