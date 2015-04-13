<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<head>
  <base href="<%=basePath%>">
  <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
  <title>味你，商家管理平台</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">        
  
  <!-- DataTables CSS -->
  <link rel="stylesheet" type="text/css" href="DataTables-1.10.4/css/jquery.dataTables.css">
  <link rel="stylesheet" href="jquery-ui-1.11.2/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" href="jtimepicker/jquery.timepicker.css">
  <link rel="stylesheet" href="css/templatemo_main.css">
</head>
<body>
  <div id="main-wrapper">
  <%@ include file="../common/head.jsp"  %> 
    <div class="template-page-wrapper">
   	  <%@ include file="../common/sidebar.jsp"  %>
      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li><a href="pages/index.jsp">返回首页</a></li>
            <li><a href="pages/orderManage/manageOrder.jsp">订单管理</a></li>
            <li class="active">当天订单</li>
              <input id="dateFlag" value="today" style="display: none"/>
          </ol>
          <%@ include file="commonContent.jsp"  %>
       </div>
      </div>
        <input id="messages"/>
   	<%@ include file="../common/footer.jsp"  %>
    </div>
   </div>


  	<!-- <script src="js/jquery.min.js"></script> -->
  	<script type="text/javascript" charset="utf8" src="DataTables-1.10.4/js/jquery-1.11.1.min.js"></script>  
  	<script src="js/bootstrap.min.js"></script>
  	<script src="jquery-validation-1.11.1/dist/jquery.validate.js" type="text/javascript"></script>
  	<script src="jquery-ui-1.11.2/jquery-ui.js"></script>
  	
    <script src="js/templatemo_script.js"></script>
  	<!-- DataTables -->
  	<script type="text/javascript" charset="utf8" src="DataTables-1.10.4/js/jquery.dataTables.min.js"></script>
  	<script type="text/javascript" charset="utf8" src="DataTables-1.10.4/js/dataTables.bootstrap.js"></script>  
  	
  	<script type="text/javascript" charset="utf8" src="jtimepicker/jquery.timepicker.js"></script>  
    <script type="text/javascript" charset="utf8" src="js/orderManage.js"></script>

      <script type="text/javascript">

          var webSocket;
          var messages = document.getElementById("messages");
          openSocket();

          function openSocket(){
              // Ensures only one connection is open at a time
              if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                  //writeResponse("WebSocket is already opened.");
                  return;
              }
              // Create a new instance of the websocket
              webSocket = new WebSocket("ws://localhost:8080/weini-vendor/echo?vendorId="+$("#vendorId").val());

              /**
               * Binds functions to the listeners for the websocket.
               */
              webSocket.onopen = function(event){
                  // For reasons I can't determine, onopen gets called twice
                  // and the first time event.data is undefined.
                  // Leave a comment if you know the answer.
                  if(event.data === undefined)
                      return;

                  writeResponse(event.data);
              };

              webSocket.onmessage = function(event){
                  writeResponse(event.data);
              };

              webSocket.onclose = function(event){
                  writeResponse("Connection closed");
              };
          }

          /**
           * Sends the value of the text input to the server
           */
          function send(){
              var text = document.getElementById("messageinput").value;
              webSocket.send(text);
          }

          function closeSocket(){
              webSocket.close();
          }

          function writeResponse(text){
              //orderTable.ajax.reload();
              messages.innerHTML += "<br/>" + text;
          }

      </script>

</body>
</html>