<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	   <%@ include file="/WEB-INF/headerPhantom.jsp" %>
<title>TCHAT</title>
</head>
<body>
<form>
	
		
  <fieldset>
    <legend>Echo</legend>
    <input id="txtMessage" type="text"><br>
    <input onclick="doCloseConnection();" value="Disconnect" type="button">
    <input onclick="doSendMessage();" value="Send" type="button">
    <br>
    <textarea  id="txtAreaEcho" rows="4" cols="50">
    </textarea>
  </fieldset>
</form> 
<script type="text/javascript">
var webSocket;
var msg;
window.addEventListener("load", function(event) { 
	webSocket = new WebSocket("ws://localhost:8080/Presentation/wsTchat");
	var txtAreaEcho = document.getElementById("txtAreaEcho");
	txtAreaEcho.value = "";
	msg = document.getElementById("txtMessage");
	webSocket.onopen = function(msgEvent) { 
		txtAreaEcho.value += "Connected ... \n";
	};
	webSocket.onmessage = function(msgEvent) { 
		txtAreaEcho.value += "Server 2 : " + msgEvent.data + "\n";
	};
	webSocket.onclose = function(msgEvent) {
		txtAreaEcho.value += "Disconnect ... \n";
	};
	webSocket.onerror = function(msgEvent) { 
		txtAreaEcho.value += "Error ... \n";
	};
}); 
function doSendMessage() {
    webSocket.send(msg.value);
    msg.value = "";
}
function doCloseConnection(){
    webSocket.close();
}
</script>

         <%@ include file="/WEB-INF/footerPhantom.jsp" %>
</body>
</html>