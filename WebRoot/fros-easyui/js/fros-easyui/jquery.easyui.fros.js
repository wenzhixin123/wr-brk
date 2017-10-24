
(function() {

	/********** DWR **********/
	dwr.message = {
			reLoginMsg : "Error invoking service. Please re-login and try again."
	};
	
	dwr.util.useLoadingMessage();
	
	dwr.engine.setErrorHandler(function(message, ex) {
		dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
		if (message == null || message == "") {
			$.messager.alert("Message", "A server error has occured.", "warning");
		} else if (message.indexOf("0x80040111") != -1) {
			// Ignore NS_ERROR_NOT_AVAILABLE if Mozilla is being narky
			dwr.engine._debug(message);
		} else {
			$.messager.alert("Message", message, "warning");
		}
	});
	
	dwr.engine.setTextHtmlHandler(function() {
		$.messager.alert("Message", dwr.message.reLoginMsg, "warning");
	});
	
	/********** selectCodeValues **********/
	$(function() {
		$(document).data("selectCodeValues", {});
	});

	
	/********** global shortcuts **********/
	//8:BackSpace; 9:Tab; 13:Return;
	//16:Shift; 17:Ctrl; 18:Alt;
	//27:Esc; 32:Space;
	//37:Left; 38:Up; 39:Right; 40:Down;
	//65-90:A-Z;
	document.onkeydown = function(event) {
		if (! event) {
			event = window.event;
		}
		if (event.keyCode == 8) {
			if (event.target && event.target.type != "text" && event.target.type != "textarea") {
				return false;
			}
			if (event.srcElement && event.srcElement.type != "text" && event.srcElement.type != "textarea") {
				return false;
			}
		}
		if (event.altKey && (event.keyCode == 37 || event.keyCode == 39)) {
			return false;
		}
		if (event.ctrlKey && event.keyCode >= 65 && event.keyCode <= 90) {
			var keyChar = String.fromCharCode(event.keyCode);
			if (keyChar != "C" && keyChar != "X" && keyChar != "V" && keyChar != "Z") {
				var $topWindow = $("body");
				$("div.panel[style*='z-index']:visible").each(function() {
					if ($(this).css("z-index") > ($topWindow.css("z-index") == "auto" ? 0 : $topWindow.css("z-index"))) {
						$topWindow = $(this);
					}
				});
				$topWindow.find("a[key='" + keyChar + "']:visible").click();
				return false;
			}
		}
	};

	$(function() {
		$(document).find("input:visible:first").focus();
	});

	
	/********** parser **********/
	$.parser.plugins.remove("datagrid");
	$.parser.plugins.remove("treegrid");
	$.parser.plugins.remove("combobox");
	$.parser.plugins.remove("combogrid");
	
	$.parser.onComplete = function(context) {
		//disable unwanted scrolling bars in Chrome
		$("[fit='true']", context).each(function() {
			$(this).parent().css("overflow", "hidden");
		});
		initForms(context);
		initDatagrids(context);
		initCombos(context);
		initComboboxes(context);
		initCombogrids(context);
		initLinkbuttons(context);
		initToolbars(context);
		initDialogs(context);
	};
	
	
	/********** datagrid **********/
	var datagridDefaults = {
		pagination : true,
		rownumbers : true,
		singleSelect : false,
		url : contextPath + "/JsonFacadeServlet",
		frozenColumns : [[{field:"ck", checkbox:true}]],
		
		refreshText : "Refresh",
		exportExcelCurrentPageText : "Export Excel (Current Page)",
		exportExcelAllText : "Export Excel (All)",
		exportExcelErrorMsg : "Can not export Excel for this table.",
		dataChangedMsg : "Data changed. Discard changes?",
		loadDataErrorMsg : "Error loading data.",
		reLoginMsg : "Error loading data. Please re-login and try again.",
		
		//query parameters
		onBeforeLoad : function(row, param) {
			//datagrid.onBeforeLoad(param)
			//treegrid.onBeforeLoad(row, param)
			if (! param) {
				param = row;
			}
			var $datagrid = $(this);
			//end edit before reload
			$datagrid.datagrid("forceEndEdit");
			if ($datagrid.datagrid("getChanges").length > 0) {
				if (confirm($.fn.datagrid.defaults.dataChangedMsg) == false) {
					return false;
				}
			}
			//query parameters
			var parameters = $datagrid.datagrid("options").parameters;
			if (! parameters) {
				return false;
			}
			if (! parameters.parameters.queryInfo.queryType) {
				$datagrid.datagrid("rejectChanges");
				return false;
			}
			var pagingInfo = null;
			if (param.rows) {
				pagingInfo = {
					pageSize : param.rows,
					currentPage : param.page
				};
			}
			$.extend(parameters.parameters.queryInfo, {
				orderBy : (param.sort ? param.sort + " " + param.order : undefined),
				pagingInfo : pagingInfo
			});
			for (var key in param) {
				delete param[key];
			}
			param.json_parameters = $.toJSON(parameters);
		},
		
		//query result
		loadFilter : function(data) {
			if (data.exception) {
				$.messager.alert("Message", $.fn.datagrid.defaults.loadDataErrorMsg + data.exception, "warning");
				return null;
			}
			if (data.result) {
				$.extend(true, $(document).data("selectCodeValues"), data.result.selectCodeValues);
				$.extend(data, {
					total : data.result.pagingInfo ? data.result.pagingInfo.totalRows : undefined,
					rows : data.result.dataList
				});
			}
			var $datagrid = $(this);
			if ($datagrid.hasClass("easyui-treegrid") && data.rows) {
				var parentIdField = $datagrid.treegrid("options").parentIdField;
				$.each(data.rows, function(index, row) {
					row._parentId = row[parentIdField];
				});
			}
			return data;
		},
		
		//init editors
		onLoadSuccess : function(row, data) {
			//datagrid.onLoadSuccess(data)
			//treegrid.onLoadSuccess(row, data)
			if (! data) {
				data = row;
			}
			if (! data.result && (! data.rows || data.rows.length == 0)) {
				return;
			}
			var target = this;
			setTimeout(function() {
				initDatagridEditors(target);
			}, 0);
			groupDatagrid(target, data.rows);
			$(this).datagrid("refreshFooter");
		},
		
		onLoadError : function() {
			$.messager.alert("Message", $.fn.datagrid.defaults.reLoginMsg, "warning");
		},

		//resize cached editor cell or viewer cell on column resizing
		onResizeColumn : function(field, width) {
			resizeDatagridEditor(this, field, width);
		},

		//fit columns width
		view : {
			_onAfterRender : $.fn.datagrid.defaults.view.onAfterRender,
			
			onAfterRender : function(target) {
				$.fn.datagrid.defaults.view._onAfterRender(target);
				fitColumnWidth(target);
			}
		},
		
		//formatters
		formatter : function (value, rowData, rowIndex) {
			if (this.codeType && value) {
				var selectCodeValues = $(document).data("selectCodeValues");
				var field = this.field;
				var codeType = this.codeType;
				if (selectCodeValues[codeType] && selectCodeValues[codeType][value]) {
					return selectCodeValues[codeType][value];
				} else {
					var selectCodeKeys = {};
					selectCodeKeys[codeType] = value;
					SelectCodeManager.getSelectCodeValuesByKeys(selectCodeKeys,
						{
							callback:function(result) {
								$.extend(true, selectCodeValues, result);
								try{
									if (value.split(",").length > 1) {
										var values = value.split(",");
										var texts = [];
										for (var i = 0; i < values.length; i++) {
											texts.push(result[codeType][values[i]] || values[i]);
										}
										selectCodeValues[codeType][value] = texts.join(",");
									}
									value=value.replace("（","(");
									value=value.replace("）",")");
									$("td[field='" + field + "'] > div:contains('" + value + "')").each(function(index, cell) {
										var $cell = $(cell);
										if ($cell.html() == value) {
											$cell.html(selectCodeValues[codeType][value] || value);
										}
									});
								}catch(ex){
									console.error("lw_   "+  value);
								}
							},
							errorHandler:function(message) {
									$.messager.alert("Message", message, "warning"); 
							}
						}
					
					);
				}
			}
			return value;
		},
		
		//editors
		editors : {
			datetimebox : {
				init : function(container, options) {
					var editor = $("<input type='text'>").appendTo(container);
					editor.datetimebox(options);
					return editor;
				},
				destroy : function(target) {
					$(target).datetimebox("destroy");
				},
				getValue : function(target) {
					return $(target).datetimebox("getValue");
				},
				setValue : function(target, value) {
					$(target).datetimebox("setValue", value);
				},
				resize : function(target, width) {
					$(target).datetimebox("resize", width);
				}
			},
			combogrid : {
				init : function(container, options) {
					var editor = $("<input type='text'>").appendTo(container);
					editor.combogrid(options);
					return editor;
				},
				destroy : function(target) {
					$(target).combogrid("destroy");
				},
				getValue : function(target) {
					return $(target).combogrid("getValue");
				},
				setValue : function(target, value) {
					$(target).combogrid("setValue", value);
				},
				resize : function(target, width) {
					$(target).combogrid("resize", width);
				}
			}
		},
		
		//click begins edit
		onClickRow : function(rowIndex, rowData) {
			var $datagrid = $(this);
			//datagrid.onClickRow(rowIndex, rowData)
			//treegrid.onClickRow(row)
			if ($datagrid.hasClass("easyui-treegrid")) {
				//treegrid
				var idField = $datagrid.treegrid("options").idField;
				rowData = rowIndex;
				var id = rowData[idField];
				var currentId = $datagrid.treegrid("getCurrentRow");
				if (currentId && currentId != id) {
					$datagrid.treegrid("endEdit", currentId);
				}
				$.each($datagrid.treegrid("getSelectionsId"), function(index, selectedId) {
					if (selectedId != id) {
						$datagrid.treegrid("unselect", selectedId);
						$datagrid.treegrid("endEdit", selectedId);
					}
				});
				$datagrid.treegrid("select", id);
				$datagrid.treegrid("beginEdit", id);
			} else {
				//datagrid
				if (! rowData) {
					$datagrid.datagrid("selectRow", rowIndex);
				}
				if ($datagrid.attr("singleSelect") == undefined) {
					var selectionsIndex = $datagrid.datagrid("getSelectionsIndex");
					if (selectionsIndex.length > 0 && selectionsIndex.indexOf(rowIndex) == -1) {
						$datagrid.datagrid("selectRow", rowIndex);
					}
					$.each(selectionsIndex, function(index, selectedIndex) {
						if (selectedIndex != rowIndex) {
							if ($datagrid.datagrid("validateRow", selectedIndex)) {
								$datagrid.datagrid("unselectRow", selectedIndex);
							}
						}
					});
				}
				if (! $datagrid.datagrid("options").singleSelect) {
					var currentRow = $datagrid.datagrid("getCurrentRow");
					if (currentRow != rowIndex
							&& $datagrid.datagrid("validateRow", currentRow)
							&& $datagrid.datagrid("getSelectionsIndex").indexOf(rowIndex) >= 0) {
						$datagrid.datagrid("endEdit", currentRow);
						$datagrid.datagrid("beginEdit", rowIndex);
					} else {
						$datagrid.datagrid("unselectRow", rowIndex);
						if (currentRow != undefined) {
							$datagrid.datagrid("selectRow", currentRow);
						}
					}
				}
			}
		},
		
		onSelect : function(rowIndex, rowData) {
			var $datagrid = $(this);
			if ($datagrid.datagrid("options").singleSelect) {
				var currentRow = $datagrid.datagrid("getCurrentRow");
				if (currentRow != rowIndex) {
					if ($datagrid.datagrid("validateRow", currentRow)) {
						$datagrid.datagrid("endEdit", currentRow);
						$datagrid.datagrid("beginEdit", rowIndex);
					} else {
						$datagrid.datagrid("unselectRow", rowIndex);
						if (currentRow != undefined) {
							$datagrid.datagrid("selectRow", currentRow);
						}
					}
				}
				if ($datagrid.data("lastSelectedIndex") == rowIndex
						&& $datagrid.datagrid("validateRow", currentRow)) {
					$datagrid.datagrid("unselectRow", rowIndex);
					$datagrid.data("lastSelectedIndex", null);
				} else {
					$datagrid.data("lastSelectedIndex", rowIndex);
				};
			}
		},
		
		//unselect ends edit
		onUnselect : function(rowIndex, rowData) {
			var $datagrid = $(this);
			var currentRow = $datagrid.datagrid("getCurrentRow");
			if (currentRow != undefined && currentRow == rowIndex) {
				if ($datagrid.datagrid("validateRow", currentRow)) {
					$datagrid.datagrid("endEdit", currentRow);
				} else {
					$datagrid.datagrid("selectRow", currentRow);
				}
			}
		},
		
		onDblClickRow : function(rowIndex, rowData) {
			var $datagrid = $(this);
			$datagrid.datagrid("selectRow", rowIndex);
		},
		
		onHeaderContextMenu : function(e, field) {
			e.preventDefault();
		},
		
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			var $datagrid = $(this);
			var rowContextMenu = $datagrid.data("rowContextMenu");
			if (! rowContextMenu) {
				var rowContextMenu = $("<div id='tmenu' style='width:200px;'>" +
						"<div id='Refresh'>" + $.fn.datagrid.defaults.refreshText + "</div>" +
						"<div class='menu-sep'></div>" +
						"<div id='ExportExcelCurrentPage'>" + $.fn.datagrid.defaults.exportExcelCurrentPageText + "</div>" +
						"<div id='ExportExcelAll'>" + $.fn.datagrid.defaults.exportExcelAllText + "</div>" +
				"</div>").appendTo("body").menu({
					onClick : function(item) {
						switch (item.id) {
						case "Refresh" :
							$datagrid.datagrid("reload");
							break;
						case "ExportExcelCurrentPage" :
							$datagrid.datagrid("exportExcel", true);
							break;
						case "ExportExcelAll" :
							$datagrid.datagrid("exportExcel");
							break;
						default :
						}
					}
				});
				$datagrid.data("rowContextMenu", rowContextMenu);
			}
			rowContextMenu.menu("show", {
				left : e.pageX,
				top : e.pageY
			});
		},
		
		onAfterEdit : function(rowIndex, rowData, changes) {
			if (! $.isEmptyObject(changes)) {
				$(this).datagrid("refreshFooter");
			}
		},
		
		onBeginEdit : function(rowIndex, rowData) {
		}
		
	};
	
	$.extend(true, $.fn.datagrid.defaults, datagridDefaults);
	
	$.extend(true, $.fn.treegrid.defaults, datagridDefaults, {
		view : {
			_render : $.fn.treegrid.defaults.view.render,
			
			render : function(target, container, frozen) {
				if (this.treeNodes) {
					this._render(target, container, frozen);
				} else {
					$(container).append("<e cellspacing='0' cellpadding='0' border='0'><tbody></tbody></table>");
				}
			}
		}
	});

	//use cached editors
	for (var editorType in $.fn.datagrid.defaults.editors) {
		var editor = $.fn.datagrid.defaults.editors[editorType];
		$.extend(editor, {
			_init : editor.init,
			init : function(container, options) {
				var $datagrid = container.closest(".datagrid-view").find("table.easyui-datagrid,table.easyui-treegrid");
				var field = container.closest("td[field]").attr("field");
				var editorTargets = $datagrid.data("editorTargets");
				$.each(editorTargets[field], function(index, $element) {
					$element.appendTo(container);
				});
				return editorTargets[field][0];
			},
			destroy : function(target) {
				$(target).parent().children().each(function() {
					$(this).detach();
				});
			},
			_getValue : editor.getValue,
			getValue : function(target) {
				var value = this._getValue(target);
				if ($.trim(value) == "") {
					return null;
				}
				return value;
			},
			_resize : editor.resize,
			resize : function(target, width) {
				var $target = $(target);
				if (this._resize && $target.data("width") != width && $target.width() != width) {
					$target.data("width", width);
					this._resize(target, width);
				}
			}
		});
	}

	//datagrid methods
	$.extend($.fn.datagrid.methods, {
		//commonQuery
		commonQuery : function(jq, param){
			return jq.each(function() {
				var $datagrid = $(this);
				var options = $datagrid.datagrid("options");
				var queryFields = [];
				if (options.queryFields) {
					queryFields = queryFields.concat(options.queryFields);
				}
				var handledMultiples = [];
				$("#" + param.paramForm + " input[name]").each(function() {
					var $input = $(this);
					var name = $input.attr("name");
					var value = this.value;
					if (! value || handledMultiples.indexOf(name) >= 0) {
						return;
					}
					var fieldType;
					var operator = $input.attr("operator");
					if ($input.hasClass("combo-value")) {
						var $comboInput = $input.parent().prev();
						if ($comboInput.hasClass("easyui-datebox") || $comboInput.hasClass("easyui-datetimebox")) {
							fieldType = "Date";
						}
						operator = $comboInput.attr("operator");
						if ($comboInput.attr("multiple")) {
							var values = [];
							$("#" + param.paramForm + " input[name='" + name + "']").each(function() {
								values.push(this.value);
							});
							queryFields.push({
								fieldName : name,
								fieldType : "String[]",
								fieldStringValue : values.join(","),
								operator : operator
							});
							handledMultiples.push(name);
							return;
						}
					}
					if ($input.attr("type") == "checkbox") {
						operator = $input.closest("[operator]").attr("operator");
						var values = [];
						$("#" + param.paramForm + " input[name='" + name + "']:checked").each(function() {
							values.push($(this).attr("value"));
						});
						queryFields.push({
							fieldName : name,
							fieldType : "String[]",
							fieldStringValue : values.join(","),
							operator : operator
						});
						handledMultiples.push(name);
						return;
					}
					if ($input.attr("type") == "radio") {
						operator = $input.closest("[operator]").attr("operator");
						value = $("#" + param.paramForm + " input[name='" + name + "']:checked").attr("value");
						queryFields.push({
							fieldName : name,
							fieldStringValue : value,
							operator : operator
						});
						handledMultiples.push(name);
						return;
					}
					queryFields.push({
						fieldName : name,
						fieldType : fieldType,
						fieldStringValue : value,
						operator : operator
					});
				});
				$.extend(options.parameters.parameters.queryInfo, {
					queryType : param.queryType,
					queryFields : queryFields
				});
				if ($datagrid.hasClass("easyui-treegrid")) {
					$datagrid.treegrid("reload");
				} else {
					$datagrid.datagrid("load");
				}
			});
		},
		
		setQueryFields : function(jq, queryFields) {
			return jq.each(function() {
				$(this).datagrid("options").queryFields = queryFields;
			});
		},
		
		fitColumnWidth : function(jq) {
			return jq.each(function() {
				fitColumnWidth(this);
			});
		},
		
		getSelectedIndex : function(jq) {
			var $datagrid = $(jq[0]);
			return $datagrid.datagrid("getRowIndex", $datagrid.datagrid("getSelected"));
		},
		
		getSelectionsIndex : function(jq) {
			var $datagrid = $(jq[0]);
			var rows = $datagrid.datagrid("getSelections");
			var selectionsIndex = [];
			for (var i = 0; i < rows.length; i++) {
				selectionsIndex.push($datagrid.datagrid("getRowIndex", rows[i]));
			}
			return selectionsIndex;
		},
		
		setCurrentRow : function(jq, index) {
			return jq.each(function() {
				if (index == undefined) {
					$(this).removeData("currentRow");
				} else {
					$(this).data("currentRow", index);
				}
			});
		},
		
		getCurrentRow : function(jq) {
			return $(jq[0]).data("currentRow");
		},
		
		//override methods to cache datagrid editors
		_beginEdit : function(jq, index) {
			return jq.each(function() {
				beginEdit(this, index);
			});
		},
		_endEdit : function(jq, index) {
			return jq.each(function() {
				stopEdit(this, index, false);
			});
		},
		_cancelEdit : function(jq, index) {
			return jq.each(function() {
				stopEdit(this, index, true);
			});
		},

		beginEdit : function(jq, index) {
			var $datagrid = $(jq[0]);
			if ($datagrid.datagrid("getCurrentRow") == undefined) {
				if ($datagrid.data("editorsInitOk")) {
					$datagrid.datagrid("setCurrentRow", index);
					$datagrid.datagrid("_beginEdit", index);
					if ($datagrid.datagrid("options").onBeginEdit) {
						$datagrid.datagrid("options").onBeginEdit.apply(jq[0], [index, $datagrid.datagrid("getRows")[index]]);
					}
				} else {
					$datagrid.data("toStartEdit", index);
				}
			}
			return jq;
		},
		
		endEdit : function(jq, index) {
			var $datagrid = $(jq[0]);
			if (index == undefined) {
				index = $datagrid.datagrid("getCurrentRow");
			}
			if ($datagrid.datagrid("validateRow", index)) {
				$datagrid.datagrid("setCurrentRow", undefined);
				$datagrid.datagrid("_endEdit", index);
			}
			return jq;
		},
		
		cancelEdit : function(jq, index) {
			var $datagrid = $(jq[0]);
			if (index == undefined) {
				index = $datagrid.datagrid("getCurrentRow");
			}
			$datagrid.datagrid("setCurrentRow", undefined);
			$datagrid.datagrid("_cancelEdit", index);
			return jq;
		},
		
		forceEndEdit : function(jq, index) {
			var $datagrid = $(jq[0]);
			if (index == undefined) {
				index = $datagrid.datagrid("getCurrentRow");
			}
			$datagrid.datagrid("setCurrentRow", undefined);
			if (index == undefined) {
				return jq;
			}
			if ($datagrid.datagrid("validateRow", index)) {
				$datagrid.datagrid("_endEdit", index);
			} else {
				$datagrid.datagrid("_cancelEdit", index);				
			}
			return jq;
		},
		
		validate : function(jq) {
			var $datagrid = $(jq[0]);
			var index = $datagrid.datagrid("getCurrentRow");
			if (index != undefined) {
				return $datagrid.datagrid("validateRow", index);
			}
			return true;
		},
		
		_appendRow : $.fn.datagrid.methods.appendRow,
		
		appendRow : function(jq, row) {
			this.forceEndEdit(jq);
			return jq.each(function() {
				var $datagrid = $(this);
				$datagrid.datagrid("_appendRow", row);
				//fit columns width
				fitColumnWidth(this);
				var rowcount = $datagrid.datagrid("getRows").length;
				if (rowcount == 1) {
					//init editors
					initDatagridEditors(this);
				}
				//select and edit
				$.fn.datagrid.defaults.onClickRow.apply(this, [rowcount - 1]);
			});
		},
		
		_insertRow : $.fn.datagrid.methods.insertRow,
		
		insertRow : function(jq, param) {
			if (param.index == -1) {
				param.index = 0;
			}
			this.forceEndEdit(jq);
			return jq.each(function() {
				var $datagrid = $(this);
				$datagrid.datagrid("_insertRow", param);
				//fit columns width
				fitColumnWidth(this);
				var rowcount = $datagrid.datagrid("getRows").length;
				if (rowcount == 1) {
					//init editors
					initDatagridEditors(this);
				}
				//select and edit
				$.fn.datagrid.defaults.onClickRow.apply(this, [param.index]);
			});
		},
		
		_updateRow : $.fn.datagrid.methods.updateRow,
		
		updateRow : function(jq, param) {
			this.forceEndEdit(jq);
			var target = jq[0];
			var opts = $.data(target, "datagrid").options;
			var insertedRows = $.data(target, "datagrid").insertedRows;
			var updatedRows = $.data(target, "datagrid").updatedRows;
			if (insertedRows.indexOf(param.row) == -1) {
				var oldRow = opts.editConfig.getRow(target, param.index);
				if (insertedRows.indexOf(oldRow) == -1) {
					if (oldRow == param.row) {
						//no way to check if the row has been changed
						updatedRows.push(param.row);
					} else {
						var changed = false;
						for (var field in oldRow) {
							if (oldRow[field] != param.row[field]) {
								changed = true;
								break;
							}
						};
						$.extend(oldRow, param.row);
						param.row = oldRow;
						if (changed) {
							if (updatedRows.indexOf(param.row) == -1) {
								updatedRows.push(param.row);
							}
						}
					}
				}
			}
			this._updateRow(jq, param);
			this.refreshFooter(jq);
			return jq;
		},
		
		_deleteRow : $.fn.datagrid.methods.deleteRow,
		
		deleteRow : function(jq, index) {
			this.forceEndEdit(jq);
			this._deleteRow(jq, index);
			this.refreshFooter(jq);
			return jq;
		},
		
		deleteSelectedRows : function(jq) {
			this.forceEndEdit(jq);
			return jq.each(function() {
				var $datagrid = $(this);
				$.each($datagrid.datagrid("getSelectionsIndex").reverse(), function(index, selectedIndex) {
					$datagrid.datagrid("_deleteRow", selectedIndex);
				});
				$datagrid.datagrid("refreshFooter");
			});
		},
		
		_loadData : $.fn.datagrid.methods.loadData,
		
		loadData : function(jq, data) {
			this.forceEndEdit(jq);
			return this._loadData(jq, data);
		},
		
		_getData : $.fn.datagrid.methods.getData,
		
		getData : function(jq) {
			this.forceEndEdit(jq);
			return this._getData(jq);
		},
		
		_getChanges : $.fn.datagrid.methods.getChanges,
		
		getChanges : function(jq, type) {
			this.forceEndEdit(jq);
			var insertedRows = this._getChanges(jq, "inserted");
			var updatedRows = this._getChanges(jq, "updated");
			var deletedRows = this._getChanges(jq, "deleted");
			$.each(insertedRows, function(index, row) {
				row.rowState = "Added";
			});
			$.each(updatedRows, function(index, row) {
				row.rowState = "Modified";
			});
			$.each(deletedRows, function(index, row) {
				row.rowState = "Deleted";
			});
			if (! type) {
				var rows = [];
				rows = rows.concat(insertedRows);
				rows = rows.concat(updatedRows);
				rows = rows.concat(deletedRows);
				return rows;
			} else {
				switch (type) {
				case "inserted" :
					return insertedRows;
				case "updated" :
					return updatedRows;
				case "deleted" :
					return deletedRows;
				default :
					return [];
				}
			}
		},
		
		refreshSavedData : function(jq, savedRows) {
			return jq.each(function() {
				var $datagrid = $(this);
				var insertedRows = $datagrid.datagrid("_getChanges", "inserted");
				var updatedRows = $datagrid.datagrid("_getChanges", "updated");
				var refreshRows = [].concat(insertedRows).concat(updatedRows);
				for (var i = 0; i < refreshRows.length; i++) {
					var index = $datagrid.datagrid("getRowIndex", refreshRows[i]);
					$datagrid.datagrid("_updateRow", {
						index : index,
						row : savedRows[i]
					});
				}
				$datagrid.datagrid("acceptChanges");
				$datagrid.datagrid("refreshFooter");
			});
		},
		
		getColumnEditor : function(jq, field) {
			return $(jq[0]).data("editorTargets")[field][0];
		},
		
		setGroupField : function(jq, groupField) {
			return jq.each(function() {
				var $datagrid = $(this);
				$datagrid.datagrid("options").groupField = groupField;
			});
		},
		
		exportExcel : function(jq, currentPageOnly) {
			return jq.each(function() {
				var $datagrid = $(this);
				var queryParameters = $datagrid.datagrid("options").parameters;
				if (! queryParameters.parameters.queryInfo.queryType) {
					$.messager.alert("Message", $.fn.datagrid.defaults.exportExcelErrorMsg, "warning");
					return;
				}
				var exportParameters = {
						serviceName : "commonQueryManager",
						methodName : "exportExcel",
						parameters : {
							title : "DATA",
							queryInfo : $.extend(true, {}, queryParameters.parameters.queryInfo),
							fieldDefinitions : []
						}
				};
				if (! currentPageOnly) {
					exportParameters.parameters.queryInfo.pagingInfo = null;
				}
				var $headerTr = $datagrid.parent().find(".datagrid-view2 .datagrid-header tr"); 
				$.each($datagrid.datagrid("getColumnFields"), function(index, field) {
					var columnOption = $datagrid.datagrid("getColumnOption", field);
					exportParameters.parameters.fieldDefinitions.push({
						fieldName : columnOption.field,
						label : columnOption.title,
						width : $headerTr.find("td[field='" + columnOption.field + "']").width() + 10
					});
				});
				var json_parameters = $.toJSON(exportParameters);
				//send request
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", contextPath + "/JsonFacadeServlet");
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "json_parameters");
				hiddenField.setAttribute("value", json_parameters);
				form.appendChild(hiddenField);
				document.body.appendChild(form);
				form.submit();
			});
		},
		
		refreshFooter : function(jq) {
			return jq.each(function() {
				var $datagrid = $(this);
				if (! $datagrid.attr("showFooter")) {
					return;
				}
				$datagrid.datagrid("forceEndEdit");
				var footerRows = [];
				$datagrid.find("tfoot tr").each(function(indextf, tr) {
					var $tr = $(tr);
					var footerRow = {};
					$tr.find("td").each(function(indextd, td) {
						var $td = $(td);
						var field = $td.attr("field");
						var value = 0;
						var footerType = $td.attr("footerType");
						switch (footerType) {
						case "count" :
							value = $datagrid.datagrid("getRows").length;
							break;
						case "sum" :
							$.each($datagrid.datagrid("getRows"), function(rowIndex, row) {
								value += isNaN(+row[field]) ? 0 : +row[field];
							});
							break;
						case "average" :
							var count = $datagrid.datagrid("getRows").length;
							if (count == 0) {
								value = 0;
							} else {
								$.each($datagrid.datagrid("getRows"), function(rowIndex, row) {
									value += isNaN(+row[field]) ? 0 : +row[field];
								});
								value = value / count;
							}
							break;
						default :
							value = $td.html();
						}
						footerRow[field] = value;
					});
					footerRows.push(footerRow);
				});
				$datagrid.datagrid("reloadFooter", footerRows);
			});
		}
		
	});

	//treegrid methods
	$.extend($.fn.treegrid.methods, {
		
		getSelectedId : function(jq) {
			var $datagrid = $(jq[0]);
			var idField = $datagrid.treegrid("options").idField;
			return $datagrid.treegrid("getSelected")[idField];
		},
		
		getSelectionsId : function(jq) {
			var $datagrid = $(jq[0]);
			var idField = $datagrid.treegrid("options").idField;
			var rows = $datagrid.treegrid("getSelections");
			var selectionsId = [];
			for (var i = 0; i < rows.length; i++) {
				selectionsId.push(rows[i][idField]);
			}
			return selectionsId;
		},
		
		_append : $.fn.treegrid.methods.append,
		
		append : function(jq, param) {
			return jq.each(function() {
				var $datagrid = $(this);
				var idField = $datagrid.treegrid("options").idField;
				var parentIdField = $datagrid.treegrid("options").parentIdField;
				var treeField = $datagrid.treegrid("options").treeField;
				if (! $.isArray(param.data)) {
					param.data = [param.data];
				}
				$.each(param.data, function(index, row) {
					row[parentIdField] = param.parent;
					if (! row[idField]) {
						row[idField] = "id_" + new Date().getTime();
					}
					if (! row[treeField]) {
						row[treeField] = "new";
					}
				});
				$datagrid.treegrid("endEdit");
				$datagrid.treegrid("_append", param);
				//fit columns width
				fitColumnWidth(this);
				var rowcount = $datagrid.datagrid("getData").rows.length;
				if (rowcount == 0) {
					//init editors
					initDatagridEditors(this);
				}
				//select and edit
				$.fn.datagrid.defaults.onClickRow.apply(this, [param.data[0]]);
				$.data(this, "datagrid").insertedRows.push(param.data[0]);
			});
		},
		
		updateRow : function(jq, param) {
			var $datagrid = $(jq[0]);
			$datagrid.treegrid("endEdit");
			var target = jq[0];
			var opts = $.data(target, "datagrid").options;
			var insertedRows = $.data(target, "datagrid").insertedRows;
			var updatedRows = $.data(target, "datagrid").updatedRows;
			if (insertedRows.indexOf(param.row) == -1) {
				var oldRow = opts.editConfig.getRow(target, param.id);
				if (oldRow == param.row) {
					//no way to check if the row has been changed
					updatedRows.push(param.row);
				} else {
					var changed = false;
					for (var field in oldRow) {
						if (oldRow[field] != param.row[field]) {
							changed = true;
							break;
						}
					};
					$.extend(oldRow, param.row);
					param.row = oldRow;
					if (changed) {
						if (updatedRows.indexOf(param.row) == -1) {
							updatedRows.push(param.row);
						}
					}
				}
			}
			return $datagrid.treegrid("refresh", param.id);
		},

		__remove : $.fn.treegrid.methods.remove,
		
		_remove : function(jq, id) {
			return jq.each(function() {
				var $datagrid = $(this);
				var insertedRows = $datagrid.treegrid("_getChanges", "inserted");
				var deletedRows = $datagrid.treegrid("_getChanges", "deleted");
				var row = $datagrid.treegrid("find", id);
				markDeletedRows(row);
				function markDeletedRows(row) {
					if (insertedRows.indexOf(row) >= 0) {
						insertedRows.remove(row);
					} else {
						if (deletedRows.indexOf(row) == -1) {
							deletedRows.push(row);
						}
					}
					if (row.children) {
						$.each(row.children, function(index, row) {
							markDeletedRows(row);
						});
					}
				};
				$datagrid.treegrid("__remove", id);
			});
		},
		
		remove : function(jq, id) {
			return jq.each(function() {
				var $datagrid = $(this);
				$datagrid.treegrid("endEdit").treegrid("_remove", id);
			});
		},
		
		removeSelectedNodes : function(jq) {
			return jq.each(function() {
				var $datagrid = $(this);
				$datagrid.treegrid("endEdit");
				$.each($datagrid.treegrid("getSelectionsId"), function(index, selectedId) {
					$datagrid.treegrid("_remove", selectedId);
				});
			});
		},
		
		_loadData : $.fn.datagrid.methods.loadData,
		
		loadData : function(jq, data) {
			this.forceEndEdit(jq);
			return this._loadData(jq, data);
		},
		
		getChanges : function(jq, type) {
			var $datagrid = $(jq[0]);
			var idField = $datagrid.treegrid("options").idField;
			var parentIdField = $datagrid.treegrid("options").parentIdField;
			var rows = $datagrid.datagrid("getChanges", type);
			$.each(rows, function(index, row) {
				fixChildrenParentId(row);
			});
			function fixChildrenParentId(row) {
				if (row.children) {
					$.each(row.children, function(childIndex, childRow) {
						childRow[parentIdField] = row[idField];
						fixChildrenParentId(childRow);
					});
				}
			};
			return rows;
		},
		
		refreshSavedData : function(jq, savedRows) {
			return jq.each(function() {
				var $datagrid = $(this);
				var idField = $datagrid.treegrid("options").idField;
				var insertedRows = $datagrid.treegrid("_getChanges", "inserted");
				var updatedRows = $datagrid.treegrid("_getChanges", "updated");
				var refreshRows = [].concat(insertedRows).concat(updatedRows);
				for (var i = 0; i < refreshRows.length; i++) {
					$datagrid.datagrid("getPanel").find("tr[node-id='" + refreshRows[i][idField] + "']").attr("node-id", savedRows[i][idField]);
					$.extend(refreshRows[i], savedRows[i]);
					$datagrid.treegrid("refresh", savedRows[i][idField]);
				}
				$datagrid.datagrid("acceptChanges");
			});
		}
		
	});
	
	//init datagrids
	function initDatagrids(context) {
		$(".easyui-datagrid,.easyui-treegrid", context).each(function() {
			var $datagrid = $(this);
			var frozenColumns = getColumns($("thead[frozen=true]", this));
			var columns = getColumns($("thead[frozen!=true]", this));
			var allColumns = [];
			for (var i = 0; i < frozenColumns.length; i++) {
				allColumns = allColumns.concat(frozenColumns[i]);
			}
			for (var i = 0; i < columns.length; i++) {
				allColumns = allColumns.concat(columns[i]);
			}
			var fieldCodeTypes = {};
			for (var i = 0; i < allColumns.length; i++) {
				var columnOption = allColumns[i];
				var $th = $datagrid.find("th[field='" + columnOption.field + "']");
				$.extend(columnOption, {
					title : getI18nTitle($datagrid.attr("i18nRoot"), columnOption.field, $th.attr("title")),
					sortable : true,
					formatter : (columnOption.formatter || $.fn.datagrid.defaults.formatter),
					codeType : $th.attr("codeType")
				});
				if (columnOption.codeType) {
					fieldCodeTypes[columnOption.field] = columnOption.codeType;
				}
			}
			var options = {
					frozenColumns : frozenColumns.length > 0 ? frozenColumns : $.fn.datagrid.defaults.frozenColumns,
					columns : columns,
					parameters : {
						serviceName : "commonQueryManager",
						methodName : "query",
						parameters : {
							queryInfo : {
								fieldCodeTypes : fieldCodeTypes
							}
						}
					},
					groupField : $datagrid.attr("groupField"),
					parentIdField : $datagrid.attr("parentIdField")
			};
			if ($datagrid.hasClass("easyui-treegrid")) {
				$datagrid.treegrid(options);
			} else {
				$datagrid.datagrid(options);
			}
			//move the toolbar into gridpanel
			$datagrid.closest(".datagrid").parent().find(".datagrid-toolbar").prependTo($datagrid.parent().parent());
			$datagrid.datagrid("refreshFooter");
		});
	};


	/********** combo **********/
	$.fn._combo = $.fn.combo;

	$.fn.combo = function(options, param) {
		var result = $.fn._combo.apply(this, [options, param]);
		if (typeof options != "string") {
			this.each(function() {
				var target = this;
				var state = $.data(target,"combo");
				var opts = $.data(target, "combo").options;
				var combo = $.data(target, "combo").combo;
				var input = combo.find(".combo-text");
				//In FireFox input chinese will not fire 'keydown' event.
				//But 2 'input' events with the same event.target.value will be fired.
				//So we can start the timer at the second time.
				input.unbind("input").bind("input", function(event) {
					if ($(event.target).data("inputPreviousValue") == event.target.value) {
						if (opts.editable) {
							if(state.timer){
								clearTimeout(state.timer);
							}
							state.timer = setTimeout(function() {
								var q = input.val();
								if (state.previousValue != q) {
									state.previousValue = q;
									$(target).combo("showPanel");
									opts.keyHandler.query.call(target, input.val());
									$(target).combo("validate");
								}
							}, opts.delay);
						}
					}
					$(event.target).data("inputPreviousValue", event.target.value);
				});
				var $target = $(target);
				if (($target.hasClass("combobox-f") || $target.hasClass("combogrid-f"))
						&& $target.attr("customValuePermitted") != "true"
						&& ! $target.combo("options").customValuePermitted
						&& ! $target.combo("options").multiple) {
					input.unbind("keydown.combo").bind("keydown.combo", function(e) {
						switch (e.keyCode) {
						case 38 :  // up
							opts.keyHandler.up.call(target);
							break;
						case 40 :  // down
							opts.keyHandler.down.call(target);
							break;
						case 13 :  // enter
							e.preventDefault();
							opts.keyHandler.enter.call(target);
							return false;
						case 9 :   //TAB
							//Do nothing. onBlur will run
							$target.data("tabEvent", true);
							break;
						case 27 :  // esc
							$target.combo("hidePanel");
							break;
						default :
							if (opts.editable) {
								if(state.timer){
									clearTimeout(state.timer);
								}
								state.timer = setTimeout(function() {
									var q = input.val();
									if (state.previousValue != q) {
										state.previousValue = q;
										$target.combo("showPanel");
										opts.keyHandler.query.call(target, input.val());
										$target.combo("validate");
									}
								}, opts.delay);
							}
						}
					});
					input.unbind("focus").bind("focus", function(event) {
						$target.data("oldValues", $target.combo("getValues"));
						$target.data("oldText", $target.combo("getText"));
					}).unbind("blur").bind("blur", function(event) {
						setTimeout(function() {
							if (event.target.value) {
								if ($target.hasClass("combobox-f")) {
									if ($target.combobox("panel").find("div.combobox-item-selected").size() == 0) {
										if ($target.data("oldValues")) {
											$target.combobox("setValues", $target.data("oldValues"));
											$target.combobox("setText", $target.data("oldText"));
										}
									}
								} else if ($target.hasClass("combogrid-f")) {
									function checkNull() {
										if ($target.combogrid("grid").data("loading")) {
											setTimeout(function() {
												checkNull();
											}, 100);
										} else {
											if ($target.combogrid("grid").datagrid("getSelectedIndex") == -1) {
												if ($target.data("oldValues") && ! $target.data("reloaded")) {
													$target.combogrid("setValues", $target.data("oldValues"));
													$target.combogrid("setText", $target.data("oldText"));
												}
											}
										}
									};
									checkNull();
								}
							}
							if ($target.data("tabEvent")) {
								$target.removeData("tabEvent");
								//call opts.keyHandler.enter
								if ($target.hasClass("combobox-f")) {
									opts.keyHandler.enter.call(target);
								} else if ($target.hasClass("combogrid-f")) {
									//Need to wait until combogrid is loaded, or can not get the right value and text in onHidePanel event
									function callEnter() {
										if ($target.combogrid("grid").data("loading")) {
											setTimeout(function() {
												callEnter();
											}, 100);
										} else {
											opts.keyHandler.enter.call(target);
										}
									};
									callEnter();
								}
							} else {
								var _onHidePanel = opts.onHidePanel;
								opts.onHidePanel = function() {};
								opts.keyHandler.enter.call(target);
								opts.onHidePanel = _onHidePanel;
							}
						}, opts.delay);
					});
				} else {
					input.unbind("blur").bind("blur", function(event) {
						$target.combo("hidePanel");
					});
				}
			});
		}
		return result;
	};

	$.fn.combo.methods = $.fn._combo.methods;
	$.fn.combo.defaults = $.fn._combo.defaults;

	$.fn.combo._parseOptions = $.fn._combo.parseOptions;
	//fix bug in easyui-1.2.4, attr 'multiple' value is 'multiple', not 'true'
	$.fn.combo.parseOptions = function(target) {
		var options = $.fn.combo._parseOptions(target);
		var t = $(target);
		return $.extend(options, {
			multiple : (t.attr("multiple") ? (t.attr("multiple") == "true" || t.attr("multiple") == true || t.attr("multiple") == "multiple") : undefined)
		});
	};
	
	$.extend($.fn.combo.methods, {
		readonly : function(jq, readonly) {
			return jq.each(function() {
				var $combo = $(this);
				readonly = readonly == undefined ? true : readonly;
				if (readonly) {
					$combo.combo("disable");
					$combo.parent().find(".combo-text").removeAttr("disabled").attr("readonly", readonly);
				} else {
					$combo.combo("enable");
					$combo.parent().find(".combo-text").removeAttr("readonly");
				}
			});
		},
		
		_getValue : $.fn.combo.methods.getValue,
		
		getValue : function(jq) {
			var $combo = $(jq[0]);
			if ($combo.combo("options").multiple) {
				return $combo.combo("getValues").join(",");
			} else {
				return $combo.combo("_getValue");
			}
		}
	});
	
	function initCombos(context) {
		$(".combo-f[readonly]", context).combo("readonly");
	};
	
	
	/********** combobox **********/
	$.fn._combobox = $.fn.combobox;

	$.fn.combobox = function(options, param) {
		if (typeof options == "string") {
			this.each(function() {
				if (! $.data(this, "combobox")) {
					$(this).combobox({}).combo("textbox").css("color", "white");
				}
			});
		}
		return $.fn._combobox.apply(this, [options, param]);
	};

	$.fn.combobox.methods = $.fn._combobox.methods;
	$.fn.combobox.parseOptions = $.fn._combobox.parseOptions;
	$.fn.combobox.defaults = $.fn._combobox.defaults;
	
	$.extend(true, $.fn.combobox.defaults, {
		width : 155,
		
		keyHandler : {
			_query : $.fn.combobox.defaults.keyHandler.query,
			
			query : function(q) {
				$.fn.combobox.defaults.keyHandler._query.apply(this, [q]);
				//if exactly one row is matched, select it.
				var $combo = $(this);
				var $item = $combo.combo("panel").find("div.combobox-item:visible");
				if ($item.size() == 1) {
					$combo.combo("setValues", [$item.attr("value")]);
					$item.addClass("combobox-item-selected");
				}
			}
		},
		
		filter : function(q, row) {
			var opts = $(this).combobox("options");
			return row[opts.textField].toUpperCase().indexOf(q.toUpperCase()) >= 0;
		}
	});

	$.extend($.fn.combobox.methods, {
		
		_setValue : $.fn.combobox.methods.setValue,
		
		setValue : function(jq, value) {
			return jq.each(function() {
				var $combobox = $(this);
				if ($combobox.combobox("options").multiple) {
					$combobox.combobox("setValues", value ? value.split(",") : []);
				} else {
					$combobox.combobox("_setValue", value);
				}
				$combobox.data("oldValues", $combobox.combobox("getValues"));
				$combobox.data("oldText", $combobox.combobox("getText"));
			});
		}

	});

	//init comboboxes
	function initComboboxes(context) {
		$(".easyui-combobox[codeType]", context).each(function() {
			var $combobox = $(this);
			$combobox.css("color", "white");
			$combobox.attr("comboname", $combobox.attr("name"));
		});
		var codeTypes = {};
		$(".easyui-combobox, th[editor='combobox'], th[editor*='combobox']", context).each(function() {
			var $combobox = $(this);
			var codeType = $combobox.attr("codeType");
			if (codeType && ! codeTypes[codeType]) {
				codeTypes[codeType] = {queryType : codeType};
			}
		});
		if ($.isEmptyObject(codeTypes)) {
			return;
		}
		var queryInfos = [];
		for (var codeType in codeTypes) {
			queryInfos.push(codeTypes[codeType]);
		}
		SelectCodeManager.getSelectCodeDatas(queryInfos, function(result) {
			for (var codeType in result) {
				var selectCodeData = result[codeType];
				var selectCodeDataObject = {};
				for (var i = 0; i < selectCodeData.dataList.length; i++) {
					selectCodeDataObject[selectCodeData.dataList[i][selectCodeData.keyFieldName]] = selectCodeData.dataList[i][selectCodeData.labelFieldName];
				}
				var selectCodeValuesObject = {};
				selectCodeValuesObject[codeType] = selectCodeDataObject;
				$.extend(true, $(document).data("selectCodeValues"), selectCodeValuesObject);
				var options = {
						valueField : selectCodeData.keyFieldName,
						textField : selectCodeData.labelFieldName,
						data : selectCodeData.dataList,
						panelHeight : Math.min(selectCodeData.dataList.length, 10) * 20 + 2
				};
				$(".easyui-combobox[codeType='" + codeType + "']", context).each(function() {
					var $combobox = $(this);
					var value = $.data(this, "combobox") ? $combobox.combobox("getValue") : this.value;
					if ($.data(this, "combobox")) {
						$combobox.combobox($.extend(true, $combobox.combobox("options"), options));
					} else {
						$combobox.combobox($.extend(true, {}, options));
					}
					if ($combobox.attr("readonly")) {
						$combobox.combobox("readonly");
					}
					if (value) {
						$combobox.combobox("setValue", value);
					}
					$combobox.combobox("textbox").css("color", "");
				});
				$("th[editor='combobox'][codeType='" + codeType + "'], th[editor*='combobox'][codeType='" + codeType + "']", context).each(function() {
					var $th = $(this);
					var $datagrid = $th.closest("table");
					var columnOption = $datagrid.datagrid("getColumnOption", $th.attr("field"));
					columnOption.editor = {
							type : "combobox",
							options : columnOption.editor.options ? $.extend(columnOption.editor.options, options) : options
					};
				});
			}
		});
	};


	/********** combogrid **********/
	$.fn._combogrid = $.fn.combogrid;

	$.fn.combogrid = function(options, param) {
		if (typeof options == "string") {
			this.each(function() {
				if (! $.data(this, "combogrid")) {
					$(this).combogrid({}).combo("textbox").css("color", "white");
				}
			});
		}
		return $.fn._combogrid.apply(this, [options, param]);
	};

	$.fn.combogrid.methods = $.fn._combogrid.methods;
	$.fn.combogrid.parseOptions = $.fn._combogrid.parseOptions;
	$.fn.combogrid.defaults = $.fn._combogrid.defaults;
	
	$.extend(true, $.fn.combogrid.defaults, {
		width : 155,
		panelWidth : 500,
		panelHeight : 265 + 17, 
		pagination : true,
		rownumbers : true,
		mode : "remote",
		url : contextPath + "/JsonFacadeServlet",
		
		//query parameters
		onBeforeLoad : function(param) {
			var $datagrid = $(this);
			$datagrid.data("loading", true);
			var options = $datagrid.datagrid("options");
			var parameters = options.parameters;
			if (! parameters) {
				return false;
			}
			var queryFields = [];
			if (options.queryFields) {
				queryFields = queryFields.concat(options.queryFields);
			}
			if (param.q) {
				queryFields.push({
					fieldName : "[" + $datagrid.datagrid("getColumnFields").join(",") + "]",
					fieldValue : param.q,
					operator : "ilikeAnywhere"
				});
			}
			var pagingInfo = null;
			if (param.rows) {
				pagingInfo = {
					pageSize : param.rows,
					currentPage : param.page
				};
			}
			$.extend(parameters.parameters.queryInfo, {
				queryFields : queryFields,
				orderBy : (param.sort ? param.sort + " " + param.order : undefined),
				pagingInfo : pagingInfo
			});
			for (var key in param) {
				delete param[key];
			}
			param.json_parameters = $.toJSON(parameters);
		},

		//query result
		loadFilter : $.fn.datagrid.defaults.loadFilter,

		onLoadSuccess : function(data) {
			var $combogrid = $(this);
			//cache selectCodeValues
			var codeType = $combogrid.combogrid("options").codeType;
			var selectCodeData = data.result;
			var selectCodeDataObject = {};
			if(selectCodeData.dataList != undefined && selectCodeData.dataList != null && selectCodeData.dataList != null){
				for (var i = 0; i < selectCodeData.dataList.length; i++) {
					selectCodeDataObject[selectCodeData.dataList[i][selectCodeData.keyFieldName]] = selectCodeData.dataList[i][selectCodeData.labelFieldName];
				}
			}
			var selectCodeValuesObject = {};
			selectCodeValuesObject[codeType] = selectCodeDataObject;
			$.extend(true, $(document).data("selectCodeValues"), selectCodeValuesObject);
			
			var $datagrid = $combogrid.combogrid("grid");

			//if exactly one row is matched, select it.
			if ($combogrid.attr("customValuePermitted") != "true"
						&& ! $combogrid.combo("options").customValuePermitted
						&& ! $combogrid.combo("options").multiple) {
				if ($datagrid.datagrid("getRows").length == 1) {
					$datagrid.datagrid("selectRow", 0);
				} else {
					var q = $combogrid.combogrid("getText").toUpperCase();
					var fields = $datagrid.datagrid("getColumnFields");
					$.each($datagrid.datagrid("getRows"), function(rowIndex, rowData) {
						for (var i = 0; i < fields.length; i++) {
							if (rowData[fields[i]] && rowData[fields[i]].toUpperCase() == q) {
								$datagrid.datagrid("selectRow", rowIndex);
								return false;
							}
						}
					});
				}
			}
			
			$datagrid.removeData("loading");
		},
		
		onLoadError : $.fn.datagrid.defaults.onLoadError,

		onShowPanel : function() {
			var $combogrid = $(this);
			//relaod data if panel is showed by clicking arrow
			//in such cases, timer will be the same as previous
			var timer = $combogrid.data("combo").timer;
			var previousTimer = $combogrid.data("previousTimer");
			$combogrid.data("previousTimer", timer);
			if (timer && (timer == previousTimer)) {
				if (! $combogrid.data("reloaded")) {
					$combogrid.combogrid("grid").datagrid("load", {}).datagrid("clearSelections");
					$combogrid.data("reloaded", true);
				}
			} else {
				$combogrid.data("reloaded", false);
			}
			//fit columns width
			fitColumnWidth(($(this).combogrid("panel").find("table:empty")));
		}
	});

	//combogrid methods
	$.extend($.fn.combogrid.methods, {
		
		setQueryFields : function(jq, queryFields) {
			return jq.each(function() {
				$(this).combogrid("grid").datagrid("options").queryFields = queryFields;
			});
		},
		
		//override setValue, set text at the same time
		_setValue : $.fn.combogrid.methods.setValue,
		setValue : function(jq, value) {
			return jq.each(function() {
				var $combogrid = $(this);
				$combogrid.combogrid("grid").datagrid("clearSelections");
				if ($combogrid.combogrid("options").multiple) {
					$combogrid.combogrid("setValues", value ? value.split(",") : []);
				} else {
					$combogrid.combogrid("_setValue", value);
				}
				$combogrid.data("oldValues", $combogrid.combogrid("getValues"));
				if (! value) {
					return;
				}
				var codeType = $combogrid.combogrid("options").codeType;
				var selectCodeValues = $(document).data("selectCodeValues");
				if (selectCodeValues[codeType] && selectCodeValues[codeType][value]) {
					$combogrid.combogrid("setText", selectCodeValues[codeType][value]);
					$combogrid.data("oldText", $combogrid.combogrid("getText"));
				} else {
					var selectCodeKeys = {};
					selectCodeKeys[codeType] = value;
					$combogrid.combogrid("setText", "");
					SelectCodeManager.getSelectCodeValuesByKeys(selectCodeKeys, function(result) {
						$.extend(true, selectCodeValues, result);
						if (value.split(",").length > 1) {
							var values = value.split(",");
							var texts = [];
							for (var i = 0; i < values.length; i++) {
								texts.push(result[codeType][values[i]] || values[i]);
							}
							selectCodeValues[codeType][value] = texts.join(",");
						}
						$combogrid.combogrid("setText", selectCodeValues[codeType][value] || value);
						$combogrid.data("oldText", $combogrid.combogrid("getText"));
					});
				}
			});
		}
	});

	//init combogrids
	function initCombogrids(context) {
		$(".easyui-combogrid[codeType]", context).each(function() {
			var $combogrid = $(this);
			$combogrid.css("color", "white");
			$combogrid.attr("comboname", $combogrid.attr("name"));
		});
		var codeTypes = {};
		$(".easyui-combogrid, th[editor='combogrid'], th[editor*='combogrid']", context).each(function() {
			var $combogrid = $(this);
			var codeType = $combogrid.attr("codeType");
			if (codeType && ! codeTypes[codeType]) {
				codeTypes[codeType] = {};
			}
		});
		if ($.isEmptyObject(codeTypes)) {
			return;
		}
		var codeTypeList = [];
		for (var codeType in codeTypes) {
			codeTypeList.push(codeType);
		}
		SelectCodeManager.getSelectCodeDefinitions(codeTypeList, function(result) {
			for (var codeType in result) {
				var selectCodeDefinition = result[codeType];
				var combogridOptions = COMBOGRID_OPTIONS[codeType] || {};
				var columns = combogridOptions.columns;
				if (! columns) {
					columns = [{field : selectCodeDefinition.keyFieldName}, {field : selectCodeDefinition.labelFieldName}];
				}
				var fieldCodeTypes = {};
				for (var i = 0; i < columns.length; i++) {
					var columnOption = columns[i];
					$.extend(columnOption, {
						title : getI18nTitle(combogridOptions.i18nRoot, columnOption.field, columnOption.title),
						sortable : true,
						formatter : columnOption.formatter || $.fn.datagrid.defaults.formatter
					});
					if (columnOption.codeType) {
						fieldCodeTypes[columnOption.field] = columnOption.codeType;
					}
				}
				var options = {
						codeType : codeType,
						idField : selectCodeDefinition.keyFieldName,
						textField : selectCodeDefinition.labelFieldName,
						columns : [columns],
						parameters : {
							serviceName : "selectCodeManager",
							methodName : "getSelectCodeData",
							parameters : {
								queryInfo : {
									queryType : codeType,
									fieldCodeTypes : fieldCodeTypes
								}
							}
						}
				};
				$(".easyui-combogrid[codeType='" + codeType + "']", context).each(function() {
					var $combogrid = $(this);
					var value = $.data(this, "combogrid") ? $combogrid.combogrid("getValue") : this.value;
					if ($.data(this, "combogrid")) {
						$combogrid.combogrid($.extend(true, $combogrid.combogrid("options"), options));
					} else {
						$combogrid.combogrid($.extend(true, {}, options));
					}
					if ($combogrid.attr("readonly")) {
						$combogrid.combogrid("readonly");
					}
					if (value) {
						$combogrid.combogrid("setValue", value);
					}
					$combogrid.combogrid("textbox").css("color", "");
				});
				$("th[editor='combogrid'][codeType='" + codeType + "'], th[editor*='combogrid'][codeType='" + codeType + "']", context).each(function() {
					var $th = $(this);
					var $datagrid = $th.closest("table");
					var columnOption = $datagrid.datagrid("getColumnOption", $th.attr("field"));
					columnOption.editor = {
							type : "combogrid",
							options : columnOption.editor.options ? $.extend(columnOption.editor.options, options) : options
					};
				});
			}
		});
	};


	/********** datebox **********/
	$.extend($.fn.datebox.defaults, {
		width : 155,
		
		formatter : function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		},
		
		parser : function(s){
			if (!s) return new Date();
			var ss = s.split('-');
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
	});

	/********** datetimebox **********/
	$.extend($.fn.datetimebox.defaults, {
		width : 155
	});


	/********** form **********/
	$.extend($.fn.form.methods, {
		setData : function(jq, data) {
			return jq.each(function() {
				var $form = $(this);
				$form.data("data", data);
				$form.find("input[name]:not(.combo-value), input[comboname], textarea[name]").each(function() {
					var $input = $(this);
					var name = $input.attr("name") || $input.attr("comboname");
					var value = (data[name] == null || data[name] == undefined) ? "" : data[name];
					if ($input.hasClass("easyui-combobox")) {
						$input.combobox("setValue", value);
					} else if ($input.hasClass("easyui-combogrid")) {
						$input.combogrid("setValue", value);
					} else if ($input.hasClass("easyui-datebox")) {
						$input.datebox("setValue", value);
					} else if ($input.hasClass("easyui-datetimebox")) {
						$input.datetimebox("setValue", value);
					} else if ($input.hasClass("easyui-combotree")) {
						$input.combotree("setValue", value);
					} else if ($input.attr("type") == "checkbox") {
						this.checked = value == $input.attr("on");
					} else if ($input.attr("type") == "radio") {
						this.checked = value == $input.attr("value");
					} else {
						this.value = value;
					}
				});
				$form.data("oldData", $form.form("getData"));
			});
		},
		
		getData : function(jq) {
			var $form = $(jq[0]);
			var data = $.extend({}, $form.data("data"));
			$form.find("input[name]:not(.combo-value), input[comboname], textarea[name]").each(function() {
				var $input = $(this);
				var name = $input.attr("name") || $input.attr("comboname");
				var value;
				if ($input.hasClass("combo-f")) {
					value = $input.combo("getValue");
				} else if ($input.attr("type") == "checkbox") {
					value = this.checked ? $input.attr("on") : $input.attr("off");
				} else if ($input.attr("type") == "radio") {
					value = this.checked ? $input.attr("value") : data[name];
				} else {
					value = this.value;
				}
				if (value == "") {
					value = null;
				}
				data[name] = value;
			});
			return data;
		},
		
		dataChanged : function(jq) {
			var $form = $(jq[0]);
			return $.toJSON($form.data("oldData")) != $.toJSON($form.form("getData"));
		},
		
		disable : function(jq) {
			return jq.each(function() {
				var $form = $(this);
				$form.find("input[name]:not(.combo-value):not([disabled_fixed],[readonly_fixed])").attr("disabled", "disabled");
				$form.find("textarea[name]:not([disabled_fixed],[readonly_fixed])").attr("disabled", "disabled");
				$form.find("input.combo-f:not([disabled_fixed],[readonly_fixed])").combo("disable");
			});
		},
		
		enable : function(jq) {
			return jq.each(function() {
				var $form = $(this);
				$form.find("input[name]:not(.combo-value):not([disabled_fixed],[readonly_fixed])").removeAttr("disabled");
				$form.find("textarea[name]:not([disabled_fixed],[readonly_fixed])").removeAttr("disabled");
				$form.find("input.combo-f:not([disabled_fixed],[readonly_fixed])").combo("enable");
			});
		},
		
		readonly : function(jq, readonly) {
			return jq.each(function() {
				var $form = $(this);
				readonly = readonly == undefined ? true : readonly;
				$form.find("input[name]:not(.combo-value):not([disabled_fixed],[readonly_fixed])").attr("readonly", readonly);
				$form.find("textarea[name]:not([disabled_fixed],[readonly_fixed])").attr("readonly", readonly);
				$form.find("input.combo-f:not([disabled_fixed],[readonly_fixed])").combo("readonly", readonly);
			});
		},
		
		fitHeight : function(jq) {
			return jq.each(function() {
				var $form = $(this);
				if ($form.data("fittedHeight")) {
					return;
				}
				if ($form.find("table").size() == 0) {
					return;
				}
				if ($form.closest(".panel:hidden").size() != 0) {
					return;
				}
				var $panel = $form.closest("div[region]");
				if ($panel.size() > 0) {
					var $div = $("<div/>");
					$div.append($panel.children()).appendTo($panel);
					var height = $div.height() + 4;
					if (! $panel.panel("options").noheader && $panel.panel("options").title) {
						height += 26;
					}
					$panel.panel("resize", {height : height});
					$panel.closest(".easyui-layout").layout("resize");
					$form.data("fittedHeight", true);
				}
			});
		}

	});
	

	//init forms
	function initForms(context) {
		//mark the disabled and readonly fields
		$(".easyui-form input[disabled]", context).attr("disabled_fixed", true);
		$(".easyui-form textarea[disabled]", context).attr("disabled_fixed", true);
		$(".easyui-form input[readonly]", context).attr("readonly_fixed", true);
		$(".easyui-form textarea[readonly]", context).attr("readonly_fixed", true);
		//auto format form items into columns
		$(".easyui-form[columns]", context).each(function() {
			var $form = $(this);
			var $div = $("<div/>").insertBefore($form);
			var columns = $form.attr("columns");
			if (! columns) {
				columns = 3;
			}
			var vertical = $form.attr("orientation") == "vertical";
			$form.find("textarea").each(function(index, textarea) {
				var $textarea = $(textarea);
				if ($textarea.css("display") == "none") {
					$textarea.attr("type", "hidden");
				}
			});
			var $inputs = $form.children(":not(.combo):not([type=hidden])");
			$form.detach();
			if (vertical) {
				var rows = Math.floor($inputs.size() / columns);
				if ($inputs.length % columns > 0) {
					rows++;
				}
				var verticalInputs = [];
				for (var i = 0; i < rows; i++) {
					for (var j = 0; j < columns; j++) {
						verticalInputs.push($inputs[i + j * rows]);
					}
				}
				$inputs = $(verticalInputs);
				$form.find("input").each(function(index, input) {
					$(input).attr("tabindex", index + 1);
				});
			}
			var $table = $("<table/>").appendTo($form);
			var $tr;
			var indexInRow = 0;
			$inputs.each(function(index, input) {
				var $input = $(input);
				if (indexInRow == 0 || indexInRow >= columns) {
					$tr = $("<tr/>").appendTo($table);
					indexInRow = 0;
				}
				if (! input) {
					return;
				}
				var $next = $input.next();
				var i18nKey = $input.attr("name");
				if (! i18nKey) {
					i18nKey = $input.find("[name]").attr("name");
				}
				if (! i18nKey && $next.hasClass("combo")) {
					i18nKey = $next.find("[name]").attr("name");
				}
				if (! i18nKey) {
					i18nKey = $input.attr("comboname");
				}
				if (! i18nKey) {
					i18nKey = $input.find("[comboname]").attr("comboname");
				}
				var title = $input.attr("title");
				if (title) {
					$input.removeAttr("title");
				} else {
					title = $input.find("[name]").attr("title");
					if (title) {
						$input.find("[name]").removeAttr("title");
					}
				}
				var colspan = $input.attr("colspan");
				if (! colspan) {
					colspan = $input.find("[name]").attr("colspan");
				}
				var label = getI18nTitle($form.attr("i18nRoot"), i18nKey, title);
				if ($input.attr("required")) {
					label = "<span style='color: red;'>* </span>" + label;
				}
				if (label) {
					$tr.append("<td align='right'> " + label + ":</td>");
				} else {
					$tr.append("<td/>");
				}
				var $td = $("<td/>").appendTo($tr).append($input);
				if ($next.hasClass("combo")) {
					$td.append($next);
				}
				indexInRow++;
				if (colspan && ! vertical) {
					colspan = parseInt(colspan);
					$td.attr("colspan", colspan * 2 - 1);
					$input.css("width", "100%");
					indexInRow += colspan - 1;
				}
			});
			$form.insertAfter($div);
			$div.detach();
			$form.form("fitHeight");
		});
	};


	/********** linkbuttons **********/
	$.extend($.fn.linkbutton.methods, {
		forceDisable : function(jq) {
			return jq.each(function() {
				var $linkbutton = $(this);
				$linkbutton.attr("forceDisable", true);
				$linkbutton.linkbutton("disable");
			});
		},
		
		_enable : $.fn.linkbutton.methods.enable,
		
		enable : function(jq) {
			return jq.each(function() {
				var $linkbutton = $(this);
				if (! $linkbutton.attr("forceDisable")) {
					$linkbutton.linkbutton("_enable");
				}
			});
		}
	});
	
	
	//init linkbuttons
	function initLinkbuttons(context) {
		$(".easyui-linkbutton[key]", context).each(function() {
			var $linkbutton = $(this);
			var key = $linkbutton.attr("key");
			key = key.toUpperCase();
			$linkbutton.attr("key", key);
			var text = $linkbutton.linkbutton("options").text + " <b>(<u>" + key + "</u>)</b>";
			$linkbutton.linkbutton("options").text = text;
			$linkbutton.find(".l-btn-text").html(text);
		});
	}
	
	
	/********** panels **********/
	$.extend($.fn.panel.methods, {
		loading : function(jq) {
			return jq.each(function() {
				var wrap = $(this);
				$("<div class='datagrid-mask panel' style='z-index: 10000;'></div>").css({
					display : "block",
					width : wrap.width(),
					height : wrap.height()
				}).appendTo(wrap);
				$("<div class='datagrid-mask-msg panel' style='z-index: 10000;'></div>")
				.html($.fn.datagrid.defaults.loadMsg).appendTo(wrap).css({
					display : "block",
					left : (wrap.width() - $("div.datagrid-mask-msg", wrap).outerWidth()) / 2,
					top : (wrap.height() - $("div.datagrid-mask-msg", wrap).outerHeight()) / 2
				});
			});
		},
		
		loaded : function(jq) {
			return jq.each(function() {
				var wrap = $(this);
				wrap.children("div.datagrid-mask-msg").remove();
				wrap.children("div.datagrid-mask").remove();
			});
		}
	});
	
	
	/********** tabs **********/
	$.extend($.fn.tabs.defaults, {
		onSelect : function(title) {
			var tab = $(this).tabs("getTab", title);
			tab.find(".easyui-form[columns]").form("fitHeight");
			setTimeout(function() {
				tab.find(".easyui-datagrid,.easyui-treegrid").datagrid("fitColumnWidth");
				tab.find(".easyui-treegrid").treegrid("fixRowHeight")
				.parent().find(".datagrid-view1 .datagrid-body td > div").css("height", "").css("width", "");
			}, 0);
		}
	});

	
	/********** toolbar **********/
	function initToolbars(context) {
		$(".datagrid-toolbar", context).each(function() {
			$(this).find(".easyui-linkbutton").each(function(index) {
				var $linkbutton = $(this);
				$linkbutton.linkbutton($.extend($linkbutton.linkbutton("options"), {
					plain : true
				}));
				$linkbutton.css("float", "left");
				if (index > 0) {
					$linkbutton.before($("<div class='datagrid-btn-separator'/>"));
				}
			});
		});
	};


	/********** window & dialog **********/
	$.extend($.fn.window.defaults, {
		onOpen : function() {
			var $window = $(this);
			if (window.top.document.body) {
				$window.window("resize");
				var left = (window.top.document.body.clientWidth - $window.parent().outerWidth()) / 2;
				left = left - (window.top.document.body.clientWidth - document.body.clientWidth);
				left = Math.max(left, 0);
				var top = (window.top.document.body.clientHeight - $window.parent().outerHeight()) / 2;
				top = top - (window.top.document.body.clientHeight - document.body.clientHeight);
				top = Math.max(top, 0);
				$window.window("move", {
					left : left,
					top : top
				});
			}
			
			$window.data("preFocus", $("*:focus"));
			$window.find("a:first").unbind("keydown").bind("keydown", function(e) {
				//13:Return; 32:Space; 27:Esc;
				if (e.keyCode == 13 || e.keyCode == 32) {
					e.target.click();
					e.preventDefault();
				} else if (e.keyCode == 27) {
					$(this).closest(".window").find(".panel-tool-close").click();
					e.preventDefault();
				}
			}).focus();
			$window.find("input:visible:first").focus();
		},
		
		onBeforeClose : function() {
			var $window = $(this);
			$window.data("preFocus").focus();
		}
	});

	$.extend($.fn.dialog.defaults, {
		onOpen : $.fn.window.defaults.onOpen
	});

	function initDialogs(context) {
		$(".easyui-dialog", context).each(function() {
			var $dialog = $(this);
			$dialog.find(".dialog-buttons").addClass("dialog-button").appendTo($dialog);
		});
	};
	


	/********** private methods **********/
	
	function getI18nTitle(i18nRoot, i18nKey, defaultTitle) {
		if (defaultTitle) {
			return defaultTitle;
		}
		if (! i18nRoot) {
			return i18nKey;
		}
		var i18nRoots = i18nRoot.split(",");
		for (var i = 0; i < i18nRoots.length; i++) {
			var root = "i18n." + $.trim(i18nRoots[i]);
			if (eval(root) && eval(root)[i18nKey]) {
				return eval(root)[i18nKey];
			}
		}
		return i18nKey;
	};
	
	function initDatagridEditors(target) {
		var $datagrid = $(target);
		var options = $datagrid.datagrid("options");
		var $headerTr = $datagrid.parent().find(".datagrid-view2 .datagrid-header tr");
		//editor cells
		var editorCells = {};
		$datagrid.data("editorCells", editorCells);
		$.each(options.columns[0], function(index, columnOption) {
			if (! columnOption.editor) {
				return;
			}
			var editorCell = editorCells[columnOption.field];
			if (! editorCell) {
				editorCell = $("<div class='datagrid-cell datagrid-editable'/>");
				editorCells[columnOption.field] = editorCell;
				editorCell.html("<table border='0' cellspacing='0' cellpadding='1'><tr><td></td></tr></table>");
				editorCell.children("table").attr("align", columnOption.align);
				editorCell.children("table").bind("click dblclick contextmenu", function(e) {
					e.stopPropagation();
				});
			}
			editorCell.width(columnOption.width || $headerTr.find("td[field='" + columnOption.field + "']").width());
		});
		//editor targets
		if ($datagrid.data("editorTargets")) {
			return;
		}
		var editorTargets = {};
		$datagrid.data("editorTargets", editorTargets);
		$.each(options.columns[0], function(index, columnOption) {
			if (! columnOption.editor) {
				return;
			}
			var type, editorOptions;
			if (typeof columnOption.editor == "string") {
				type = columnOption.editor;
			} else {
				type = columnOption.editor.type;
				editorOptions = columnOption.editor.options;
			}
			var editor = options.editors[type];
			var container = $("<div/>");
			var target = editor._init(container, editorOptions);
			editorTargets[columnOption.field] = [];
			container.children().each(function() {
				editorTargets[columnOption.field].push($(this));
			});
			editor.resize(target, columnOption.width || $headerTr.find("td[field='" + columnOption.field + "']").width());
		});
		$datagrid.data("editorsInitOk", true);
		if ($datagrid.data("toStartEdit") != undefined) {
			$datagrid.datagrid("beginEdit", $datagrid.data("toStartEdit"));
		}
	};
	
	function resizeDatagridEditor(target, field, width) {
		var $datagrid = $(target);
		var editorCells = $datagrid.data("editorCells");
		if (editorCells) {
			var editorCell = editorCells[field];
			if (editorCell) {
				if (editorCell.parent().length == 0) {
					editorCell.width(width);
				} else {
					var viewerCell = $datagrid.parent()
					.find(".datagrid-view2 .datagrid-body tr.datagrid-row-editing td[field='" + field + "']")
					.data("viewerCell");
					if (viewerCell) {
						viewerCell.width(width - 8);
					}
				}
			}
		}
	};
	
	function fitColumnWidth(target) {
		var $datagrid = $(target);
		var $datagridView = $datagrid.parent().find(".datagrid-view2");
		if ($datagridView.parent().parent().parent().parent().parent().css("display") == "none") {
			return;
		}
		if ($datagridView.find(".datagrid-row-editing").size() > 0) {
			return;
		}
		$datagridView.find(".datagrid-header .datagrid-cell").css("text-align", "center");
		var $headerTds = $datagridView.find(".datagrid-header td > div");
		if ($datagrid.hasClass("easyui-treegrid")) {
			$headerTds.each(function(index) {
				var $headerTd = $(this);
				var field = $headerTd.parent().attr("field");
				if ($datagrid.datagrid("getColumnOption", field).width) {
					return;
				}
				var headerWidth = $headerTd.width();
				var $bodyTds = $datagridView.find(".datagrid-body td[field='" + field + "'] > div");
				var $footerTds = $datagridView.find(".datagrid-footer td[field='" + field + "'] > div");
				var bodyWidth = 0;
				$bodyTds.each(function(index, bodyTd) {
					bodyWidth = Math.max(bodyWidth, $(bodyTd).width());
				});
				var width = Math.max(headerWidth, bodyWidth);
				if (width < 80) {
					width = 80;
				}
				$headerTd.width(width);
				$bodyTds.width(width);
				$footerTds.width(width);
				resizeDatagridEditor(target, field, width + 8);
			});
		} else {
			$headerTds.each(function(index) {
				var $headerTd = $(this);
				var field = $headerTd.parent().attr("field");
				if ($datagrid.datagrid("getColumnOption", field).width) {
					return;
				}
				var headerWidth = $headerTd.width();
				var $bodyTds = $datagridView.find(".datagrid-body td[field='" + field + "'] > div");
				var $footerTds = $datagridView.find(".datagrid-footer td[field='" + field + "'] > div");
				var bodyWidth = $bodyTds.size() > 0 ? $($bodyTds.get(0)).width() : 0;
				var width = Math.max(headerWidth, bodyWidth);
				if (width < 80) {
					width = 80;
				}
				$headerTd.width(width);
				$bodyTds.width(width);
				$footerTds.width(width);
				resizeDatagridEditor(target, field, width + 8);
			});
		}
	};

	function groupDatagrid(target, rows) {
		var $datagrid = $(target);
		var groupFields = $datagrid.datagrid("options").groupField;
		if (! groupFields) {
			return;
		}
		if (typeof groupFields == "string") {
			groupFields = groupFields.split(",");
		}
		for (var i = 0; i < groupFields.length; i++) {
			groupFields[i] = $.trim(groupFields[i]);
		}
		groupDatagridOneColumn($datagrid, groupFields, groupFields[0], rows, 0, rows.length - 1);
		function groupDatagridOneColumn($datagrid, groupFields, field, rows, beginIndex, endIndex) {
			var lastValue = undefined;
			var lastIndex = -1;
			for (var i = beginIndex; i <= endIndex; i++) {
				var value = rows[i][field];
				if (i > 0 && value == lastValue) {
					if (i == endIndex) {
						$datagrid.datagrid("mergeCells", {
							index : lastIndex,
							field : field,
							rowspan : i - lastIndex + 1
						});
						if (groupFields.indexOf(field) < groupFields.length - 1) {
							groupDatagridOneColumn($datagrid, groupFields, groupFields[groupFields.indexOf(field) + 1], rows, lastIndex, i);
						}
					}
				} else {
					if (i - lastIndex > 1) {
						$datagrid.datagrid("mergeCells", {
							index : lastIndex,
							field : field,
							rowspan : i - lastIndex
						});
						if (groupFields.indexOf(field) < groupFields.length - 1) {
							groupDatagridOneColumn($datagrid, groupFields, groupFields[groupFields.indexOf(field) + 1], rows, lastIndex, i - 1);
						}
					}
					lastValue = value;
					lastIndex = i;
				}
			}
		};
	};
	

	//override methods to cache datagrid editors
	function beginEdit(target, rowIndex) {
		var opts = $.data(target, 'datagrid').options;
		var tr = opts.editConfig.getTr(target, rowIndex);
		var row = opts.editConfig.getRow(target, rowIndex);
		if (tr.hasClass('datagrid-row-editing')) {
			return;
		}
		if (opts.onBeforeEdit.call(target, rowIndex, row) == false) {
			return;
		}
		tr.addClass('datagrid-row-editing');
		createEditor(target, rowIndex);
		fixEditorSize(target);
		tr.find('div.datagrid-editable').each(function() {
					var field = $(this).parent().attr('field');
					var ed = $.data(this, 'datagrid.editor');
					ed.actions.setValue(ed.target, row[field]);
				});
		validateRow(target, rowIndex);
	};
	function stopEdit(target, rowIndex, revert) {
		var opts = $.data(target, 'datagrid').options;
		var updatedRows = $.data(target, 'datagrid').updatedRows;
		var insertedRows = $.data(target, 'datagrid').insertedRows;
		var tr = opts.editConfig.getTr(target, rowIndex);
		var row = opts.editConfig.getRow(target, rowIndex);
		if (!tr.hasClass('datagrid-row-editing')) {
			return;
		}
		if (!revert) {
			if (!validateRow(target, rowIndex)) {
				return;
			}
			var changed = false;
			var newValues = {};
			tr.find('div.datagrid-editable').each(function() {
				var field = $(this).parent().attr('field');
				var ed = $.data(this, 'datagrid.editor');
				var value = ed.actions.getValue(ed.target);
				if (row[field] != value) {
					row[field] = value;
					changed = true;
					newValues[field] = value;
				}
			});
			if (changed) {
				if (insertedRows.indexOf(row) == -1) {
					if (updatedRows.indexOf(row) == -1) {
						updatedRows.push(row);
					}
				}
			}
		}
		tr.removeClass('datagrid-row-editing');
		//fix bug when leave the row with mouse down
		tr.removeClass('datagrid-row-over');
		destroyEditor(target, rowIndex);
		//keep not diplayed tds(merged cells), because updateRow will reset the style
		var $notDisplayedTds = tr.find("td:not(:visible)");
		$(target).datagrid('refreshRow', rowIndex);
		$notDisplayedTds.css("display", "none");
		if (!revert) {
			opts.onAfterEdit.call(target, rowIndex, row, newValues);
		} else {
			opts.onCancelEdit.call(target, rowIndex, row);
		}
	};
	function createEditor(target, rowIndex) {
		var opts = $.data(target, 'datagrid').options;
		var tr = opts.editConfig.getTr(target, rowIndex);
		tr.children('td').each(function() {
			var cell = $(this).find('div.datagrid-cell');
			var field = $(this).attr('field');
			var col = getColumnOption(target, field);
			if (col && col.editor) {
				var type, editorOpts;
				if (typeof col.editor == 'string') {
					type = col.editor;
				} else {
					type = col.editor.type;
					editorOpts = col.editor.options;
				}
				var editor = opts.editors[type];
				if (editor) {
//					var html = cell.html();
//					var width = cell.outerWidth();
//					cell.addClass('datagrid-editable');
//					if ($.boxModel == true) {
//						cell.width(width - (cell.outerWidth() - cell.width()));
//					}
//					cell
//							.html('<table border="0" cellspacing="0" cellpadding="1"><tr><td></td></tr></table>');
//					cell.children('table').attr('align', col.align);
//					cell.children('table').bind('click dblclick contextmenu',
//						function(e){
//							e.stopPropagation();
//						});;
//					$.data(cell[0], 'datagrid.editor', {
//								actions : editor,
//								target : editor.init(cell.find('td'), editorOpts),
//								field : field,
//								type : type,
//								oldHtml : html
//							});
					//use cached editor cell
					var html = cell.html();
					var $datagrid = $(target);
					var $td = $(this);
					$td.data("viewerCell", cell);
					cell.detach();
					cell = $datagrid.data("editorCells")[field];
					cell.appendTo($td);
					$.data(cell[0], 'datagrid.editor', {
								actions : editor,
								target : editor.init(cell.find('td'), editorOpts),
								field : field,
								type : type,
								oldHtml : html
							});
				}
			}
		});
		fixRowHeight(target, rowIndex);
	};
	function destroyEditor(target, rowIndex) {
		var opts = $.data(target, 'datagrid').options;
		var tr = opts.editConfig.getTr(target, rowIndex);				
		tr.children('td').each(function() {
					var cell = $(this).find('div.datagrid-editable');
					if (cell.length) {
						var ed = $.data(cell[0], 'datagrid.editor');
						if (ed.actions.destroy) {
							ed.actions.destroy(ed.target);
						}
						$.removeData(cell[0], 'datagrid.editor');
//						var width = cell.outerWidth();
//						cell.removeClass('datagrid-editable');
//						if ($.boxModel == true) {
//							cell.width(width
//									- (cell.outerWidth() - cell.width()));
//						}
						//use cached viewer cell
						var $td = $(this);
						cell.detach();
						$td.data("viewerCell").appendTo($td);
					}
				});
	};
	function fixEditorSize(target) {
		var panel = $.data(target, 'datagrid').panel;
		panel.find('div.datagrid-editable').each(function() {
					var ed = $.data(this, 'datagrid.editor');
					if (ed.actions.resize) {
						ed.actions.resize(ed.target, $(this).width());
					}
				});
	};
	function validateRow(target, rowIndex) {
		return $(target).datagrid("validateRow", rowIndex);
	};
	function getColumnOption(target, field) {
		return $(target).datagrid("getColumnOption", field);
	};
	function fixRowHeight(target, rowIndex) {
		return $(target).datagrid("fixRowHeight", rowIndex);
	};

	function getColumns(thead) {
		var columns = [];
		$('tr', thead).each(function() {
			var cols = [];
			$('th', this).each(function() {
						var th = $(this);
						var col = {
							title : th.html(),
							align : th.attr('align') || 'left',
							sortable : th.attr('sortable') == 'true' || false,
							checkbox : th.attr('checkbox') == 'true' || false
						};
						if (th.attr('field')) {
							col.field = th.attr('field');
						}
						if (th.attr('formatter')) {
							col.formatter = eval(th.attr('formatter'));
						}
						if (th.attr('styler')) {
							col.styler = eval(th.attr('styler'));
						}
						if (th.attr('editor')) {
							var s = $.trim(th.attr('editor'));
							if (s.substr(0, 1) == '{') {
								col.editor = eval('(' + s + ')');
							} else {
								col.editor = s;
							}
						}
						if (th.attr('rowspan')) {
							col.rowspan = parseInt(th.attr('rowspan'));
						}
						if (th.attr('colspan')) {
							col.colspan = parseInt(th.attr('colspan'));
						}
						if (th.attr('width')) {
							col.width = parseInt(th.attr('width'));
						}
						if (th.attr('hidden')) {
							col.hidden = th.attr('hidden') == 'true';
						}
						if(th.attr('resizable')){
							col.resizable=th.attr('resizable')=='true';
						}
						cols.push(col);
					});
			columns.push(cols);
		});
		return columns;
	};

})();
