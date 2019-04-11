
jQuery(document).ready(function(){
    jQuery("#item-media-user-head").bind('click', function() {
        if(jQuery(this).hasClass("tt")){
            jQuery(this).css({"transform":"rotate(360deg)"});
            jQuery(this).removeClass("tt");
            jQuery(this).parent().addClass("fab-opened");
        }else{
            jQuery(this).css({"transform":"rotate(-360deg)"});
            jQuery(this).addClass("tt");
        }
    });
});
var UserINFO;
if (null !== localStorage.getItem("UserINFO") && "" !== localStorage.getItem("UserINFO") && undefined !== localStorage.getItem("UserINFO")) {
    UserINFO = JSON.parse(localStorage.getItem("UserINFO"));
    jQuery("#block-title-user-name").text(UserINFO.userName);
    /*头像*/
    jQuery("#item-media-user-head").css({
        "background":"url(/baiyajin/file/ICON/Restaurant.png)",
        "background-size":"100% auto",
        "background-repeat":"no-repeat"
    });
}else{
    // - 打开登录屏
    jQuery("#login-screen").slideDown(1000);
    document.getElementById("login-screen-open").click();
}

/*验证登录*/
function checkLogin2() {
    localStorage.setItem("UserINFO", "");
    var check = true;

    if(!$("#userName").val()){
        toastBottom = app.toast.create({text: '请输入手机号',closeTimeout: 2000,});
        toastBottom.open();

        return check = false;
    }

    if(!$("#password").val()){
        toastBottom = app.toast.create({text: '请输入密码',closeTimeout: 2000,});
        toastBottom.open();
        return check = false;
    }
    var param = {
        "password": $("#password").val(),
        "userName": $("#userName").val(),
        "phone": $("#userName").val()
    };

    if(checkPhone($("#userName").val())){
        delete param.userName;
    }else{
        delete param.phone;
    }

    //校验通过，继续执行业务逻辑
    if (check) {
        $.ajax({
            url: "/SystemUserController/login",
            type: "post",
            async: false,
            data: JSON.stringify(param),
            timeout: 5000,
            headers: {'Content-Type': 'application/json;charset=utf-8'},
            handleAs: "json",
            dataType: "json",
            success: function (data) {
                if (data.user) {
                    //取时必须JSON.parse()拿,字符串的json就被转换成对象，就可以用对象的方式取值了
                    localStorage.setItem("UserINFO",JSON.stringify(data.user));
                    UserINFO = data.user;

                    /*名字*/
                    jQuery("#block-title-user-name").text(UserINFO.userName);
                    /*头像*/
                    jQuery("#item-media-user-head").css({
                        "background":"url(/static/image/cherry.jpg)",
                        "background-size":"100% auto",
                        "background-repeat":"no-repeat"
                    });
                    // - 关闭登录屏
                    jQuery("#login-screen").slideUp(1000);
                }
                toastBottom = app.toast.create({text: data.message,closeTimeout: 2000,});
                toastBottom.open();
            },
            error: function (data) {
                //异常处理；
                toastBottom = app.toast.create({text: data.message,closeTimeout: 2000,});
                toastBottom.open();
            }
        });
    }
}

function show_popover_backdrop() {
    $('.popover-backdrop.regedit_account').toggleClass('backdrop-in');
    $('.popover-backdrop.regedit_account').toggleClass('backdrop-out');
    $('#buttom_block').slideToggle();
}

function show_popover_backdrop2() {
    $('#buttom_f').css('z-index','1');
    $('#buttom_f .link.icon-only').click();
    $('.popover-backdrop.regedit_account').toggleClass('backdrop-in');
    $('.popover-backdrop.regedit_account').toggleClass('backdrop-out');
    $('.dialog.dialog-buttons-2').removeClass('modal-out');
    $('.dialog.dialog-buttons-2').addClass('modal-in');
    $('.dialog').css({'opacity':'1','display':'block'});
}

function show_popover_backdrop3() {
    $('.popover-backdrop.alert_password').toggleClass('backdrop-in');
    $('.popover-backdrop.alert_password').toggleClass('backdrop-out');
    $('.dialog.dialog-buttons-2').removeClass('modal-out');
    $('.dialog.dialog-buttons-2').addClass('modal-in');
    $('.dialog').css({'opacity':'1','display':'block','z-index':'13502'});
    $('.alert_password').css({'z-index':'13501'});
}

//申请成为管理员
function applyForBecomeAdministrator() {
    if (UserINFO !== null && UserINFO !== "" && UserINFO !== undefined) {
        if (UserINFO.userTypeID === "4") {
            toastBottom = app.toast.create({text: '演示账号不能申请',closeTimeout: 2000,});toastBottom.open();
        } else if (UserINFO.userTypeID === "6") {
            toastBottom = app.toast.create({text: '你已经最大了',closeTimeout: 2000,});toastBottom.open();
        }/* else if (UserINFO.userTypeID === "3") {
            toastBottom = app.toast.create({text: '你已经是管理员了',closeTimeout: 2000,});toastBottom.open();
        }*/else {
            mui.ajax('/baiyajin/CateringUserController/applyForBecomeAdministrator', {
                data: {"userName": UserINFO.userName},
                dataType: 'json',
                type: 'post',
                timeout: 5000,
                headers: {'Content-Type': 'application/json;charset=utf-8'},
                success: function (data) {
                    if (data === 0) {
                        toastBottom = app.toast.create({text: '发送请求成功,请等待审核',closeTimeout: 2000,});toastBottom.open();
                    }
                },
                error: function () {
                    toastBottom = app.toast.create({text: '网络异常',closeTimeout: 2000,});toastBottom.open();
                }
            });
        }
    }
}


/*退出登录*/
function logout() {
    /*localStorage为空就让登录*/
    if (null === localStorage.getItem("UserINFO") || "" === localStorage.getItem("UserINFO")) {
        toastBottom = app.toast.create({text: '已经注销了',closeTimeout: 2000,});
        toastBottom.open();
    } else {
        localStorage.removeItem("UserINFO");
        localStorage.clear("UserINFO");
        UserINFO = null;
        $("#password").val("");
        $("#userName").val("");
        toastBottom = app.toast.create({text: '注销成功',closeTimeout: 2000,});
        toastBottom.open();
    }
    // - 打开登录屏
    jQuery("#login-screen").slideDown(1000);
    jQuery("#block-title-user-name").text("");
    document.getElementById("login-screen-open").click();
}

/*注册*/
function regeditAccount(obj){
    if (null !== UserINFO && undefined !== UserINFO) {
        if($("#regedit_userName").val() === UserINFO.userName || $("#regedit_userPhone").val() === UserINFO.userPhone){
            toastBottom = app.toast.create({text: '你已经注册过了',closeTimeout: 2000,});
            toastBottom.open();
        }
    }else{
        if (!verifyValueWhetherEmpty($("#regedit_userName").val(), obj)){
            toastBottom = app.toast.create({text: '请输入用户名',closeTimeout: 2000,});toastBottom.open();
            return false;
        }
        if (!verifyValueWhetherEmpty($("#regedit_userPhone").val(), obj)){
            toastBottom = app.toast.create({text: '请输入手机号',closeTimeout: 2000,});toastBottom.open();
            return false;
        }
        if (!checkPhone($("#regedit_userPhone").val())){
            toastBottom = app.toast.create({text: '手机号格式不正确',closeTimeout: 2000,});toastBottom.open();
            return false;
        }
        if (!verifyValueWhetherEmpty($("#regedit_password").val(), obj)){
            toastBottom = app.toast.create({text: '请输入密码',closeTimeout: 2000,});toastBottom.open();
            return false;
        }

        var param = {
            "password": $("#regedit_password").val(),
            "name": $("#regedit_userName").val(),
            "phone": $("#regedit_userPhone").val()
        };

        $.ajax({
            url: "/SystemUserController/registerAccount",
            type: "post",
            async: false,
            data: JSON.stringify(param),
            timeout: 5000,
            headers: {'Content-Type': 'application/json;charset=utf-8'},
            handleAs: "json",
            dataType: "json",
            success: function (data) {
                $("#userName").val($("#regedit_userPhone").val());
                $("#password").val($("#regedit_password").val());
                toastBottom = app.toast.create({text: data.message,closeTimeout: 2000,});
                toastBottom.open();
            }, error: function (data) {
                toastBottom = app.toast.create({text: data.message,closeTimeout: 2000,});
                toastBottom.open();
            }
        });
    }
}

/*修改密码*/
function alterPassword() {
    if($("#theOriginalName").val() === "" || $("#theOriginalName").val() === null){
        toastBottom = app.toast.create({text: '原用户名不能为空',closeTimeout: 2000,});
        toastBottom.open();
    }else if($("#originalPassword").val() === "" || $("#theOriginalName").val() === null){
        toastBottom = app.toast.create({text: '原密码不能为空',closeTimeout: 2000,});
        toastBottom.open();
    }else if($("#newPassword").val() === "" || $("#theOriginalName").val() === null){
        toastBottom = app.toast.create({text: '新密码不能为空',closeTimeout: 2000,});
        toastBottom.open();
    }else{
        mui.ajax('/baiyajin/CateringUserController/alterPasswordInterface', {
            data: {
                "userName": $("#theOriginalName").val(),
                "password": $("#originalPassword").val(),
                "newPassword": $("#newPassword").val()
            },
            dataType: 'json',
            type: 'post',
            timeout: 5000,
            headers: {'Content-Type': 'application/json;charset=utf-8'},
            success: function (data) {
                if(data === 1){
                    toastBottom = app.toast.create({text: '名字或者密码错误',closeTimeout: 2000,});
                    toastBottom.open();
                }
                if(data === 0){
                    toastBottom = app.toast.create({text: '修改成功',closeTimeout: 2000,});
                    toastBottom.open();
                    cancel_dialog('.popover-backdrop.alert_password');
                }
            }, error: function () {
                toastBottom = app.toast.create({text: '网络异常',closeTimeout: 2000,});
                toastBottom.open();
            }
        });
    }
}

function cancel_dialog(ele) {
    $('#buttom_f').css('z-index','13001');
    $(ele).removeClass('backdrop-in');
    $(ele).addClass('backdrop-out');
    $('.dialog.dialog-buttons-2').removeClass('modal-in');
    $('.dialog.dialog-buttons-2').addClass('modal-out');
    $('.dialog').css({'opacity':'0','display':'none'});
}

function TestJWT() {
    $.ajax({
        url: "/baiyajin/RestaurantUserController/testJwt",
        type: "post",
        async: true,
        data: JSON.stringify({"token":UserINFO.token}),
        timeout: 5000,
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        handleAs: "json",
        dataType: "json",
        success: function (data) {
            if(data === 0){
                toastBottom = app.toast.create({text: "token还在有效期内",closeTimeout: 2000,});
                toastBottom.open();
            }else{
                toastBottom = app.toast.create({text: "token已过期",closeTimeout: 2000,});
                toastBottom.open();
            }
        },
        error: function (data) {
            //异常处理；
            toastBottom = app.toast.create({text: '网络不好',closeTimeout: 2000,});
            toastBottom.open();
        }
    });
}