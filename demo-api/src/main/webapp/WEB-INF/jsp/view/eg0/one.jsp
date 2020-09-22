<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<main class="fh5co-page fh5co-light-grey-section">
	<div class="container">
		<section id="tab_table" class="tab_table">
			<div class="col-md-4 col-sm-5">
				<div class="tab_title">Number of Registration</div>
			</div>
		</section>
		<article id="chart_table" class="chart_table">
			<div class="col-md-12" style="height:600px">
				<div>
					<br/>
					<button id="testJSON">testJSON</button>    
					<span id="response"></span><br/>
					<hr />
					<span id="nation">National</span> Number of Registration
					<select id="City" onchange="change()">
						<option value="Establishment">Establishment</option>
						<option value="Change">Change of Registration</option>
						<option value="Dissolution">Dissolution</option>
					</select>
					<span id="name" style="font-size:25px;"></span>
					<span id="type" style="color:red;"></span>
					<button id="GoBack" onclick="javascript:location.reload();">Go Back to Nation</button>
					<div style="clear: both;">
					<svg id="map" style="float:left;"></svg>
					<svg id="bar1" style="float:left;"></svg>
					</div>
				</div>
			</div>
		</article>
		<div class="clearfix"></div>
	</div>
</main>