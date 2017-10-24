/// <reference path="../../shipxyAPI.js" />
/// <reference path="view.js" />
/// <reference path="util.js" />
/// <reference path="xwin.js" />
(function () {
    var fleet, //我的船队，shipxyAPI.Fleet的一个实例
        groupList, //我的船队分组列表（左侧组名列表）
        groupShipObj, //分组的船舶数据，shipxyAPI.Ships的一个实例
        searchOj, //船舶查询，shipxyAPI.Search的一个实例
        searchMaxCount = 100, //查询结果最大返回数
        addGroupView, //添加分组视图
        addShipView, //添加船舶视图
        modifyGroupView, //修改分组视图
        editGroupView, //管理分组视图
        editShipView, //管理船舶视图
        searchView, //查询船舶视图
        modifyPwdView; //修改密码视图
    //文本框占位文字
    var PlaceHolder = function () { this.init.apply(this, arguments) };
    PlaceHolder.prototype = {
        init: function (input, text) {
            if (typeof input == 'string') input = document.getElementById(input);
            var that = this; this.input = input; this.setText(text || input.getAttribute('placeholder') || '');
            input.onfocus = function () { if (!this.value || this.value == that.text) this.value = ''; this.style.color = 'black' };
            input.onblur = function () { if (!this.value) this.value = that.text; if (this.value == that.text) this.style.color = '#ccc' };
            input.onblur();
        },
        setText: function (text) { this.text = text; if (this.input.style.color) this.input.value = this.text }
    };
    //取得船舶显示名称，优先级：自定义船名、船名、MMSI、shipId
    var getShipName = function (data) {
        return data.customName || data.name || data.MMSI || data.shipId;
    };
    Manager = {
        //初始化：初始化船队列表
        init: function () {
            //初始化船队列表
            fleet = new shipxyAPI.Fleet(function (status) {
                if (status == 0) {
                    Manager.showGroupList(this.data);
                } else {
                    Message.alert('初始化获取船队列表失败！');
                }
            });
        },
        //显示船队分组数据，data为船队的所有分组数据
        showGroupList: function (data) {
            if (!data) return;
            if (!groupList) {//创建列表
                groupList = new View.List(data, {
                    labelField: 'name',
                    colorField: 'color',
                    bold: true,
                    selectedIndex: 0,
                    tip: '点击管理分组',
                    itemClick: function (item) {
                        Manager.showEditGroupView(item.data); //显示管理分组视图，编辑组内的船舶
                    }
                });
                util.getElement('groupList').appendChild(groupList.ui);
            } else {//刷新
                groupList.setSource(data);
            }
            var sel = groupList.getSelectedIndex(), len = data.length;
            if (sel > -1) {//当列表选择项索引超过数据长度，重置选择索引
                if (sel >= len) {
                    sel -= len;
                    groupList.setSelectedIndex(sel);
                }
                this.showEditGroupView(data[sel]); //当有选择项，显示对应的管理分组视图
            }
        },
        //获取分组的船舶数据，并显示在分组船舶表格中
        getGroupShips: function (group) {
            if (!group) return;
            if (!groupShipObj) groupShipObj = new shipxyAPI.Ships(null, shipxyAPI.Ships.INIT_SHIPID);
            groupShipObj.condition = group.getShipIds(); //请求该组的船舶数据
            groupShipObj.getShips(function (status) {
                if (status == 0) {
                    var gs = group.data;
                    //为分组每条船的数据加上船名、MMSI、IMO、呼号、船籍等内容，以便清楚查看船舶的基本信息
                    for (var i = 0, len = gs.length; i < len; i++) {
                        var ship = gs[i];
                        var obj = {};
                        for (var j = 0, len2 = this.data.length; j < len2; j++) {
                            var o = this.data[j];
                            if (this.data[j].shipId == ship.shipId) {
                                obj = o;
                                break;
                            }
                        }
                        ship.name = obj.name || '';
                        ship.MMSI = obj.MMSI || '';
                        ship.IMO = obj.IMO || '';
                        ship.callsign = obj.callsign || '';
                        ship.country = obj.country || '';
                        ship.group = group;
                    }
                    editGroupView.shipTable.setSource(gs); //刷新管理分组视图的船舶表格数据
                }
            });
            var gs = group.data;
            for (var i = 0, len = gs.length; i < len; i++) {
                gs[i].group = group;
            }
            if (!editGroupView.shipTable) {//创建表格
                editGroupView.shipTable = new View.Table(null, {
                    colKeys: ['shipId', 'name', 'MMSI', 'IMO', 'callsign', 'country', 'customName', 'remarks'], //每列的字段，与标题一一对应
                    colTitles: ['shipId', '船名', 'MMSI', 'IMO', '呼号', '船籍', '自定义船名', '备注'], //每列标题文本，可自由增减字段或排序，但必须与colKeys一一对应，否则不会正确显示
                    tip: '点击管理船舶', //表格行提示
                    extendTips: ['customName', 'remarks'], //额外的单元格提示，作用是单独对某一个或多个单元格重设提示
                    //行点击事件
                    itemClick: function (item) {
                        Manager.showEditShipView(item.data, item.data.group);
                    }
                });
                var shipList = util.getElement('editGroup_shipList');
                shipList.appendChild(editGroupView.shipTable.ui);
            }
            //管理分组视图打开时，立即把分组船的shipId、自定义船名、备注显示出来，因为这个时候分组船只有这几个内容，所以需要额外调用shipxyAPI.Ships.getShips()方法请求船舶的详细数据
            editGroupView.shipTable.setSource(gs);
            editGroupView.shipTable.setSelectedIndex(-1); //清空表格的选择项
            editGroupView.style.display = ''; //显示
        },
        //根据关键字查询船舶，并显示在查询结果表格中
        searchShip: function (key) {
            if (!key || key == '请输入船名、呼号、MMSI或IMO') { return; }
            if (!searchOj) searchOj = new shipxyAPI.Search();
            //调用API查询船舶接口
            searchOj.searchShip({ keyword: key, max: searchMaxCount }, function (status) {
                if (!searchView.resultTable) {//创建表格
                    searchView.resultTable = new View.Table(this.data, {
                        width: 560,
                        colKeys: ['shipId', 'name', 'MMSI', 'IMO', 'callsign', 'country'], //每列的字段，与标题一一对应
                        colTitles: ['shipId', '船名', 'MMSI', 'IMO', '呼号', '船籍'], //每列标题文本，可自由增减字段或排序，但必须与colKeys一一对应，否则不会正确显示
                        tip: '点击添加', //表格行提示
                        //行点击事件
                        itemClick: function (item) {
                            var addShip_id = util.getElement('addShip_id');
                            if (addShip_id) addShip_id.value = item.data.shipId; //选择该船
                            Manager.hideSearchView();
                        }
                    });
                    searchView.setParam({ height: 450 });
                    util.getElement('searchResult').appendChild(searchView.resultTable.ui);
                } else {//刷新
                    searchView.resultTable.setSource(this.data);
                }
                searchView.show();
            });
        },
        //显示搜索窗口
        showSearchView: function () {
            if (!searchView) {
                searchView = new XWin({
                    width: 600,
                    title: '搜索船舶',
                    content: '<div class="panel_content"><div class="searchBox">' +
                    '<div class="inputBox">' +
                    '<input name="searchKey" type="text" id="searchKey" maxlength="20"/>' +
                    '</div>' +
                    '<input class="button" type="button" value="搜 索" onclick="Manager.searchShip(util.getElement(\'searchKey\').value)" />' +
                    '</div>' +
                    '<div id="searchResult" /></div>'
                });
                new PlaceHolder(util.getElement('searchKey'), '请输入船名、呼号、MMSI或IMO');
            }
            searchView.show();
            searchView.setPosition([(document.body.offsetWidth - searchView.offsetWidth) / 2, 150]);
            util.getElement('searchKey').focus(); //输入框自动获得焦点
        },
        //关闭搜索窗口
        hideSearchView: function () {
            if (searchView && !searchView.ishide()) {
                searchView.hide();
            }
        },
        //显示管理分组窗口
        showEditGroupView: function (group) {
            if (!editGroupView) {
                editGroupView = document.createElement('div');
                editGroupView.className = 'panel';
                util.getElement('worker').appendChild(editGroupView);
                editGroupView.innerHTML = '<div class="panel_title">管理【<span class="light" id="edit_groupName"></span>】分组<span id="edit_color" style="display:none;"></span></div>' +
                '<div class="panel_content">' +
                '<p class="center">' +
                '<input class="button" type="button" value="添加船舶" onclick="Manager.showAddShipView(util.getElement(\'edit_groupName\').innerHTML)"/>' +
                '<input class="button" type="button" value="修改该组" onclick="Manager.showModifyGroupView(util.getElement(\'edit_groupName\').innerHTML,util.getElement(\'edit_color\').innerHTML)"/>' +
                '<input class="button" type="button" value="删除该组" onclick="Manager.delGroup(util.getElement(\'edit_groupName\').innerHTML)"/>' +
                '</p>' +
                '<div id="editGroup_shipList"></div>' +
                '</div>'
            }
            util.getElement('edit_groupName').innerHTML = group.name; //组名
            util.getElement('edit_color').innerHTML = group.color; //组颜色
            editGroupView.style.display = '';
            this.getGroupShips(group); //当打开管理分组视图，立即获取该组的船舶详细数据并显示出来
        },
        //关闭管理分组窗口
        hideEditGroupView: function () {
            if (editGroupView) {
                editGroupView.style.display = 'none';
            }
        },
        //显示添加分组窗口
        showAddGroupView: function () {
            if (!addGroupView) {
                addGroupView = new XWin({
                    dragbody: true,
                    title: '添加新组',
                    width: 240,
                    content: '<div class="panel_content">' +
                    '<table class="mtable">' +
                    '<tr><td align="right"><label for="addGroup_name">组名：</label></td><td><input id="addGroup_name" type="text"/></td></tr>' +
                    '<tr><td align="right"><label for="addGroup_color">颜色：</label></td><td><input id="addGroup_color" type="text"/><img src="images/radio.gif" title="选择颜色" onclick="colorPane.open(\'colorPane\',\'addGroup_color\',event || window.event);"/></td></tr>' +
                    '<tr><td/><td><input class="button" type="button" value="添 加" onclick="Manager.addGroup(util.getElement(\'addGroup_name\').value,util.getElement(\'addGroup_color\').value);return false;"/>' +
                    '<input class="button" type="button" value="取 消" onclick="Manager.hideAddGroupView();return false;"/></td></tr>' +
                    '</table>'
                });
                new PlaceHolder(addGroup_color, '请输入或选择颜色');
            }
            addGroupView.show();
            //清空输入框文本内容
            util.getElement('addGroup_name').value = '';
            util.getElement('addGroup_color').value = '';
        },
        //关闭添加分组窗口
        hideAddGroupView: function () {
            if (addGroupView && !addGroupView.ishide()) {
                addGroupView.hide();
            }
        },
        //显示修改分组窗口
        showModifyGroupView: function (name, color) {
            if (!modifyGroupView) {
                modifyGroupView = new XWin({
                    dragbody: true,
                    width: 240,
                    title: '修改【<span class="light" id="modifyGroup_oldName"></span>】分组</p>',
                    content: '<div class="panel_content">' +
                    '<table class="mtable">' +
                    '<tr><td align="right"><label for="modifyGroup_name">组名：</label></td><td><input id="modifyGroup_name" type="text"/></td></tr>' +
                    '<tr><td align="right"><label for="modifyGroup_color">颜色：</label></td><td><input id="modifyGroup_color" type="text"/><img src="images/radio.gif" title="选择颜色" onclick="colorPane.open(\'colorPane\',\'modifyGroup_color\',event || window.event);"/></td></tr>' +
                    '<tr><td/><td><input class="button" type="button" value="修 改" onclick="Manager.modifyGroup(util.getElement(\'modifyGroup_oldName\').innerHTML,util.getElement(\'modifyGroup_name\').value,util.getElement(\'modifyGroup_color\').value);return false;"/>' +
                    '<input class="button" type="button" value="取 消" onclick="Manager.hideModifyGroupView();return false;"/></td></tr>' +
                    '</table>'
                });
                new PlaceHolder(modifyGroup_color, '请输入或选择颜色');
            }
            modifyGroupView.show();
            util.getElement('modifyGroup_oldName').innerHTML = name; //旧组名
            util.getElement('modifyGroup_name').value = name;
            util.getElement('modifyGroup_color').value = color;
        },
        //关闭修改分组窗口
        hideModifyGroupView: function () {
            if (modifyGroupView && !modifyGroupView.ishide()) {
                modifyGroupView.hide();
            }
        },
        //显示管理船舶窗口
        showEditShipView: function (ship, group) {
            if (!editShipView) {
                editShipView = new XWin({
                    dragbody: true,
                    width: 300,
                    title: '管理<span id="editShip_gn" style="display:none;"></span>【<span class="light" id="editShip_name"></span>】船舶',
                    content: '<div class="panel_content">' +
                    '<table class="mtable">' +
                    '<tr><td align="right"><label for="modifyShip_id">船舶MMSI：</label></td><td><input id="modifyShip_id" type="text" disabled="disabled"/></td></tr>' +
                    '<tr><td align="right"><label for="modifyShip_gn">船队分组：</label></td><td><select id="modifyShip_gn"></select></td></tr>' +
                    '<tr><td align="right"><label for="modifyShip_cn">自定义船名：</label></td><td><input id="modifyShip_cn" type="text"/></td></tr>' +
                    '<tr><td align="right"><label for="modifyShip_rmk">备注信息：</label></td><td><textarea id="modifyShip_rmk"></textarea></td></tr>' +
                    '<tr><td/><td><input class="button" type="button" value="修 改" onclick="Manager.changeGroup(util.getElement(\'modifyShip_id\').value,util.getElement(\'modifyShip_cn\').value,util.getElement(\'modifyShip_rmk\').value,util.getElement(\'editShip_gn\').innerHTML,util.getElement(\'modifyShip_gn\').value);return false;"/>' +
                    '<input class="button" type="button" value="删 除" onclick="Manager.delShip(util.getElement(\'modifyShip_id\').value,util.getElement(\'editShip_gn\').innerHTML)"/>' +
                    '<input class="button" type="button" value="取 消" onclick="Manager.hideEditShipView();return false;"/></td></tr>' +
                    '</table>' +
                    '</div>'
                })
            }
            editShipView.show();
            var slc = '', gs = fleet.data, gn;
            for (var i = 0, len = gs.length; i < len; i++) {
                gn = gs[i].name;
                slc += '<option value="' + gn + '">' + gn + '</option>';
            }
            var editShip_gn = util.getElement('modifyShip_gn');
            modifyShip_gn.innerHTML = slc; //组名下拉列表
            modifyShip_gn.value = group.name;
            util.getElement('editShip_gn').innerHTML = group.name;
            util.getElement('editShip_name').innerHTML = getShipName(ship);
            util.getElement('modifyShip_id').value = ship.shipId;
            util.getElement('modifyShip_cn').value = ship.customName;
            util.getElement('modifyShip_rmk').innerHTML = ship.remarks;
        },
        //关闭管理船舶窗口
        hideEditShipView: function () {
            if (editShipView && !editShipView.ishide()) {
                editShipView.hide();
            }
        },
        //显示添加船舶窗口
        showAddShipView: function (groupName) {
            if (!addShipView) {
                addShipView = new XWin({
                    dragbody: true,
                    title: '<span id="addShip_gn" style="display:none;"></span>添加船舶',
                    width: 300,
                    content: '<div class="panel_content">' +
                    '<table class="mtable">' +
                    '<tr><td align="right"><label for="addShip_id">船舶MMSI：</label></td><td><input id="addShip_id" type="text"/><input class="button" type="button" value="搜索船舶" onclick="Manager.showSearchView();return false;"/></td></tr>' +
                    '<tr><td align="right"><label for="addShip_cn">自定义船名：</label></td><td><input id="addShip_cn" type="text"/></td></tr>' +
                    '<tr><td align="right"><label for="addShip_rmk">备注信息：</label></td><td><textarea id="addShip_rmk"></textarea></td></tr>' +
                    '<tr><td/><td><input class="button" type="button" value="添 加" onclick="Manager.addShip(util.getElement(\'addShip_id\').value,util.getElement(\'addShip_cn\').value,util.getElement(\'addShip_rmk\').value,util.getElement(\'addShip_gn\').innerHTML);return false;"/>' +
                    '<input class="button" type="button" value="取 消" onclick="Manager.hideAddShipView();return false;"/></td></tr>' +
                    '</table>' +
                    '</div>'
                });
                new PlaceHolder(util.getElement('addShip_id'), '请输入MMSI或搜索船舶');
            }
            addShipView.show();
            util.getElement('addShip_gn').innerHTML = groupName;
            var addShip_id = util.getElement('addShip_id');
            addShip_id.value = '';
            addShip_id.focus();
            util.getElement('addShip_cn').value = '';
            util.getElement('addShip_rmk').value = '';

        },
        //关闭添加船舶窗口
        hideAddShipView: function () {
            if (addShipView && !addShipView.ishide()) {
                addShipView.hide();
            }
        },
        //添加组
        addGroup: function (name, color) {
            if (!name) {
                Message.alert('请输入组名！');
                return;
            }
            if (!color || color == '请输入或选择颜色') {
                Message.alert('请输入颜色值！');
                return;
            }
            var group = new shipxyAPI.Group();
            group.name = name;
            group.color = color;
            var that = this;
            //调用API的船队添加分组接口
            fleet.addGroup(group, function () {
                if (status == 0) {//成功
                    that.showGroupList(this.data); //刷新船队分组列表
                    that.hideAddGroupView();
                } else {//失败
                    Message.alert('添加分组失败！');
                }
            });
        },
        //修改组
        modifyGroup: function (oldName, newName, color) {
            if (!newName) {
                Message.alert('请输入组名！');
                return;
            }
            if (!color || color == '请输入或选择颜色') {
                Message.alert('请输入颜色值！');
                return;
            }
            var that = this;
            //调用API的船队修改分组接口
            fleet.modifyGroup(oldName, { name: newName, color: color }, function (status) {
                if (status == 0) {//成功
                    that.showGroupList(this.data); //刷新船队分组列表
                    that.hideModifyGroupView();
                } else {//失败
                    Message.alert('修改分组失败！');
                }
            });
        },
        //删除组
        delGroup: function (name) {
            var that = this;
            Message.confirm('确定要删除该组吗？删除该组之后，该组下的所有船舶都将被删除。', function () {
                //调用API的船队删除分组接口
                fleet.delGroup(name, function (status) {
                    if (status == 0) {//成功
                        that.showGroupList(this.data); //刷新船队分组列表
                    } else {//失败
                        Message.alert('删除分组失败！');
                    }
                });
            });
        },
        //添加船
        addShip: function (shipId, cn, rmk, groupName) {
            if (!shipId || shipId == '请输入MMSI或搜索船舶') {
                Message.alert('请指定船舶MMSI！');
                return;
            }
            if (!groupName) {
                Message.alert('请指定分组！');
                return;
            }
            if (fleet.getGroupsByShipId(shipId).length > 0) {
                Message.alert('船队中已存在该船，不能重复添加！');
                return;
            }
            var that = this;
            //调用API的船队添加船舶接口
            fleet.addShip({ shipId: shipId, customName: cn, remarks: rmk }, groupName, function (status) {
                if (status == 0) {//成功
                    that.getGroupShips(fleet.getGroup(groupName)); //刷新该组的船舶表格数据
                    that.showGroupList(fleet.data); //刷新船队分组列表
                    that.hideAddShipView();
                } else {//失败
                    Message.alert('添加船舶失败！');
                }
            });
        },
        //修改船
        modifyShip: function (shipId, cn, rmk, groupName) {
            var that = this;
            //调用API的船队修改船舶接口
            fleet.modifyShip({ shipId: shipId, customName: cn, remarks: rmk }, groupName, function (status) {
                if (status == 0) {//成功
                    that.getGroupShips(fleet.getGroup(groupName)); //刷新该组的船舶表格数据
                    that.showGroupList(fleet.data); //刷新船队分组列表
                    that.hideEditShipView();
                } else {//失败
                    Message.alert('修改船舶失败！');
                }
            });
        },
        //删除船
        delShip: function (shipId, gn) {
            var that = this;
            Message.confirm('确定要将该船从【<span class="light">' + gn + '</span>】组删除吗？', function () {
                //调用API的船队删除船舶接口
                fleet.delShip(shipId, gn, function (status) {
                    if (status == 0) {//成功
                        that.getGroupShips(fleet.getGroup(gn)); //刷新该组的船舶表格数据
                        that.showGroupList(fleet.data); //刷新船队分组列表
                        that.hideEditShipView();
                    } else {//失败
                        Message.alert('删除船舶失败！');
                    }
                });
            });
        },
        //船舶换组修改，若组相同，则只修改船，否则换组
        changeGroup: function (shipId, cn, rmk, groupName, newGroupName) {
            if (groupName != newGroupName) {//当换组
                var that = this;
                fleet.delShip(shipId, groupName, function (status) {
                    if (status == 0) {//成功
                        that.getGroupShips(fleet.getGroup(groupName)); //刷新该组的船舶表格数据
                        that.showGroupList(fleet.data); //刷新船队分组列表
                        that.modifyShip(shipId, cn, rmk, newGroupName); //再修改该船舶，将该船加入新组内
                    } else {//失败
                        Message.alert('修改船舶失败！');
                    }
                });
            } else {//当不换组
                this.modifyShip(shipId, cn, rmk, groupName); //直接修改该船的信息
            }
        },
        //显示修改密码窗口
        showModifyPwdView: function () {
            if (!modifyPwdView) {
                modifyPwdView = new XWin({
                    dragbody: true,
                    title: '修改密码',
                    width: 230,
                    content: '<div class="panel_content">' +
                    '<table class="mtable">' +
                    '<tr><td align="right"><label for="newPwd">新密码：</label></td><td><input id="newPwd" type="text"/></td></tr>' +
                    '<tr><td/><td><input class="button" type="button" value="确 认" onclick="Manager.modifyPwd(util.getElement(\'newPwd\').value);return false;"/>' +
                    '<input class="button" type="button" value="取 消" onclick="Manager.hideModifyPwdView();return false;"/></td></tr>' +
                    '</table>' +
                    '</div>'
                });
            }
            modifyPwdView.show();
        },
        //关闭修改密码窗口
        hideModifyPwdView: function () {
            if (modifyPwdView && !modifyPwdView.ishide()) {
                modifyPwdView.hide();
            }
        },
        //修改密码
        modifyPwd: function (newPwd) {
            if (!newPwd) {
                Message.alert('新密码不能为空！');
                return;
            }
            if (!/^\w+$/.test(newPwd)) {
                Message.alert('密码输入非法，只能是数字和英文字母');
                return;
            }
            //API管理修改密码
            shipxyAPI.modifyPassword(newPwd, function (status) {
                if (status == 0) {
                    Manager.hideModifyPwdView();
                    Message.alert('修改密码成功');
                } else {
                    Message.alert('修改密码失败！');
                }
            });
        }
    }
})();