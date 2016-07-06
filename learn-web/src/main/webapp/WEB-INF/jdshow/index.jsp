<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../common/taglib.jsp" %>
	<%@include file="../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>jdshow后台管理系统</title>
	<script type="text/javascript" src="${ctx }/js/jdshow/index.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#west").jdshowMain(${functions});
		});
	</script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" id="main">
		<div data-options="region:'north'" style="height:10%" id="north">
			header
		</div>
        <div data-options="region:'south'" style="height:10%;" id="south">
        	buttoom
        </div>
        <div data-options="region:'west',split:true" title="West" style="width:10%;" id="west">
            <div class="easyui-accordion" data-options="fit:true,border:false" id="function">
            </div>
        </div>
        <div data-options="region:'center'" style="height:80%;width: 90%;" class="easyui-tabs"  id="centerTabs">
    	</div>
	</div>
</body>
</html>