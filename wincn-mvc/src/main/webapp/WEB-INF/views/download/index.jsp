<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>download页面</h1>
	<button onclick="clickHandler();">测试</button>
	
	
	<script type="text/javascript" charset="utf-8" src="/resources/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	
		function clickHandler(){
			$.get("/download/public",function(data){
				console.log(data)
			},"text");	
		}
	</script>
</body>

</html>