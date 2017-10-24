/// <reference path="../shipxyAPI.js" />
/// <reference path="../shipxyMap.js" />
/// <reference path="ColorPane.js" />
/// <reference path="ZeroClipboard.js" />
(function () {
    var preLink = null, //上一次选择的示例标题项
    win = null, //窗口
    clip = null; //剪贴板
    //展开/闭合列表
    var toggleList = function (box, list) {
        if (!box || !list) return;
        var a = list.getElementsByTagName('A')[0];
        var isOn = a.className == 'on';
        a.className = isOn ? '' : 'on'; //+-图标切换
        var other = null, len = box.children.length;
        //关闭其他展开的列表
        for (var i = 0; i < len; i++) {
            other = box.children[i];
            if (other != list) {//非本身列表
                if (other.getAttribute('data-toggle') == 'on') {
                    arguments.callee(box, other); //递归
                }
            }
        }
        var ul = list.getElementsByTagName('UL')[0];
        if (ul) {
            ul.className = isOn ? '' : 'on'; //展开/闭合列表
        }
        list.setAttribute('data-toggle', isOn ? 'off' : 'on'); //更改展开状态
    };
    //根据代码标号生成对应示例的标签页面
    var getContent = function (code) {
        var title = '', content = '<div class="content">';
        switch (code) {
            case '1':
                title = '设置以下属性，用来初始化地图';
                content += '<p><label for="page1_lat">中心纬度：</label><input type="text" id="page1_lat" value="32"/></p>' +
                '<p><label for="page1_lng">中心经度：</label><input type="text" id="page1_lng" value="122"/></p>' +
                '<p><label for="page1_zoom">缩放级别：</label><input type="text" id="page1_zoom" value="5"/></p>' +
                '<p>地图类型：<input type="radio" class="radio" id="page1_cmap" name="page1_mapTypeRadio" value="cmap" checked="checked"/><label for="page1_cmap" class="radio-label">海图</label>' +
                '<input type="radio" class="radio" id="page1_googlemap" name="page1_mapTypeRadio" value="googlemap"/><label for="page1_googlemap" class="radio-label">地图</label>' +
                '<input type="radio" class="radio" id="page1_googlesatellite" name="page1_mapTypeRadio" value="googlesatellite"/><label for="page1_googlesatellite" class="radio-label">卫星图</label></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.showMap();return false;">显示地图</a></div>';
                break;
            case '2':
                var center = map.getCenter();
                title = '当前地图中心点坐标';
                content += '<p>中心纬度：<span id="page2_lat">' + center.lat + '</span></p>' +
                '<p>中心经度：<span id="page2_lng">' + center.lng + '</span></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getCenter();return false;">获取</a></div>';
                break;
            case '3':
                title = '当前地图的缩放级别';
                content += '<p>缩放级别：<span id="page3_zoom">' + map.getZoom() + '</span></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getZoom();return false;">获取</a></div>';
                break;
            case '4':
                title = '当前地图类型';
                content += '<p>地图类型：<span id="page4_mapType">' + shipxyMap.MapType.getName(map.getMapType()) + '</span></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getMapType();return false;">获取</a></div>';
                break;
            case '5':
                var size = map.getSize();
                title = '当前地图视窗的像素大小';
                content += '<p>高度为：<span id="page5_height">' + size.height + '</span></p>' +
                '<p>宽度为：<span id="page5_width">' + size.width + '</span></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getSize();return false;">获取</a></div>';
                break;
            case '6':
                var latLngBounds = map.getLatLngBounds();
                var southWest = latLngBounds.southWest;
                var northEast = latLngBounds.northEast;
                title = '当前地图视窗西南东北角经纬度坐标';
                content += '<p>西南角经度：<span id="page6_southWest_lng">' + southWest.lng + '</span></p>' +
                '<p>西南角纬度：<span id="page6_southWest_lat">' + southWest.lat + '</span></p>' +
                '<p>东北角经度：<span id="page6_northEast_lng">' + northEast.lng + '</span></p>' +
                '<p>东北角纬度：<span id="page6_northEast_lat">' + northEast.lat + '</span></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getLatLngBounds();return false;">获取</a></div>';
                break;
            case '7':
                title = '缩放地图';
                content += '<div class="btnLink"><a href="javascript:void(0)" onclick="action.zoomIn();return false;">放大地图</a><a href="javascript:void(0)" onclick="action.zoomOut();return false;">缩小地图</a></div>';
                break;
            case '8':
                title = '设置地图中心点经纬度坐标和缩放级别';
                content += '<p><label for="page8_lat">中心纬度：</label><input type="text" id="page8_lat" value="30.5643"/></p>' +
                '<p><label for="page8_lng">中心经度：</label><input type="text" id="page8_lng" value="121.5234"/></p>' +
                '<p><label for="page8_zoom">缩放级别：</label><input type="text" id="page8_zoom" value="8"/></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.setMap();return false;">设置地图</a></div>';
                break;
            case '9':
                title = '设置地图类型';
                content += '<p>地图类型：<input type="radio" class="radio" id="page9_cmap" name="page9_mapTypeRadio" value="cmap"/><label for="page9_cmap" class="radio-label">海图</label>' +
                '<input type="radio" class="radio" id="page9_googlemap" name="page9_mapTypeRadio" value="googlemap" checked="checked"/><label for="page9_googlemap" class="radio-label">地图</label>' +
                '<input type="radio" class="radio" id="page9_googlesatellite" name="page9_mapTypeRadio" value="googlesatellite"/><label for="page9_googlesatellite" class="radio-label">卫星图</label></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.setMapType();return false;">设置地图</a></div>';
                break;
            case '10':
                action.initClearAll();
                title = '清除地图叠加层';
                content += '<div class="btnLink"><a href="javascript:void(0)" onclick="action.removeAllOverlay();return false;">清除所有</a></div>';
                break;
            case '11':
                title = '添加、删除与定位船舶';
                content += '<p><label for="page11_shipId">船舶叠加物ID:</label><input type="text" id="page11_shipId" value="MyShip1"/><em>如果不填，系统会自动创建一个唯一的标识号</em></p>' +
                '<fieldset><legend>设置边线样式</legend>' +
                '<table><tr>' +
                '<td><label for="page11_lineColor">颜色：</label></td><td><input type="text" id="page11_lineColor" value="0x000000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page11_lineColor\');"/></td>' +
                '<td><label for="page11_lineAlpha">透明度：</label></td><td><input type="text" id="page11_lineAlpha" value="0.8"/></td>' +
                '<td><label for="page11_lineThickness">粗细：</label></td><td><input type="text" id="page11_lineThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend>设置填充样式</legend>' +
                '<table><tr>' +
                '<td><label for="page11_fillColor">颜色：</label></td><td><input type="text" id="page11_fillColor" value="0x0000FF" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page11_fillColor\');"/></td>' +
                '<td><label for="page11_fillAlpha">透明度：</label></td><td><input type="text" id="page11_fillAlpha" value="0.6"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend>设置标签风格</legend>' +
                '<p>设置字体风格</p>' +
                '<div class="fontDiv">' +
                '<table><tr>' +
                '<td><label for="page11_font">名称：</label></td><td><input type="text" id="page11_font" value="Verdana" /></td>' +
                '<td><label for="page11_fontSize">大小：</label></td><td><input type="text" id="page11_fontSize" value="14"/></td>' +
                '<td><label for="page11_fontColor">颜色：</label></td><td><input type="text" id="page11_fontColor" value="0xFF33CC" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page11_fontColor\');"/></td>' +
                '</tr><tr>' +
                '<td colspan="2"><input type="checkbox" id="page11_bold" class="radio" checked="checked"/><label for="page11_bold" class="radio-label">粗体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page11_italic" class="radio" checked="checked"/><label for="page11_italic" class="radio-label">斜体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page11_underline" class="radio" checked="checked"/><label for="page11_underline" class="radio-label">下划线</label></td>' +
                '</tr></table>' +
                '</div>' +
                '<fieldset><legend><input type="checkbox" id="page11_border" class="radio" checked="checked"/><label for="page11_border" class="radio-label">显示边框</label></legend>' +
                '<table><tr>' +
                '<td><label for="page11_borderColor">颜色：</label></td><td><input type="text" id="page11_borderColor" value="0xFF0000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page11_borderColor\');"/></td>' +
                '<td><label for="page11_borderAlpha">透明度：</label></td><td><input type="text" id="page11_borderAlpha" value="0.8"/></td>' +
                '<td><label for="page11_borderThickness">粗细：</label></td><td><input type="text" id="page11_borderThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page11_back" class="radio" checked="checked"/><label for="page11_back" class="radio-label">显示背景</label></legend>' +
                '<table><tr>' +
                '<td><label for="page11_backColor">颜色：</label></td><td><input type="text" id="page11_backColor" value="0xFFCCFF" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page11_backColor\');"/></td>' +
                '<td><label for="page11_backAlpha">透明度：</label></td><td><input type="text" id="page11_backAlpha" value="0.6"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<label for="page11_labelZoomlevels">标签显示级别：</label><input type="text" id="page11_labelZoomlevels" value="[5,12]" /><em>格式如输入框示例，在*到*级显示标签</em>' +
                '</fieldset>' +
                '<fieldset><legend>其他</legend>' +
                '<table><tr>' +
                '<td><label for="page11_zoomlevels">显示级别：</label></td><td colspan="2"><input type="text" id="page11_zoomlevels" value="[5,12]" /><em>格式如输入框示例，在*到*级显示船舶</em></td>' +
                '</tr><tr>' +
                '<td><input type="checkbox" id="page11_label" class="radio" checked="checked"/><label for="page11_label" class="radio-label">显示标签</label></td>' +
                '<td><input type="checkbox" id="page11_rect" class="radio" checked="checked"/><label for="page11_rect" class="radio-label">显示选择框</label></td>' +
                '<td><input type="checkbox" id="page11_miniTrack" class="radio" checked="checked"/><label for="page11_miniTrack" class="radio-label">显示自带三分钟轨迹</label></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.addShip();return false;">添加</a><a href="javascript:void(0)" onclick="action.removeShip();return false;">删除</a><a href="javascript:void(0)" onclick="action.locateShip();return false;">定位</a></div>';
                break;
            case '12':
                title = '添加、删除与定位批量船舶';
                content += '<div class="btnLink"><a href="javascript:void(0)" onclick="action.addShipArray();return false;">添加</a><a href="javascript:void(0)" onclick="action.removeShipArray();return false;">删除</a><a href="javascript:void(0)" onclick="action.locateShipArray();return false;">定位</a></div>';
                break;
            case '13':
                title = '添加、删除与定位轨迹';
                content += '<p><label for="page13_trackId">轨迹叠加物ID:</label><input type="text" id="page13_trackId" value="MyTrack1"/><em>如果不填，系统会自动创建一个唯一的标识号</em></p>' +
                '<fieldset><legend>设置轨迹线样式</legend>' +
                '<table><tr>' +
                '<td><label for="page13_lineColor">颜色：</label></td><td><input type="text" id="page13_lineColor" value="0x0000ff" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page13_lineColor\');"/></td>' +
                '<td><label for="page13_lineAlpha">透明度：</label></td><td><input type="text" id="page13_lineAlpha" value="0.8"/></td>' +
                '<td><label for="page13_lineThickness">粗细：</label></td><td><input type="text" id="page13_lineThickness" value="3"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend>设置轨迹点样式</legend>' +
                '<table><tr>' +
                '<td><label for="page13_radius">半径大小：</label></td><td><input type="text" id="page13_radius" value="8"/></td>' +
                '<td><label for="page13_pointFillColor">填充颜色：</label></td><td><input type="text" id="page13_pointFillColor" value="0x6666ff" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page13_pointFillColor\');"/></td>' +
                '<td><label for="page13_pointFillAlpha">填充透明度：</label></td><td><input type="text" id="page13_pointFillAlpha" value="0.6"/></td>' +
                '</tr><tr>' +
                '<td><label for="page13_pointLineColor">边线颜色：</label></td><td><input type="text" id="page13_pointLineColor" value="0xff0000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page13_pointLineColor\');"/></td>' +
                '<td><label for="page13_pointLineThickness">边线粗细：</label></td><td><input type="text" id="page13_pointLineThickness" value="2"/></td>' +
                '<td><label for="page13_pointLineAlpha">边线透明度：</label></td><td><input type="text" id="page13_pointLineAlpha" value="0.7"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend>设置标签风格</legend>' +
                '<p>设置字体风格</p>' +
                '<div class="fontDiv">' +
                '<table><tr>' +
                '<td><label for="page13_font">名称：</label></td><td><input type="text" id="page13_font" value="Arial" /></td>' +
                '<td><label for="page13_fontSize">大小：</label></td><td><input type="text" id="page13_fontSize" value="14"/></td>' +
                '<td><label for="page13_fontColor">颜色：</label></td><td><input type="text" id="page13_fontColor" value="0x0066A7" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page13_fontColor\');"/></td>' +
                '</tr><tr>' +
                '<td colspan="2"><input type="checkbox" id="page13_bold" class="radio" checked="checked"/><label for="page13_bold" class="radio-label">粗体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page13_italic" class="radio" checked="checked"/><label for="page13_italic" class="radio-label">斜体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page13_underline" class="radio" checked="checked"/><label for="page13_underline" class="radio-label">下划线</label></td>' +
                '</tr></table>' +
                '</div>' +
                '<fieldset><legend><input type="checkbox" id="page13_border" class="radio" checked="checked"/><label for="page13_border" class="radio-label">显示边框</label></legend>' +
                '<table><tr>' +
                '<td><label for="page13_borderColor">颜色：</label></td><td><input type="text" id="page13_borderColor" value="0xFF0000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page13_borderColor\');"/></td>' +
                '<td><label for="page13_borderAlpha">透明度：</label></td><td><input type="text" id="page13_borderAlpha" value="0.8"/></td>' +
                '<td><label for="page13_borderThickness">粗细：</label></td><td><input type="text" id="page13_borderThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page13_back" class="radio" checked="checked"/><label for="page13_back" class="radio-label">显示背景</label></legend>' +
                '<table><tr>' +
                '<td><label for="page13_backColor">颜色：</label></td><td><input type="text" id="page13_backColor" value="0xBBBBBB" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page13_backColor\');"/></td>' +
                '<td><label for="page13_backAlpha">透明度：</label></td><td><input type="text" id="page13_backAlpha" value="0.8"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<label for="page13_labelZoomlevels">标签显示级别：</label><input type="text" id="page13_labelZoomlevels" value="[4,16]" /><em>格式如输入框示例，在*到*级显示标签</em>' +
                '</fieldset>' +
                '<fieldset><legend>其他</legend>' +
                '<table><tr>' +
                '<td><label for="page13_zoomlevels">显示级别：</label></td><td colspan="2"><input type="text" id="page13_zoomlevels" value="[4, 16]" /><em>格式如输入框示例，在*到*级显示船舶</em></td>' +
                '</tr><tr>' +
                '<td><input type="checkbox" id="page13_label" class="radio" checked="checked"/><label for="page13_label" class="radio-label">显示标签</label></td>' +
                '<td><input type="checkbox" id="page13_vacuate" class="radio" checked="checked"/><label for="page13_vacuate" class="radio-label">是否抽稀</label></td>' +
                '<td><label for="page13_distance">抽稀间距：</label><input type="text" id="page13_distance" value="100"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.addTrack();return false;">添加</a><a href="javascript:void(0)" onclick="action.removeTrack();return false;">删除</a><a href="javascript:void(0)" onclick="action.locateTrack();return false;">定位</a></div>';
                break;
            case '14':
                action.addShipArray();
                action.addTracks();
                map.setCenter(new shipxyMap.LatLng(32, 122), 2);
                title = '删除所有船舶、所有轨迹';
                content += '<div class="btnLink"><a href="javascript:void(0)" onclick="action.removeShipType();return false;">删除船舶</a><a href="javascript:void(0)" onclick="action.removeTrackType();return false;">删除轨迹</a></div>';
                break;
            case '15':
                title = '模糊查询船舶';
                content += '<p><label for="page15_kw">关键字：</label><input type="text" id="page15_kw" value=""/></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.searchShip();return false;">查 询</a></div>' +
                '<div id="page15_result"></div>';
                break;
            case '16':
                title = '获取指定船舶的数据';
                content += '<p><label for="page16_shipId">船舶ID：</label><input type="text" id="page16_shipId" value="412371410"/></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getShipData();return false;">获 取</a></div>' +
                '<div id="page16_result"></div>';
                break;
            case '17':
                title = '获取批量船舶的数据';
                content += '<p><label for="page17_shipIds">船舶ID列表：(输入格式如下面输入框示例，逗号分隔)</label></p>' +
                '<div class="area">' +
                '<textarea id="page17_shipIds" cols="52" rows="3">412521000,412719000,412371410,372355000,413778705,412353450,413352251</textarea>' +
                '<div class="btnLink getData"><a href="javascript:void(0)" onclick="action.getShipsData();return false;">获 取</a></div>' +
                '</div>' +
                '<table class="results" id="page17_results" style="display:none;"><tr><td><ul id="page17_list"></ul></td><td><div id="page17_result"></div></td></tr></table>';
                break;
            case '18':
                title = '获取指定区域的船舶数据';
                content += '<p><label for="page18_region">区域范围：(最少3个点，输入格式如下面输入框示例，一对大括号代表一个点，多个点用逗号分隔)</label></p>' +
                '<div class="area">' +
                '<textarea id="page18_region" cols="52" rows="3">[{ lat: 31.275, lng: 121.736 }, { lat:31.275, lng: 121.79 }, { lat: 31.245, lng: 121.79 }, { lat: 31.243, lng: 121.742}]</textarea>' +
                '<div class="btnLink getData"><a href="javascript:void(0)" onclick="action.getRegionData();return false;">获 取</a></div>' +
                '</div>' +
                '<table class="results" id="page18_results" style="display:none;"><tr><td><ul id="page18_list"></ul></td><td><div id="page18_result"></div></td></tr></table>';
                break;
            case '19':
                title = '查询轨迹数据';
                //初始化日期与时间选择器
                var nDate = new Date();
                var sDateTSpan = nDate.setDate(nDate.getDate() - 7); //7天前
                var startDate = new Date(sDateTSpan);
                content += '<p><label for="page19_shipId">船 舶 ID：</label><input type="text" id="page19_shipId" value="412473010"/></p>' +
                '<p><label for="page19_startTime">起始时间：</label><input type="text" id="page19_startTime" value="' + view.formatDateToString(startDate).substr(0, 10) + ' 00:00:00' + '"/></p>' +
                '<p><label for="page19_endTime">结束时间：</label><input type="text" id="page19_endTime" value="' + view.formatDateToString(new Date()) + '"/></p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.searchTrack();return false;">查 询</a></div>' +
                '<div class="trackMsg"><span id="trackMsg"></span></div>';
                break;
            case '20':
                title = '添加、删除点';
                content += '<p><label for="page20_overlayId">点叠加物ID:</label><input type="text" id="page20_overlayId" value="MyMarker1"/><em>如果不填，会自动创建一个唯一的标识号</em></p>' +
                '<fieldset><legend>点</legend>' +
                '<table><tr>' +
                '<td><label for="page20_zIndex">显示层级：</label></td><td><select id="page20_zIndex"><option value="1">地图层</option><option value="2">船舶层</option><option value="3">轨迹层</option><option value="4" selected="selected">叠加物层</option></select></td>' +
                '<td><input type="checkbox" id="page20_isEditable" class="radio"/><label for="page20_isEditable" class="radio-label">可编辑</label></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page20_imageUrl">图片URL：</label></td><td colspan="2"><input type="text" id="page20_imageUrl" value="js/img/mark.png" /></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page20_imageX">图片X偏移：</label><input type="text" id="page20_imageX" value="0" /></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page20_imageY">图片Y偏移：</label><input type="text" id="page20_imageY" value="0"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page20_zoomlevels">显示级别：</label></td><td colspan="2"><input type="text" id="page20_zoomlevels" value="[5,12]" /><em>格式如输入框示例，在*到*级显示船舶</em></td>' +
                '</tr>' +
                '</table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page20_label" class="radio" checked="checked"/><label for="page20_label" class="radio-label">显示标签</label></legend>' +
                '<p>设置标签</p>' +
                '<div class="fontDiv">' +
                '<table>' +
                '<tr>' +
                '<td><input type="radio" name="page20Radio" id="page20_text" class="radio" checked="true"/><label for="page20_text">文本标签：</label></td><td><input type="text" id="page20_textValue" value="自定义点"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><input type="radio" name="page20Radio" id="page20_htmlText" class="radio" /><label for="page20_htmlText">HTML标签：</label></td><td><input type="text" id="page20_htmlTextValue"  value="<font size=\'15\' color=\'#0000FF\' ><u><a href=\'http://www.shipxy.com\' target=\'_blank \'>www.shipxy.com</a></u></font>"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page20_labelZoomlevels">标签显示级别：</label></td><td><input type="text" id="page20_labelZoomlevels" value="[5,12]"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page20_textX">标签左上角距离点的距离X：</label><input type="text" id="page20_textX" value="0" /></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page20_textY">标签左上角距离点的距离Y：</label><input type="text" id="page20_textY" value="0"/></td>' +
                '</tr>' +
                '</table>' +
                '</div>' +
                '<p>设置字体风格</p>' +
                '<div class="fontDiv">' +
                '<table><tr>' +
                '<td><label for="page20_font">名称：</label></td><td><input type="text" id="page20_font" value="Verdana" /></td>' +
                '<td><label for="page20_fontSize">大小：</label></td><td><input type="text" id="page20_fontSize" value="14"/></td>' +
                '<td><label for="page20_fontColor">颜色：</label></td><td><input type="text" id="page20_fontColor" value="0xFF33CC" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page20_fontColor\');"/></td>' +
                '</tr><tr>' +
                '<td colspan="2"><input type="checkbox" id="page20_bold" class="radio" checked="checked"/><label for="page20_bold" class="radio-label">粗体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page20_italic" class="radio" checked="checked"/><label for="page20_italic" class="radio-label">斜体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page20_underline" class="radio" checked="checked"/><label for="page20_underline" class="radio-label">下划线</label></td>' +
                '</tr></table>' +
                '</div>' +
                '<fieldset><legend><input type="checkbox" id="page20_border" class="radio" checked="checked"/><label for="page20_border" class="radio-label">显示边框</label></legend>' +
                '<table><tr>' +
                '<td><label for="page20_borderColor">颜色：</label></td><td><input type="text" id="page20_borderColor" value="0xFF0000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page20_borderColor\');"/></td>' +
                '<td><label for="page20_borderAlpha">透明度：</label></td><td><input type="text" id="page20_borderAlpha" value="0.8"/></td>' +
                '<td><label for="page20_borderThickness">粗细：</label></td><td><input type="text" id="page20_borderThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page20_back" class="radio" checked="checked"/><label for="page20_back" class="radio-label">显示背景</label></legend>' +
                '<table><tr>' +
                '<td><label for="page20_backColor">颜色：</label></td><td><input type="text" id="page20_backColor" value="0xFFCCFF" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page20_backColor\');"/></td>' +
                '<td><label for="page20_backAlpha">透明度：</label></td><td><input type="text" id="page20_backAlpha" value="0.6"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '</fieldset>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.addMarker();return false;">添加</a><a href="javascript:void(0)" onclick="action.removeMarker();return false;">删除</a><a href="javascript:void(0)" onclick="action.locateMarker();return false;">定位</a></div>';
                break;
            case '21':
                title = '添加、删除线';
                content += '<p><label for="page21_overlayId">线叠加物ID:</label><input type="text" id="page21_overlayId" value="MyPolyline1"/><em>如果不填，会自动创建一个唯一的标识号</em></p>' +
                '<fieldset><legend>线</legend>' +
                '<table><tr>' +
                '<td><label for="page21_zIndex">显示层级：</label></td><td><select id="page21_zIndex"><option value="1">地图层</option><option value="2">船舶层</option><option value="3">轨迹层</option><option value="4" selected="selected">叠加物层</option></select></td>' +
                '<td><input type="checkbox" id="page21_isEditable" class="radio"/><label for="page21_isEditable" class="radio-label">可编辑</label></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page21_zoomlevels">显示级别：</label></td><td colspan="2"><input type="text" id="page21_zoomlevels" value="[5,12]" /><em>格式如输入框示例，在*到*级显示船舶</em></td>' +
                '</tr>' +
                '</table>' +
                '</fieldset>' +
                '<fieldset><legend>设置线样式</legend>' +
                '<table><tr>' +
                '<td><label for="page21_lineColor">颜色：</label></td><td><input type="text" id="page21_lineColor" value="0XFF3399" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page21_lineColor\');"/></td>' +
                '<td><label for="page21_lineAlpha">透明度：</label></td><td><input type="text" id="page21_lineAlpha" value="0.8"/></td>' +
                '<td><label for="page21_lineThickness">粗细：</label></td><td><input type="text" id="page21_lineThickness" value="5"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page21_label" class="radio" checked="checked"/><label for="page21_label" class="radio-label">显示标签</label></legend>' +
                '<p>设置标签</p>' +
                '<div class="fontDiv">' +
                '<table>' +
                '<tr>' +
                '<td><input type="radio" name="page21Radio" id="page21_text" class="radio" checked="true"/><label for="page21_text">文本标签：</label></td><td><input type="text" id="page21_textValue" value="自定义线"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><input type="radio" name="page21Radio" id="page21_htmlText" class="radio" /><label for="page21_htmlText">HTML标签：</label></td><td><input type="text" id="page21_htmlTextValue"  value="<font size=\'15\' color=\'#0000FF\' ><u><a href=\'http://www.shipxy.com\' target=\'_blank \'>www.shipxy.com</a></u></font>"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page21_labelZoomlevels">标签显示级别：</label></td><td><input type="text" id="page21_labelZoomlevels" value="[5,12]"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page21_textX">左上角距离线的中心点的距离X：</label><input type="text" id="page21_textX" value="0" /></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page21_textY">左上角距离线的中心点的距离Y：</label><input type="text" id="page21_textY" value="0"/></td>' +
                '</tr>' +
                '</table>' +
                '</div>' +
                '<p>设置字体风格</p>' +
                '<div class="fontDiv">' +
                '<table><tr>' +
                '<td><label for="page21_font">名称：</label></td><td><input type="text" id="page21_font" value="Verdana" /></td>' +
                '<td><label for="page21_fontSize">大小：</label></td><td><input type="text" id="page21_fontSize" value="14"/></td>' +
                '<td><label for="page21_fontColor">颜色：</label></td><td><input type="text" id="page21_fontColor" value="0xFF33CC" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page21_fontColor\');"/></td>' +
                '</tr><tr>' +
                '<td colspan="2"><input type="checkbox" id="page21_bold" class="radio" checked="checked"/><label for="page21_bold" class="radio-label">粗体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page21_italic" class="radio" checked="checked"/><label for="page21_italic" class="radio-label">斜体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page21_underline" class="radio" checked="checked"/><label for="page21_underline" class="radio-label">下划线</label></td>' +
                '</tr></table>' +
                '</div>' +
                '<fieldset><legend><input type="checkbox" id="page21_border" class="radio" checked="checked"/><label for="page21_border" class="radio-label">显示边框</label></legend>' +
                '<table><tr>' +
                '<td><label for="page21_borderColor">颜色：</label></td><td><input type="text" id="page21_borderColor" value="0xFF0000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page21_borderColor\');"/></td>' +
                '<td><label for="page21_borderAlpha">透明度：</label></td><td><input type="text" id="page21_borderAlpha" value="0.8"/></td>' +
                '<td><label for="page21_borderThickness">粗细：</label></td><td><input type="text" id="page21_borderThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page21_back" class="radio" checked="checked"/><label for="page21_back" class="radio-label">显示背景</label></legend>' +
                '<table><tr>' +
                '<td><label for="page21_backColor">颜色：</label></td><td><input type="text" id="page21_backColor" value="0xFFCCFF" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page21_backColor\');"/></td>' +
                '<td><label for="page21_backAlpha">透明度：</label></td><td><input type="text" id="page21_backAlpha" value="0.6"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '</fieldset>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.addPolyline();return false;">添加</a><a href="javascript:void(0)" onclick="action.removePolyline();return false;">删除</a><a href="javascript:void(0)" onclick="action.locatePolyline();return false;">定位</a></div>';
                break;
            case '22':
                title = '添加、删除面';
                content += '<p><label for="page22_overlayId">面叠加物ID:</label><input type="text" id="page22_overlayId" value="MyPolygon1"/><em>如果不填，会自动创建一个唯一的标识号</em></p>' +
                '<fieldset><legend>面</legend>' +
                '<table><tr>' +
                '<td><label for="page22_zIndex">显示层级：</label></td><td><select id="page22_zIndex"><option value="1">地图层</option><option value="2">船舶层</option><option value="3">轨迹层</option><option value="4" selected="selected">叠加物层</option></select></td>' +
                '<td><input type="checkbox" id="page22_isEditable" class="radio"/><label for="page22_isEditable" class="radio-label">可编辑</label></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page22_zoomlevels">显示级别：</label></td><td colspan="2"><input type="text" id="page22_zoomlevels" value="[5,12]" /><em>格式如输入框示例，在*到*级显示船舶</em></td>' +
                '</tr>' +
                '</table>' +
                '</fieldset>' +
                '<fieldset><legend>设置边线样式</legend>' +
                '<table><tr>' +
                '<td><label for="page22_lineColor">颜色：</label></td><td><input type="text" id="page22_lineColor" value="0x000000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page22_lineColor\');"/></td>' +
                '<td><label for="page22_lineAlpha">透明度：</label></td><td><input type="text" id="page22_lineAlpha" value="0.8"/></td>' +
                '<td><label for="page22_lineThickness">粗细：</label></td><td><input type="text" id="page22_lineThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend>设置填充样式</legend>' +
                '<table><tr>' +
                '<td><label for="page22_fillColor">颜色：</label></td><td><input type="text" id="page22_fillColor" value="0x0000FF" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page22_fillColor\');"/></td>' +
                '<td><label for="page22_fillAlpha">透明度：</label></td><td><input type="text" id="page22_fillAlpha" value="0.6"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page22_label" class="radio" checked="checked"/><label for="page22_label" class="radio-label">显示标签</label></legend>' +
                '<p>设置标签</p>' +
                '<div class="fontDiv">' +
                '<table>' +
                '<tr>' +
                '<td><input type="radio" name="page22Radio" id="page22_text" class="radio" checked="true"/><label for="page22_text">文本标签：</label></td><td><input type="text" id="page22_textValue" value="自定义面"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><input type="radio" name="page22Radio" id="page22_htmlText" class="radio" /><label for="page22_htmlText">HTML标签：</label></td><td><input type="text" id="page22_htmlTextValue"  value="<font size=\'15\' color=\'#0000FF\' ><u><a href=\'http://www.shipxy.com\' target=\'_blank \'>www.shipxy.com</a></u></font>"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td><label for="page22_labelZoomlevels">标签显示级别：</label></td><td><input type="text" id="page22_labelZoomlevels" value="[5,12]"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page22_textX">左上角距离面的中心点的距离X：</label><input type="text" id="page22_textX" value="0" /></td>' +
                '</tr>' +
                '<tr>' +
                '<td colspan="2"><label for="page22_textY">左上角距离面的中心点的距离Y：</label><input type="text" id="page22_textY" value="0"/></td>' +
                '</tr>' +
                '</table>' +
                '</div>' +
                '<p>设置字体风格</p>' +
                '<div class="fontDiv">' +
                '<table><tr>' +
                '<td><label for="page22_font">名称：</label></td><td><input type="text" id="page22_font" value="Verdana" /></td>' +
                '<td><label for="page22_fontSize">大小：</label></td><td><input type="text" id="page22_fontSize" value="14"/></td>' +
                '<td><label for="page22_fontColor">颜色：</label></td><td><input type="text" id="page22_fontColor" value="0xFF33CC" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page22_fontColor\');"/></td>' +
                '</tr><tr>' +
                '<td colspan="2"><input type="checkbox" id="page22_bold" class="radio" checked="checked"/><label for="page22_bold" class="radio-label">粗体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page22_italic" class="radio" checked="checked"/><label for="page22_italic" class="radio-label">斜体</label></td>' +
                '<td colspan="2"><input type="checkbox" id="page22_underline" class="radio" checked="checked"/><label for="page22_underline" class="radio-label">下划线</label></td>' +
                '</tr></table>' +
                '</div>' +
                '<fieldset><legend><input type="checkbox" id="page22_border" class="radio" checked="checked"/><label for="page22_border" class="radio-label">显示边框</label></legend>' +
                '<table><tr>' +
                '<td><label for="page22_borderColor">颜色：</label></td><td><input type="text" id="page22_borderColor" value="0xFF0000" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page22_borderColor\');"/></td>' +
                '<td><label for="page22_borderAlpha">透明度：</label></td><td><input type="text" id="page22_borderAlpha" value="0.8"/></td>' +
                '<td><label for="page22_borderThickness">粗细：</label></td><td><input type="text" id="page22_borderThickness" value="1"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '<fieldset><legend><input type="checkbox" id="page22_back" class="radio" checked="checked"/><label for="page22_back" class="radio-label">显示背景</label></legend>' +
                '<table><tr>' +
                '<td><label for="page22_backColor">颜色：</label></td><td><input type="text" id="page22_backColor" value="0xFFCCFF" readonly="readonly" onclick="colorPane.open(\'colorPane\',\'page22_backColor\');"/></td>' +
                '<td><label for="page22_backAlpha">透明度：</label></td><td><input type="text" id="page22_backAlpha" value="0.6"/></td>' +
                '</tr></table>' +
                '</fieldset>' +
                '</fieldset>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.addPolygon();return false;">添加</a><a href="javascript:void(0)" onclick="action.removePolygon();return false;">删除</a><a href="javascript:void(0)" onclick="action.locatePolygon();return false;">定位</a></div>';
                break;
            case '23':
                title = '获取我的船队列表';
                content += '<p>只有设置了我的船队，才有船队列表。</p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getFleetList();return false;">获 取</a></div>' +
                '<table class="results" id="page23_results" style="display:none;"><tr><td><ul id="page23_list"></ul></td><td><div id="page23_result"></div></td></tr></table>';
                break;
            case '24':
                title += '获取船队船舶数据';
                content += '<p>只有设置了我的船队，才有船队船舶数据。</p>' +
                '<div class="btnLink"><a href="javascript:void(0)" onclick="action.getFleetShipData();return false;">获 取</a></div>' +
                '<table class="results" id="page24_results" style="display:none;"><tr><td><ul id="page24_list"></ul></td><td><div id="page24_result"></div></td></tr></table>';
                break;
        }
        content += '</div>';
        return [title, content];
    };

    action = {
        //显示地图
        showMap: function () {
            if (map) {
                view.G('mapDiv').innerHTML = '';
            }
            var radios = document.getElementsByName('page1_mapTypeRadio');
            var mapType = '', i = 0, len = radios.length, radio = null;
            for (; i < len; i++) {
                radio = radios[i];
                if (radio.checked) {
                    mapType = radio.value; //取得选择项值
                }
            }
            var mapOptions = new shipxyMap.MapOptions();
            mapOptions.center = new shipxyMap.LatLng(parseFloat(view.G('page1_lat').value), parseFloat(view.G('page1_lng').value));
            mapOptions.zoom = parseFloat(view.G('page1_zoom').value);
            mapOptions.mapType = mapType;
            //mapDiv是一个DIV容器的id，用于放置flash地图组件
            map = new shipxyMap.Map('mapDiv', mapOptions); //创建地图实例
        },
        //获取地图当前中心点
        getCenter: function () {
            var latLng = map.getCenter();
            view.G('page2_lat').innerHTML = latLng.lat;
            view.G('page2_lng').innerHTML = latLng.lng;
        },
        //获取地图当前缩放级别
        getZoom: function () {
            view.G('page3_zoom').innerHTML = map.getZoom();
        },
        //获取当前地图类型
        getMapType: function () {
            view.G('page4_mapType').innerHTML = shipxyMap.MapType.getName(map.getMapType());
        },
        //获取地图视窗像素大小
        getSize: function () {
            var size = map.getSize();
            view.G('page5_height').innerHTML = size.height;
            view.G('page5_width').innerHTML = size.width;
        },
        //获取地图视窗经纬度范围大小
        getLatLngBounds: function () {
            var latLngBounds = map.getLatLngBounds();
            var southWest = latLngBounds.southWest;
            var northEast = latLngBounds.northEast;
            view.G('page6_southWest_lng').innerHTML = southWest.lng;
            view.G('page6_southWest_lat').innerHTML = southWest.lat;
            view.G('page6_northEast_lng').innerHTML = northEast.lng;
            view.G('page6_northEast_lat').innerHTML = northEast.lat;
        },
        //放大一级
        zoomIn: function () {
            map.zoomIn();
        },
        //缩小一级
        zoomOut: function () {
            map.zoomOut();
        },
        //设置地图：中心点、缩放级别
        setMap: function () {
            map.setCenter(new shipxyMap.LatLng(parseFloat(view.G('page8_lat').value), parseFloat(view.G('page8_lng').value)), parseFloat(view.G('page8_zoom').value));
        },
        //设置地图类型
        setMapType: function () {
            var radios = document.getElementsByName('page9_mapTypeRadio');
            var mapType = '', i = 0, len = radios.length, radio = null;
            for (; i < len; i++) {
                radio = radios[i];
                if (radio.checked) {
                    mapType = radio.value;
                }
            }
            map.setMapType(mapType);
        },
        //删除所有叠加物
        removeAllOverlay: function () {
            map.removeAllOverlay();
        },
        //初始化清除所有叠加物示例
        initClearAll: function () {
            /*****船舶1号*****/
            /**船舶1号样式**/
            var option = new shipxyMap.ShipOptions();
            option.fillStyle.color = 0x00ff66; //填充颜色
            option.isShowLabel = true; //是否显示label
            option.isShowMiniTrack = true//船舶自带三分钟轨迹
            option.isSelected = true; //船舶框选
            /**船舶1号数据**/
            var data = new shipxyAPI.Ship();
            data.shipId = "1"
            data.name = 'shipName1';
            data.callsign = 'BIPE';
            data.imo = '0';
            data.shipType = '货船';
            data.navStatus = '在航（主机推动）';
            data.length = 135;
            data.beam = 21;
            data.draught = 7.3;
            data.lat = 32.5;
            data.lng = 121.6;
            data.heading = 60;
            data.course = 80;
            data.speed = 13;
            data.dest = 'BEH LUN';
            data.eta = '6.26 21:00';
            data.lastTime = 1340674688;
            var shipOverlay = new shipxyMap.Ship("1", data, option);
            /*****船舶2号*****/
            /**船舶2号样式**/
            var option2 = new shipxyMap.ShipOptions();
            option2.fillStyle.color = 0x00ff00; //填充颜色
            option2.isShowLabel = true; //是否显示label
            option2.isShowMiniTrack = true//船舶自带三分钟轨迹
            option2.isSelected = false; //船舶框选
            /**船舶2号数据**/
            var data2 = new shipxyAPI.Ship();
            data2.shipId = "2"
            data2.name = 'shipName2';
            data2.callsign = 'BIPE';
            data2.imo = '0';
            data2.shipType = '货船';
            data2.navStatus = '在航（主机推动）';
            data2.length = 135;
            data2.beam = 21;
            data2.draught = 7.3;
            data2.lat = 31.1;
            data2.lng = 122.6;
            data2.heading = 60;
            data2.course = 80;
            data2.speed = 13;
            data2.dest = 'BEH LUN';
            data2.eta = '6.26 21:00';
            data2.lastTime = 1340674688;
            var shipOverlay2 = new shipxyMap.Ship("2", data2, option2);
            /**添加到地图上显示**/
            map.addOverlay(shipOverlay);
            map.addOverlay(shipOverlay2);
            //添加两条轨迹
            action.addTracks();
        },
        //添加船舶
        addShip: function () {
            map.removeAllOverlay();
            /*****船舶显示样式*****/
            var option = new shipxyMap.ShipOptions();
            /*边框样式*/
            option.strokeStyle.color = view.G('page11_lineColor').value;
            option.strokeStyle.alpha = view.G('page11_lineAlpha').value;
            option.strokeStyle.thickness = view.G('page11_lineThickness').value;
            /*填充样式*/
            option.fillStyle.color = view.G('page11_fillColor').value;
            option.fillStyle.alpha = view.G('page11_fillAlpha').value;
            /*标签样式*/
            //标签线条
            option.labelOptions.border = view.G('page11_border').checked; //有边框  
            option.labelOptions.borderStyle.color = view.G('page11_borderColor').value;
            option.labelOptions.borderStyle.alpha = view.G('page11_borderAlpha').value;
            //标签文字
            option.labelOptions.fontStyle.name = view.G('page11_font').value;
            option.labelOptions.fontStyle.size = view.G('page11_fontSize').value;
            option.labelOptions.fontStyle.color = view.G('page11_fontColor').value;
            option.labelOptions.fontStyle.bold = view.G('page11_bold').checked;  //粗体
            option.labelOptions.fontStyle.italic = view.G('page11_italic').checked;  //斜体
            option.labelOptions.fontStyle.underline = view.G('page11_underline').checked;  //下划线
            //标签填充
            option.labelOptions.background = view.G('page11_back').checked; //有背景  
            option.labelOptions.backgroundStyle.color = view.G('page11_backColor').value;  //边框样式
            option.labelOptions.backgroundStyle.alpha = view.G('page11_backAlpha').value;
            var lzls = view.G('page11_labelZoomlevels').value;
            option.labelOptions.zoomlevels = lzls ? eval('(' + lzls + ')') : [1, 18]; //显示级别
            option.isShowLabel = view.G('page11_label').checked; //是否显示label
            option.isShowMiniTrack = view.G('page11_miniTrack').checked; //船舶自带三分钟轨迹
            option.isSelected = view.G('page11_rect').checked; //船舶框选
            var zls = view.G('page11_zoomlevels').value;
            option.zoomlevels = zls ? eval('(' + zls + ')') : [1, 18]; //显示级别
            /*****船舶数据*****/
            var data = new shipxyAPI.Ship();
            data.shipId = '1';
            data.name = 'shipName1';
            data.callsign = 'BIPE';
            data.imo = '0';
            data.shipType = '货船';
            data.navStatus = '在航（主机推动）';
            data.length = 135;
            data.beam = 21;
            data.draught = 7.3;
            data.lat = 32.5;
            data.lng = 121.6;
            data.heading = 60;
            data.course = 80;
            data.speed = 13;
            data.dest = 'BEH LUN';
            data.eta = '6.26 21:00';
            data.lastTime = 1340674688;
            this.addshipOverlay = new shipxyMap.Ship(view.G('page11_shipId').value, data, option);
            //添加到地图上显示
            map.addOverlay(this.addshipOverlay, true); //优先显示
            map.locateOverlay(this.addshipOverlay, this.addshipOverlay.options.zoomlevels[0]);
        },
        //删除船舶
        removeShip: function () {
            if (this.addshipOverlay) {
                map.removeOverlay(this.addshipOverlay); //从地图上删除
                this.addshipOverlay = null;
            }
        },
        //定位船舶
        locateShip: function () {
            if (this.addshipOverlay) map.locateOverlay(this.addshipOverlay, 10); //定位到中心点、10级
        },
        //根据船舶id请求最新数据并定位
        locateShipById: function (shipId) {
        	var result = view.G('result');
            map.removeAllOverlay(); //先清空地图叠加物
            var ships = new shipxyAPI.Ships(shipId, shipxyAPI.Ships.INIT_SHIPID);
            //请求该船舶数据
            ships.getShips(function (status) {
                var data = this.data;
                if (status == 0 && data && data.length > 0) {//成功且有数据
                    var d = data[0];
                    var ship = new shipxyMap.Ship(d.shipId, d);
                    map.addOverlay(ship); //添加
                    map.locateOverlay(ship); //并定位到中心点
                    var c = '';
                    if (data && data.length > 0) {//有数据
                        c += view.createTableByShipData1(d); //根据数据生成数据表
                    } else {//无数据
                        c += '没有该船舶数据';
                    }
                    result.innerHTML = c;
                    //view.openWin('船舶数据',c);
                } else {
                    alert('定位该船出错！');
                }
            });
        },
        //添加批量船舶
        addShipArray: function () {
            if (!this.addshipOverlayArray) {
                this.addshipOverlayArray = [];
                for (var i = 0; i < 50; i++) {//添加50条船
                    var d = new shipxyAPI.Ship();
                    d.name = 'shipname' + i;
                    d.callsign = 'BIPE';
                    d.shipId = d.mmsi = Math.round(Math.random() * 10000000).toString();
                    d.imo = '0';
                    d.shipType = '货船';
                    d.navStatus = '在航（主机推动）';
                    d.length = 135;
                    d.beam = 21;
                    d.draught = 7.3;
                    /*全球的随机位置*/
                    d.lat = Math.floor(Math.random() * 160 - 80);
                    d.lng = Math.floor(Math.random() * 360 - 180);
                    d.heading = Math.floor(Math.random() * 360);
                    d.course = Math.floor(Math.random() * 360);
                    d.speed = Math.floor(Math.random() * 25);
                    d.dest = 'BEH LUN';
                    d.eta = '6.26 21:00';
                    d.lastTime = 1340674688 + (100000 * Math.random());
                    var option = new shipxyMap.ShipOptions(); //默认option
                    var shipOverlay = new shipxyMap.Ship(d.shipId, d, option);
                    this.addshipOverlayArray.push(shipOverlay);
                }
            }
            map.addOverlays(this.addshipOverlayArray); //批量添加
            map.setCenter(new shipxyMap.LatLng(31.4, 121.5), 2); //定位到某处
        },
        //删除批量船舶
        removeShipArray: function () {
            if (this.addshipOverlayArray) {
                map.removeOverlays(this.addshipOverlayArray); //批量删除
                this.addshipOverlayArray = null;
            }
        },
        //定位批量船舶
        locateShipArray: function () {
            if (this.addshipOverlayArray) map.locateOverlays(this.addshipOverlayArray); //批量定位：所有船舶位置组成的大矩形范围中心
        },
        //添加轨迹
        addTrack: function () {
            map.removeAllOverlay();
            var trackData = new shipxyAPI.Track('413695000', 1340701800, 1340702640);
            /**********轨迹数据**********/
            trackData.data = [{ lastTime: 1340701937, from: 0, course: 217, speed: 9.908560311284047, lat: 30.5595, lng: 122.166833 },
                { lastTime: 1340702077, from: 0, course: 217, speed: 10.007782101167315, lat: 30.554333, lng: 122.162333 },
                { lastTime: 1340702217, from: 0, course: 226, speed: 9.408560311284047, lat: 30.549833, lng: 122.157333}];
            /**********轨迹显示样式控制**********/
            var opts = new shipxyMap.TrackOptions();
            var zls = view.G('page13_zoomlevels').value;
            opts.zoomlevels = zls ? eval('(' + zls + ')') : [1, 18]; //轨迹显示级别
            opts.isShowLabel = view.G('page13_label').checked; //显示标签
            opts.isVacuate = view.G('page13_vacuate').checked; //抽稀
            opts.distance = view.G('page13_distance').value; //抽稀间距：100像素
            /*****轨迹点样式*****/
            opts.pointStyle.radius = view.G('page13_radius').value; //轨迹点半径大小
            /*轨迹点边线样式*/
            opts.pointStyle.strokeStyle.thickness = view.G('page13_pointLineThickness').value;
            opts.pointStyle.strokeStyle.color = view.G('page13_pointLineColor').value;
            opts.pointStyle.strokeStyle.alpha = view.G('page13_pointLineAlpha').value;
            /*轨迹点填充样式*/
            opts.pointStyle.fillStyle.color = view.G('page13_pointFillColor').value;
            opts.pointStyle.fillStyle.alpha = view.G('page13_pointFillAlpha').value;
            /*****轨迹线样式*****/
            opts.strokeStyle.thickness = view.G('page13_lineThickness').value;
            opts.strokeStyle.color = view.G('page13_lineColor').value;
            opts.strokeStyle.alpha = view.G('page13_lineAlpha').value;
            /*****标签风格*****/
            var lzls = view.G('page13_labelZoomlevels').value;
            opts.labelOptions.zoomlevels = lzls ? eval('(' + lzls + ')') : [1, 18]; //标签显示级别
            /*标签字体风格*/
            opts.labelOptions.fontStyle.name = view.G('page13_font').value;
            opts.labelOptions.fontStyle.size = view.G('page13_fontSize').value;
            opts.labelOptions.fontStyle.color = view.G('page13_fontColor').value;
            opts.labelOptions.fontStyle.bold = view.G('page13_bold').checked;
            opts.labelOptions.fontStyle.italic = view.G('page13_italic').checked;
            opts.labelOptions.fontStyle.underline = view.G('page13_underline').checked;
            /*标签边框样式*/
            opts.labelOptions.border = view.G('page13_border').checked; //显示边框
            opts.labelOptions.borderStyle.thickness = view.G('page13_borderThickness').value;
            opts.labelOptions.borderStyle.color = view.G('page13_borderColor').value;
            opts.labelOptions.borderStyle.alpha = view.G('page13_borderAlpha').value;
            /*标签背景样式*/
            opts.labelOptions.background = view.G('page13_back').checked; //显示背景
            opts.labelOptions.backgroundStyle.color = view.G('page13_backColor').value;
            opts.labelOptions.backgroundStyle.alpha = view.G('page13_backAlpha').value;
            //创建轨迹
            this.track = new shipxyMap.Track(view.G('page13_trackId').value, trackData, opts);
            map.addOverlay(this.track); //添加
        },
        //删除轨迹
        removeTrack: function () {
            if (this.track) {
                map.removeOverlay(this.track); //删除
                this.track = null;
            }
        },
        //定位轨迹
        locateTrack: function () {
            if (this.track) map.locateOverlay(this.track, 10); //定位，10级
        },
        //添加多条轨迹
        addTracks: function () {
            var tracks = [];
            /*****轨迹1号*****/
            var t1 = new shipxyAPI.Track('413695000', 1340701800, 1340702640);
            t1.data = [{ lastTime: 1340701937, from: 0, course: 217, speed: 9.908560311284047, lat: 31.5, lng: 121.75 },
                { lastTime: 1340702077, from: 0, course: 217, speed: 10.007782101167315, lat: 30.75, lng: 123 },
                { lastTime: 1340703077, from: 0, course: 217, speed: 10.007782101167315, lat: 28.6, lng: 122.6 },
                { lastTime: 1340704217, from: 0, course: 226, speed: 9.408560311284047, lat: 26, lng: 121 },
                { lastTime: 1340712077, from: 0, course: 217, speed: 10.007782101167315, lat: 0.2, lng: 103.6 }

                ];
            var opts1 = new shipxyMap.TrackOptions();
            opts1.strokeStyle.color = 0xff0000;
            /*****轨迹2号*****/
            var t2 = new shipxyAPI.Track('413695111', 1340701800, 1340702640);
            t2.data = [{ lastTime: 1340701937, from: 0, course: 217, speed: 9.908560311284047, lat: 3, lng: 100 },
                { lastTime: 1340702077, from: 0, course: 217, speed: 10.007782101167315, lat: 20, lng: 121 },
                { lastTime: 1340702217, from: 0, course: 226, speed: 9.408560311284047, lat: 15, lng: 160}];
            var opts2 = new shipxyMap.TrackOptions();
            opts2.strokeStyle.color = 0xff00ff;
            tracks.push(new shipxyMap.Track(t1.trackId, t1, opts1));
            tracks.push(new shipxyMap.Track(t2.trackId, t2, opts2));
            map.addOverlays(tracks); //批量添加
        },
        //根据船舶类型删除所有船舶
        removeShipType: function () {
            map.removeOverlayByType(shipxyMap.OverlayType.SHIP); //删除所有船舶
        },
        //根据轨迹类型删除所有轨迹
        removeTrackType: function () {
            map.removeOverlayByType(shipxyMap.OverlayType.TRACK); //删除所有轨迹
        },
        //查询船舶
        searchShip: function () {
            var kw = view.G('page15_kw').value;
            if (!kw) return;
            var result = view.G('page15_result');
            var search = new shipxyAPI.Search();
            var opt = { keyword: kw, max: 20 }; //关键字、指定最大返回20条结果记录
            var that = this;
            //查询船舶数据
            alert(kw);
            search.searchShip(opt, function (status) {
            	alert(status);
                if (status == 0) {//成功
                    var data = this.data, c = '', len = 0;
                    c += '<table class="dataTable" cellspacing="0"><caption>查询结果<span>(' + (data && data.length ? data.length : 0) + ')</span></caption>' +
                    '<thead><tr><th scope="col">shipId</th><th scope="col">船名</th><th scope="col">MMSI</th><th scope="col">IMO</th><th scope="col">呼号</th></tr></thead><tbody>';
                    if (data && data.length > 0) {//有数据
                        var d = null, i = 0;
                        for (len = data.length; i < len; i++) {
                            d = data[i];
                            c += '<tr title="点击定位此船" onclick="action.locateShipById(\'' + d.shipId + '\')" class="' + (i % 2 == 1 ? 'odd' : '') + '">' +
                            '<td>' + d.shipId + '</td><td>' + d.name + '</td><td>' + d.MMSI + '</td><td>' + d.IMO + '</td><td>' + d.callsign + '</td></tr>';
                        }
                    } else {//无数据
                        c += '<tr><td colspan="5">没有返回结果</td></tr>';
                    }
                    c += '</tbody></table>';
                    result.innerHTML = c; //生成表格
                } else {//失败
                    alert('查询出错。');
                }
            });
        },
        //获取指定船舶数据
        getShipData: function () {
            var shipId = view.G('page16_shipId').value;
            if (shipId) {
                var result = view.G('page16_result');
                var ships = new shipxyAPI.Ships(shipId, shipxyAPI.Ships.INIT_SHIPID);
                //请求船舶数据
                ships.getShips(function (status) {
                    var data = this.data;
                    if (status == 0) {//成功
                        var c = '';
                        if (data && data.length > 0) {//有数据
                            var d = data[0];
                            c += view.createTableByShipData(d); //根据数据生成数据表
                            action.locateShipById(d.shipId); //定位到地图上显示
                        } else {//无数据
                            c += '没有该船舶数据';
                        }
                        result.innerHTML = c;
                    } else {//失败
                        alert('获取该船舶数据出错！');
                    }
                });
            }
        },
        //获取批量船舶数据
        getShipsData: function () {
            var shipIds = view.G('page17_shipIds').value;
            if (shipIds) {
                shipIds = shipIds.split(',');
                view.G('page17_results').style.display = '';
                var li = '', i = 0, len = shipIds.length, d;
                //生成id列表
                for (; i < len; i++) {
                    d = shipIds[i];
                    li += '<li><a href="javascript:void(0)" title="点击切换数据并定位" data-shipId="' + d + '">' + d + '</a></li>';
                }
                var list = view.G('page17_list'), result = view.G('page17_result');
                list.innerHTML = li;
                var ships = new shipxyAPI.Ships(shipIds, shipxyAPI.Ships.INIT_SHIPID), dataHash = {};
                //请求批量船舶数据
                ships.getShips(function (status) {
                    if (status == 0) {//成功
                        var c = '', data = this.data;
                        if (data && data.length > 0) {//有数据
                            for (i = 0, len = data.length; i < len; i++) {
                                d = data[i];
                                dataHash[d.shipId] = d;
                            }
                            var fist = data[0]; //第一条数据
                            c += view.createTableByShipData(fist); //根据数据生成数据表
                            action.locateShipById(fist.shipId); //定位到地图上显示
                        } else {//无数据
                            c += '没有数据';
                        }
                        result.innerHTML = c;
                    } else {//失败
                        alert('获取批量船舶数据出错！');
                    }
                });
                //列表点击事件
                list.onclick = function (e) {
                    e = e || window.event;
                    e = e.target || e.srcElement;
                    if (e.tagName == 'A') {
                        e = e.getAttribute('data-shipId');
                        if (e) {
                            var d = dataHash[e]; //从缓存中取出该船的数据
                            /*切换数据并定位*/
                            if (!d) {
                                result.innerHTML = '该船舶没有数据';
                                map.removeAllOverlay();
                            } else {
                                result.innerHTML = view.createTableByShipData(d);
                                action.locateShipById(e);
                            }
                        }
                    }
                    return false;
                };
            }
        },
        //获取区域船舶数据
        getRegionData: function () {
            var regionData = view.G('page18_region').value;
            if (regionData) {
                regionData = eval('(' + regionData + ')');
                var list = view.G('page18_list'), result = view.G('page18_result');
                var region = new shipxyAPI.Region();
                region.data = regionData;
                var ships = new shipxyAPI.Ships(region, shipxyAPI.Ships.INIT_REGION), dataHash = {};
                //请求区域船舶数据
                ships.getShips(function (status) {
                    if (status == 0) {//成功
                        var c = '', li = '', data = this.data, i, len, d;
                        view.G('page18_results').style.display = '';
                        if (data && data.length > 0) {//有数据
                            for (i = 0, len = data.length; i < len; i++) {
                                d = data[i];
                                dataHash[d.shipId] = d;
                                li += '<li><a href="javascript:void(0)" title="点击切换数据并定位" data-shipId="' + d.shipId + '">' + d.shipId + '</a></li>';
                            }
                            var fist = data[0]; //第一条数据
                            c += view.createTableByShipData(fist); //根据数据生成数据表
                            action.locateShipById(fist.shipId); //定位到地图上显示
                        } else {//无数据
                            c += '没有数据';
                        }
                        list.innerHTML = li;
                        result.innerHTML = c;
                    } else {//失败
                        alert('获取区域船舶数据出错！');
                    }
                });
                //列表点击事件
                list.onclick = function (e) {
                    e = e || window.event;
                    e = e.target || e.srcElement;
                    if (e.tagName == 'A') {
                        e = e.getAttribute('data-shipId');
                        if (e) {
                            var d = dataHash[e]; //从缓存中取出该船的数据
                            /*切换数据并定位*/
                            if (!d) {
                                result.innerHTML = '该船舶没有数据';
                                map.removeAllOverlay();
                            } else {
                                result.innerHTML = view.createTableByShipData(d);
                                action.locateShipById(e);
                            }
                        }
                    }
                    return false;
                };
            }
        },
        //查询轨迹
        searchTrack: function () {
            var srttime = view.G('page19_startTime').value, endtime = view.G('page19_endTime').value;
            //起始日期对象
            var srtdate = new Date(parseInt(srttime.substr(0, 4), 10),
                parseInt(srttime.substr(5, 2), 10) - 1,
                parseInt(srttime.substr(8, 2), 10),
                parseInt(srttime.substr(11, 2), 10),
                parseInt(srttime.substr(14, 2), 10), 00);
            //结束日期对象
            var enddate = new Date(parseInt(endtime.substr(0, 4), 10),
                parseInt(endtime.substr(5, 2), 10) - 1,
                parseInt(endtime.substr(8, 2), 10),
                parseInt(endtime.substr(11, 2), 10),
                parseInt(endtime.substr(14, 2), 10), 00);
            srtdate = srtdate.getTime() / 1000; //起始时间，秒数
            enddate = enddate.getTime() / 1000; //结束时间，秒数
            if (srtdate >= enddate) {
                view.setTrackMsg('开始时间必须小于等于结束时间相同');
                return;
            }
            var tracks = new shipxyAPI.Tracks();
            view.setTrackMsg('正在查询轨迹，请稍候...');
            //获取轨迹数据
            tracks.getTrack(view.G('page19_shipId').value, srtdate, enddate, function (status) {
                if (status == 0) {//成功
                    view.setTrackMsg('');
                    var data = this.data;
                    if (data && data.data && data.data.length > 0) {//有数据
                        var opts = new shipxyMap.TrackOptions();
                        opts.strokeStyle.color = 0x0000ff; //蓝色
                        opts.pointStyle.strokeStyle.color = 0x0000ff;
                        opts.labelOptions.borderStyle.color = 0x0000ff;
                        //创建轨迹
                        var track = new shipxyMap.Track(data.trackId, data, opts);
                        map.addOverlay(track); //添加
                        //map.locateOverlay(track); //定位
                    } else {//无数据
                        view.setTrackMsg('暂无轨迹');
                    }
                } else {//失败
                    view.setTrackMsg('查询轨迹数据出错');
                }
            });
        },
        //添加点
        addMarker: function () {
            map.removeAllOverlay();
            /*****点显示样式*****/
            var opts = new shipxyMap.MarkerOptions()
            var zls = view.G('page20_zoomlevels').value;
            opts.zoomlevels = zls ? eval('(' + zls + ')') : [1, 18]; //显示级别
            opts.zIndex = view.G('page20_zIndex').value; //显示层级
            opts.isShowLabel = view.G('page20_label').checked; //是否显示label
            opts.isEditable = view.G('page20_isEditable').checked; //是否可编辑
            opts.imageUrl = view.G('page20_imageUrl').value; //图片URL
            opts.imagePos = new shipxyMap.Point(parseInt(view.G('page20_imageX').value) || 0, parseInt(view.G('page20_imageY').value || 0)); //图片偏移量
            /*标签样式*/
            //标签线条
            opts.labelOptions.border = view.G('page20_border').checked; //有边框  
            opts.labelOptions.borderStyle.color = view.G('page20_borderColor').value;
            opts.labelOptions.borderStyle.alpha = view.G('page20_borderAlpha').value;
            opts.labelOptions.borderStyle.thickness = view.G('page20_borderThickness').value;
            //标签文字
            opts.labelOptions.fontStyle.name = view.G('page20_font').value;
            opts.labelOptions.fontStyle.size = view.G('page20_fontSize').value;
            opts.labelOptions.fontStyle.color = view.G('page20_fontColor').value;
            opts.labelOptions.fontStyle.bold = view.G('page20_bold').checked;  //粗体
            opts.labelOptions.fontStyle.italic = view.G('page20_italic').checked;  //斜体
            opts.labelOptions.fontStyle.underline = view.G('page20_underline').checked;  //下划线
            //标签填充
            opts.labelOptions.background = view.G('page20_back').checked; //有背景  
            opts.labelOptions.backgroundStyle.color = view.G('page20_backColor').value;  //边框样式
            opts.labelOptions.backgroundStyle.alpha = view.G('page20_backAlpha').value;
            var lzls = view.G('page20_labelZoomlevels').value;
            opts.labelOptions.zoomlevels = lzls ? eval('(' + lzls + ')') : [1, 18]; //显示级别
            if (view.G("page20_text").checked) {
                opts.labelOptions.text = view.G('page20_textValue').value;
                opts.labelOptions.htmlText = "";
            } else if (view.G("page20_htmlText").checked) {
                opts.labelOptions.text = "";
                opts.labelOptions.htmlText = view.G('page20_htmlTextValue').value;
            }
            opts.labelOptions.labelPosition = new shipxyMap.Point(parseInt(view.G('page20_textX').value) || 0, parseInt(view.G('page20_textY').value || 0));
            /*****点*****/
            var data = new shipxyMap.LatLng(37.2, 122);
            this.marker = new shipxyMap.Marker(view.G('page20_overlayId').value, data, opts);
            //添加到地图上显示
            map.addOverlay(this.marker);
            map.locateOverlay(this.marker, this.marker.options.zoomlevels[0]);
        },
        //删除点
        removeMarker: function () {
            if (this.marker) {
                map.removeOverlay(this.marker);
                this.marker = null;
            }
        },
        //定位点
        locateMarker: function () {
            if (this.marker) map.locateOverlay(this.marker, 10);
        },
        //添加线
        addPolyline: function () {
            map.removeAllOverlay();
            /*****线显示样式*****/
            var opts = new shipxyMap.PolylineOptions()
            var zls = view.G('page21_zoomlevels').value;
            opts.zoomlevels = zls ? eval('(' + zls + ')') : [1, 18]; //显示级别
            opts.zIndex = view.G('page21_zIndex').value; //是否显示label
            opts.isShowLabel = view.G('page21_label').checked; //是否显示label
            opts.isEditable = view.G('page21_isEditable').checked; //是否可编辑
            /*线样式*/
            opts.strokeStyle.color = view.G('page21_lineColor').value;
            opts.strokeStyle.alpha = view.G('page21_lineAlpha').value;
            opts.strokeStyle.thickness = view.G('page21_lineThickness').value;
            /*标签样式*/
            //标签线条
            opts.labelOptions.border = view.G('page21_border').checked; //有边框  
            opts.labelOptions.borderStyle.color = view.G('page21_borderColor').value;
            opts.labelOptions.borderStyle.alpha = view.G('page21_borderAlpha').value;
            opts.labelOptions.borderStyle.thickness = view.G('page21_borderThickness').value;
            //标签文字
            opts.labelOptions.fontStyle.name = view.G('page21_font').value;
            opts.labelOptions.fontStyle.size = view.G('page21_fontSize').value;
            opts.labelOptions.fontStyle.color = view.G('page21_fontColor').value;
            opts.labelOptions.fontStyle.bold = view.G('page21_bold').checked;  //粗体
            opts.labelOptions.fontStyle.italic = view.G('page21_italic').checked;  //斜体
            opts.labelOptions.fontStyle.underline = view.G('page21_underline').checked;  //下划线
            //标签填充
            opts.labelOptions.background = view.G('page21_back').checked; //有背景  
            opts.labelOptions.backgroundStyle.color = view.G('page21_backColor').value;  //边框样式
            opts.labelOptions.backgroundStyle.alpha = view.G('page21_backAlpha').value;
            var lzls = view.G('page21_labelZoomlevels').value;
            opts.labelOptions.zoomlevels = lzls ? eval('(' + lzls + ')') : [1, 18]; //显示级别
            if (view.G("page21_text").checked) {
                opts.labelOptions.text = view.G('page21_textValue').value;
                opts.labelOptions.htmlText = "";
            } else if (view.G("page21_htmlText").checked) {
                opts.labelOptions.text = "";
                opts.labelOptions.htmlText = view.G('page21_htmlTextValue').value;
            }
            opts.labelOptions.labelPosition = new shipxyMap.Point(parseInt(view.G('page21_textX').value) || 0, parseInt(view.G('page21_textY').value || 0));
            /*****线*****/
            var data = [];
            data[0] = new shipxyMap.LatLng(37.2, 123.2);
            data[1] = new shipxyMap.LatLng(36.4, 123);
            data[2] = new shipxyMap.LatLng(36, 122);
            data[3] = new shipxyMap.LatLng(35.4, 123);
            this.polyline = new shipxyMap.Polyline(view.G('page21_overlayId').value, data, opts);
            //添加到地图上显示
            map.addOverlay(this.polyline);
            map.locateOverlay(this.polyline, this.polyline.options.zoomlevels[0]);
        },
        //删除线
        removePolyline: function () {
            if (this.polyline) {
                map.removeOverlay(this.polyline);
                this.polyline = null;
            }
        },
        //定位线
        locatePolyline: function () {
            if (this.polyline) map.locateOverlay(this.polyline, 8);
        },
        //添加面
        addPolygon: function () {
            map.removeAllOverlay();
            /*****面显示样式*****/
            var opts = new shipxyMap.PolygonOptions();
            var zls = view.G('page22_zoomlevels').value;
            opts.zoomlevels = zls ? eval('(' + zls + ')') : [1, 18]; //显示级别
            opts.zIndex = view.G('page22_zIndex').value; //是否显示label
            opts.isShowLabel = view.G('page22_label').checked; //是否显示label
            opts.isEditable = view.G('page22_isEditable').checked; //是否可编辑
            /*边框样式*/
            opts.strokeStyle.color = view.G('page22_lineColor').value;
            opts.strokeStyle.alpha = view.G('page22_lineAlpha').value;
            opts.strokeStyle.thickness = view.G('page22_lineThickness').value;
            /*填充样式*/
            opts.fillStyle.color = view.G('page22_fillColor').value;
            opts.fillStyle.alpha = view.G('page22_fillAlpha').value;
            /*标签样式*/
            //标签线条
            opts.labelOptions.border = view.G('page22_border').checked; //有边框  
            opts.labelOptions.borderStyle.color = view.G('page22_borderColor').value;
            opts.labelOptions.borderStyle.alpha = view.G('page22_borderAlpha').value;
            opts.labelOptions.borderStyle.thickness = view.G('page22_borderThickness').value;
            //标签文字
            opts.labelOptions.fontStyle.name = view.G('page22_font').value;
            opts.labelOptions.fontStyle.size = view.G('page22_fontSize').value;
            opts.labelOptions.fontStyle.color = view.G('page22_fontColor').value;
            opts.labelOptions.fontStyle.bold = view.G('page22_bold').checked;  //粗体
            opts.labelOptions.fontStyle.italic = view.G('page22_italic').checked;  //斜体
            opts.labelOptions.fontStyle.underline = view.G('page22_underline').checked;  //下划线
            //标签填充
            opts.labelOptions.background = view.G('page22_back').checked; //有背景  
            opts.labelOptions.backgroundStyle.color = view.G('page22_backColor').value;  //边框样式
            opts.labelOptions.backgroundStyle.alpha = view.G('page22_backAlpha').value;
            var lzls = view.G('page22_labelZoomlevels').value;
            opts.labelOptions.zoomlevels = lzls ? eval('(' + lzls + ')') : [1, 18]; //显示级别
            if (view.G("page22_text").checked) {
                opts.labelOptions.text = view.G('page22_textValue').value;
                opts.labelOptions.htmlText = "";
            } else if (view.G("page22_htmlText").checked) {
                opts.labelOptions.text = "";
                opts.labelOptions.htmlText = view.G('page22_htmlTextValue').value;
            }
            opts.labelOptions.labelPosition = new shipxyMap.Point(parseInt(view.G('page22_textX').value) || 0, parseInt(view.G('page22_textY').value || 0));
            /*****面*****/
            var data = [];
            data[0] = new shipxyMap.LatLng(37.2, 122);
            data[1] = new shipxyMap.LatLng(37.2, 123.6);
            data[2] = new shipxyMap.LatLng(36.4, 123.6);
            data[3] = new shipxyMap.LatLng(36.4, 122);
            this.polygon = new shipxyMap.Polygon(view.G('page22_overlayId').value, data, opts);
            //添加到地图上显示
            map.addOverlay(this.polygon);
            map.locateOverlay(this.polygon, this.polygon.options.zoomlevels[0]);
        },
        //删除面
        removePolygon: function () {
            if (this.polygon) {
                map.removeOverlay(this.polygon); //从地图上删除
                this.polygon = null;
            }
        },
        //定位面
        locatePolygon: function () {
            if (this.polygon) map.locateOverlay(this.polygon, 9); //定位到中心点、9级
        },
        //获取船队列表
        getFleetList: function () {
            var list = view.G('page23_list'), result = view.G('page23_result'), dataHash = {};
            var fleet = new shipxyAPI.Fleet(function (status) {
                if (status == 0) {
                    view.G('page23_results').style.display = '';
                    var s = '', li = '';
                    if (this.data && this.data.length > 0) {
                        var i, len = this.data.length, g;
                        for (i = 0; i < len; i++) {
                            g = this.data[i];
                            dataHash[g.name] = g;
                            li += '<li><a href="javascript:void(0)" title="点击切换船队分组" style="color:' + g.color + ';">' + g.name + '</a></li>';
                        }
                        var fist = this.data[0]; //第一条数据
                        s = view.createTableByFleetList(fist); //根据数据生成数据表
                    } else {
                        s = '无船队列表';
                    }
                    list.innerHTML = li;
                    result.innerHTML = s;
                } else {//失败
                    alert('获取船队列表出错！');
                }
            });
            //列表点击事件
            list.onclick = function (e) {
                e = e || window.event;
                e = e.target || e.srcElement;
                if (e.tagName == 'A') {
                    e = e.innerHTML;
                    if (e) {
                        var g = dataHash[e]; //从缓存中取出该分组的数据
                        /*切换分组数据*/
                        if (!g) {
                            result.innerHTML = '该分组没有船舶';
                        } else {
                            result.innerHTML = view.createTableByFleetList(g);
                        }
                    }
                }
                return false;
            };
        },
        //获取船队船舶数据
        getFleetShipData: function () {
            var list = view.G('page24_list'), result = view.G('page24_result'), dataHash = {};
            var fleet = new shipxyAPI.Fleet(function (status) { });
            var fleetShipObj = new shipxyAPI.Ships(fleet, shipxyAPI.Ships.INIT_FLEET);
            fleetShipObj.getShips(function (status) {
                if (status == 0) {//成功
                    view.G('page24_results').style.display = '';
                    var c = '', li = '', data = this.data, i, len, d;
                    if (data && data.length > 0) {//有数据
                        for (i = 0, len = data.length; i < len; i++) {
                            d = data[i];
                            dataHash[d.shipId] = d;
                            li += '<li><a href="javascript:void(0)" title="点击切换数据并定位" data-shipId="' + d.shipId + '">' + d.shipId + '</a></li>';
                        }
                        var fist = data[0]; //第一条数据
                        c += view.createTableByShipData(fist); //根据数据生成数据表
                        action.locateShipById(fist.shipId); //定位到地图上显示
                    } else {//无数据
                        c += '没有数据';
                    }
                    list.innerHTML = li;
                    result.innerHTML = c;
                } else {//失败
                    alert('获取船队船舶数据出错！');
                }
            });
            //列表点击事件
            list.onclick = function (e) {
                e = e || window.event;
                e = e.target || e.srcElement;
                if (e.tagName == 'A') {
                    e = e.getAttribute('data-shipId');
                    if (e) {
                        var d = dataHash[e]; //从缓存中取出该船的数据
                        /*切换数据并定位*/
                        if (!d) {
                            result.innerHTML = '该船舶没有数据';
                            map.removeAllOverlay();
                        } else {
                            result.innerHTML = view.createTableByShipData(d);
                            action.locateShipById(e);
                        }
                    }
                }
                return false;
            };
        }
    };

    view = {
        G: function (id) {
            return document.getElementById(id);
        },
        //设置页面布局
        resize: function () {
            var header = this.G('header'), mainer = this.G('mainer'), lister = this.G('lister');
            var h = mainer.style.height = document.body.clientHeight - header.offsetHeight;
            mainer.style.width = document.body.clientWidth - lister.offsetWidth + 'px';
            lister.style.height = this.G('mapDiv').style.height = mainer.style.height = h + 'px';
            //this.G('codeFrame').style.height = h - 26 + 'px';
        },
        //初始化左侧列表
        initList: function () {
            var box = this.G('listbox'), that = this;
            //列表区域点击事件
            box.onclick = function (e) {
                e = e || window.event;
                var target = e.target || e.srcElement;
                if (target.tagName == 'A') {
                    var cmd = target.getAttribute('data-cmd');
                    if (cmd == 'listhead') {//列表标题
                        toggleList(box, target.parentNode.parentNode);
                    } else {//示例标题
                        if (preLink) {
                            preLink.className = ''; //上一次的选择项，清掉高亮突出的背景色
                        }
                        var code = target.getAttribute('data-code'); //示例的代码标号
                        //加载该代码文件
                        that.G('codeFrame').src = 'code/' + (code || '0') + '.htm'; //此行代码会使chrome、firefox、opera加载源页面，导致闪烁
                        var btnRun = that.G('btnRun');
                        if (btnRun) {
                            btnRun.href = 'code/a' + (code || '0') + '.htm'; //给运行按钮绑定该代码文件
                        }
                        action.removeAllOverlay(); //清空地图叠加物
                        that.G('taber').onclick(null, that.G('mapTab')); //重置选项卡为地图
                        var content = getContent(code); //生成示例窗口的内容
                        if (content) {
                            that.openWin(content[0], content[1]); //打开、显示
                        }
                        target.className = 'sel'; //选择项，加上背景色，以便高亮突出
                        preLink = target; //记录选择项
                    }
                }
                return false;
            }
        },
        //初始化Tab
        initTaber: function () {
            var taber = this.G('taber'), that = this;
            //选项卡点击事件
            taber.onclick = function (e, target) {
                if (!preLink) return; //若没有选择示例，代码选项卡不可用
                e = e || window.event;
                target = target || e.target || e.srcElement;
                if (target.tagName == 'A') {
                    var isCodeTab = target.id == 'codeTab'; //是否是代码选项卡
                    that.G('mapTab').className = that.G('codeTab').className = '';
                    target.className = 'on'; //选项卡高亮突出
                    that.G('coder').style.display = isCodeTab ? '' : 'none'; //切换代码区域
                    if (isCodeTab) {//当是代码卡，关闭窗口
                        that.closeWin();
                        //当是代码卡，复制粘贴功能开启
                        if (!clip) {
                            clip = new ZeroClipboard.Client();
                            clip.setHandCursor(true);
                            clip.addEventListener('mouseOver', function (client) {
                                var copyStr = window.frames['codeFrame'].document.body.innerHTML.replace(/\<\/?textarea[^\>]*\>/ig, '').replace(/&lt;/ig, '<').replace(/&gt;/ig, '>') || '';
                                clip.setText(copyStr);
                                //alert('复制成功');
                                that.G('btnCopy').className = 'copyAnchorHover';
                            });
                            clip.addEventListener('mouseOut', function (client) {
                                that.G('btnCopy').className = '';
                            });
                            clip.glue('btnCopy');
                        }
                        clip.show(); //打开剪贴板
                    } else {//否则，打开窗口
                        that.openWin();
                        if (clip) clip.hide(); //关闭剪贴板
                    }
                }
                return false;
            }
        },
        //打开窗口
        openWin: function (title, content) {
            if (!win) {
                win = new XWin({
                    modal: false, //非模态
                    width: 420,
                    position: [document.body.clientWidth - 450, 120] //[left,top]
                });
            }
            if (title != null) {
                win.setTitle(title); //标题
            }
            if (content) {
                win.setContent(content); //内容
            }
            win.show(); //显示
        },
        //关闭窗口
        closeWin: function () {
            if (win) {
                win.hide();
            }
        },
        //设置轨迹查询提示消息
        setTrackMsg: function (msg) {
            var trackMsg = this.G("trackMsg");
            trackMsg.innerHTML = msg;
            if (msg) {
                trackMsg.style.display = '';
            } else {//当是空消息，隐藏
                trackMsg.style.display = 'none';
            }
        },
        //根据船舶数据生成表格
        createTableByShipData: function (d) {
            var speed = isNaN(d.speed) ? '' : (d.speed == 0 ? '0.0' : (Math.round((d.speed) * 10) / 10) + '节'); //转成节
            return '<table class="xinfo"><tr>'
            + '<td class="ll">船名：</td><td class="o" title="' + d.name + '">' + d.name + '</td>'
            + '<td class="l">纬度：</td><td class="ro" title="' + d.lat + '">' + d.lat + '</td>'
            + '</tr><tr>'
            + '<td>呼号：</td><td class="o" title="' + d.callsign + '">' + d.callsign + '</td>'
            + '<td class="l">经度：</td><td class="ro" title="' + d.lng + '">' + d.lng + '</td>'
            + '</tr><tr>'
            + '<td>MMSI：</td><td class="o" title="' + d.MMSI + '">' + d.MMSI + '</td>'
            + '<td class="l">船首向：</td><td class="ro" title="' + d.heading + '">' + d.heading + '</td>'
            + '</tr><tr>'
            + '<td>IMO：</td><td class="o" title="' + d.IMO + '">' + d.IMO + '</td>'
            + '<td class="l">航迹向：</td><td class="ro" title="' + d.course + '">' + d.course + '</td>'
            + '</tr><tr>'
            + '<td>船籍：</td><td class="o" title="' + d.country + '">' + d.country + '</td>'
            + '<td class="l">航速：</td><td class="ro" title="' + speed + '">' + speed + '</td>'
            + '</tr><tr>'
            + '<td>类型：</td><td class="o" title="' + d.type + '">' + d.type + '</td>'
            + '<td class="l">货物类型：</td><td class="ro" title="' + d.cargoType + '">' + d.cargoType + '</td>'
            + '</tr><tr>'
            + '<td>状态：</td><td class="o" title="' + d.status + '">' + d.status + '</td>'
            + '<td class="l">目的地：</td><td class="ro" title="' + d.dest + '">' + d.dest + '</td>'
            + '</tr><tr>'
            + '<td>船长：</td><td class="o" title="' + d.length + '">' + d.length + '</td>'
            + '<td class="l">预到时间：</td><td class="ro" title="' + d.eta + '">' + d.eta + '</td>'
            + '</tr><tr>'
            + '<td>船宽：</td><td class="o" title="' + d.beam + '">' + d.beam + '</td>'
            + '<td class="l">最后时间：</td><td class="ro" title="' + d.lastTime + '">' + d.lastTime + '</td>'
            + '</tr><tr>'
            + '<td>吃水：</td><td class="o" title="' + d.draught + '">' + d.draught + '</td>'
            + '<td class="l">旋转角速度：</td><td class="ro" title="' + d.rot + '">' + d.rot + '</td>'
            + '</tr></table>';
        },
        createTableByShipData1: function (d) {
            var speed = isNaN(d.speed) ? '' : (d.speed == 0 ? '0.0' : (Math.round((d.speed) * 10) / 10) + '节'); //转成节
            return '<table class="xinfo"><tr>'
            + '<td class="ll">船名：</td><td class="o" title="' + d.name + '">' + d.name + '</td>'
            + '</tr><tr>'
            + '<td class="l">纬度：</td><td class="ro" title="' + d.lat + '">' + d.lat + '</td>'
            + '</tr><tr>'
            + '<td>呼号：</td><td class="o" title="' + d.callsign + '">' + d.callsign + '</td>'
            + '</tr><tr>'
            + '<td class="l">经度：</td><td class="ro" title="' + d.lng + '">' + d.lng + '</td>'
            + '</tr><tr>'
            + '<td>MMSI：</td><td class="o" title="' + d.MMSI + '">' + d.MMSI + '</td>'
            + '</tr><tr>'
            + '<td class="l">船首向：</td><td class="ro" title="' + d.heading + '">' + d.heading + '</td>'
            + '</tr><tr>'
            + '<td>IMO：</td><td class="o" title="' + d.IMO + '">' + d.IMO + '</td>'
            + '</tr><tr>'
            + '<td class="l">航迹向：</td><td class="ro" title="' + d.course + '">' + d.course + '</td>'
            + '</tr><tr>'
            + '<td>船籍：</td><td class="o" title="' + d.country + '">' + d.country + '</td>'
            + '</tr><tr>'
            + '<td class="l">航速：</td><td class="ro" title="' + speed + '">' + speed + '</td>'
            + '</tr><tr>'
            + '<td>类型：</td><td class="o" title="' + d.type + '">' + d.type + '</td>'
            + '</tr><tr>'
            + '<td class="l">货物类型：</td><td class="ro" title="' + d.cargoType + '">' + d.cargoType + '</td>'
            + '</tr><tr>'
            + '<td>状态：</td><td class="o" title="' + d.status + '">' + d.status + '</td>'
            + '</tr><tr>'
            + '<td class="l">目的地：</td><td class="ro" title="' + d.dest + '">' + d.dest + '</td>'
            + '</tr><tr>'
            + '<td>船长：</td><td class="o" title="' + d.length + '">' + d.length + '</td>'
            + '</tr><tr>'
            + '<td class="l">预到时间：</td><td class="ro" title="' + d.eta + '">' + d.eta + '</td>'
            + '</tr><tr>'
            + '<td>船宽：</td><td class="o" title="' + d.beam + '">' + d.beam + '</td>'
            + '</tr><tr>'
            + '<td class="l">最后时间：</td><td class="ro" title="' + d.lastTime + '">' + d.lastTime + '</td>'
            + '</tr><tr>'
            + '<td>吃水：</td><td class="o" title="' + d.draught + '">' + d.draught + '</td>'
            + '</tr></table>';
        },
        createTableByFleetList: function (data) {
            if (!data || data.data.length == 0) return '';
            var ret = '<table style="width:300px;height:auto;" class="dataTable" cellspacing="0"><thead><tr><th scope="col">shipId</th><th scope="col">自定义船名</th><th scope="col">备注</th></tr></thead><tbody>';
            var ships = data.data, j = 0, len = ships.length, d;
            for (j = 0; j < len; j++) {
                d = ships[j];
                ret += '<tr style="color:' + data.color + '" class="' + (j % 2 == 1 ? 'odd' : '') + '"><td>' + d.shipId + '</td><td>' + d.customName + '</td><td>' + d.remarks + '</td></tr>';
            }
            ret += "</tbody></table>";
            return ret;
        },
        //日期格式化为YYYY-MM-DD HH:MM:SS
        formatDateToString: function (date) {
            if (!(date instanceof Date)) return '';
            var m = date.getMonth() + 1, d = date.getDate(), H = date.getHours(), M = date.getMinutes(), S = date.getSeconds();
            if (m < 10) { m = '0' + m; }
            if (d < 10) { d = '0' + d; }
            if (H < 10) { H = '0' + H; }
            if (M < 10) { M = '0' + M; }
            if (S < 10) { S = '0' + S; }
            return [date.getFullYear(), '-', m, '-', d, ' ', H, ':', M, ':', S].join('');
        }
    };
})();