// util/filters/dateFormat.filter.js
angular.module('admin.util', [])
  .filter('dateFormat', function ($filter) {
    return function (input) {
        if (input == null) { return ""; }

        var _date = $filter('date')(new Date(input), 'dd/MM/yyyy');
        return _date.toUpperCase();
    };
  });
