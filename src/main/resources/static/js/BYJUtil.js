//获取一组随机rgba颜色
function getRandmColor(opacity) {
    //获取0-1之间的随机数
    var r=Math.floor(Math.random()*256);
    var g=Math.floor(Math.random()*256);
    var b=Math.floor(Math.random()*256);
    opacity === undefined?(opacity =Math.random()):opacity;
    return "rgba("+r+','+g+','+b+','+opacity+")";
}



//昨天的时间
function getYesterdayFormatDate() {
    var day1 = new Date();
    day1.setTime(day1.getTime()-24*60*60*1000);
    // console.log("昨天的时间"+day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate());
    return day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate();
}

//今天的时间
function getTodayFormatDate() {
    var day2 = new Date();
    day2.setTime(day2.getTime());
    // console.log("今天的时间"+day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate());
    var m = ""+(day2.getMonth()+1),d=""+day2.getDate();
    if (m.length === 1) {
        m = "0" + (day2.getMonth()+1);
    }
    if (d.length === 1) {
        d = "0" + day2.getDate();
    }
    return day2.getFullYear()+"-" + m + "-" + d;
}
/*获取年月*/
function getMonthDate() {
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var m = ""+(day2.getMonth()+1);
    if (m.length === 1) {
        m = "0" + (day2.getMonth()+1);
    }
    return day2.getFullYear()+"-" + m;
}
/*获取年*/
function getYearDate() {
    var day2 = new Date();
    day2.setTime(day2.getTime());
    return day2.getFullYear();
}


//getMonday(type,dates) //type为字符串类型，有两种选择，"s"代表开始,"e"代表结束，dates为数字类型，不传或0代表本周，-1代表上周，1代表下周
//getMonday("s",1)  //得到下周一的yyyy-mm-dd格式日期
//getMonday("e",1)  //得到下周日的yyyy-mm-dd格式日期
function getMonday(type, dates) {
    var now = new Date();
    var nowTime = now.getTime();
    var day = now.getDay();
    var longTime = 24 * 60 * 60 * 1000;
    var n = longTime * 7 * (dates || 0);
    if (type == "s") {
        var dd = nowTime - (day - 1) * longTime + n;
    };
    if (type == "e") {
        var dd = nowTime + (7 - day) * longTime + n;
    };
    dd = new Date(dd);
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;
    var d = dd.getDate();
    m = m < 10 ? "0" + m: m;
    d = d < 10 ? "0" + d: d;
    var day = y + "-" + m + "-" + d;
    // console.log(day);
    return day;
}


// getMonth(type,months)  //type为字符串类型，有两种选择，"s"代表开始,"e"代表结束，months为数字类型，不传或0代表本月，-1代表上月，1代表下月
// getMonth("s",1)  //得到下月第一天的yyyy-mm-dd格式日期
// getMonth("e",1)  //得到下月最后一天的yyyy-mm-dd格式日期
function getMonth(type, months) {
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    if (Math.abs(months) > 12) {
        months = months % 12;
    };
    if (months != 0) {
        if (month + months > 12) {
            year++;
            month = (month + months) % 12;
        } else if (month + months < 1) {
            year--;
            month = 12 + month + months;
        } else {
            month = month + months;
        };
    };
    month = month < 10 ? "0" + month: month;
    var date = d.getDate();
    var firstday = year + "-" + month + "-" + "01";
    var lastday = "";
    if (month == "01" || month == "03" || month == "05" || month == "07" || month == "08" || month == "10" || month == "12") {
        lastday = year + "-" + month + "-" + 31;
    } else if (month == "02") {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
            lastday = year + "-" + month + "-" + 29;
        } else {
            lastday = year + "-" + month + "-" + 28;
        };
    } else {
        lastday = year + "-" + month + "-" + 30;
    };
    var day = "";
    if (type == "s") {
        day = firstday;
    } else {
        day = lastday;
    };
    // console.log(day);
    return day;
}

/*从数据库获取的时间格式很长,格式化成年月日格式*/
function FormatDate(strTime) {
    return new Date(strTime).Format("yyyy年MM月dd日 hh:mm:ss");
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()
        //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function FormatDateYMD(strTime) {
    return new Date(strTime).Format("yyyy年MM月dd日");
}

/*UUID*/
function generateUUID() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}


//校验值是否为空
function verifyValueWhetherEmpty(value,obj) {
    setElementStatusIsEnabled(obj);
    if(value !== "" && value !== null && value !== undefined && value.length > 0)return true;
    return false;
}



/*验证手机号是否合格*/
function checkPhone(phoneNumber) {
    var pattern = /^1[34578]\d{9}$/;
    if(phoneNumber === "1254085110"){
        return true;
    }else if(!pattern.test(phoneNumber)){
        return false;
    }else{
        return true;
    }
}

/*判断一个字符是否是数字*/
function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
    } else {
        return false;
    }
}





//压缩图片方法
function dealImage(path, callback){
    characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在压缩图片,请稍等...','long');
    var image = new Image();
    image.src = path;
    image.onload = function(){
        var square = 700;
        var canvas = document.createElement('canvas');

        var context = canvas.getContext('2d');
        context.clearRect(0, 0, square, square);
        var imageWidth;
        var imageHeight;
        if (this.width > this.height) {
            imageWidth = Math.round(square * this.width / this.height);
            imageHeight = square;
        } else {
            imageHeight = Math.round(square * this.height / this.width);
            imageWidth = square;
        }
        canvas.width = imageWidth;
        canvas.height = imageHeight;
        context.drawImage(this, 0, 0, imageWidth, imageHeight);
        var data = canvas.toDataURL('image/jpeg');
        //压缩完成执行回调
        callback(data);
    };
}

//预览图片
function clickPreviewImage(file,elementPreviewImageTag,elementFilenameTag,elementPreviewTag,elementClearTag,funimage,funimageflag,funmusic,uploadExcel){
    characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在读取文件,请稍等...','long');
    if (file.files && file.files[0]) {
        var reader = new FileReader();//每循环一次都要重新new一个FileReader实例
        reader.onload = function(evt){
            // 调用函数处理图片
            if($("#upload_input").attr("accept") === "image/*"){
                dealImage(evt.target.result,function(base){
                    characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在准备上传图片,请稍等...','long');
                    //直接将获取到的base64的字符串，放到一个image标签中就可看到测试后的压缩之后的样式图了
                    $(elementPreviewImageTag).attr("src",base);
                    $(elementPreviewImageTag).attr("imageName",file.value.split("\\")[2]);
                    $(elementPreviewImageTag).slideDown(1000);
                    $(elementFilenameTag).text(file.value.split("\\")[2].split(".")[0]);
                    $(elementPreviewTag).slideDown(1000);
                    $(elementClearTag).slideDown(1000);
                    if(funimageflag){
                        funimage();
                    }
                });
            }else if($("#upload_input").attr("accept") === "audio/*"){
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在准备上传音乐,请稍等...','long');
                $(elementPreviewImageTag).attr("src",reader.result.split(',')[1]);
                $(elementPreviewImageTag).attr("imageName",file.value.split("\\")[2]);
                // $(elementPreviewImageTag).slideDown(1000);//音乐就不用显示图片了
                $(elementFilenameTag).text(file.value.split("\\")[2].split(".")[0]);
                $(elementPreviewTag).slideDown(1000);
                $(elementClearTag).slideDown(1000);
                funmusic();
            }else if($("#upload_input").attr("accept") === ".XLS,.xlsx,.csv"){
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在准备上传Excel,请稍等...','long');
                $(elementPreviewImageTag).attr("src",reader.result.split(',')[1]);
                $(elementPreviewImageTag).attr("imageName",file.value.split("\\")[2]);
                // $(elementPreviewImageTag).slideDown(1000);//音乐就不用显示图片了
                $(elementFilenameTag).text(file.value.split("\\")[2].split(".")[0]);
                $(elementPreviewTag).slideDown(1000);
                $(elementClearTag).slideDown(1000);
                uploadExcel();
            }
        };
        reader.readAsDataURL(file.files[0]);
    }
}


// remove file
function clickRemoveImageFile(elementPreviewImageTag,elementPreviewTag,elementClearTag,elementFilenameTag){
    $(elementPreviewImageTag).slideUp(1000);
    $(elementPreviewTag).slideUp(1000);
    $(elementClearTag).slideUp(1000);
    $(elementPreviewImageTag).attr("src","");
    $(elementFilenameTag).text("");
}

/*显示上传图片组件*/
function show_upload_image_compter() {
    clickRemoveImageFile('#previewImage','.dropify-preview','.dropify-clear','.dropify-filename');
    characterTurnAsomersaultSpecialEffects(".mui-toast-message", "正在加载组件...",'long');
    // $("#previewimg").children().remove();
    $("#upload_input").attr("accept","image/*");
    $(".dropify-message").children("p:first").text("点击选择图片文件,大小仅支持4M以内");
    $(".dropify-message .file-icon").css({"background":"url(/baiyajin/static/image/Dfire/messageFeedbackImage.png)","background-size":"contain"});
    $(".dropify-infos-message").text("点击重新选择图片文件");

    $(".dropify-preview").css({"background":"none"});

    $("#previewimg").slideDown(1000);
    $("#previewImage").slideDown();
}
/*显示上传音乐组件*/
function show_upload_musci_compter() {
    clickRemoveImageFile('#previewImage','.dropify-preview','.dropify-clear','.dropify-filename');
    characterTurnAsomersaultSpecialEffects(".mui-toast-message", "正在加载组件...",'long');

    $("#upload_input").attr("accept","audio/*");
    $(".dropify-message").children("p:first").text("点击选择音乐文件,大小仅支持4M以内");
    $(".dropify-message .file-icon").css({"background":"url(/baiyajin/static/image/baiya/musicFormat.png)","background-size":"contain"});
    $(".dropify-infos-message").text("点击重新选择音乐文件");

    $(".dropify-preview").css({
        "background":"url(/baiyajin/static/image/baiya/musicFormat.png)",
        "background-size":"contain",
        "background-position":"50%",
        "background-repeat":"no-repeat"
    });

    $("#previewimg").slideDown(1000);
}

/*点击上传按钮,先判断是否有没有登录,没登录就让登录,登录了就调用上传窗口上传文件*/
function click_whetherLogin_Or_show_upload_compter(flag) {
    if(whetherLogin()){
        if(undefined === userINFO.userPhone || "" === userINFO.userPhone){
            /*完善手机号*/
            // $("#perfectPhone_box").load("/baiyajin/templates/Util/perfectPhone.html");
            $("#perfectPhone_box").slideDown(1000);
            $("#perfectPhone_login_form").slideDown(1000);
        }else{
            flag === "music"?show_upload_musci_compter():show_upload_image_compter();
        }
    }else{
        /*加载登录窗口*/
        // $("#loginWindows_box").load("/baiyajin/templates/Util/login.html");
        $("#loginWindows_box").slideDown(1000);
        $("#loginWindows_box #login_form button").css({"width":"50%"});
        $("#loginWindows_box #regedit_form").css({"display":"none"});
        $("#loginWindows_box #login_form").css({"display":"block","transform":"translate(-50%,-50%)"});
        /*延时解决出来时动画乱*/
        // $("#login_form button").css({"width":"50%"});
        // $("#login_form").fadeIn(1000);
       /* setTimeout(function () {
            var login_form = document.getElementById("login_form");
            login_form.style.display = "block";
            $("#login_form button").css({"width":"50%"});
        }, 500);*/
    }
}

function characterTurnAsomersaultSpecialEffects(ele, content,duration) {
    /*需要引入<script src="/baiyajin/static/js/jquery.beattext.js"></script><script src="/baiyajin/static/js/easying.js"></script>*/
    /*
     *  参数详解:
     * upTime          上移的时间
     * downTime        下落的时间
     * beatHeight      上移高度
     * isAuth          是否自动
     * isRotate        是否旋转
    */
    mui.toast(content, {
        duration: duration,  /*duration：持续显示时间，short：2000ms（默认）, long(3500ms)*/
        type: 'div' /* 是否使用h5绘制的对话框 */
    });
    // $(ele).beatText({isAuth:false,isRotate:true});//isAuth:false就是鼠标放上去才动
    var r = Math.floor(Math.random()*7+1);
    if(r === 1){
        $(ele).beatText({isAuth:true,isRotate:true});
    }else if(r === 2){
        $(ele).beatText({isAuth: true, beatHeight: "1em", isRotate: true, upTime: 100, downTime: 100});
    }else if(r === 3){
        $(ele).beatText({isAuth:true,beatHeight:"3em",isRotate:true, upTime: 100, downTime: 100});
    }else if(r === 4){
        $(ele).beatText({isAuth:true,upTime:700,downTime:700,beatHeight:"3em",isRotate:true});
    }else if(r === 5){
        $(ele).beatText({isAuth:true,beatHeight:"3em",isRotate:true});
    }else if(r === 6){
        $(ele).beatText({isAuth:true,beatHeight:"1em",isRotate:false,upTime:100,downTime:100});
    }else{
        $(ele).beatText({isAuth:true,upTime:700,downTime:700,beatHeight:"3em",isRotate:true});
    }
}


/*上传文件,如果没登录主弹出登录注册，如果没完善手机号就弹出完善手机号*/
function uploadImage(){
    mui.ajax('/baiyajin/BaiyaImageController/uploadImage', {
        data: {
            "userPhone": userINFO.userPhone,
            "userName": userINFO.userName,
            "imageName": $("#previewImage").attr("imageName"),//有格式的后缀
            // "imageName": $(".dropify-filename").text(),//无格式后缀
            "format": $("#previewImage").attr("imageName").substring($("#previewImage").attr("imageName").lastIndexOf("."),$("#previewImage").attr("imageName").length),
            "base64": $("#previewImage").attr("src"),
            "imgPath": "file:/home/"+userINFO.userPhone+"/",
        },
        dataType: 'json',
        // async:false,
        type: 'post',
        timeout: 5000,
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        success: function (data) {
            if(data === 0){
                $("#playList2Buttom").css({"background":"url(/baiyajin/file/"+userINFO.userPhone+"/"+$('#previewImage').attr("imageName")+")","background-size": "100% 100%","display":"none"});
                $("#switchBackgroundDiv").css({"background":"url(/baiyajin/file/"+userINFO.userPhone+"/"+$('#previewImage').attr("imageName")+")","background-size": "cover","display":"none"});
                $("#previewimg").slideUp(1000);
                $("#playList2Buttom").slideDown(1000);
                $("#switchBackgroundDiv").slideDown(1000);
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '图片上传成功','long');
            }else if(data === 1){
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '已经存在了,换个文件试试','long');
            }else if(data === 2){
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '上传图片失败','long');
            }else{
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '文件名只能是中文、数字、下划线','long');
            }
        }
    });
}

/*上传音乐*/
function uploadMusic(){
    characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在上传音乐,请稍等...',8000);
    mui.ajax('/baiyajin/BaiyaMusicController/uploadMusic', {
        data: {
            "userPhone": userINFO.userPhone,
            "userName": userINFO.userName,
            "imageName": $("#previewImage").attr("imageName"),
            "format": $("#previewImage").attr("imageName").substring($("#previewImage").attr("imageName").lastIndexOf("."),$("#previewImage").attr("imageName").length),
            "base64": $("#previewImage").attr("src")
        },
        dataType: 'json',
        // async:false,
        type: 'post',
        timeout: 5000,
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        success: function (data) {
            if(data === 0){
                $("#audio").attr("src","/baiyajin/file/"+userINFO.userPhone+"/"+$('#previewImage').attr("imageName"));
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '上传成功,即将播放','long');
            }else if(data === 1){
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '已经存在了,换个文件试试','long');
            }else if(data === 2){
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '保存音乐的过程中出错','long');
            }else{
                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '文件名只能是中文、数字、下划线','long');
            }
        },error: function () {
            characterTurnAsomersaultSpecialEffects(".mui-toast-message", '网络异常，请刷新重试','long');
        }
    });
}


/*上传交易的excel*/
function uploadExcel() {
    /*扫码的*/
    if($("#previewimg").attr("methodOfExecution") === "popup_uploading_excel_scan_QR_module"){
        /*点击了确认*/
        if ("3,6".indexOf(userINFO.userTypeID) !== -1) {
            characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在上传Excel,请稍等...','long');
            mui.ajax('/baiyajin/MerchantMessageCenter/uploadScanQRCodeTransactionDataToSaveToDatabase', {
                data: {
                    "fileName": $("#previewImage").attr("imageName"),
                    "format": $("#previewImage").attr("imageName").substring($("#previewImage").attr("imageName").lastIndexOf("."),$("#previewImage").attr("imageName").length),
                    "excelBase64": $("#previewImage").attr("src")
                },
                dataType: 'json',
                // async:false,
                type: 'post',
                timeout: 5000,
                headers: {'Content-Type': 'application/json;charset=utf-8'},
                success: function (data) {
                    if(data === 7){
                        characterTurnAsomersaultSpecialEffects(".mui-toast-message", '上传成功','long');
                    }else if(data === 8){
                        characterTurnAsomersaultSpecialEffects(".mui-toast-message", '格式不对','long');
                    }else if(data === 0){
                        characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第一列必须为:商户号','long');
                    }else if(data === 1){
                        characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第二列必须为:终端号','long');
                    }else if(data === 2){
                        characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第三列必须为:交易金额','long');
                    }else{
                        characterTurnAsomersaultSpecialEffects(".mui-toast-message", '文件名只能是中文、数字、下划线','long');
                    }
                }
            });
        } else {
            mui.toast("你没有权限...")
        }
        /*终端交易*/
    }else if($("#previewimg").attr("methodOfExecution") === "popup_uploading_excel_module"){
        mui.prompt('日期格式为：2018-7-23', '请按格式输入', '请输入日期', ['确认', '取消'], function (month) {
            /*点击了确认*/
            if (month.index === 0 && "3,6".indexOf(userINFO.userTypeID) !== -1) {
                var times = month.value.split("-");
                if (isNumber(times[0]) && isNumber(times[1]) && times[1] < 13 && times[1] > 0 && isNumber(times[2]) && times[2] && 32 && times[2] > 0) {
                    if (times[1].length === 1) {times[1] = "0" + times[1];}
                    if (times[2].length === 1) {times[2] = "0" + times[2];}
                    characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在上传Excel,请稍等...','long');
                    /*console.log(times[0] + "-------" + times[1] + "---------" + times[2]);
                    console.log($("#previewImage").attr("imageName"));
                    console.log($("#previewImage").attr("imageName").substring($("#previewImage").attr("imageName").lastIndexOf("."),$("#previewImage").attr("imageName").length));
                    console.log($("#previewImage").attr("src"));*/
                    mui.ajax('/baiyajin/MerchantMessageCenter/uploadTransactionDataToSaveToDatabase', {
                        data: {
                            "year": times[0],
                            "month": times[1],
                            "day": times[2],
                            "fileName": $("#previewImage").attr("imageName"),
                            "format": $("#previewImage").attr("imageName").substring($("#previewImage").attr("imageName").lastIndexOf("."),$("#previewImage").attr("imageName").length),
                            "excelBase64": $("#previewImage").attr("src")
                        },
                        dataType: 'json',
                        // async:false,
                        type: 'post',
                        timeout: 5000,
                        headers: {'Content-Type': 'application/json;charset=utf-8'},
                        success: function (data) {
                            if(data === 7){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '上传成功','long');
                            }else if(data === 8){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '格式不对','long');
                            }else if(data === 0){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第一列必须为:商户编号','long');
                            }else if(data === 1){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第二列必须为:本日笔数','long');
                            }else if(data === 2){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第三列必须为:本日交易额','long');
                            }else if(data === 3){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第四列必须为:本月笔数','long');
                            }else if(data === 4){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第五列必须为:本月交易额','long');
                            }else if(data === 5){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第五列必须为:本年笔数','long');
                            }else if(data === 6){
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '表头第五列必须为:本年交易额','long');
                            }else{
                                characterTurnAsomersaultSpecialEffects(".mui-toast-message", '网络异常，请刷新重试','long');
                            }
                        }
                    });

                } else {
                    mui.alert("你输入的日期格式不对")
                }
            } else {
                mui.toast("你没有权限...")
            }
        });
    }
}

/*检查是否登录的方法*/
var userINFO;
function whetherLogin(){
    if (null !== localStorage.getItem("userINFO") && "" !== localStorage.getItem("userINFO") && undefined !== localStorage.getItem("userINFO")) {
        userINFO = JSON.parse(localStorage.getItem("userINFO"));
        return true;
    }else{
        return false;
    }
}

//启用
function setElementStatusIsEnabled(obj) {
    $(obj).attr("disabled", false);
}

//禁用
function setElementStatusIsDisabled(obj) {
    $(obj).attr("disabled", true);
}

/*丢一个带加号的字符串，返回一个以引号加逗号隔开的字符串*/
function passStringAndCommaPartitionReturn(separator, obj) {
    var t = obj.split(separator);
    var tt = "";
    for (var i = 0; i < t.length; i++) {
        if (i === t.length - 1) {
            tt += "'" + t[i] + "'";
        } else {
            tt += "'" + t[i] + "'" + ",";
        }
    }
    return tt;
}

/*处理去除掉文件.后缀名*/
function remove_File_Suffix(fileName){
    // fileName.substring(fileName.lastIndexOf("."),fileName.length);//取格式
    return fileName.substring(0,fileName.lastIndexOf("."));//取文件名
}

/*数字格式化,从右往左每隔三位加逗号(利用递归)*/
function formatNumber(str) {
    if(str.length <= 3){
        return str;
    } else {
        return formatNumber(str.substr(0,str.length-3))+','+str.substr(str.length-3);
    }
}

//对字符串进行加密
function compileStr(code){
    var c=String.fromCharCode(code.charCodeAt(0)+code.length);
    for(var i=1;i<code.length;i++)
    {
        c+=String.fromCharCode(code.charCodeAt(i)+code.charCodeAt(i-1));
    }
    return escape(c);
}

//字符串进行解密
function uncompileStr(code){
    code=unescape(code);
    var c=String.fromCharCode(code.charCodeAt(0)-code.length);
    for(var i=1;i<code.length;i++)
    {
        c+=String.fromCharCode(code.charCodeAt(i)-c.charCodeAt(i-1));
    }
    return c;
}

/*js创建a标签下载文件*/
function createAelementDownloadFile(fileName,url,time) {
    characterTurnAsomersaultSpecialEffects(".mui-toast-message", '正在下载',time);
    const a = document.createElement('a'); // 创建a标签
    a.setAttribute('download', fileName);// download属性
    a.setAttribute('href', url);// href链接
    a.click();// 自执行点击事件
}

/*点击元素控制其它或者自己隐藏显示*/
function up_And_down_animation(PointThatClass,WhoControlsClass,time) {
    if($(PointThatClass).hasClass("already")){
        $(PointThatClass).removeClass("already");
        $(WhoControlsClass).slideUp(time);
    }else{
        $(PointThatClass).addClass("already");
        $(WhoControlsClass).slideDown(time);
    }
}

/*广播音乐*/
function broadcastMusic() {
    if($(".menu_icon1").hasClass("I_grabbed_it")){
        /*获取音乐进度跟图片路径*/
        var src = $("#audio").attr('src');
        var playStatus = false;
        if(!document.getElementById("audio").paused){
            playStatus = true;
        }
        var m = {
            "audio":src,
            "playStatus":playStatus,
            "musicName":src.substring(src.lastIndexOf("/") + 1, src.length),
            "currentTime":document.getElementById("audio").currentTime,
            "QM":userINFO === null ?whoWasRobbed:userINFO.userName
        };
        /*如果点的是抢,广播让其它人的增加  already_be_robbed   class*/
        websocket.send(JSON.stringify(m));
    }
}
/*广播背景图片*/
function broadcastImage() {
    if($(".menu_icon1").hasClass("I_grabbed_it")){
        var m = {
            "playList2Buttom":document.getElementById("playList2Buttom").style.background,
            "switchBackgroundDiv":document.getElementById("switchBackgroundDiv").style.background,
            "QM":userINFO === null ?whoWasRobbed:userINFO.userName
        };
        /*如果点的是抢,广播让其它人的增加  already_be_robbed   class*/
        websocket.send(JSON.stringify(m));
    }
}


function getRandomName() {
    var name = ["神秘人","小可爱","嘎嘣脆","么么哒","佩奇"];
    return name[Math.floor(Math.random()*name.length)];
}


/*重新写的上传音乐组件*/
function show_baiya_upload_Music_module(){
    if(whetherLogin()){
        if(undefined === userINFO.userPhone || "" === userINFO.userPhone){
            /*完善手机号*/
            // $("#perfectPhone_box").load("/baiyajin/templates/Util/perfectPhone.html");
            $("#perfectPhone_box").slideDown(1000);
            $("#perfectPhone_login_form").slideDown(1000);
        }else{
            if($("#baiya_upload_Music").children().length === 0){
                $("#baiya_upload_Music").load("/baiyajin/templates/Util/baiya_upload_Music.html");
            }
            $("#baiya_upload_Music").slideDown(1000);
        }
    }else{
        $("#loginWindows_box").slideDown(1000);
        $("#loginWindows_box #login_form button").css({"width":"50%"});
        $("#loginWindows_box #regedit_form").css({"display":"none"});
        $("#loginWindows_box #login_form").css({"display":"block","transform":"translate(-50%,-50%)"});
    }
}


//点击查看反馈图片
function onclickAlterPreviewImage(obj) {

    if(obj.src !== undefined){
        $("#onclickAlterPreviewImage").css({
            "background":"url("+obj.src+")",
            "background-repeat":"no-repeat",
            "background-position":"50%",
            "background-size":"contain"
        });
    }else{
        $("#onclickAlterPreviewImage").css({
            "background":$(obj).css("background"),
            "background-repeat":"no-repeat",
            "background-position":"50%",
            "background-size":"contain"
        });
    }
    $("#onclickAlterPreviewImage").slideDown(2000);


}

$("#onclickAlterPreviewImage").on('click', function () {
    $("#onclickAlterPreviewImage").slideToggle(2000);
});

/*传入id或者class来改变元素的颜色*/
function alterElementColor(ele,borderAllColor,borderTopColor,borderBottomColor,borderLeftColor,borderRightColor,fontColor,backColor){
    var color = getRandmColor();
    if(borderAllColor !== ""){
        $(ele).css({"border":"1px dashed "+color});
    }else{
        if(borderTopColor !== ""){
            $(ele).css({"border-top":"1px dashed "+color});
        }
        if(borderBottomColor !== ""){
            $(ele).css({"border-bottom":"1px dashed "+color});
        }
        if(borderLeftColor !== ""){
            $(ele).css({"border-left":"1px dashed "+color});
        }
        if(borderRightColor !== ""){
            $(ele).css({"border-right":"1px dashed "+color});
        }
    }

    if(fontColor !== ""){
        $(ele).css({"color":color});
    }
    if(backColor !== ""){
        $(ele).css({"background":color});
    }
}

function dynamic_stripe_progress_bar(ele){
    /*点击了就设置它的样式*/
    var color = getRandmColor();
    $(ele).css({
        "animation":"progress-bar-stripes 2s linear infinite",
        "background":"linear-gradient(-45deg,"+color+" 25%,transparent 25%,transparent 50%,"+color+" 50%,"+color+" 75%,transparent 75%,transparent)",
        "background-size":"30px 30px",
    });
}


/*校验终端号长度*/
function verifyTerminalNumberLength(terminalNumber) {
    //终端号长度不对，应该是8位
    var terminalNumberREG = /^[0-9]{8}$/;
    if (!terminalNumberREG.test(terminalNumber)) {
        return false;
    } else {
        return true;
    }
}


/*校验商户号长度*/
function verifyMerchantNumberLength(merchantIdentificationNumber) {
    //商户号长度不对，应该是8位,且只能是数字
    var merchantIdentificationNumberREG = /^[0-9a-zA-Z]{15}$/;
    if (!merchantIdentificationNumberREG.test(merchantIdentificationNumber)) {
        return false;
    } else {
        return true;
    }
}

/*校验激活码长度*/
function verifyactivationCodeLength(activationCode) {
    //激活码长度不对，应该是16位
    var activationCodeREG = /^[0-9a-zA-Z]{16}$/;
    if (activationCodeREG.test(activationCode)) {
        return true;
    } else {
        return false;
    }
}


// Js获取请求地址的参数
function getRequestAddressParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = decodeURI(window.location.search).substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

/*Vue与framework7冲突自定义的提示*/
function baiya_toast(obj,toast,dration){
    obj.toast_text_flag = true;
    obj.toast_text = toast;
    setTimeout(() => {
        obj.toast_text_flag = false;
        obj.toast_text = '';
    }, dration);
}