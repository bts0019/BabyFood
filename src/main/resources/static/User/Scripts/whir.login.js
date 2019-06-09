/*!
* 插件名称：登录插件封装，使用方法： 
$('#form1').login({
url: "http://www.uniyou.com.cn/User/Scripts/LoginHandler.ashx",//处理登录验证逻辑的Url
userName: $("input[name='userName']"),//用户名输入框
password: $("input[name='password']"),//密码输入框
msg: $(".msg"),                                        //提示信息
button: $("#btnSumit")                            //提交按钮
});  
*/
(function ($) {
    var errMsg = {
        'inputUserName': '对不起，请输入用户名',
        'inputPassword': '对不起，请填写登录密码',
        'inputCode': '对不起，请输入验证码',
        'passwordLength': '对不起，密码应在6-32位字符内',
        'noreg': '此账号未注册',
        'inviladUserName': '对不起，您输入的帐号不存在',
        'accountNotMatch': '对不起，您的账号密码不匹配',
        'userLocked': '帐号锁定中，您暂时无法登录',
        'needEmailActive': '对不起，您的账户需要邮件激活',
        'serverdown': '服务器繁忙，请稍后再试',
        "inviladCode": "对不起，验证码输入错误"

    };
    $.fn.login = function (option) {
        var defaults = {
            url: '/account/login/',
            msg: $(this).find('.msg'),
            userName: $(this).find("input[name='userName']"),
            password: $(this).find("input[name='password']"),
            sevenDay: $(this).find("input[name='SevenDay']"),
            urlr: $(this).find("input[name='UrlReferrer']"),
            code: $(this).find("input[name='code']"),
            button: $(this).find("#button")
        };
        var options = $.extend(defaults, option);

        //提交数据

        function submit() {
            var userNameInput = $.trim(options.userName.val());
            var passwordInput = $.trim(options.password.val());
            var sevenDayInput = options.sevenDay.is(":checked");
            var urlr = $.trim(options.urlr.val());
            var codeInput = $.trim(options.code.val());
            if (userNameInput == '') {
                showErrMsg('对不起，登录名不能为空');
                options.userName.focus();
                return;
            }
            if (passwordInput == '') {
                showErrMsg('对不起，密码不能为空');
                options.password.focus();
                return;
            }
            //if (codeInput == '') {
            //    showErrMsg('对不起，验证码不能为空');
            //    options.code.focus();
            //    return;
            //}
            showSucessMsg("正在处理，请稍候...");
            $.ajax({
                type: "POST",
                url: options.url,
                data: {
                    action: "login",
                    userName: userNameInput,
                    password: passwordInput,
                    sevenDay: sevenDayInput
                },
                success: function (msg) {
                    var result = eval("[" + msg + "]")[0];
                    if (result.status == "ok") {
                        //登录成功处理
                        showSucessMsg("登录成功，页面即将跳转....");
                        if (urlr != "" && urlr.toLocaleLowerCase().indexOf("register") == -1 && urlr.toLocaleLowerCase().indexOf("retrieve") == -1 && urlr.toLocaleLowerCase().indexOf("login") == -1) {
                            setTimeout("window.location.href = '" + urlr + "'", 1000);
                        } else {
                            setTimeout("window.location.href = 'http://www.uniyou.com.cn/User/Scripts/" + _basepath + "User/Index.aspx'", 1000);
                        }
                        
                    } else {
                        showErrMsg(errMsg[result.status]);
                        $("#loginCodeImg").trigger("click");
                    }
                }
            });
        }

        //显示错误信息
        function showErrMsg(msg) {
            options.msg.html(msg);
            options.msg.css("color", "red");
        }

        //显示错误信息
        function showSucessMsg(msg) {
            options.msg.html(msg);
            options.msg.css("color", "green");
        }

        //绑定按钮事件
        options.button.bind('click', function () {
            submit();
            return false;
        });


        options.userName.bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                e.preventDefault();
                options.password.focus();
            }
        });

        options.password.bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                e.preventDefault();
                options.code.focus();
            }
        });
        options.code.bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                e.preventDefault();
                submit();
            }
        });
    };
})(jQuery);