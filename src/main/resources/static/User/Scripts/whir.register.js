
(function ($) {
    var errMsg = {
        'inputUserName': '对不起，请输入用户名',
        'inputPassword': '对不起，请填写登录密码',
        'inputCode': '对不起，请输入验证码',
        'passwordLength': '对不起，密码应在6-32位字符内',
        'noreg': '此账号未注册',
        'inviladUserName': '对不起，帐号已存在',
        'accountNotMatch': '对不起，账号密码不匹配',
        'userLocked': '帐号锁定中，暂时无法登录',
        'serverdown': '服务器繁忙，请稍后再试',
        "inviladCode": "对不起，验证码输入错误",
        "inviladEmail": "对不起，邮箱已存在，请更换其他邮箱",
        "inputEmail": "对不起，请输入邮箱",
        "needEmailActive": "注册成功，账户待激活，请登录邮箱并激活账户",
        "forbidenUserName": "对不起，该用户名禁止注册"
    };
    $.fn.register = function (option) {
        var defaults = {
            url: '/account/login/',
            msg: $(this).find('.msg'),
            userName: $(this).find("input[name='userName']"),
            password: $(this).find("input[name='password']"),
            rePassword: $(this).find("input[name='rePassword']"),
            email: $(this).find("input[name='email']"),
            code: $(this).find("input[name='code']"),
            button: $(this).find("#button")
        };
        var options = $.extend(defaults, option);

        //提交数据

        function submit() {
            var userNameInput = $.trim(options.userName.val());
            var passwordInput = $.trim(options.password.val());
            var rePassword = $.trim(options.rePassword.val());
            var email = $.trim(options.email.val());
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
            if (passwordInput != rePassword) {
                showErrMsg('对不起，两次密码输入不一致');
                options.password.focus();
                return;
            }
            if (codeInput == '') {
                showErrMsg('对不起，验证码不能为空');
                options.code.focus();
                return;
            }
            if (email == '') {
                showErrMsg('对不起，邮箱不能为空');
                options.email.focus();
                return;
            }

            showSucessMsg("正在处理，请稍候...");
            $.ajax({
                type: "POST",
                url: options.url,
                data: {
                    action: "register",
                    userName: userNameInput,
                    password: passwordInput,
                    email: email,
                    code: codeInput
                },
                success: function (msg) {
                    var result = eval("[" + msg + "]")[0];
                    if (result.status == "ok") {
                        var s = "http://" + email.substring(email.lastIndexOf("@") + 1);
                        showSucessMsg("注册成功，即将跳转到首页...");
                        window.location.href = _basepath + "user/index.aspx";
                    }
                    else if (result.status == "needEmailActive") {
                        showSucessMsg(errMsg[result.status]);
                    }
                    else {
                        showSucessMsg("");
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
                options.rePassword.focus();
            }
        });
        options.rePassword.bind('keydown', function (e) {
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