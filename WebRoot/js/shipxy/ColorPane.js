(function () {
    //十六进制颜色值域RGB格式颜色值之间的相互转换
    //十六进制颜色值的正则表达式
    var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
    var init = function (pane, input) {
        var ColorHex = new Array('00', '33', '66', '99', 'CC', 'FF');
        var SpColorHex = new Array('FF0000', '00FF00', '0000FF', 'FFFF00', '00FFFF', 'FF00FF');
        var current = null;
        var colorTable = '';
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 6; j++) {
                colorTable = colorTable + '<tr height=15>';
                colorTable = colorTable + '<td width=15 style="background-color:#000000">';
                if (i == 0) {
                    colorTable = colorTable + '<td data-cmd="color" width=15 style="cursor:pointer;background-color:#' + ColorHex[j] + ColorHex[j] + ColorHex[j] + '">';
                }
                else {
                    colorTable = colorTable + '<td data-cmd="color" width=15 style="cursor:pointer;background-color:#' + SpColorHex[j] + '">';
                }
                colorTable = colorTable + '<td width=15 style="background-color:#000000">';
                for (k = 0; k < 3; k++) {
                    for (l = 0; l < 6; l++) {
                        colorTable = colorTable + '<td data-cmd="color" width=15 style="cursor:pointer;background-color:#' + ColorHex[k + i * 3] + ColorHex[l] + ColorHex[j] + '">';
                    }
                }
            }
        }
        colorTable = '<table cellspacing="0" cellpadding="0" style="border:1px #000000 solid;border-bottom:none;border-collapse: collapse;width:337px;" bordercolor="000000">'
        + '<tr height=20><td bgcolor="#eeeeee" style="font:12px tahoma;padding-left:2px;">'
        + '<span style="float:left;color:#999999;"></span>'
        + '<span style="float:right;padding-right:3px;cursor:pointer;"><font color="#666666">[<u data-cmd="close">关闭</u>]</font></span>'
        + '</td></table>'
        + '<table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="000000" style="cursor:pointer;">'
        + colorTable + '</table>';
        pane.innerHTML = colorTable;
        pane.style.left = (document.body.clientWidth - 340) / 2 + "px";
        pane.style.top = (document.body.clientHeight - 200) / 2 + "px";
        pane.onclick = function (e) {
            e = e || window.event;
            e = e.target || e.srcElement;
            var tag = e.tagName, cmd;
            if (tag == 'TD') {
                cmd = e.getAttribute('data-cmd');
                if (cmd == 'color') {
                    doclick(e.style.backgroundColor, pane, input);
                }
            } else if (tag == 'U') {
                cmd = e.getAttribute('data-cmd');
                if (cmd == 'close') {
                    colorPane.close(pane);
                }
            }
        }
    };
    var doclick = function (obj, pane, input) {
        var c = colorPane.colorHex(obj);
        if (c.charAt(0) == '#') {
            c = "0x" + c.substr(1);
        }
        input.value = c.toUpperCase();
        colorPane.close(pane);
    };
    //颜色板
    colorPane = {
        close: function (pane) {
            if (pane) {
                if (typeof pane == 'string') pane = document.getElementById(pane);
                pane.style.display = 'none';
            }
        },
        open: function (pane, input) {
            if (pane && input) {
                if (typeof pane == 'string') pane = document.getElementById(pane);
                if (typeof input == 'string') input = document.getElementById(input);
                init(pane, input);
                pane.style.display = '';
            }
        },
        /*RGB颜色转换为16进制*/
        colorHex: function (sRgb) {
            if (/^(rgb|RGB)/.test(sRgb)) {
                var aColor = sRgb.replace(/(?:\(|\)|rgb|RGB)*/g, "").split(",");
                var strHex = "#";
                for (var i = 0; i < aColor.length; i++) {
                    var hex = Number(aColor[i]).toString(16);
                    if (hex === "0") {
                        hex += hex;
                    }
                    strHex += hex;
                }
                if (strHex.length !== 7) {
                    strHex = sRgb;
                }
                return strHex;
            } else if (reg.test(sRgb)) {
                var aNum = sRgb.replace(/#/, "").split("");
                if (aNum.length === 6) {
                    return sRgb;
                } else if (aNum.length === 3) {
                    var numHex = "#";
                    for (var i = 0; i < aNum.length; i += 1) {
                        numHex += (aNum[i] + aNum[i]);
                    }
                    return numHex;
                }
            } else {
                return sRgb;
            }
        },
        /*16进制颜色转为RGB格式*/
        colorRgb: function (sColor) {
            sColor = sColor.toLowerCase();
            if (sColor && reg.test(sColor)) {
                if (sColor.length === 4) {
                    var sColorNew = "#";
                    for (var i = 1; i < 4; i += 1) {
                        sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
                    }
                    sColor = sColorNew;
                }
                //处理六位的颜色值
                var sColorChange = [];
                for (var i = 1; i < 7; i += 2) {
                    sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
                }
                return "RGB(" + sColorChange.join(",") + ")";
            } else {
                return sColor;
            }
        }
    }
})();