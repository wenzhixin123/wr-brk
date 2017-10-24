/* Name:		easyUI_extention.js
 * Note:		扩展easyUI各个组件的功能,以适应开发需求
 * UNC:			通用命名规范,无
 * Author:		huangkf
 */

$.extend($.fn.datagrid.methods,{
	getAllSelected:function(jq){
		//获得所有已选中的行对象数据,无论是新增行,还是编辑行,只要选中了,就返回数据
		jq.datagrid("endAllEdits");
		var rows = [];
		$('.datagrid-view2 .datagrid-body tr.datagrid-row-selected', jq.datagrid("getPanel")).each(function(){
			var index = parseInt($(this).attr('datagrid-row-index'));
			if (jq.datagrid("getData").rows[index]){
				rows.push(jq.datagrid("getData").rows[index]);
			}
		});
		return rows;
	},
	getEditRowsIndex:function(jq){
		//获得所有正在编辑中的行序号(rowIndex),返回一个数组
		var _i=[];
        var trs=$(jq.datagrid("getPanel")
        	.find("div.datagrid-view2").find("div.datagrid-body tr").filter(".datagrid-row-editing"));
        $.each(trs,function(){
        	_i.push($(this).attr("datagrid-row-index"));
        });
        return _i;
	},
	getEditRow:function(jq,rowIndex){
		//获得指定rowIndex的正在编辑中的行对象
		if(rowIndex==undefined||!jq.datagrid("validateRow",rowIndex)){
			return null;
		}
		jq.datagrid("endEdit",rowIndex);
		return $.data(jq[0], "datagrid").options.editConfig.getRow(jq[0], rowIndex);
	},
	getEditRowsData:function(jq){
		//获得所有正在编辑中的行对象数据
		var target=jq[0];
		var _rd=[];
		var editRowsIndex=jq.datagrid("getEditRowsIndex");
		$.each(editRowsIndex,function(){
			_rd.push(jq.datagrid("getEditRow",this))
		});
		return _rd;
	},
	endAllEdits:function(jq){
		//把所有正在编辑的行都结束编辑
		var rowIndex=jq.datagrid("getEditRowsIndex");
		$.each(rowIndex,function(){
			jq.datagrid("endEdit",this);
		});
	}
});

//扩展easyUI 的form ,获取表单中input框和各控件的数据,返回一个对象
//实例: var valueObj = $("#exampleForm").form("getFormData"); 返回的对象形如:{a:1,b:2,c:3}
//      而var valueObj =  $("#exampleForm").form("getFormData","__"); 得到 {__a:1,__b:2,__c:3}
$.extend($.fn.form.methods,{
	getFormData:function(jq,prefix){
		var allInput=$("input[id]:visible,input[comboname]",jq);
		//参数prefix为属性前缀,若有传入前缀,则在属性前加前缀,如前缀 __,默认为空
		if(!Boolean(prefix)){
			prefix = '';
		}
		//收集查询表单的数据,包括input框和各种控件
			var searchObj={};
			$.each(allInput,function(){
				//收集显式的input框的数据
				if($(this).is(":visible")&&this.id&&$(this).val()){
					searchObj[prefix+this.id]=$(this).val();
				}
				//收集easyUI不同控件的数据,控件必须继承了easyUI的combo控件,支持datebox,combobox,combogrid
				if($(this).attr("comboname")&&$(this).combo("getValue")){
					if($(this).prev("input[id]").attr("id")||$(this).next("input[id]").attr("id")){
						//情况1:使用隐藏域作为实际查询条件
						var idName;
						if($(this).prev("input[id]").attr("id")){idName=$(this).prev("input[id]").attr("id");}
						if($(this).next("input[id]").attr("id")){idName=$(this).next("input[id]").attr("id");}
						searchObj[prefix+idName]=$(this).combo("getValue");
					}else{
						//情况2:使用显式的input框作为实际查询条件
						searchObj[prefix+$(this).attr("comboname")]=$(this).combo("getValue");
					}
					
				}
			});
			return searchObj;
	},
	getFormDataByName:function(jq,prefix){
		var allInput=$("input[name]:visible,input[comboname]",jq);
		//参数prefix为属性前缀,若有传入前缀,则在属性前加前缀,如前缀 __,默认为空
		if(!Boolean(prefix)){
			prefix = '';
		}
		//收集查询表单的数据,包括input框和各种控件
			var searchObj={};
			$.each(allInput,function(){
				//收集显式的input框的数据
				if($(this).is(":visible")&&this.name&&$(this).val()){
					searchObj[prefix+this.name]=$(this).val();
				}
				//收集easyUI不同控件的数据,控件必须继承了easyUI的combo控件,支持datebox,combobox,combogrid
				if($(this).attr("comboname")&&$(this).combo("getValue")){
					if($(this).prev("input[id]").attr("id")||$(this).next("input[id]").attr("id")){
						//情况1:使用隐藏域作为实际查询条件
						var idName;
						if($(this).prev("input[id]").attr("name")){idName=$(this).prev("input[id]").attr("name");}
						if($(this).next("input[id]").attr("name")){idName=$(this).next("input[id]").attr("name");}
						searchObj[prefix+idName]=$(this).combo("getValue");
					}else{
						//情况2:使用显式的input框作为实际查询条件
						searchObj[prefix+$(this).attr("comboname")]=$(this).combo("getValue");
					}
					
				}
			});
			return searchObj;
	}
});