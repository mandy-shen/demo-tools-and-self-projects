<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="../js/nv.d3.js"></script>
<script type="text/javascript">
angular.module("myApp", []).controller('oneController', ['$scope','$http', function ($scope,$http) {
	var ctrl = this;
	$scope.model = {};
	$scope.selectTypeList = [];
	
	$scope.selectTypeList.push({'type':'0','topic':'Establishment'},{'type':'1','topic':'Change'},{'type':'2','topic':'Dissolution'});
	$scope.model.selectType = $scope.selectTypeList[0].type;
	$scope.model.chartData = [];
	
	ctrl.listCmpy = function() {
		$scope.model.selectTopic = $scope.selectTypeList[$scope.model.selectType].topic;
		$q.post({ caseType: $scope.model.selectTopic})
		.then(
			function(data) {
				$scope.model.initData = d3.nest().key(function(d){
					if (d.cmpyAddress) {
						return d.cmpyAddress.substring(0,3);
					} else {
						return 'unknown';
					}
				}).entries(data); 
				
				angular.forEach($scope.model.initData, function(region) {
					angular.forEach(region.values, function(cmpyVo) {
						console.log(cmpyVo);
					});
				});
			},
			function(error) {
			
			});
	}
	
	ctrl.listCmpy();
	
}]);
</script>