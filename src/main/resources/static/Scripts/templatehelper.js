template.helper('indexImage', function (data) {
    if (data != "") {
        var images = data.split("|");
        if (0 < images.length) {
            return images[0];
        }
    }
    return "";
});

template.helper('ToDate', function (date, format) {
    if (date != "") {
        var dates = date.split(" ");
        var dateStr = dates[0];
        date = new Date(dateStr);
        date = { year: date.getFullYear(), month: date.getMonth() + 1, day: date.getDate(), hour: date.getHours(), minutes: date.getMinutes() };
        switch (format) {
            case '-':
                return date.year + '-' + fixTime(date.month) + '-' + fixTime(date.day);
            case 'zh':
                return date.year + '年' + fixTime(date.month) + '月' + fixTime(date.day) + '日';
            case 'yyMM':
                return date.year + '-' + fixTime(date.month);
            case 'dd':
                return fixTime(date.day);
            default:
                return date.year + '-' + fixTime(date.month) + '-' + fixTime(date.day) + ' ' + fixTime(date.hour) + ':' + fixTime(date.minutes);
        }
    }
    return "";
});

function fixTime(value) {
	return value.toString().length > 1 ? value : "0" + value;
}

template.helper('SubString', function (data, subLenth) {
    if (data != "") {
        data = data.replace(/<[^>]+>/g, "");
        if (subLenth == 0) {
            subLenth = data.length;
        }
        return data.substring(0, subLenth);
    }
    return "";
});

template.helper('GetCityName', function (data) {
    return GetRegionNameByCode(data);
});


template.helper('decode', function (data) {
    return data;
});
