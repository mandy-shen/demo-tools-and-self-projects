<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
var websocket;

function start() {
	var wsUri = "ws://localhost:8080/WebsocketClock/real-time-clock";
	websocket = new WebSocket(wsUri);
	
	websocket.onmessage = function (event) {
		last_time = event.data;
		writeToScreen('<span style="color: blue;">' + last_time + '</span>');
		document.getElementById("msg").innerHTML = "<span style='color: blue;'>" + event.data + "</span>";
	};
	
	websocket.onerror = function (event) {
		writeToScreen('<span style="color: red;"> ' + 'ERROR:</span> ' + event.data);
		websocket.close();
	};
}

function writeToScreen(message){
    var output = document.getElementById("msg").innerHTML = message;
}

function stop() {
	websocket.send("stop");
}
</script>