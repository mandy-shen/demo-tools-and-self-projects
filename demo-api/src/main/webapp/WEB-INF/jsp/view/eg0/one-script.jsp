<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
$(document).ready(function() {
	$('#testJSON').click(function() {
		$.getJSON('${pageContext.request.contextPath}/item/test.json', function(data) {
			var text = 'banNo=' + data[0].banNo + ', cmpyName=' + data[0].cmpyName + ', investAmt=' + data[0].investAmt;
			$('#response').text('response:' + text);
		});
	});
});

var baseColor = 220;
var height = 450;
var width = 400;
var data = {}
var topodata2 = {};

d3.json('${pageContext.request.contextPath}/data/city.json', function(resTopodata2){
d3.csv('${pageContext.request.contextPath}/data/caseType.csv', function(resData){
	data = resData;
	topodata2 = resTopodata2
	bar(data);


	var features = topojson.feature(topodata2, topodata2.objects["city"]).features;
      	
	var projection=d3.geo.mercator().center([122,24]).scale(7000); 
	var path = d3.geo.path().projection(projection);
	var svg = d3.select('#map');

	svg.attr('width', 400)
    	.attr('height', 450)
    	.attr("viewBox", "0 50 500 500");
	var g=svg.append("g");


	var allcsmDetail=d3.nest()
		.key(function(d){return d.Case_Type;})
		.key(function(d){return d.Cmpy_Address.substring(0,3);})
		.rollup(function(d){return d.length;})
		.entries(data);
	var index=getform();
	var csmDetail=allcsmDetail[index].values;
	var arr=[];
	for(var i=0;i<csmDetail.length;i++) arr.push(csmDetail[i].values);
	var max=d3.max(arr);
	g.selectAll("path").data(features).enter().append("path")
		.attr("class",function(d){return d.properties.C_Name;}).attr("d",path)
		.attr("fill",function(d,i) {  	
						var name=d.properties.C_Name;
						var value=-100;
						for(var j=0;j<csmDetail.length;j++) {
							if(csmDetail[j].key==name){
							value=csmDetail[j].values;
							break;
							}
						}
						var color = 0.9 -value/max * 0.6;

        					return  d3.hsl(baseColor ,color, color);})
		.classed("city",true)
		.on("mouseover",mouse).on("click",click);
	function mouse(d){
		d3.select(this).classed("mouse",true).on("mouseleave",leave);
		var Cname=d.properties.C_Name;
		d3.selectAll("."+Cname).classed("mouse",true);
		function leave(){
			d3.select(this).classed("mouse",false);
			d3.selectAll("."+Cname).classed("mouse",false);
		}
	}
	function click(d){
		ccsmDetail(d);
  		var b = path.bounds(d);
		d3.select(this).on("click",reset).mouse;
	}
	function reset(){
		$("#City").attr("disabled",false);
		d3.select("form").select("text").attr("x",0).attr("y",0).text("");
		change();
		$('select').show();
		d3.selectAll("path").classed("click",false).on("click",click);
	}
});
});


function bar(data){
	var allcsmDetail=d3.nest()
		.key(function(d){return d.Case_Type;})
		.key(function(d){return d.Cmpy_Address.substring(0,3);})
		.rollup(function(d){return d.length;})
		.entries(data);
	var index=getform();
	var csmDetail=allcsmDetail[index].values;
	var arr=[];
	for(var i=0;i<csmDetail.length;i++) arr.push(csmDetail[i].values);
	var max=d3.max(arr);
	var Xscale=d3.scale.linear().domain([0,max]).range([0,width-110]);
	var Yscale=d3.scale.linear().domain([0,21]).range([0,height]);
  var svg = d3.select("#bar1");
  svg.attr('width', 500)
    .attr('height', 450)
    .attr("viewBox", "-180 -50 600 550");
	var barg=svg.append("g");

	
	var bar=barg.selectAll("rect").data(csmDetail);
	var labels=barg.selectAll("text").data(csmDetail);
	bar.enter().append("rect");
	labels.enter().append("text");

	bar.attr("class",function(d){return d.key;}).sort(function(a,b){return b.values-a.values;})
		.attr("x",110)
		.attr("y",function(d,i){return Yscale(i);})
		.attr("width",function(d){
			return Xscale(d.values);})
		.attr("height",20)
		.attr("fill",function(d) {
			var color = 0.9 - d.values/max * 0.6;
        		return  d3.hsl(baseColor ,color, color);})
		.on("mouseover",mouse);
	d3.select("#map").select("g").selectAll("rect").sort(function(a,b){
			return b.values-a.values;
		}).transition();

	labels.attr("class","labels").sort(function(a,b){return b.values-a.values;})
		.attr("x",0)
		.attr("y",function(d,i){return Yscale(i)+14;})
		.text(function(d){
			if(d.key=="") return "unknown "+d.values;
			else return d.key+" "+d.values;});
	function mouse(d){
		d3.select(this).classed("mouse",true).on("mouseleave",leave);
		var Cname=d.key;
		d3.selectAll("."+Cname).classed("mouse",true);
		function leave(){
			d3.select(this).classed("mouse",false);
			d3.selectAll("."+Cname).classed("mouse",false);
		}
	}
}
function change(){
	var allcsmDetail=d3.nest()
		.key(function(d){return d.Case_Type;})
		.key(function(d){return d.Cmpy_Address.substring(0,3);})
		.rollup(function(d){return d.length;})
		.entries(data);
	var index=getform();
	var csmDetail=allcsmDetail[index].values;
	var arr=[];
	for(var i=0;i<csmDetail.length;i++) arr.push(csmDetail[i].values);
	var max=d3.max(arr);
	var Xscale=d3.scale.linear().domain([0,max]).range([0,width-110]);
	var Yscale=d3.scale.linear().domain([0,21]).range([0,height]);
	var bar=d3.select("#bar1").select("g").selectAll("rect").data(csmDetail,function(d){return d.key;});
	var labels=d3.select("#bar1").select("g").selectAll("text").data(csmDetail);
	var g=d3.select("#map").select("g").selectAll("path");
	bar.exit().remove();
	labels.exit().remove();
	bar.enter().append("rect").on("mouseover",mouse);
	labels.enter().append("text");
	
		bar.sort(function(a,b){return b.values-a.values;})
		.attr("fill","#fff")
		.attr("width",0)
		.transition().duration(750).attr("class",function(d){return d.key;})
		.attr("x",110)
		.attr("y",function(d,i){return Yscale(i);})
		.attr("width",function(d){return Xscale(d.values);})
		.attr("height",20)
		.attr("fill",function(d) {
			var color = 0.9 - d.values/max * 0.6;
        		return  d3.hsl(baseColor ,color, color);})
	
        		
		labels.sort(function(a,b){return b.values-a.values;}).transition().duration(750).attr("class","labels")
		.attr("x",0)
		.attr("y",function(d,i){return Yscale(i)+14;})
		.text(function(d){return d.key+" "+d.values;});
		g.transition().duration(750)
		.attr("fill",function(d,i) {  	
						var name=d.properties.C_Name;
						var value=-100;
						for(var j=0;j<csmDetail.length;j++) {
							if(csmDetail[j].key==name) {
							value=csmDetail[j].values;
							break;
							}
						}
						var color = 0.9 -value/max * 0.6;
        					return  d3.hsl(baseColor ,color, color);});
	function mouse(d){
		d3.select(this).classed("mouse",true).on("mouseleave",leave);
		var Cname=d.key;
		d3.selectAll("."+Cname).classed("mouse",true);
		function leave(){
			d3.select(this).classed("mouse",false);
			d3.selectAll("."+Cname).classed("mouse",false);
		}
	}
}
function ccsmDetail(d){
	var type = $("#City").val();
	$("#City").attr("disabled",true);
	var value=d.properties.C_Name;
	d3.select("form").select("text").attr("x",0).attr("y",0).text(value);
	$("#type").text(type);
	$('select').hide();
	$('#nation').hide();
	var allcsmDetail=d3.nest()
		.key(function(d){return d.Cmpy_Address.substring(0,3);})
		.key(function(d){return d.Cmpy_Address.substring(0,6);})
		.rollup(function(d){return d.length;})
		.entries(data);
	var rank=d3.nest()
		.key(function(d){return d.Cmpy_Address.substring(0,6);})
		.key(function(d){return d.Cmpy_Address.substring(0,3);})
		.rollup(function(d){return d.length;})
		.entries(data);
	var csmDetail;
	for(var i=0;i<22;i++){
		if(value==allcsmDetail[i].key) {csmDetail=allcsmDetail[i].values; break;}
	}
	var arr=[];
	for(var i=0;i<csmDetail.length;i++) arr.push(csmDetail[i].values);
	var max=d3.max(arr);
	var Xscale=d3.scale.linear().domain([0,max]).range([0,width-200]);
	var Yscale=d3.scale.linear().domain([0,21]).range([0,height]);
	var bar=d3.select("#bar1").select("g").selectAll("rect").data(csmDetail);
	var labels=d3.select("#bar1").select("g").selectAll("text").data(csmDetail);
	bar.exit().remove();
	labels.exit().remove();
	bar.enter().append("rect");
	labels.enter().append("text");

	bar.sort(function(a,b){return b.values-a.values;})
		.attr("fill","#fff")
		.transition().duration(750).attr("class",function(d){return d.key;})
		.attr("x",200)
		.attr("y",function(d,i){return Yscale(i);})
		.attr("width",function(d){return Xscale(d.values);})
		.attr("height",20)
		.attr("fill",function(d) {
			var color = 0.9 - d.values/max * 0.6;
        		return  d3.hsl(baseColor ,color, color);})

    labels.sort(function(a,b){return b.values-a.values;}).transition().duration(750).attr("class","labels")
		.attr("x",0)
		.attr("y",function(d,i){return Yscale(i)+14;})
		.text(function(d){
			var rankarr=[];
			for(i=0;i<rank.length;i++){
				if(rank[i].key==d.key) break;
			}
			for(var j=0;j<rank[i].values.length;j++)
				rankarr.push(rank[i].values[j].values);
			rankarr.sort(function(a,b){return b-a;});
			return d.key+"\t\t"+d.values;});

}
function getform() {
    var obj = document.getElementById("City");
    var c = obj.selectedIndex;
	return c;
}

</script>