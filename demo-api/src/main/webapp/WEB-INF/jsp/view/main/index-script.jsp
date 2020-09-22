<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

	$("#goBackHomePage").hide();
	
    $('.grid_1_of_3').click(function() {
		$("#goBackHomePage").show();
		if (this.id != '') {
			window.location = '../' + this.id;
		} else {
			$("#goBackHomePage").hide();
		}
    });
	
</script>