var whir = window.whir || {};
whir.request =
{
    get: function (name) {
        if (location.href.indexOf("?") == -1 || location.href.toLowerCase().indexOf(name.toLowerCase() + '=') == -1) {
            return '';
        }
        var queryString = location.href.substring(location.href.indexOf("?") + 1);
        var parameters = queryString.split("&");
        var pos, paraName, paraValue;
        for (var i = 0; i < parameters.length; i++) {
            pos = parameters[i].indexOf('=');
            if (pos == -1) {
                continue;
            }
            paraName = parameters[i].substring(0, pos);
            paraValue = parameters[i].substring(pos + 1);
            if (paraName.toLowerCase() == name.toLowerCase()) {
                return decodeURIComponent(paraValue.replace(/\+/g, " "));
            }
        }
        return '';
    }
};