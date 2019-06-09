/**
* 脚本名称：弹出窗口辅助类
* 脚本说明：替代浏览器默认的alert，confirm等弹出框，弹出框样式，由js注入样式，方便插件调用，也可以在页面配置
* 脚本作者：万户网络技术部
* 
*/
var css = "";
css += "#dvMsgBox { display: none; position: absolute; font-size: 12px; width: 300px; overflow: hidden; z-index: 999; border-radius: 3px; }";
css += "#dvMsgBox .top { height: 30px; background-color: #F1F1F1; padding-left: 8px; float: left; width: 100%;  margin-bottom: 10px; }";
css += "#dvMsgBox .top .right { height: 100%; padding-right: 6px; }";
css += "#dvMsgBox .top .right .title { height: 100%; line-height: 30px; color: #575765;font-weight: bold; vertical-align: middle; font-size: 14px; overflow: hidden; }";
css += "#dvMsgBox .body { background: #fff; padding: 10px;}";
css += "#dvMsgBox .body .right { background: #fff; padding-right: 2px; }";
css += "#dvMsgBox .body .right .ct { line-height: 26px; vertical-align: middle; width: 100%; text-align: center; color: #333;; font-size: 14px; }";
css += "#dvMsgBox .body .right .ct .pro { width: 280px; border: solid 1px lavender;; height: 25px; background: #ffffff; line-height: 23px; overflow: hidden; }";
css += "#dvMsgBox .body .right .ct .pro .bg { width: 0%; height: 100%; background: #c9dffc; }";
css += "#dvMsgBox .bottom { background: #fff; padding-left: 6px;   }";
css += "#dvMsgBox .bottom .right { height: 100%; /*background:transparent url(right-corners.png) no-repeat right bottom;*/ padding-right: 6px; }";
css += "#dvMsgBox .icon { width: 32px; height: 32px; float: left; margin-right: 10px; }";
css += "#dvMsgBox .error { background: url(icon-error.gif) no-repeat; }";
css += "#dvMsgBox .info { background: url(icon-info.gif) no-repeat; }";
css += "#dvMsgBox .warning { background: url(icon-warning.gif) no-repeat; }";
css += "#dvMsgBox .clear { clear: both; }";
css += "#dvMsgBox .btnOk { padding: 3px 5px; }";
css += "#dvMsgBox .btnfocus { padding: 3px 5px; }";
css += "#dvMsgBox .height { height: 10px; line-height: 10px; }";
css += "#ShowBolightBox { display: none; -moz-opacity: 0.5; filter: alpha(opacity=50); opacity: 0.5; background-color: #000000; z-index: 100; position: absolute; left: 0px; top: 0px; }";
css += "#dvMsgBtns { text-align: center; width: 100%; }";
var head = document.getElementsByTagName('HEAD').item(0);
var link = document.createElement("style");
link.type = "text/css";
link.innerHTML = css;
head.appendChild(link);

var whir = window.whir || {};
//是否为ie浏览器  
whir.IsIE = !!document.all;
//ie浏览器版本  
whir.IEVersion = (function () { if (!whir.IsIE) return -1; try { return parseFloat(/msie ([\d\.]+)/i.exec(navigator.userAgent)[1]); } catch (e) { return -1; } })();
//按id获取对象  
whir.$ = function (Id, isFrame) { var o; if ("string" == typeof (Id)) o = document.getElementById(Id); else if ("object" == typeof (Id)) o = Id; else return null; return isFrame ? (whir.IsIE ? frames[Id] : o.contentWindow) : o; }
//按标签名称获取对象  
//页面的高和宽******************************  
whir.isStrict = document.compatMode == "CSS1Compat";
whir.BodyScale = { x: 0, y: 0, tx: 0, ty: 0 }; //（x，y）：当前的浏览器容器大小  （tx，ty）：总的页面滚动宽度和高度 
whir.getClientHeight = function () { /*if(whir.IsIE)*/return whir.isStrict ? document.documentElement.clientHeight : document.body.clientHeight; /*else return self.innerHeight;*/ }
whir.getScrollHeight = function () { var h = !whir.isStrict ? document.body.scrollHeight : document.documentElement.scrollHeight; return Math.max(h, this.getClientHeight()); }
whir.getHeight = function (full) { return full ? this.getScrollHeight() : this.getClientHeight(); }
whir.getClientWidth = function () { /*if(whir.IsIE)*/return whir.isStrict ? document.documentElement.clientWidth : document.body.clientWidth; /*else return self.innerWidth;*/ }
whir.getScrollWidth = function () { var w = !whir.isStrict ? document.body.scrollWidth : document.documentElement.scrollWidth; return Math.max(w, this.getClientWidth()); }
whir.getWidth = function (full) { return full ? this.getScrollWidth() : this.getClientWidth(); }
whir.initBodyScale = function () { whir.BodyScale.x = whir.getWidth(false); whir.BodyScale.y = whir.getHeight(false); whir.BodyScale.tx = whir.getWidth(true); whir.BodyScale.ty = whir.getHeight(true); }
//页面的高和宽******************************  
whir.msg = {
    INFO: 'info',
    ERROR: 'error',
    WARNING: 'warning',
    IsInit: false,
    timer: null,
    dvTitle: null,
    dvCT: null,
    dvBottom: null,
    dvBtns: null,
    lightBox: null,
    dvMsgBox: null,
    defaultWidth: 300,
    moveProcessbar: function () {
        var o = whir.$('dvProcessbar'), w = o.style.width;
        if (w == '') w = 20;
        else {
            w = parseInt(w) + 20;
            if (w > 100) w = 0;
        }
        o.style.width = w + '%';
    },
    InitMsg: function (width) {
        //ie下不按照添加事件的循序来执行，所以要注意在调用alert等方法时要检测是否已经初始化IsInit=true       
        var ifStr = '<iframe src="javascript:false" mce_src="javascript:false" style="position:absolute; visibility:inherit; top:0px;left:0px;width:100%; height:100%; z-index:-1;'
          + 'filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\';"></iframe>',
      html =
          '<div class="top">' +
          '     <div class="right">' +
          '         <div class="title" id="dvMsgTitle"></div>' +
          '     </div>' +
          '</div>' +
          '<div class="body">' +
          '   <div class="right">' +
          '         <div class="ct" id="dvMsgCT"></div>' +
          '   </div>' +
          '</div>' +
          '<div class="bottom" id="dvMsgBottom">' +
          '     <div class="right">' +
          '         <div id="dvMsgBtns"></div>' +
          '     </div>' +
          '</div>';
        this.dvMsgBox = document.createElement("div");
        this.dvMsgBox.id = "dvMsgBox";
        this.dvMsgBox.innerHTML += html;
        document.body.appendChild(this.dvMsgBox);
        this.lightBox = document.createElement("div");
        this.lightBox.id = "ShowBolightBox";
        document.body.appendChild(this.lightBox);
        if (whir.IsIE && whir.IEVersion < 7) {//加iframe层修正ie6下无法遮盖住select的问题  
            this.lightBox.innerHTML += ifStr;
            this.dvMsgBox.innerHTML += ifStr;
        }
        this.dvBottom = whir.$('dvMsgBottom');
        this.dvBtns = whir.$('dvMsgBtns');
        this.dvCT = whir.$('dvMsgCT');
        this.dvTitle = whir.$('dvMsgTitle');
        this.IsInit = true;
    },
    checkDOMLast: function () {//此方法非常关键，要不无法显示弹出窗口。两个对象dvMsgBox和lightBox必须处在body的最后两个节点内  
        if (document.body.lastChild != this.lightBox) {
            document.body.appendChild(this.dvMsgBox);
            document.body.appendChild(this.lightBox);
        }
    },
    createBtn: function (p, v, fn) {
        var btn = document.createElement("input");
        btn.type = "button";
        btn.className = 'btnOk';
        btn.value = v;
        btn.onmouseover = function () { this.className = 'btnfocus'; }
        btn.onmouseout = function () { this.className = 'btnOk'; }
        btn.onclick = function () {
            whir.msg.hide();
            if (fn) fn(p);
        }
        return btn;
    },
    alert: function (msg) {
        this.show({ buttons: { yes: '确认' }, msg: msg, title: '提示信息' });
    },
    alertError: function (msg) {
        this.show({ buttons: { yes: '确认' }, msg: msg, title: '错误提示信息', isError: true });
    },
    alertSucess: function (msg) {
        this.show({ buttons: { yes: '确认' }, msg: msg, title: '成功提示信息', isSucess: true });
    },
    confirm: function (msg, fn, cancleFn) {
        //fn为回调函数，参数和show方法的一致  
        this.show({ buttons: { yes: '确认', no: '取消' }, msg: msg, title: '确认信息', fn: fn, cancleFn: cancleFn });
    },
    prompt: function (labelWord, defaultValue, txtId, fn) {
        if (!labelWord) labelWord = '请输入：';
        if (!defaultValue) defaultValue = "";
        if (!txtId) txtId = "msg_txtInput";
        this.show({ title: '输入提示', msg: labelWord + '<input type="text" id="' + txtId + '" style="width:200px" value="' + defaultValue + '"/>', buttons: { yes: '确认', no: '取消' }, fn: fn });
    },
    process: function (title, msg) {
        if (!msg) msg = '正在处理..';
        this.show({ title: title, msg: msg, wait: true });
    },
    show: function (option) {
        //cfg:{title:'',msg:'',wait:true,icon:'默认为信息',buttons:{yes:'',no:''},fn:function(btn){回调函数,btn为点击的按钮，可以为yes，no},width:显示层的宽}  
        //如果是等待则wait后面的配置不需要了。。   
        if (!option) throw ("没有指定配置文件！");
        //添加窗体大小改变监听  
        if (whir.IsIE) window.attachEvent("onresize", this.onResize);
        else window.addEventListener("resize", this.onResize, false);

        if (!this.IsInit) this.InitMsg(); //初始化dom对象  
        else this.checkDOMLast(); //检查是否在最后  

        //检查是否要指定宽，默认为300  
        if (option.width) this.defaultWidth = option.width;
        this.dvMsgBox.style.width = this.defaultWidth + 'px';
        //可以直接使用show方法停止为进度条的窗口  
        if (this.timer) { clearInterval(this.timer); this.timer = null; }
        this.dvTitle.innerHTML = '';
        if (option.title) this.dvTitle.innerHTML = option.title;
        this.dvCT.innerHTML = '';
        if (option.wait) {
            if (option.msg) this.dvCT.innerHTML = option.msg;
            this.dvCT.innerHTML += '<div class="pro"><div class="bg" id="dvProcessbar"></div></div>';
            this.dvBtns.innerHTML = '';
            this.dvBottom.style.height = '10px';
            this.timer = setInterval(function () { whir.msg.moveProcessbar(); }, 1000);
        }
        else {
            //if(!cfg.icon)cfg.icon=whir.msg.INFO;  
            if (!option.buttons || (!option.buttons.yes && !option.buttons.no)) {
                option.buttons = { yes: '确定' };
            }
            if (option.icon) {
                this.dvCT.innerHTML = '<div class="icon ' + option.icon + '"></div>';
            }
            if (option.msg) {
                var html = option.msg;
                if (option.isSucess) {
                    html = "<span style='color:green'>" + html + "</sapn>";
                }
                if (option.isError) {
                    html = "<span style='color:red'>" + html + "</sapn>";
                }
                this.dvCT.innerHTML += html + '<div class="clear"></div>';
            }
            this.dvBottom.style.height = '45px';
            this.dvBtns.innerHTML = '<div class="height"></div>';
            if (option.buttons.yes) {
                this.dvBtns.appendChild(this.createBtn('yes', option.buttons.yes, option.fn));
                if (option.buttons.no) this.dvBtns.appendChild(document.createTextNode('　'));
            }
            if (option.buttons.no) this.dvBtns.appendChild(this.createBtn('no', option.buttons.no, option.cancleFn));
        }
        whir.initBodyScale();
        this.dvMsgBox.style.display = 'block';
        this.lightBox.style.display = 'block';
        this.onResize(false);
    },
    hide: function () {
        this.dvMsgBox.style.display = 'none';
        this.lightBox.style.display = 'none';
        if (this.timer) { clearInterval(this.timer); this.timer = null; }
        if (whir.IsIE) window.detachEvent('onresize', this.onResize);
        else window.removeEventListener('resize', this.onResize, false);
    },
    onResize: function (isResize) {
        if (isResize) whir.initBodyScale();
        whir.msg.lightBox.style.width = whir.BodyScale.tx + 'px';
        whir.msg.lightBox.style.height = whir.BodyScale.ty + 'px';
        whir.msg.dvMsgBox.style.top = 240 + 'px';
        whir.msg.dvMsgBox.style.left = Math.floor((whir.BodyScale.x - whir.msg.dvMsgBox.offsetWidth) / 2) + 'px';
    }

}

//-------   loading begin ------------


whir.loading =
{
    add: function (title, opacity) {
        opacity = opacity == undefined ? 0.4 : opacity;
        var width = $(window).width();
        var height = $(document).height();
        //背景
        var html = "<div id='loading' style='position:absolute;left:0;width:100%;height:" + height + "px;top:0;background:#F2F2F2;opacity:" + opacity + ";filter:alpha(opacity=" + opacity * 100 + ");z-index:10000;'>";
        html += "</div>";
        //提示
        html += "<div id='loadingTxt' style='position:fixed;cursor1:wait;left:" + ((width / 2) - 75) + "px;top:200px;padding:5px 5px 10px 30px;";
        html += "background:#fff url(" + _basepath + "Uploadfiles/loading.gif) no-repeat scroll 5px 10px;border:1px solid #ccc;color:#000;z-index:10001;'>";
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

//-------   loading end ------------

var styleCss = "";
styleCss += ".error_red{background-color: rgb(248, 210, 210); border: 1px solid rgb(248, 118, 118);}";
styleCss += ".success_green{background-color: ghostwhite; border: 1px solid green;}";
styleCss += ".icono-areaChart,.icono-barChart,.icono-book,.icono-book:after,.icono-book:before,.icono-bookmarkEmpty,.icono-bookmarkEmpty:before,.icono-camera,.icono-chain:after,.icono-chain:before,.icono-clock,.icono-commentEmpty,.icono-creditCard,.icono-crop,.icono-crop:before,.icono-display,.icono-document,.icono-eye,.icono-file,.icono-flag:after,.icono-flag:before,.icono-folder,.icono-forbidden,.icono-frown,.icono-frown:after,.icono-headphone,.icono-heart,.icono-heart:after,.icono-heart:before,.icono-home,.icono-home:after,.icono-home:before,.icono-imac,.icono-imacBold,.icono-image,.icono-infinity:after,.icono-infinity:before,.icono-iphone,.icono-iphoneBold,.icono-keyboard,.icono-macbook:before,.icono-macbookBold:before,.icono-mail,.icono-mail:before,.icono-market,.icono-market:after,.icono-meh,.icono-meh:after,.icono-microphone,.icono-microphone:before,.icono-mouse,.icono-mouse:before,.icono-nexus,.icono-paperClip,.icono-paperClip:after,.icono-paperClip:before,.icono-piano,.icono-pin,.icono-pin:before,.icono-power,.icono-rename,.icono-ruler,.icono-search,.icono-signIn,.icono-signIn:before,.icono-signOut,.icono-signOut:before,.icono-smile,.icono-smile:after,.icono-stroke,.icono-sync,.icono-tag,.icono-tag:after,.icono-terminal,.icono-trash,.icono-user,.icono-user:before,.icono-video,.icono-volumeHigh:after,.icono-volumeHigh:before,.icono-volumeLow:before,.icono-volumeMedium:before,.icono-youtube,.icono-youtube:before,[class*=icono-][class*=Circle],[class*=icono-][class*=Square],[class*=icono-check][class*=Circle]{border:2px solid}.icono-chain:after,.icono-chain:before,.icono-downArrow:before,.icono-dropper:before,.icono-flickr:after,.icono-flickr:before,.icono-indent:before,.icono-leftArrow:before,.icono-list:before,.icono-outdent:before,.icono-paperClip:before,.icono-rename:before,.icono-rightArrow:before,.icono-upArrow:before,.icono-video:before,.icono-volumeDecrease:after,.icono-volumeDecrease:before,.icono-volumeHigh:after,.icono-volumeHigh:before,.icono-volumeIncrease:after,.icono-volumeIncrease:before,.icono-volumeLow:before,.icono-volumeMedium:before,.icono-volumeMute:after,.icono-volumeMute:before,.stickCenterV{position:absolute;top:50%;-webkit-transform:translateY(-50%);-ms-transform:translateY(-50%);transform:translateY(-50%)}.icono-cup:after,.icono-display:after,.icono-display:before,.icono-imac:after,.icono-imacBold:after,.icono-imacBold:before,.icono-iphone:after,.icono-iphone:before,.icono-macbook:before,.icono-macbookBold:before,.icono-market:after,.icono-microphone:after,.icono-microphone:before,.icono-mouse:after,.icono-mouse:before,.icono-search:before,.icono-sitemap:after,.icono-sitemap:before,.icono-tag:after,.icono-trash:before,.icono-user:before,.stickCenterH,[class*=icono-exclamation]:after,[class*=icono-textAlign].icono-textAlignCenter:after,[class*=icono-textAlign].icono-textAlignCenter:before{position:absolute;left:50%;-webkit-transform:translateX(-50%);-ms-transform:translateX(-50%);transform:translateX(-50%)}.icono-camera:before,.icono-clock:after,.icono-clock:before,.icono-document:after,.icono-eye:before,.icono-forbidden:before,.icono-gear:before,.icono-gplus:after,.icono-instagram:before,.icono-keyboard:before,.icono-pin:before,.icono-video:after,.icono-youtube:after,.stickCenter,[class*=icono-check]:before,[class*=icono-cross]:after,[class*=icono-cross]:before,[class*=icono-plus]:after,[class*=icono-plus]:before{position:absolute;left:50%;top:50%;-webkit-transform:translate(-50%,-50%);-ms-transform:translate(-50%,-50%);transform:translate(-50%,-50%)}.spin[class*=spin]{-webkit-animation:loading-spinner 2s infinite linear;animation:loading-spinner 2s infinite linear}@-webkit-keyframes loading-spinner{0%{-webkit-transform:rotate(0deg);transform:rotate(0deg)}100%{-webkit-transform:rotate(360deg);transform:rotate(360deg)}}@keyframes loading-spinner{0%{-webkit-transform:rotate(0deg);transform:rotate(0deg)}100%{-webkit-transform:rotate(360deg);transform:rotate(360deg)}}.icono-icono{width:13px;height:4px;box-shadow:inset 0 0 0 32px,0 16px,17px -18px;-webkit-transform:skew(0,30deg);-ms-transform:skew(0,30deg);transform:skew(0,30deg);margin:11px 19px 19px 2px}.icono-icono:before{position:absolute;width:13px;height:4px;box-shadow:inset 0 0 0 32px,0 16px,-17px -17px;right:-17px;top:-10px;-webkit-transform:skew(0,-48deg);-ms-transform:skew(0,-48deg);transform:skew(0,-48deg)}.icono-icono:after{position:absolute;width:22px;height:15px;left:0;top:-5px;border:4px solid;border-top-color:transparent;border-bottom:none;-webkit-transform:skew(0,-30deg) scaleY(0.6);-ms-transform:skew(0,-30deg) scaleY(0.6);transform:skew(0,-30deg) scaleY(0.6)}.icono-home{width:22px;height:16px;border-top:none;margin:15px 6px 3px}.icono-home:before{width:18px;height:18px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);position:absolute;left:-2px;top:-7px;border-right-color:transparent;border-bottom-color:transparent}.icono-home:after{width:6px;height:10px;bottom:0;position:absolute;left:50%;-webkit-transform:translateX(-50%);-ms-transform:translateX(-50%);transform:translateX(-50%);border-width:1px;border-bottom:none}.icono-mail{width:28px;height:18px;overflow:hidden;margin:8px 3px}.icono-mail:before{position:absolute;width:24.62px;height:24.62px;-webkit-transform:rotate(50deg) skew(-10deg,-20deg);-ms-transform:rotate(50deg) skew(-10deg,-20deg);transform:rotate(50deg) skew(-10deg,-20deg);top:-20px;left:-3px}.icono-rss{width:22px;height:22px;overflow:hidden;margin:6px}.icono-rss:after,.icono-rss:before{position:absolute;border-radius:50%}.icono-rss:before{width:6px;height:6px;box-shadow:0 0 32px inset;left:0;bottom:0}.icono-rss:after{width:27px;height:27px;right:15%;top:15%;border:4px solid transparent;box-shadow:inset 0 0 0 2px,0 0 0 2px}.icono-bars,.icono-hamburger{width:20px;height:2px;box-shadow:inset 0 0 0 32px,0 -6px,0 6px;margin:16px 7px}[class*=icono-cross],[class*=icono-plus]{width:30px;height:30px;margin:2px}[class*=icono-check]:before,[class*=icono-cross]:after,[class*=icono-cross]:before,[class*=icono-plus]:after,[class*=icono-plus]:before{box-shadow:inset 0 0 0 32px}[class*=icono-check]:before,[class*=icono-cross]:before,[class*=icono-plus]:before{width:20px;height:2px}[class*=icono-cross]:after,[class*=icono-plus]:after{height:20px;width:2px}[class*=icono-cross][class*=Circle]:before,[class*=icono-plus][class*=Circle]:before{width:13px}[class*=icono-cross][class*=Circle]:after,[class*=icono-plus][class*=Circle]:after{height:13px}.icono-cross,.icono-crossCircle{-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg)}[class*=icono-check]{width:28px;height:28px;margin:3px 0 3px 6px;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg)}[class*=icono-check]:after,[class*=icono-check]:before{box-shadow:inset 0 0 0 32px}[class*=icono-check]:after{position:absolute;height:12px;width:2px;left:4px;bottom:14px}[class*=icono-check][class*=Circle]{border-radius:50%;width:30px;height:30px;margin:2px}[class*=icono-check][class*=Circle]:before{width: 11px;top: 10px;left: 9px;}[class*=icono-check][class*=Circle]:after{height:8px;left:3px;bottom:5px}.icono-power{width:22px;height:22px;border-radius:50%;border-top-color:transparent;margin:6px}.icono-power:before{position:absolute;top:-15%;left:8px;width:2px;height:60%;box-shadow:inset 0 0 0 32px}.icono-heart{width:20px;height:20px;border-top-color:transparent;border-left-color:transparent;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-radius:4px 0;margin:9px 7px 5px}.icono-heart:after,.icono-heart:before{position:absolute}.icono-heart:before{width:8px;height:14px;left:-10px;bottom:-2px;border-radius:20px 0 0 20px;border-right-color:transparent}.icono-heart:after{width:14px;height:8px;right:-2px;top:-10px;border-radius:20px 20px 0 0;border-bottom-color:transparent}.icono-infinity{width:32px;height:16px;margin:9px 1px}.icono-infinity:after,.icono-infinity:before{width:10px;height:10px;position:absolute;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg)}.icono-infinity:before{left:0;border-radius:32px 0 32px 32px}.icono-infinity:after{right:1px;border-radius:32px 32px 32px 0}.icono-flag{width:22px;height:25px;border-left:3px solid;margin:5px 6px 4px}.icono-flag:after,.icono-flag:before{position:absolute;width:9px;height:8px}.icono-flag:before{left:-2px;top:1px;border-radius:0 2px 0 0;border-right-width:3px}.icono-flag:after{width:5px;left:9px;top:4px;border-left-width:3px;border-radius:2px 2px 0}.icono-file{width:26px;height:32px;border-radius:0 12px 0 0;margin:1px 4px}.icono-file:before{position:absolute;top:-2px;right:-2px;border-style:solid;width:0;height:0;border-width:5px;border-top-color:transparent;border-right-color:transparent}.icono-document{width:26px;height:32px;border-radius:0 0 0 10px;margin:1px 4px}.icono-document:before{position:absolute;width:0;height:0;left:-3px;bottom:-3px;border-width:5px;border-style:solid;border-bottom-color:transparent;border-left-color:transparent}.icono-document:after{width:13px;height:2px;box-shadow:inset 0 0 0 32px,0 -5px 0 0,0 5px 0 0}.icono-folder{width:18px;height:22px;border-left-width:0;border-radius:0 3px 3px 0;margin:8px 2px 4px 14px}.icono-folder:before{position:absolute;width:12px;height:20px;left:-12px;bottom:-2px;border-width:0 0 2px 2px;border-style:solid;border-radius:0 0 0 3px}.icono-folder:after{position:absolute;width:10px;height:5px;left:-12px;top:-7px;border-width:2px 2px 0;border-style:solid;border-radius:3px 3px 0 0}.icono-pin{width:26px;height:26px;border-radius:50% 50% 50% 0;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);margin:1px 4px 7px}.icono-pin:before{position:absolute;width:6px;height:6px;border-radius:50%}.icono-frown,.icono-meh,.icono-smile{border-radius:50%;height:30px;width:30px;margin:2px}.icono-frown:before,.icono-meh:before,.icono-smile:before{border-radius:50%;box-shadow:8px 0 0 0,0 0 0 2px inset;height:4px;width:4px;left:7px;position:absolute;top:27%}.icono-frown:after,.icono-meh:after,.icono-smile:after{border-radius:50%;-webkit-transform:translateX(-50%);-ms-transform:translateX(-50%);transform:translateX(-50%);border-top-color:transparent;border-left-color:transparent;border-right-color:transparent;height:16px;left:50%;position:absolute;top:6%;width:16px}.icono-eye{border-radius:80% 20%;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-width:2px 1px 1px 2px;height:28px;width:28px;margin:3px}.icono-eye:before{border-radius:50%;box-shadow:0 -3px 0 3px inset;height:11px;width:11px}.icono-sliders{height:30px;width:30px;margin:2px}.icono-sliders:after,.icono-sliders:before{-webkit-transform:translateX(-50%);-ms-transform:translateX(-50%);transform:translateX(-50%);left:50%;position:absolute}.icono-sliders:before{width:8px;height:7px;border-radius:2px;top:67%;box-shadow:inset 0 0 0 32px,10px -10px,-10px -14px}.icono-sliders:after{position:absolute;width:2px;height:100%;box-shadow:inset 0 0 0 32px,10px 0,-10px 0}.icono-share{height:9px;width:9px;border-radius:50%;box-shadow:inset 0 0 0 32px,22px -11px 0 0,22px 11px 0 0;margin:12px 24px 13px 1px}.icono-share:after,.icono-share:before{position:absolute;width:24px;height:2px;box-shadow:inset 0 0 0 32px;left:0}.icono-share:before{top:-2px;-webkit-transform:rotate(-25deg);-ms-transform:rotate(-25deg);transform:rotate(-25deg)}.icono-share:after{top:9px;-webkit-transform:rotate(25deg);-ms-transform:rotate(25deg);transform:rotate(25deg)}.icono-sync{width:26px;height:26px;border-radius:50%;border-right-color:transparent;border-left-color:transparent;margin:4px}.icono-sync:after,.icono-sync:before{position:absolute;width:0;height:0;border-width:6px;border-style:solid;border-right-color:transparent;border-bottom-color:transparent;border-left-color:transparent}.icono-sync:before{-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);right:-7px;top:0}.icono-sync:after{-webkit-transform:rotate(135deg);-ms-transform:rotate(135deg);transform:rotate(135deg);left:-7px;bottom:0}.icono-reset{width:26px;height:26px;border-radius:50%;border-width:2px;border-style:solid;border-left-color:transparent;margin:4px}.icono-reset:before{position:absolute;width:0;height:0;left:-7px;bottom:0;border-width:6px;border-style:solid;border-right-color:transparent;border-left-color:transparent;border-bottom-color:transparent;-webkit-transform:rotate(135deg);-ms-transform:rotate(135deg);transform:rotate(135deg)}.icono-gear{width:32px;height:32px;border:3px dotted;border-radius:50%;margin:1px}.icono-gear:before{width:22px;height:22px;box-shadow:0 0 0 3px,0 0 0 2px inset;border-radius:50%;border:6px solid transparent;box-sizing:border-box}.icono-signIn{width:18px;height:32px;border-left:none;border-radius:0 3px 3px 0;margin:1px 8px}.icono-signIn:before{position:absolute;width:11px;height:11px;left:-10px;top:7px;border-bottom:none;border-left:none;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-radius:0 4px 0 0}.icono-signOut{width:18px;height:32px;border-right:none;border-radius:3px 0 0 3px;margin:1px 8px}.icono-signOut:before{position:absolute;width:11px;height:11px;right:-2px;top:7px;border-bottom:none;border-left:none;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-radius:0 4px 0 0}.icono-support{width:26px;height:26px;border:5px solid transparent;box-shadow:0 0 0 2px inset,0 0 0 2px;border-radius:50%;margin:4px}.icono-support:before{position:absolute;width:7px;height:7px;top:-3px;left:-3px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);box-shadow:inset 0 0 0 32px,21px 0 0 0}.icono-support:after{position:absolute;width:7px;height:7px;top:-3px;right:-3px;-webkit-transform:rotate(135deg);-ms-transform:rotate(135deg);transform:rotate(135deg);box-shadow:inset 0 0 0 32px,21px 0 0 0}.icono-dropper{width:40px;height:14px;border-width:3px;border-style:solid;border-right:none;border-top-color:transparent;border-bottom-color:transparent;border-left-color:transparent;box-shadow:-9px 0 0 2px inset,0 0 0 2px inset;border-radius:50% 6px 6px 50%;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);margin:12px -2px 8px -4px}.icono-dropper:before{width:4px;height:14px;right:10px;box-shadow:inset 0 0 0 32px}.icono-tiles{width:4px;height:4px;box-shadow:0 -8px 0,-8px -8px 0,8px -8px 0,0 0 0 32px inset,-8px 0 0,8px 0 0,0 8px 0,-8px 8px 0,8px 8px 0;margin:15px}.icono-list{width:4px;height:4px;box-shadow:inset 0 0 0 32px,0 -8px 0 0,0 8px 0 0;margin:15px 26px 15px 4px}.icono-list:before{width:18px;height:4px;left:8px;box-shadow:inset 0 0 0 32px,0 -8px 0 0,0 8px 0 0}.icono-chain{width:16px;height:2px;box-shadow:inset 0 0 0 32px;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);margin:16px 9px}.icono-chain:after,.icono-chain:before{width:12px;height:8px;border-radius:4px}.icono-chain:before{right:-10px}.icono-chain:after{left:-10px}.icono-youtube{border-right-color:transparent;border-left-color:transparent;border-radius:10px;width:32px;height:22.29px;margin:6px 1px}.icono-youtube:before{position:absolute;top:0;right:0;bottom:0;left:0;border-top-color:transparent;border-bottom-color:transparent;border-radius:6px/3px}.icono-youtube:after{width:0;height:0;border-width:4px 0 4px 8px;border-style:solid;border-top-color:transparent;border-bottom-color:transparent}.icono-rename{width:26px;height:10px;border-color:transparent;border-width:3px;box-shadow:0 0 0 1px,11px 0 0 0 inset;margin:12px 4px}.icono-rename:before{width:1px;height:18px;left:9px;border-width:2px 4px;border-style:solid;border-right-color:transparent;border-left-color:transparent;box-shadow:0 0 0 1px inset}.icono-search{width:22px;height:22px;border-radius:50%;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);margin:4px 4px 8px 8px}.icono-search:before{width:4px;height:11px;box-shadow:inset 0 0 0 32px;top:19px;border-radius:0 0 1px 1px}.icono-book{width:26px;height:22px;border-radius:0 0 0 6px;border-top:none;margin:10px 4px 2px}.icono-book:before{position:absolute;width:24px;height:7px;box-sizing:border-box;border-top:none;border-right:none;left:-2px;top:-5px;border-radius:0 0 0 6px}.icono-book:after{position:absolute;width:24px;height:8px;box-sizing:border-box;left:-2px;top:-8px;border-bottom:none;border-radius:6px 0 0}.icono-forbidden{width:28px;height:28px;border-width:3px;border-radius:50%;margin:3px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg)}.icono-forbidden:before{width:24px;height:4px;box-shadow:inset 0 0 0 32px}.icono-trash{width:22px;height:22px;border-radius:0 0 3px 3px;border-top:none;margin:9px 6px 3px}.icono-trash:before{width:8px;height:2px;top:-6px;box-shadow:inset 0 0 0 32px,-10px 2px 0 0,-6px 2px 0 0,0 2px 0 0,6px 2px 0 0,10px 2px 0 0}.icono-keyboard{width:32px;height:22px;border-radius:3px;margin:6px 1px}.icono-keyboard:before{width:2px;height:2px;box-shadow:-2px -4px 0,-6px -4px 0,-10px -4px 0,2px -4px 0,6px -4px 0,8px -4px 0,10px -4px 0,-4px 0 0,-8px 0 0,-10px 0 0,inset 0 0 0 32px,4px 0 0,8px 0 0,10px 0 0,4px 4px 0,2px 4px 0,0 4px 0,-2px 4px 0,-6px 4px 0,-10px 4px 0,6px 4px 0,10px 4px 0}.icono-mouse{width:23px;height:32px;border-radius:11px 11px 12px 12px;margin:1px 5px 1px 6px}.icono-mouse:before{width:1px;height:6px;border-radius:2px;border-color:transparent;border-width:1px;top:5px;box-shadow:0 0 0 1px,0 0 0 2px inset}.icono-mouse:after{width:1px;height:4px;top:0;box-shadow:inset 0 0 0 32px,0 13px 0 0}.icono-user{width:32px;height:14px;border-radius:64px 64px 0 0/64px;margin:18px 1px 2px}.icono-user:before{width:12px;height:12px;top:-20px;border-radius:50%}.icono-crop{width:21px;height:21px;border-left:none;border-bottom:none;margin:9px 9px 4px 4px}.icono-crop:before{position:absolute;width:21px;height:21px;top:-7px;right:-7px;border-top:none;border-right:none;box-sizing:border-box}.icono-crop:after{position:absolute;width:27px;height:1px;left:2px;top:3px;box-shadow:inset 0 0 0 32px;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg)}.icono-display{width:26px;height:22px;margin:4px 4px 8px}.icono-display:before{width:4px;height:3px;bottom:-5px;box-shadow:inset 0 0 0 32px}.icono-display:after{width:14px;height:2px;bottom:-6px;box-shadow:inset 0 0 0 32px}.icono-imac{width:28px;height:24px;border-width:2px 2px 6px;border-color:transparent;border-radius:3px;box-shadow:0 0 0 1px,0 0 0 1px inset;margin:3px 3px 7px}.icono-imac:before{position:absolute;height:4px;right:-3px;left:-3px;bottom:-6px;box-shadow:inset 0 0 0 32px;border-radius:0 0 3px 3px}.icono-imac:after{width:9px;height:7px;box-shadow:inset 0 0 0 32px;bottom:-12px;border-radius:32px 32px 0 0/64px}.icono-imacBold{width:28px;height:22px;border-radius:4px;margin:4px 3px 8px}.icono-imacBold:before{width:9px;height:7px;box-shadow:inset 0 0 0 32px;bottom:-6px;border-radius:32px 32px 0 0/64px}.icono-imacBold:after{width:24px;height:3px;box-shadow:inset 0 0 0 32px;bottom:0}.icono-iphone{width:19px;height:31px;border-radius:3px;border-width:5px 1px;border-color:transparent;box-shadow:0 0 0 1px,0 0 0 1px inset;margin:2px 8px 1px 7px}.icono-iphone:after,.icono-iphone:before{box-shadow:inset 0 0 0 32px}.icono-iphone:before{width:3px;height:1px;top:-3px}.icono-iphone:after{width:3px;height:3px;bottom:-4px;border-radius:50%}.icono-iphoneBold{width:20px;height:32px;margin:1px 7px;border-radius:4px;border-width:5px 2px}.icono-macbook{width:32px;height:2px;box-shadow:inset 0 0 0 32px;border-radius:0 0 32px 32px/3px;margin:25px 1px 7px}.icono-macbook:before{width:20px;height:14px;bottom:2px;border-width:3px 1px 1px;border-color:transparent;border-radius:3px 3px 0 0;box-shadow:0 0 0 1px,0 0 0 1px inset}.icono-macbookBold{width:32px;height:2px;box-shadow:inset 0 0 0 32px;margin:25px 1px 7px}.icono-macbookBold:before{width:20px;height:14px;bottom:2px;border-width:3px 2px 1px;border-radius:3px 3px 0 0}.icono-image{width:30px;height:26px;border-radius:3px;overflow:hidden;margin:4px 2px}.icono-image:before{position:absolute;width:20px;height:20px;left:-2px;top:14px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);box-shadow:inset 0 0 0 32px,10px -6px 0 0}.icono-image:after{position:absolute;width:4px;height:4px;border-radius:50%;box-shadow:inset 0 0 0 32px;top:5px;right:5px}.icono-headphone{width:30px;height:27px;border-bottom-color:transparent;border-radius:32px/32px 32px 16px 16px;margin:2px 2px 5px}.icono-headphone:before{position:absolute;width:4px;height:12px;left:1px;bottom:-4px;border-radius:5px;box-shadow:inset 0 0 0 32px,20px 0 0 0}.icono-music{width:18px;height:6px;-webkit-transform:skewY(-15deg);-ms-transform:skewY(-15deg);transform:skewY(-15deg);box-shadow:inset 0 0 0 32px;border-radius:2px 2px 0 0;margin:4px 5px 24px 11px}.icono-music:before{position:absolute;width:2px;height:16px;left:0;top:4px;box-shadow:inset 0 0 0 32px,16px 0 0 0}.icono-music:after{position:absolute;width:10px;height:8px;left:-8px;top:17px;border-radius:50%;box-shadow:inset 0 0 0 32px,16px 0 0 0}.icono-video{width:20px;height:20px;margin:7px}.icono-video:before{width:3px;height:3px;left:-8px;box-shadow:inset 0 0 0 32px,0 -8px 0 0,0 8px 0 0,29px 0 0 0,29px -8px 0 0,29px 8px 0 0}.icono-video:after{width:0;height:0;border-width:4px 0 4px 6px;border-style:solid;border-top-color:transparent;border-bottom-color:transparent}.icono-nexus{width:21px;height:32px;border-width:3px 1px;border-radius:16px/3px;margin:1px 7px 1px 6px}.icono-microphone{width:22px;height:15px;border-width:0 2px 2px;border-radius:20px/0 0 20px 20px;margin:12px 6px 7px}.icono-microphone:before{width:10px;height:18px;top:-11px;border-radius:20px}.icono-microphone:after{width:2px;height:2px;bottom:-4px;box-shadow:inset 0 0 0 32px,0 2px,0 4px,-2px 4px,-4px 4px,-6px 4px,2px 4px,4px 4px,6px 4px}.icono-asterisk,.icono-asterisk:after,.icono-asterisk:before{width:4px;height:20px;box-shadow:inset 0 0 0 32px;border-radius:1px;margin:7px 15px}.icono-asterisk:after,.icono-asterisk:before{position:absolute;margin:0;left:0;top:0}.icono-asterisk:before{-webkit-transform:rotate(-58deg);-ms-transform:rotate(-58deg);transform:rotate(-58deg)}.icono-asterisk:after{-webkit-transform:rotate(58deg);-ms-transform:rotate(58deg);transform:rotate(58deg)}.icono-terminal{width:28px;height:24px;margin:5px 3px}.icono-terminal:before{width:5px;height:5px;position:absolute;top:50%;-webkit-transform:translateY(-50%) rotate(45deg);-ms-transform:translateY(-50%) rotate(45deg);transform:translateY(-50%) rotate(45deg);left:3px;border-width:2px 2px 0 0;border-style:solid}.icono-terminal:after{position:absolute;width:5px;height:0;border-bottom:2px solid;right:6px;bottom:4px}.icono-paperClip{width:24px;height:18px;border-left:none;border-radius:0 16px 16px 0;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);margin:5px 0 11px 10px}.icono-paperClip:before{width:18px;height:6px;right:2px;border-radius:0 16px 16px 0;border-left:none}.icono-paperClip:after{position:absolute;width:12px;height:10px;left:-12px;top:-2px;border-right:none;border-radius:16px 0 0 16px}.icono-market{width:32px;height:12px;border-top:none;margin:19px 1px 3px}.icono-market:before{width:6px;height:13px;position:absolute;top:-15px;left:-5px;border-radius:0 0 10px 10px;border-left:none;box-shadow:inset 0 0 0 32px,8px 0 0,16px 0 0,24px 0 0,32px 0 0}.icono-market:after{width:6px;height:6px;bottom:-2px}.icono-clock{width:26px;height:26px;border-radius:50%;margin:4px}.icono-clock:after,.icono-clock:before{top:35%;box-shadow:inset 0 0 0 32px;border-radius:2px}.icono-clock:before{width:2px;height:9px}.icono-clock:after{width:6px;height:2px;-webkit-transform-origin:left center;-ms-transform-origin:left center;transform-origin:left center;-webkit-transform:rotate(45deg) translate(1px,2px);-ms-transform:rotate(45deg) translate(1px,2px);transform:rotate(45deg) translate(1px,2px)}[class*=icono-textAlign]{width:28px;height:22px;margin:6px 3px}[class*=icono-textAlign]:after,[class*=icono-textAlign]:before{position:absolute;height:2px;box-shadow:inset 0 0 0 32px,0 8px 0 0,0 16px 0 0;right:0}[class*=icono-textAlign]:before{width:28px;top:0}[class*=icono-textAlign]:after{width:18px;top:4px}[class*=icono-textAlign].icono-textAlignLeft:after,[class*=icono-textAlign].icono-textAlignLeft:before{left:0}[class*=icono-exclamation]{overflow:visible;width:30px;border-bottom:2px solid;border-radius:0 0 4px 4px;margin:26px 2px 6px}[class*=icono-exclamation]:before{position:absolute;width:26px;height:26px;left:1px;top:-14px;border-width:2px 0 0 2px;border-style:solid;border-radius:4px 0;-webkit-transform:rotate(45deg) skew(12deg,12deg);-ms-transform:rotate(45deg) skew(12deg,12deg);transform:rotate(45deg) skew(12deg,12deg)}[class*=icono-exclamation]:after{width:4px;height:3px;top:-14px;box-shadow:inset 0 0 0 32px,0 3px,0 8px}[class*=icono-exclamation][class*=Circle]{margin:2px}[class*=icono-exclamation][class*=Circle]:before{display:none}[class*=icono-exclamation][class*=Circle]:after{box-shadow:inset 0 0 0 32px,0 3px,0 5px,0 10px;top:6px}.icono-frown:after{-webkit-transform:translateX(-50%) rotate(180deg);-ms-transform:translateX(-50%) rotate(180deg);transform:translateX(-50%) rotate(180deg);-webkit-transform-origin:center 85%;-ms-transform-origin:center 85%;transform-origin:center 85%}.icono-meh:after{top:0;width:12px;border-left-width:0;border-right-width:0;border-radius:0}.icono-indent,.icono-outdent{width:20px;height:16px;border-width:4px 0 4px 8px;border-style:solid;border-color:transparent;box-shadow:0 -2px,0 2px,inset 0 2px,inset 0 -2px;margin:9px 7px}.icono-indent:before,.icono-outdent:before{left:-8px;border:5px solid;border-top-color:transparent;border-bottom-color:transparent;border-right-width:0}.icono-outdent:before{border-left-width:0;border-right-width:5px}.icono-locationArrow{width:32px;height:32px;margin:1px}.icono-locationArrow:before{position:absolute;left:7px;top:16px;border-width:6px 0 6px 6px;border-style:solid;border-left-color:transparent;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg)}.icono-locationArrow:after{position:absolute;top:10px;left:2px;border-width:10px;border-style:solid;border-bottom-color:transparent;border-left-color:transparent;-webkit-transform:skew(-30deg,-30deg);-ms-transform:skew(-30deg,-30deg);transform:skew(-30deg,-30deg)}.icono-commentEmpty{width:30px;height:22px;border-radius:4px 4px 7px 7px;border-bottom-color:transparent;margin:5px 2px 7px}.icono-commentEmpty:before{position:absolute;width:6px;height:6px;border-width:0 0 2px 2px;border-style:solid;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);bottom:-4px;left:6px}.icono-commentEmpty:after{position:absolute;width:8px;height:2px;border-width:0 12px 0 6px;border-style:solid;bottom:0;left:0}.icono-comment{width:30px;height:20px;box-shadow:inset 0 0 0 32px;border-radius:4px;margin:5px 2px 9px}.icono-comment:before{position:absolute;width:8px;height:8px;box-shadow:inset 0 0 0 32px;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);bottom:-4px;left:6px}.icono-areaChart,.icono-barChart{width:30px;height:22px;border-top-width:0;border-right-width:0;border-color:transparent;box-shadow:-2px 2px;overflow:hidden;margin:4px 0 8px 4px}.icono-areaChart:before{position:absolute;left:0;bottom:7px;border:6px solid transparent;border-bottom-color:currentColor;box-shadow:0 7px}.icono-areaChart:after{position:absolute;left:11px;bottom:4px;border-width:0 6px 13px;border-style:solid;border-color:transparent transparent currentColor;box-shadow:0 4px}.icono-barChart{border-color:transparent;box-shadow:-2px 2px;margin:4px 0 8px 4px}.icono-barChart:before{position:absolute;left:0;bottom:0;width:4px;height:15px;box-shadow:inset 0 -8px 0 0,6px 0,12px 7px,18px 5px}.icono-pieChart{width:0;height:0;border:15px solid;border-right-color:transparent;border-radius:50%;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg);margin:2px}.icono-pieChart:before{position:absolute;width:0;height:0;left:-11px;top:-14px;border:14px solid;border-left-color:transparent;border-bottom-color:transparent;border-top-color:transparent;border-radius:50%}.icono-bookmark{width:0;height:0;border:9px solid;border-bottom-color:transparent;box-shadow:0 -4px;border-radius:3px 3px 0 0;margin:10px 8px 6px}.icono-bookmarkEmpty{width:18px;height:22px;border-bottom:none;border-radius:3px 3px 2px 2px;overflow:hidden;margin:6px 8px}.icono-bookmarkEmpty:before{position:absolute;width:12px;height:12px;bottom:0;left:0;border-right:none;border-bottom:none;-webkit-transform:rotate(45deg) translate(35%,35%);-ms-transform:rotate(45deg) translate(35%,35%);transform:rotate(45deg) translate(35%,35%)}.icono-filter{width:0;height:0;border:10px solid;border-bottom:none;border-left-color:transparent;border-right-color:transparent;padding:3px;box-shadow:inset 0 7px;margin:9px 4px}.icono-volume,.icono-volumeDecrease,.icono-volumeHigh,.icono-volumeIncrease,.icono-volumeLow,.icono-volumeMedium,.icono-volumeMute{width:0;height:0;border:7px solid;border-left:none;border-top-color:transparent;border-bottom-color:transparent;padding:6px 3px;box-shadow:inset 4px 0;margin:4px 10px 4px 11px}.icono-volumeHigh,.icono-volumeLow,.icono-volumeMedium{margin:4px 14px 4px 7px}.icono-volumeHigh:after,.icono-volumeHigh:before,.icono-volumeLow:before,.icono-volumeMedium:before{width:15px;height:15px;position:absolute;border-radius:50%;border-top-color:transparent;border-bottom-color:transparent;border-left-color:transparent;left:2px}.icono-volumeHigh,.icono-volumeMedium{margin:4px 16px 4px 5px}.icono-volumeHigh:before,.icono-volumeMedium:before{border-style:double;border-width:6px;left:-2px}.icono-volumeHigh{margin:4px 18px 4px 3px}.icono-volumeHigh:after{width:32px;height:32px;left:-7px}.icono-volumeDecrease,.icono-volumeIncrease,.icono-volumeMute{margin:4px 16px 4px 5px}.icono-volumeDecrease:after,.icono-volumeDecrease:before,.icono-volumeIncrease:after,.icono-volumeIncrease:before,.icono-volumeMute:after,.icono-volumeMute:before{box-shadow:inset 0 0 0 32px}.icono-volumeDecrease:before,.icono-volumeIncrease:before,.icono-volumeMute:before{width:10px;height:2px;left:17px}.icono-volumeIncrease:after,.icono-volumeMute:after{height:10px;width:2px;left:21px}.icono-volumeMute:after,.icono-volumeMute:before{-webkit-transform:translateY(-50%) rotate(45deg);-ms-transform:translateY(-50%) rotate(45deg);transform:translateY(-50%) rotate(45deg)}.icono-tag{width:18px;height:24px;border-radius:6px 6px 4px 4px;border-top:none;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);margin:5px 8px}.icono-tag:before{position:absolute;top:-4px;left:1px;width:10px;height:10px;border-width:2px 0 0 2px;border-style:solid;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-radius:5px 0 0}.icono-tag:after{top:1px;width:3px;height:3px;border-radius:50%}.icono-calendar{width:32px;height:28px;border-width:4px 2px 2px;border-style:solid;border-radius:4px;margin:5px 1px 1px}.icono-calendar:before{position:absolute;width:4px;height:4px;top:3px;left:3px;box-shadow:inset 0 0 0 32px,6px 0,12px 0,18px 0,0 6px,6px 6px,12px 6px,18px 6px,0 12px,6px 12px,12px 12px,18px 12px}.icono-calendar:after{position:absolute;width:4px;height:8px;box-shadow:inset 0 0 0 32px,16px 0;border-radius:4px;top:-8px;left:4px}.icono-camera{width:32px;height:24px;border-radius:4px;margin:5px 1px}.icono-camera:before{width:10px;height:10px;border:1px solid transparent;box-shadow:inset 0 0 0 1px,0 0 0 2px;border-radius:50%}.icono-camera:after{position:absolute;width:4px;height:2px;right:2px;top:2px;box-shadow:inset 0 0 0 32px}.icono-piano{width:28px;height:22px;margin:6px 3px}.icono-piano:before{position:absolute;left:4px;top:0;width:1px;height:100%;box-shadow:inset 0 0 0 32px,5px 0,10px 0,15px 0}.icono-piano:after{position:absolute;width:3px;height:12px;left:3px;top:0;box-shadow:inset 0 0 0 32px,5px 0,10px 0,15px 0}.icono-ruler{width:27px;height:12px;margin:11px 4px 11px 3px}.icono-ruler:before{position:absolute;width:1px;height:4px;box-shadow:inset 0 0 0 32px,6px 0,12px 0;left:5px;top:0}.icono-ruler:after{position:absolute;width:1px;height:2px;box-shadow:inset 0 0 0 32px,2px 0,6px 0,8px 0,12px 0,14px 0,18px 0,20px 0;left:1px;top:0}.icono-facebook{width:9px;height:26px;box-shadow:inset 2px 4px 0 0;border-left:3px solid;border-radius:5px 0 0;margin:4px 11px 4px 14px}.icono-facebook:before{position:absolute;top:9px;left:-6px;width:11px;height:0;border-top:4px solid;border-right:1px solid transparent}.icono-twitter{width:14px;height:23px;border-radius:0 0 0 8px;box-shadow:-6px 2px 0 0;margin:4px 7px 7px 13px}.icono-twitter:before{position:absolute;bottom:-2px;left:-6px;width:17px;height:6px;border-radius:0 0 0 8px;box-shadow:inset 4px -6px,0 -11px}.icono-twitter:after{position:absolute;width:6px;height:6px;box-shadow:inset 0 0 0 32px,13px 8px,13px 19px;border-radius:50%;left:-6px}.icono-gplus{width:10px;height:2px;box-shadow:inset 0 0 0 32px;margin:14px 4px 18px 20px}.icono-gplus:before{position:absolute;top:-5px;right:10px;content:'g'!important;font-family:georgia;font-size:32px;text-indent:0;line-height:0}.icono-gplus:after{width:2px;height:10px;box-shadow:inset 0 0 0 32px}.icono-linkedIn{width:5px;height:16px;box-shadow:inset 0 0 0 32px,8px 0;margin:12px 24px 6px 5px}.icono-linkedIn:before{position:absolute;width:5px;height:5px;box-shadow:inset 0 0 0 32px;top:-7px;left:0;border-radius:50%}.icono-linkedIn:after{position:absolute;width:12px;height:16px;border-right:1px solid;left:11px;bottom:0;border-radius:8px 5px 0 0/11px 5px 0 0;box-shadow:inset -4px 4px}.icono-instagram{width:26px;height:26px;box-shadow:inset 0 0 0 2px;border-radius:4px;margin:4px}.icono-instagram:before{width:10px;height:10px;border-radius:50%;box-shadow:0 0 0 3px}.icono-instagram:after{position:absolute;width:5px;height:5px;border-radius:1px;right:3px;top:3px;box-shadow:0 0 0 2px,1px 1px 0 2px,-5px -1px 0 1px,-10px -1px 0 1px,-16px 1px 0 2px}.icono-flickr{width:24px;height:24px;overflow:hidden;border-radius:4px;margin:5px}.icono-flickr:after,.icono-flickr:before{width:7px;height:7px;border-radius:50%}.icono-flickr:before{left:4px;box-shadow:0 0 0 1px,0 -10px 0 6px,0 10px 0 6px,-4px 0 0 3px}.icono-flickr:after{right:4px;box-shadow:0 0 0 1px,0 -10px 0 6px,0 10px 0 6px,4px 0 0 3px}.icono-delicious{width:24px;height:24px;overflow:hidden;border-radius:4px;box-shadow:inset 0 0 0 2px;margin:5px}.icono-delicious:before{position:absolute;width:12px;height:12px;box-shadow:inset 0 0 0 32px,12px -12px 0 0;left:0;bottom:0}.icono-codepen{width:2px;height:10px;box-shadow:inset 0 0 0 32px,0 15px,-11px 7px,11px 7px;margin:4px 16px 20px}.icono-codepen:before{position:absolute;right:2px;top:3px;width:11px;height:4px;-webkit-transform:skew(0,-35deg) scaleY(0.6);-ms-transform:skew(0,-35deg) scaleY(0.6);transform:skew(0,-35deg) scaleY(0.6);box-shadow:inset 0 0 0 32px,0 13px,11px 26px,12px 39px}.icono-codepen:after{position:absolute;left:2px;top:3px;width:11px;height:4px;-webkit-transform:skew(0,35deg) scaleY(0.6);-ms-transform:skew(0,35deg) scaleY(0.6);transform:skew(0,35deg) scaleY(0.6);box-shadow:inset 0 0 0 32px,0 13px,-11px 26px,-12px 39px}.icono-blogger{width:24px;height:14px;border-radius:0 0 7px 7px;margin:14px 5px 6px}.icono-blogger,.icono-blogger:before{border-width:6px;border-style:solid}.icono-blogger:before{position:absolute;width:8px;height:2px;left:-6px;top:-15px;border-radius:6px 6px 0 0}.icono-disqus{width:31px;height:31px;box-shadow:inset 0 0 0 32px;border-radius:50%;margin:1px 1px 2px 2px}.icono-disqus:before{position:absolute;width:0;height:0;border:5px solid transparent;border-top:10px solid;-webkit-transform:rotate(50deg);-ms-transform:rotate(50deg);transform:rotate(50deg);left:-5px;top:20px}.icono-dribbble{width:26px;height:26px;border-radius:50%;box-shadow:inset 0 0 0 2px;overflow:hidden;position:relative;background-image:-webkit-radial-gradient(50% 100%,transparent 0,transparent 9px,currentColor 10px,currentColor 11px,transparent 12px);background-image:radial-gradient(50% 100%,transparent 0,transparent 9px,currentColor 10px,currentColor 11px,transparent 12px);background-repeat:no-repeat;background-position:-8px center;-webkit-transform:rotate(-25deg);-ms-transform:rotate(-25deg);transform:rotate(-25deg);margin:4px}.icono-dribbble:after,.icono-dribbble:before{position:absolute;border-radius:50%;border:2px solid;width:40px;height:30px}.icono-dribbble:after{top:14px;left:-7px;width:32px}.icono-dribbble:before{left:-6px;top:-23px}.icono-creditCard{width:32px;height:24px;border-radius:3px;margin:5px 1px}.icono-creditCard:before{position:absolute;top:4px;width:100%;height:6px;box-shadow:inset 0 0 0 32px}.icono-creditCard:after{left:3px;bottom:3px;position:absolute;width:4px;height:2px;box-shadow:inset 0 0 0 32px,6px 0}.icono-cup{width:22px;height:16px;box-shadow:inset 0 0 0 32px;border-radius:0 0 5px 5px;margin:6px 6px 12px}.icono-cup:before{position:absolute;right:-3px;top:4px;width:5px;height:5px;border-radius:50%;box-shadow:0 0 0 2px}.icono-cup:after{bottom:-5px;width:26px;height:3px;border-radius:0 0 3px 3px;box-shadow:inset 0 0 0 32px}.icono-play{width:0;height:0;border-width:10px 0 10px 16px;border-style:solid;border-top-color:transparent;border-bottom-color:transparent;margin:7px 9px}.icono-pause{width:6px;height:20px;margin:7px 20px 7px 8px;box-shadow:inset 0 0 0 32px,12px 0 0 0}.icono-stop{width:0;height:0;border:10px solid;margin:7px}.icono-rewind{-webkit-transform:rotate(180deg);-ms-transform:rotate(180deg);transform:rotate(180deg)}.icono-forward,.icono-rewind{width:0;height:0;border:10px solid transparent;border-left:10px solid;margin:7px}.icono-forward:before,.icono-rewind:before{position:absolute;left:0;top:-10px;width:0;height:0;border:10px solid transparent;border-left:10px solid}.icono-next,.icono-previous{width:0;height:0;border:10px solid transparent;border-left:10px solid;border-right:none;margin:7px 14px 7px 10px;box-shadow:4px 0}.icono-previous{-webkit-transform:rotate(180deg);-ms-transform:rotate(180deg);transform:rotate(180deg);margin:7px 10px 7px 14px}.icono-caretDown,.icono-caretDownCircle,.icono-caretDownSquare,.icono-caretLeft,.icono-caretLeftCircle,.icono-caretLeftSquare,.icono-caretRight,.icono-caretRightCircle,.icono-caretRightSquare,.icono-caretUp,.icono-caretUpCircle,.icono-caretUpSquare{width:12px;height:20px;margin:7px 11px}.icono-caretDown:after,.icono-caretDown:before,.icono-caretDownCircle:after,.icono-caretDownCircle:before,.icono-caretDownSquare:after,.icono-caretDownSquare:before,.icono-caretLeft:after,.icono-caretLeft:before,.icono-caretLeftCircle:after,.icono-caretLeftCircle:before,.icono-caretLeftSquare:after,.icono-caretLeftSquare:before,.icono-caretRight:after,.icono-caretRight:before,.icono-caretRightCircle:after,.icono-caretRightCircle:before,.icono-caretRightSquare:after,.icono-caretRightSquare:before,.icono-caretUp:after,.icono-caretUp:before,.icono-caretUpCircle:after,.icono-caretUpCircle:before,.icono-caretUpSquare:after,.icono-caretUpSquare:before{width:14px;height:2px;position:absolute;bottom:0;margin:auto 0;right:2px;box-shadow:inset 0 0 0 32px;-webkit-transform-origin:right;-ms-transform-origin:right;transform-origin:right}.icono-caretDown:before,.icono-caretDownCircle:before,.icono-caretDownSquare:before,.icono-caretLeft:before,.icono-caretLeftCircle:before,.icono-caretLeftSquare:before,.icono-caretRight:before,.icono-caretRightCircle:before,.icono-caretRightSquare:before,.icono-caretUp:before,.icono-caretUpCircle:before,.icono-caretUpSquare:before{top:2px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg)}.icono-caretDown:after,.icono-caretDownCircle:after,.icono-caretDownSquare:after,.icono-caretLeft:after,.icono-caretLeftCircle:after,.icono-caretLeftSquare:after,.icono-caretRight:after,.icono-caretRightCircle:after,.icono-caretRightSquare:after,.icono-caretUp:after,.icono-caretUpCircle:after,.icono-caretUpSquare:after{top:0;-webkit-transform:rotate(-45deg);-ms-transform:rotate(-45deg);transform:rotate(-45deg)}.icono-caretLeft,.icono-caretLeftCircle,.icono-caretLeftSquare{-webkit-transform:rotate(180deg);-ms-transform:rotate(180deg);transform:rotate(180deg)}.icono-caretUp,.icono-caretUpCircle,.icono-caretUpSquare{-webkit-transform:rotate(-90deg);-ms-transform:rotate(-90deg);transform:rotate(-90deg)}.icono-caretDown,.icono-caretDownCircle,.icono-caretDownSquare{-webkit-transform:rotate(90deg);-ms-transform:rotate(90deg);transform:rotate(90deg)}[class*=icono-caret][class*=Circle]:after,[class*=icono-caret][class*=Circle]:before,[class*=icono-caret][class*=Square]:after,[class*=icono-caret][class*=Square]:before{width:11px;right:8px}.icono-downArrow,.icono-leftArrow,.icono-rightArrow,.icono-upArrow{width:16px;height:4px;margin:15px 9px;box-shadow:inset 0 0 0 2px;-webkit-transform:translateX(-3px);-ms-transform:translateX(-3px);transform:translateX(-3px)}.icono-downArrow:before,.icono-leftArrow:before,.icono-rightArrow:before,.icono-upArrow:before{border-style:solid;border-width:8px 0 8px 8px;border-color:transparent;border-left-color:inherit;left:100%;right:auto}.icono-leftArrow{-webkit-transform:translateX(3px) rotate(180deg);-ms-transform:translateX(3px) rotate(180deg);transform:translateX(3px) rotate(180deg)}.icono-upArrow{-webkit-transform:translateY(3px) rotate(-90deg);-ms-transform:translateY(3px) rotate(-90deg);transform:translateY(3px) rotate(-90deg)}.icono-downArrow{-webkit-transform:translateY(-3px) rotate(90deg);-ms-transform:translateY(-3px) rotate(90deg);transform:translateY(-3px) rotate(90deg)}.icono-sun{width:22px;height:22px;border:2px solid;border-radius:50%;box-shadow:-15px 0 0 -9px,15px 0 0 -9px,0 -15px 0 -9px,0 15px 0 -9px,11px 11px 0 -9px,-11px -11px 0 -9px,11px -11px 0 -9px,-11px 11px 0 -9px;margin:6px}.icono-moon{width:22px;height:22px;border-radius:50%;overflow:hidden;margin:6px}.icono-moon:before{position:absolute;width:20px;height:20px;top:-2px;left:6px;border-radius:50%;box-shadow:0 0 0 32px}.icono-cart{width:22px;height:0;border-width:14px 6px 0 2px;border-style:solid;border-right-color:transparent;border-left-color:transparent;margin:9px 3px 11px 9px}.icono-cart:before{position:absolute;width:4px;height:4px;border-radius:50%;box-shadow:inset 0 0 0 32px,13px 0,-4px -20px 0 1px;top:2px;left:-3px}.icono-sitemap{width:24px;height:2px;box-shadow:0 -5px;margin:21px 5px 11px}.icono-sitemap:before{width:6px;height:6px;border-radius:2px;box-shadow:inset 0 0 0 32px,11px 0,-11px 0,0 -14px 0 1px}.icono-sitemap:after{width:2px;height:10px;box-shadow:0 -7px,11px -5px,-11px -5px}[class*=icono-]{display:inline-block;vertical-align:middle;position:relative;font-style:normal;color:#ddd;text-align:left;text-indent:-9999px;direction:ltr}[class*=icono-]:after,[class*=icono-]:before{content:'';pointer-events:none}[class*=icono-][class*=Circle]{border-radius:50%;width:20px;height:20px;margin:2px;}[class*=icono-][class*=Square]{border-radius:4px;width:30px;height:30px;margin:2px}[class*=icono-],[class*=icono-] *{box-sizing:border-box}";

//正则
var regular = GetRegular();

function GetRegular() {
    return {
        "chinese": /^[\u4E00-\u9FA5]+$/,                                    //中文
        "notchinese": /^[^\u4e00-\u9fa5]+$/,                                //不能包含中文
        "mobilePhone": /^1[3|4|5|8|9][0-9]\d{8}$/,                          //手机号码
        "phone": /^[0-9]{7,8}$/,                                            //电话号码
        "areacodephone": /\d{3}-\d{8}|\d{4}-\{7,8}/,                        //电话号码（带区号）
        "email": /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,           //邮箱
        "number": /^[0-9]+$/,                                               //数字
        "containnumber": /[0-9]+/,                                              //包含数字
        "positiveinteger": /^[1-9]\d*$/,                                    //正整数
        "negative": /^-[1-9]\d*$/,                                          //负整数
        "Integer": /^-?[1-9]\d*$/,                                          //整数
        "english": /^[A-Za-z]+$/,                                           //字母
        "capitalenglish": /^[A-Z]+$/,                                       //大写字母
        "smallenglish": /^[a-z]+$/,                                         //小写字母
        "enandnum": /^[A-Za-z0-9]+$/,                                       //字母和数字
        "zipcode": /^[1-9]\d{5}$/,                                          //邮编
        "identity": /^\d{15}(\d\d[0-9xX])?$/,                               //身份证
        "url": /^[a-zA-z]+:\/\/[^\s]*$/,                                    //网址Url
        "qq": /^[1-9][0-9]{4,}$/,                                           //腾讯qq号
        "ip": /^\d+\.\d+\.\d+\.\d+$/,                                       //ip
        "chinesStart": /^[a-zA-Z\u0391-\uFFE5]+/,                           //中文或字母开头
    }
}

//获取字符串长度（中文算两位）
function GetByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var length = val.charCodeAt(i);
        if (length >= 0 && length <= 128) {
            len += 1;
        }
        else {
            len += 2;
        }
    }
    return len;
}

var verifyType = "verify-type";
var verifyText = "verify-text";


(function ($) {


    //表单对象
    var FromObject = function (ops, obj) {

        this.fromElementObj = obj;                                          //from元素
        this.parm = {};                                                     //提交参数
        this.element = [];
        this.defaults = {
            button: $(obj).find("#button"),                                 //提交按钮
            postUrl: _basepath + "User/AjaxLogin.aspx",                     //提交地址
            submitInput: "input:text,input:password,input:hidden",          //提交数据元素
            verifyEvent: "blur",                                            //验证事件名称
            isJsAlert: false,                                               //是否弹窗提示
            submitVerify: function (parm) {                                 //提交前验证参数方法
                return true;
            },
            handleParm: function (parm) {                                   //处理参数方法
                return parm;
            },
            success: function (res, formObj) {                              //提交成功后处理方法
                whir.msg.alertSucess("操作成功");
            },
            error: function (res, formObj) {                                //提交失败后处理方法
                var opt = formObj.options;


                var result;
                try {
                    result = eval("(" + res + ")");
                } catch (ex) {
                    result = res[0];
                }


                if (result.body != undefined && opt.errMsg[result.body] != undefined) {
                    whir.msg.alertError(opt.errMsg[result.body]);
                } else if (opt.errMsg[result.status] != undefined) {
                    whir.msg.alertError(opt.errMsg[result.status]);
                } else if (result.body != undefined) {
                    whir.msg.alertError(result.body);
                } else {
                    whir.msg.alertError(result.status);
                }

            },
            addErrorTips: function (errorText, verifyObj) {                 //添加错误提示
                $(verifyObj).nextAll("i").detach();
                $(verifyObj).nextAll("span").detach();
                $(verifyObj).removeClass("error_red success_green");

                var i = $("<i>").addClass("error icono-crossCircle").css({ "color": "red" });
                var span = $("<span>").html(errorText).addClass("error").css({ "color": "red" });
                $(verifyObj).after(span).after(i);
                $(verifyObj).addClass("error_red");
            },
            addSuccessTips: function (verifyObj) {                          //添加正确提示
                $(verifyObj).nextAll("i").detach();
                $(verifyObj).nextAll("span").detach();
                $(verifyObj).removeClass("error_red success_green");

                var i = $("<i>").addClass("error icono-checkCircle").css({ "color": "green" });
                $(verifyObj).after(i);
                $(verifyObj).addClass("success_green");
            },
            errMsg: {                                                       //错误提示
                'inputUserName': "对不起，请输入用户名",
                'inputPassword': "对不起，请填写登录密码",
                'inputCode': "对不起，请输入验证码",
                "inputTrueName": "对不起，真实姓名不能为空",
                "errorAddress": "地址不正确",
                'passwordLength': "对不起，密码应在6-32位字符内",
                'inviladUserName': "对不起，您输入的帐号不存在",
                'accountNotMatch': "对不起，您的账号密码不匹配",
                'userLocked': "帐号锁定中，您暂时无法登录",
                'needEmailActive': "对不起，您的账户需要邮件激活",
                'serverdown': "服务器繁忙，请稍后再试",
                "inviladCode": "对不起，验证码输入错误",
                "selectEmailOrPhone": "对不起，邮箱和手机至少选择一种注册方式",
                "alreadyRegistered": "用户名已被注册",
                "passwordDifferent": "两次密码不一致"
            },
            verifySwitch: function (verifyType, verifyVal, verifyObj, verifyText, formObj) {//验证
                try {
                    var verifyPar = verifyType.split(":");
                    if (verifyPar.length > 1) {
                        verifyType = verifyPar[0];
                    }

                    if (verifyType === "require") {
                        if (verifyVal === "") {
                            if (!formObj.options.isJsAlert) {
                                formObj.options.addErrorTips(verifyText + "为必填项", verifyObj);
                            } else {
                                whir.msg.alertError(verifyText + "为必填项", verifyObj);
                            }

                            return false;
                        }
                    } else if (verifyVal != "") {
                        switch (verifyType) {
                            case "username":
                                if (!regular["chinesStart"].test(verifyVal)) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("用户名必须以中文或字母开头", verifyObj);
                                    } else {
                                        whir.msg.alertError("用户名必须以中文或字母开头");
                                    }
                                    return false;
                                }

                                if (GetByteLen(verifyVal) < 4) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("用户名太短，至少要4个字符", verifyObj);
                                    } else {
                                        whir.msg.alertError("密码必须包含英文字母、数字、符号");
                                    }
                                    return false;
                                }

                                if (GetByteLen(verifyVal) > 16) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("用户名太长，至少要16个字符", verifyObj);
                                    } else {
                                        whir.msg.alertError("用户名太长，至少要16个字符");
                                    }
                                    return false;
                                }


                                break;
                            case "password":
                                if (verifyVal.length < 6) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("密码太短，至少6个字符", verifyObj);
                                    } else {
                                        whir.msg.alertError("密码太短，至少6个字符");
                                    }
                                    return false;
                                }

                                if (verifyVal.length > 32) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("密码太长，最多32个字符", verifyObj);
                                    } else {
                                        whir.msg.alertError("密码太长，最多32个字符");
                                    }
                                    return false;
                                }

                                $(verifyObj).parents("table").find(".Degree").css({ "backgroundColor": "#cbcbcb", "color": "#ffffff" });
                                if (verifyVal.length >= 6) {
                                    if (/[a-zA-Z]+/.test(verifyVal) && /[0-9]+/.test(verifyVal) && /\W+\D+/.test(verifyVal)) {
                                        $(verifyObj).parents("table").find(".Degree:eq(2)").css("backgroundColor", "#fe0000");
                                    } else if (/[a-zA-Z]+/.test(verifyVal) || /[0-9]+/.test(verifyVal) || /\W+\D+/.test(verifyVal)) {
                                        if (/[a-zA-Z]+/.test(verifyVal) && /[0-9]+/.test(verifyVal)) {
                                            $(verifyObj).parents("table").find(".Degree:eq(1)").css("backgroundColor", "#fe0000");
                                        } else if (/\[a-zA-Z]+/.test(verifyVal) && /\W+\D+/.test(verifyVal)) {
                                            $(verifyObj).parents("table").find(".Degree:eq(1)").css("backgroundColor", "#fe0000");
                                        } else if (/[0-9]+/.test(verifyVal) && /\W+\D+/.test(verifyVal)) {
                                            $(verifyObj).parents("table").find(".Degree:eq(1)").css("backgroundColor", "#fe0000");
                                        } else {
                                            $(verifyObj).parents("table").find(".Degree:eq(0)").css("backgroundColor", "#fe0000");
                                        }
                                    }
                                } else {
                                    $(verifyObj).parents("table").find(".Degree:eq(0)").css("backgroundColor", "#fe0000");
                                }
                                break;
                            case "chinese":
                                if (!(regular["chinese"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips(verifyText + "必须为中文", verifyObj);
                                    } else {
                                        whir.msg.alertError(verifyText + "必须为中文");
                                    }

                                    return false;
                                }
                                break;
                            case "notchinese":
                                if (!regular["notchinese"].test(verifyVal)) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips(verifyText + "不能包含中文", verifyObj);
                                    } else {
                                        alert(verifyText + "不能包含中文");
                                    }

                                    return false;
                                }
                                break;
                            case "notcontainnumber": //不能包含数字
                                if (regular["containnumber"].test(verifyVal)) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("输入有误，" + verifyText + "不能包含数字，请重新输入", verifyObj);
                                    } else {
                                        whir.msg.alertError(verifyText + "不能包含数字");
                                    }

                                    return false;
                                }
                                break;
                            case "email":
                                if (!(regular["email"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("不是正确的电子邮箱地址", verifyObj);
                                    } else {
                                        alert("不是正确的电子邮箱地址");
                                    }

                                    return false;
                                }
                                break;
                            case "maxlength":   //字符串最大长度
                                if (verifyPar.length > 0 && verifyPar[1] != "") {
                                    if (verifyVal.length > parseInt(verifyPar[1])) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "长度不能大于" + verifyPar[1], verifyObj);
                                        } else {
                                            alert(verifyText + "长度不能大于" + verifyPar[1]);
                                        }

                                        return false;
                                    }
                                } else {
                                    console.log("字符串最大长度验证参数错误");
                                    return false;
                                }
                                break;
                            case "minlength":   //字符串最小长度
                                if (verifyPar.length > 0 && verifyPar[1] !== "") {
                                    if (verifyVal.length < parseInt(verifyPar[1])) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "长度不能小于" + verifyPar[1], verifyObj);
                                        } else {
                                            alert(verifyText + "长度不能小于" + verifyPar[1]);
                                        }

                                        return false;
                                    }
                                } else {
                                    console.log("字符串最小长度验证参数错误");
                                    return false;
                                }
                                break;
                            case "equal":   //两个元素值相等
                                if (verifyPar.length > 0 && verifyPar[1] != "") {
                                    if ($(formObj.fromElementObj).find("[name='" + verifyPar[1] + "']").val() !== verifyVal) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips("两次" + verifyText + "不相等", verifyObj);
                                        } else {
                                            alert("两次" + verifyText + "不相等");
                                        }

                                        return false;
                                    }
                                } else {
                                    console.log("两个元素值相等验证参数错误");
                                    return false;
                                }
                                break;
                            case "number":
                                if (!(regular["number"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("输入有误，" + verifyText + "必须为数字，请重新输入", verifyObj);
                                    } else {
                                        alert(verifyText + "必须为数字");
                                    }

                                    return false;
                                }
                                break;
                            case "maxnumber":
                                if (verifyPar.length > 0 && verifyPar[1] != "") {
                                    if (!(regular["number"].test(verifyVal))) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "必须为数字", verifyObj);
                                        } else {
                                            alert(verifyText + "必须为数字");
                                        }

                                        return false;
                                    } else if (!(regular["number"].test(verifyPar[1]))) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "参数必须为数字", verifyObj);
                                        } else {
                                            alert(verifyText + "参数必须为数字");
                                        }

                                        return false;
                                    } else if (parseInt(verifyVal) > parseInt(verifyPar[1])) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "不能大于" + verifyPar[1], verifyObj);
                                        } else {
                                            alert(verifyText + "不能大于" + verifyPar[1]);
                                        }

                                        return false;
                                    }
                                }
                                break;
                            case "minnumber":
                                if (verifyPar.length > 0 && verifyPar[1] != "") {
                                    if (!(regular["number"].test(verifyVal))) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "必须为数字", verifyObj);
                                        } else {
                                            alert(verifyText + "必须为数字");
                                        }

                                        return false;
                                    } else if (!(regular["number"].test(verifyPar[1]))) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "参数必须为数字", verifyObj);
                                        } else {
                                            alert(verifyText + "参数必须为数字");
                                        }

                                        return false;
                                    } else if (parseInt(verifyPar[1]) > parseInt(verifyVal)) {
                                        if (!formObj.options.isJsAlert) {
                                            formObj.options.addErrorTips(verifyText + "不能小于" + verifyPar[1], verifyObj);
                                        } else {
                                            alert(verifyText + "不能小于" + verifyPar[1]);
                                        }

                                        return false;
                                    }
                                }
                                break;
                            case "mobilephone":
                                if (!(regular["mobilePhone"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("不是正确的手机号", verifyObj);
                                    } else {
                                        alert("不是正确的手机号");
                                    }

                                    return false;
                                }
                                break;
                            case "phone":
                                if (!(regular["phone"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("不是正确的电话号码", verifyObj);
                                    } else {
                                        alert("不是正确的电话号码");
                                    }
                                    return false;
                                }
                                break;
                            case "zipcode":
                                if (!(regular["zipcode"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("不是正确的邮编号", verifyObj);
                                    } else {
                                        alert("不是正确的邮编号");
                                    }
                                    return false;
                                }
                                break;
                            case "identity":
                                if (!(regular["identity"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("不是正确的身份证号码", verifyObj);
                                    } else {
                                        alert("不是正确的身份证号码");
                                    }
                                    return false;
                                }
                                break;
                            case "qq":
                                if (!(regular["qq"].test(verifyVal))) {
                                    if (!formObj.options.isJsAlert) {
                                        formObj.options.addErrorTips("不是正确的QQ号码", verifyObj);
                                    } else {
                                        alert("不是正确的QQ号码");
                                    }
                                    return false;
                                }
                                break;
                            case "exists":
                                if (verifyVal != "" && verifyPar.length == 2 && verifyPar[1] != "") {

                                    var response = whir.ajax.post(verifyPar[1], {
                                        async: false,
                                        params: {
                                            field: $(verifyObj).attr("name"),
                                            existsVal: $(verifyObj).val()
                                        }
                                    });

                                    try {
                                        var errorText = "";
                                        var result = eval("(" + response.responseText + ")")[0];
                                        if (result.status.toLocaleLowerCase() != "true") {
                                            if (result.status.toLocaleLowerCase() == "false" && result.body == "exists") {
                                                errorText = "此" + verifyText + "已被注册，请重新输入";
                                            } else if (result.body == "notField") {
                                                errorText = "查询字段不存在";
                                            } else {
                                                errorText = item.body;
                                            }

                                            if (!formObj.options.isJsAlert) {
                                                formObj.options.addErrorTips(errorText, verifyObj);
                                            } else {
                                                whir.msg.alertError(errorText);
                                            }
                                            return false;
                                        }
                                    } catch (ex) {
                                        whir.msg.alertError(ex);
                                    }
                                }

                                break;
                        }
                    }

                    if (verifyVal !== "") {
                        formObj.options.addSuccessTips(verifyObj);
                    }
                    return true;
                } catch (ex) {
                    console.log(ex);
                    return false;
                }
            }
        };
        this.options = $.extend(this.defaults, ops);
    }

    //返回处理
    function submitReturn(res, formObj) {
        var result;
        try {
            result = eval("(" + res + ")");
        } catch (ex) {
            result = res[0];
        }

        if (res == true || res == "ok" || res == "true" ||
            (result.status != undefined &&
            (result.status.toLocaleLowerCase() === "ok" ||
            result.status.toLocaleLowerCase() === "true"))
            ) {
            formObj.options.success(res, formObj);  //返回成功结果处理方法
        } else {
            formObj.options.error(res, formObj);    //返回失败结果处理方法
        }

    }

    //绑定提交事件
    function submit(formObj, isXmlPost) {

        formObj.options.button.bind("click", function () {
            if (formObj.options.postUrl !== "") {
                var parm = {};
                var isSuccess = true;

                //获取提交数据
                $(formObj.fromElementObj).find(formObj.options.submitInput).each(function () {
                    var name = $(this).attr("name");
                    var val = $(this).val();
                    if (name !== "" && name != undefined && val != undefined) {

                        var input = $(this);
                        var verifyData = input.attr(verifyType);
                        var text = input.attr(verifyText);
                        if (verifyData != undefined && verifyData !== "") {
                            verifyData = verifyData.split(",");
                            for (var y in verifyData) {
                                if (verifyData.hasOwnProperty(y)) {
                                    isSuccess = formObj.options.verifySwitch(verifyData[y], val, input, text, formObj);
                                    if (isSuccess === false) {
                                        return false;
                                    }
                                }
                            }
                        }

                        parm[name] = val;
                    }
                });

                parm = formObj.options.handleParm(parm); //处理参数

                if (isSuccess && formObj.options.submitVerify(parm)) {
                    if (JSON.stringify(parm) === "{}") {
                        console.log("提交参数为空");
                    }

                    whir.loading.add("数据提交中...");
                    formObj.parm = parm;
                    if (!isXmlPost) {
                        $.post(formObj.options.postUrl, parm, function (res) {
                            try {
                                whir.loading.remove();
                                submitReturn(res, formObj);
                            } catch (ex) {
                                whir.msg.alertError(ex);
                            }
                        });
                    } else {
                        whir.ajax.post(formObj.options.postUrl, {
                            params: parm,
                            success: function (response) {
                                try {
                                    whir.loading.remove();
                                    submitReturn(response, formObj);
                                } catch (ex) {
                                    whir.msg.alertError(ex);
                                }
                            }, error: function (res) {
                                whir.loading.remove();
                                console.log(res.response);
                                alert("服务器错误");
                            }
                        });
                    }
                }
            }
        });
    }

    //绑定验证事件
    function bindVerify(formObj) {
        $(formObj.fromElementObj).find(formObj.options.submitInput).filter("[verify-type]").bind(formObj.options.verifyEvent, function () {
            var input = $(this);
            var val = $(this).val();
            var verifyData = $(this).attr(verifyType);
            var verifyTexts = $(this).attr(verifyText);
            if (verifyData != undefined && verifyData !== "") {
                verifyData = verifyData.split(",");
                for (var y in verifyData) {
                    if (verifyData.hasOwnProperty(y)) {
                        if (formObj.options.verifySwitch(verifyData[y], val, input, verifyTexts, formObj) == false) {
                            return false;
                        }
                    }
                }
            }
        });
    }

    function loadStyle() {
        $("head").append("<style>" + styleCss + "</style>");
    }

    $.fn.formSubmit = function (option) {
        var formObj = new FromObject(option, this);
        submit(formObj, false);
        bindVerify(formObj);
        loadStyle();
    };


    $.fn.formXmlSubmit = function (option) {
        var formObj = new FromObject(option, this);
        submit(formObj, true);
        bindVerify(formObj);
        loadStyle();
    };



})(jQuery);