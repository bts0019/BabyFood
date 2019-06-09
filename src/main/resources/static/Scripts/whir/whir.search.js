$(function () {
    //初始化
    var keyword = getQueryString("keyword");
    keyword = keyword == "" ? "请输入查询关键字" : "";
    $("#txtKeyword").val(keyword);

    //搜索按钮
    $("#btnSearch").click(function () {
        doSearch();
    });
    //获取焦点
    $("#txtKeyword").focus(function () {
        if ($.trim($(this).val()) == "请输入查询关键字") {
            $(this).val("");
        }
    });
    //失去焦点
    $("#txtKeyword").blur(function () {
        if ($.trim($(this).val()) == "") {
            $(this).val("请输入查询关键字");
        }
    });
    //回车搜索
    $("#txtKeyword").bind('keydown', function (e) {
        var key = e.which;
        if (key == 13) {
            e.preventDefault();
            doSearch();
        }
    });
});

function doSearch() {
    var keyword = $.trim($("#txtKeyword").val());
    keyword = keyword == "请输入查询关键字" ? "" : keyword;
    if (keyword.length > 50) {//关键字不能大于50
        whir.msg.alertErrorMsg("关键字字数不能大于50");
    }
    else {
        if (keyword.match(/['";`~?]/)) {
            whir.msg.alertErrorMsg("查询关键字不能包含特殊符号，如：'" + '";`~?');
            return;
        }
        keyword = escape(keyword);
        if (keyword.length > 0) {
            var url = _basepath+"search.aspx?keyword=" + keyword;
            window.location.href = url;
        } else {
            $("#txtKeyword").focus();
            whir.msg.alertErrorMsg("请输入查询关键字");
        }
    }
}

//取得Url参数
function getQueryString(name) {
    if (location.href.indexOf("?") == -1 || location.href.indexOf(name + '=') == -1) {
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
        if (paraName == name) {
            return unescape(paraValue.replace(/\+/g, " "));
        }
    }
    return '';
};