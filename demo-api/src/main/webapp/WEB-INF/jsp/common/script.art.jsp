<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.retina.js"></script>
<script src="../js/jquery.easing.1.3.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/main.js"></script>
<script src="../js/move-top.js"></script>
<script type="text/javascript">
   	$(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},500);
		});
		$().UItoTop({ easingType: 'easeOutQuart' });
	});
	$(window).scroll(function(){
  			var scrolled = $(this).scrollTop()+100; 
  			$(".fixedchild").css({"top": scrolled+"px"});  
	});
</script>
<!--[if lt IE 9]>
	<script src="../js/respond.min.js"></script>
	<script src="../js/html5shiv.js"></script>
	<script src="../js/placeholder-IE-fixes.js"></script>
	<script src="../js/excanvas.js"></script>
	<script src="../js/es5-shim.js"></script>
<![endif] -->