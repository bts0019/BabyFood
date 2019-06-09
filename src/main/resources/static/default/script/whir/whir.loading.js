/*
文件说明：页面加载时Loading JS
文件描述：解决IE或FF下，初始化加载时，页面布局乱掉的问题，参考：http://283433775.iteye.com/blog/720895
*/
var whir = window.whir || {};
whir.loading =
{
    add: function (title, opacity) {
        opacity = opacity == undefined ? 0.4 : opacity;
        var width = $(window).width();
        var height = $(window).height();
        //背景
        var html = "<div id='loading' style='position:absolute;left:0;width:100%;height:" + height + "px;top:0;background:#F2F2F2;opacity:" + opacity + ";filter:alpha(opacity=" + opacity * 100 + ");z-index:10000;'>";
        html += "</div>";
        //提示
        html += "<div id='loadingTxt' style='position:absolute;cursor1:wait;left:" + ((width / 2) - 75) + "px;top:200px;width:150px;height:16px;padding:12px 5px 10px 30px;";
        html += "background:#fff url(" + _basepath + "Admin/Scripts/jquery-easyui-1.4/themes/default/images/loading.gif) no-repeat scroll 5px 10px;border:1px solid #ccc;color:#000;z-index:10001;'>";
        title = (title != undefined && title.length > 0) ? title : "正在加载，请等待...";
        html += title;
        html += "</div>";
        $("body").append(html);
    },
    remove: function () {
        $('#loading').remove();
        $('#loadingTxt').remove();
    }
};
//window.onload = function () {
//    whir.loading.remove();
//};

$(document).ready(function () {
    whir.loading.remove();
})
whir.loading.add("", 1);
