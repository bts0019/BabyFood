$(function () {
    var url = window.location.href;
    setTimeout(function () { whir.ajax.post('../../404.aspx'/*tpa=http://www.uniyou.com.cn/Scripts/whir/VisitStatisticsPostHandler.Add*/, { params: { path: url }, success: function () { }, err: function () { } }); }, 10000); //停留10秒才计数
});
