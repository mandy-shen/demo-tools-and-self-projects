<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<main ng-app="myApp" id="chart_section" class="fh5co-page fh5co-light-grey-section">
<form novalidate ng-controller="oneController as ctrl">
	<div class="container">
		<section id="tab_table" class="tab_table">
			<div class="col-md-4 col-sm-5">
				<div class="tab_title">Example1</div>
			</div>
			<div class="col-md-8 col-sm-7">
				<div class="cssmenu">
					<ul>
						<li class="active"><a href="../eg1/one">one</a></li>
						<li><a href="../eg1/two">two</a></li>
						<li><a href="../eg1/three">three</a></li>
					</ul>
				</div>
			</div>
		</section>
		<article id="chart_table" class="chart_table">
			<div class="col-md-12" style="height:600px">
				<div class="chart_input" style="padding-top:5px">
					<select ng-model="model.selectType" ng-change="ctrl.listCmpy()" style="width:250px;margin:0 auto"
							class="form-control ng-pristine ng-untouched ng-valid" ng-options="vo.type as vo.topic for vo in selectTypeList">
					</select>
				</div>
				<div class="table_scroll">
					<div class="itable">
						<div id="chart" class='with-3d-shadow with-transitions'>
    						<svg>SAMPLE</svg>
						</div>
					</div>
				</div>
			</div>
		</article>
		<div class="clearfix"></div>
	</div>
</form>
</main>