var whir = window.whir || {};
whir.ajax = {
    _getPath: _ajaxGetPath,//变量在父页面定义
    _postPath:_ajaxPostPath,//变量在父页面定义
    setGetPath: function (url) {
        this._getPath = url;
        return this;
    },
    setPostPath: function (url) {
        this._postPath = url;
        return this;
    },
    get: function (cmdName, data, callback) {
        data['_action'] = cmdName;
        $.get(this._getPath + "?_action=" + cmdName, data, function (result) {
            callback(result);
        });
    },
    getXML: function (url, data, callback) {
        $.get(url, data, function (result) {
            callback(result);
        });
    },
    htmlEncode: function (text) {
        var value = text;
        try {
            value = value.replace(/&emsp;/g, "&nbsp;");
            value = value.replace(/&/, "&amp;");
            value = value.replace(/</g, "&lt;");
            value = value.replace(/>/g, "&gt;");
            value = value.replace(/'/g, "&apos;");
            value = value.replace(/"/g, "&quot;");
        } catch (e) {
            var span = $('<span>');
            span.html(value);
            value = span.html();
            value = value.replace(/&/, "&amp;");
            value = value.replace(/</g, "&lt;");
            value = value.replace(/>/g, "&gt;");
            value = value.replace(/'/g, "&apos;");
            value = value.replace(/"/g, "&quot;");
        }
        return value;
    },
    post: function (cmdName, params) {
        if (typeof (cmdName) != 'string' || cmdName == 'undefind') {
            return;
        }
        var defaultParms = {
            url: this._postPath,
            data: '',
            dataType: 'json',
            type: 'POST',
            params: {},
            success: function (response) {
            }
        }, getXml = function (setting) {
            var xml = '';
            for (var arg in setting.params) {
                xml += ('<' + arg + '>' + whir.ajax.htmlEncode(setting.params[arg]) + '</' + arg + '>');
            }
            return xml;
        };
        //参考：http://www.w3schools.com/html/html_entities.asp
        var settings = $.extend(defaultParms, params), data = '<!DOCTYPE your_root_name[';
        data += '<!ENTITY nbsp "&#160;">';
        data += '<!ENTITY copy "&#169;">';
        data += '<!ENTITY reg "&#174;">';
        data += '<!ENTITY trade "&#8482;">';
        data += '<!ENTITY mdash "&#8212;">';
        data += '<!ENTITY ldquo "&#8220;">';
        data += '<!ENTITY rdquo "&#8221;"> ';
        data += '<!ENTITY pound "&#163;">';
        data += '<!ENTITY yen "&#165;">';
        data += '<!ENTITY euro "&#8364;">';
        data += ']><root><_type>' + cmdName + '</_type>';
        data += getXml(settings);
        data += '</root>';
        settings.data = data;
        $.ajax(settings);
    },
    fixJsonDate: function (jsonDate, format) {// 发现实体转换的日期会多了8个小时，使用该方法会修正这8个小时
        var date = null;
        if (jsonDate) {
            var strDate = jsonDate.replace("/Date(", "").replace(")/", "");
            try {
                date = new Date(parseInt(strDate) - (8 * 3600 * 1000));
            } catch (ex) { }
        }
        if (!date) {
            return "";
        }
        date = { year: date.getFullYear(), month: date.getMonth() + 1, day: date.getDate(), hour: date.getHours(), minutes: date.getMinutes() };
        switch (format) {
            case '-':
                return date.year + '-' + fixTime(date.month) + '-' + fixTime(date.day);
            case 'zh':
                return date.year + '年' + fixTime(date.month) + '月' + fixTime(date.day) + '日';
            default:
                return date.year + '-' + fixTime(date.month) + '-' + fixTime(date.day) + ' ' + fixTime(date.hour) + ':' + fixTime(date.minutes);
        }
        function fixTime(value) {
            return value.toString().length > 1 ? value : "0" + value;
        }
    },
};

