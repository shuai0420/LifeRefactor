<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>在线浏览</h1>
	<div id="documentViewer" class="flexpaper_viewer" style="width: 770px; height: 500px"></div>

	<script type="text/javascript" src="/resources/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="/resources/flexpaper/flexpaper.js"></script>

	<script type="text/javascript">
		$('#documentViewer')
				.FlexPaperViewer(
						{
							config : {

								PrintEnable : false,
								SWFFile : 'http://localhost:8888/download',
								Password : 'd4f3d34d0d07baff2358a08884eee7d8c43464b6891ef02019b381113fcb8c9c35ad00142042bf21a6c33264b2473da06437bf5a1f00a0eca032b4c658d78026a6da4ca321d1245d4997a456e3386eea794ffbd03046339d441c786c78066d64560c468323b3856bd23249e667958724ba7b637e754231bb4224646d2de3da18',
								Scale : 0.9,
								ZoomTransition : 'easeOut',
								ZoomTime : 0.5,
								ZoomInterval : 0.2,
								FitPageOnLoad : false,
								FitWidthOnLoad : false,
								FullScreenAsMaxWindow : false,
								ProgressiveLoading : false,
								MinZoomSize : 0.2,
								MaxZoomSize : 5,
								SearchMatchAll : false,
								InitViewMode : 'Portrait',
								RenderingOrder : 'flash',
								StartAtPage : '',
								ViewModeToolsVisible : true,
								ZoomToolsVisible : true,
								NavToolsVisible : true,
								CursorToolsVisible : true,
								SearchToolsVisible : true,
								WMode : 'window',
								localeChain : 'zh_CN'
							}
						});
	</script>
</body>

</html>