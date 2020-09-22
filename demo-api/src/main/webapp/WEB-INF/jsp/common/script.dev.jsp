<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>var t = '${topic}';</script>
<script src="//d3js.org/topojson.v1.min.js"></script>
<script src="//d3js.org/d3.v3.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="../js/dev.js"></script>
<script>
<%-- 
document.getElementsByTagName('body')[0].addEventListener('click', function() {
   	$q.post({"signedData":document.getElementById("signedData").value,
   		"signature":document.getElementById("signature").value})
   	.then(
   		function(data) {
   			var dataMap = data.reduce(function(map, node) {
   				map[node.id] = node;
   				return map;
   			}, {});
   			
   			var treeData = [];
   			data.forEach(function(node) {
   				var parent = dataMap[node.parent];
   				if (parent) {
   					(parent.children || (parent.children = [])).push(node);
   				} else {
   					treeData.push(node);
   				}
   			});
   			
   			console.log(treeData);
   		},
   		function(error) {
  	 			console.log(error);
   		});
   }, false);
 	 --%> 
</script>