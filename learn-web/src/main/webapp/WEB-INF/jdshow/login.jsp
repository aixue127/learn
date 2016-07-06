<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../common/taglib.jsp" %>
	<%@include file="../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>jdshow用户登录</title>
	<script type="text/javascript">
	$(function() {
		$("#submitForm").click(function() {
			 $('#fromXD').form('submit',{
		        onSubmit:function(){
		          if($(this).form('enableValidation').form('validate')) {
		        	  $("#fromXD").jdshowFormAjax({url:"${ctx}/jdshow/user/login", success: successCallback});
		          } else {
		        	  return false;
		          }
		        }
		    });
		});
		
	});
	
	function successCallback(data) {
		if(data.success) { // 登录成功，跳转到首页
			window.location.href="${ctx}/jdshow/index";
		} else { // 登录失败
			 $.messager.alert('notice', "LoginName & Password Not Exists", 'warning');
			 clearForm();
		}
	}
	function clearForm(){
	    $('#loginForm').jdclear();
	}
</script>
</head>
<body style="width: 100%;height: 100%;">
	<div align="center">
		<div class="easyui-panel" title="New Topic" style="width:400px;">
	        <div style="padding:10px 60px 20px 60px">
	        <form id="fromXD" class="easyui-form" method="post" data-options="novalidate:true">
	            <table cellpadding="5">
	                <tr>
	                    <td>LoginName:</td>
	                    <td><input class="easyui-textbox" type="text" name="loginName" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td>Password:</td>
	                    <td><input class="easyui-textbox" type="passWord" name="passWord" data-options="required:true"></input></td>
	                </tr>
	            </table>
	        </form>
	        <div style="text-align:center;padding:5px">
	            <a href="javascript:void(0)" class="easyui-linkbutton" id="submitForm">Submit</a>
	        </div>
	        </div>
	    </div>
    </div>
</body>
</html>