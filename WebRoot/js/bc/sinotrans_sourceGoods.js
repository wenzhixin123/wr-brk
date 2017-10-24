/**
 * $("#").sinotrans_sourceGoods({
 * 		path:'<%=path%>',//上下文根	(系统上下文根)	必须
 * 		key:'sourceGoodsGrid',//需要被赋值的datagrid的id	 必须	
 * 		type:'<%=HUB_CommonUtil.TRANSACTIONTYPE_PDN%>'//单据类型 必须
 *	});
 */
$.fn.sinotrans_sourceGoods=function(opt){
	var selectObj=this;
	selectObj.opts={};
	selectObj.opts=$.extend(selectObj.opts,opt);
	var divId=selectObj.opts.divId;
	var singleton=false;
	if(divId==null||''==divId){
		divId=new UUID().id;
		selectObj.opts.divId=divId;
	}
	var load=function(){
		var url=selectObj.opts.path
		 		+"/jsp/components/sourceGoods.jsp?divId="+divId+"&key="
		 		+selectObj.opts.key+"&type="+selectObj.opts.type;
		if ($("#" + divId).length > 0) {
			$("#" + divId).empty();
		} else {
			$('body')
					.append(
							'<div id="' + divId + '" class="easyui-window" closed="true" modal="true" title="选择" collapsible="false"></div>');
		}

		$("#" + divId)
				.append(
						'<iframe scrolling="no" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>');
		$('#' + divId + '').window( {
			width : 650,
			height : 410,
			top : ($(window).height() - 410) * 0.5,
			left : ($(window).width() - 650) * 0.5,
			maximizable : false,
			minimizable : false
		});
	}
	$(selectObj).unbind("click").click(function() {
		if(!singleton){
			load();			
		}
		singleton = true;
		$('#' + divId + '').window('open');
	});
	return this;
}
function UUID() {
	this.id = this.createUUID();
}
UUID.prototype.valueOf = function() {
	return this.id;
}

UUID.prototype.toString = function() {
	return this.id;
}

UUID.prototype.createUUID = function() {
	var dg = new Date(1970, 10, 15, 0, 0, 0, 0);
	var dc = new Date();
	var t = dc.getTime() - dg.getTime();
	var tl = UUID.getIntegerBits(t, 0, 31);
	var tm = UUID.getIntegerBits(t, 32, 47);
	var thv = UUID.getIntegerBits(t, 48, 59) + '1'; // version 1, security
	var csar = UUID.getIntegerBits(UUID.rand(4095), 0, 7);
	var csl = UUID.getIntegerBits(UUID.rand(4095), 0, 7);
	var n = UUID.getIntegerBits(UUID.rand(8191), 0, 7)
			+ UUID.getIntegerBits(UUID.rand(8191), 8, 15)
			+ UUID.getIntegerBits(UUID.rand(8191), 0, 7)
			+ UUID.getIntegerBits(UUID.rand(8191), 8, 15)
			+ UUID.getIntegerBits(UUID.rand(8191), 0, 15); // this last number
	// is two
	return tl + tm + thv + csar + csl + n;
}
UUID.getIntegerBits = function(val, start, end) {
	var base16 = UUID.returnBase(val, 16);
	var quadArray = new Array();
	var quadString = '';
	var i = 0;
	for (i = 0; i < base16.length; i++) {
		quadArray.push(base16.substring(i, i + 1));
	}
	for (i = Math.floor(start / 4); i <= Math.floor(end / 4); i++) {
		if (!quadArray[i] || quadArray[i] == '')
			quadString += '0';
		else
			quadString += quadArray[i];
	}
	return quadString;
}
UUID.returnBase = function(number, base) {
	return (number).toString(base).toUpperCase();
}
UUID.rand = function(max) {
	return Math.floor(Math.random() * (max + 1));
}