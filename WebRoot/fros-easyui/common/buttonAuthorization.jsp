<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- 按钮授权 -->
<!-- 
	
 -->

<script type="text/javascript">

	if (! $.fn.linkbutton.methods.forceDisable) {
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
	}

	function buttonAuthorization() {
		var $buttons = $(".l-btn[btnCode]");
		if ($buttons.size() > 0) {
			var url = "<%= request.getRequestURI() %>";
			LoginManager.getGrantedButtonsForCurrentUser(url, function(result) {
				var grantedBtnCodes = result;
				$buttons.each(function(index, button) {
					var $button = $(button);
					if (grantedBtnCodes.indexOf($button.attr("btnCode")) == -1) {
						$button.linkbutton("forceDisable");
					}
				});
			});
		}
	};
	
	$(function() {
		buttonAuthorization();
	});

	
</script>
