$(function() {
	$("body").keyup(function(e) {
		if (e.keyCode == 13) {
			var $input = $("input:focus");
			if ($input.size() > 0) {
				var inputs = $input.closest("form, .datagrid-row-editing").find("input:visible, textarea:visible");
				inputs.eq(inputs.index($input) + 1).focus();
			}
		}
	});
});
