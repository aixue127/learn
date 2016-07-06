<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>权限管理</title>
	<script type="text/javascript">
		$(function() {
			$('#inittab').datagrid({
				loadMsg:"loading.....",
				rownumbers:true,
				singleSelect:true,
				pagination:true,
			    url:'${ctx}/jdshow/rolefun/queryFunction',
			    columns:[[
					{field:'id',title:'id',width:"5%"},
			        {field:'funCode',title:'funCode',width:"20%"},
			        {field:'funName',title:'funName',width:"20%"},
			        {field:'jdurl',title:'jdurl',width:"25%"},
			        {field:'orderd',title:'orderd',width:"25%"}
			    ]],
			    toolbar: [{
			    	text:'Add',
					iconCls: 'icon-add',
					handler: function(){openDLG("add");}
				},'-',{
					text:'edit',
					iconCls: 'icon-edit',
					handler: function(){openDLG("edit");}
				},'-',{
					text:'remove',
					iconCls: 'icon-remove',
					handler: function(){
						deleteRow();
					}
				}]
			});
		});
		

		function deleteRow() {
			$("#inittab").jdshowGridDelAjax({url:"${ctx}/jdshow/rolefun/deleteFun",success:deleteCallback});
		}
		
		function deleteCallback(data) {
			if(data!=null && data.success) {
				// 关闭
				$.messager.alert('notice','delete success!','info');
				$('#inittab').datagrid('reload');
			} else {
				$.messager.alert('notice','delete fail!','error');
			}
		}
		
		function openDLG(type) {
			if(type=="add") { // 新增操作
				$("#DLG").jdclear();
			} else {
				// 获取选中的行
				var row = $('#inittab').datagrid('getSelected');
				$("#DLG").jdset(row);
			}
			$('#DLG').dialog({
			    title: 'Add&Update',
			    modal: true,
			    buttons:[{
					text:'save',
					iconCls:'icon-add',
					handler:function(){
						saveOrupdate(type);
					}
				}]
			});
		}
		function saveOrupdate(type) {
			$("#fromXD").jdshowFormAjax({url:"${ctx}/jdshow/rolefun/insOrUpdFun",success: successCallback});
		}
		
		function successCallback(data) {
			if(data!=null && data.success) {
				// 关闭
				$.messager.alert('notice','save success!','info');
				$('#DLG').dialog('close');
				$('#inittab').datagrid('reload');
			} else {
				$.messager.alert('notice','save fail!','error');
			}
		}
	</script>
</head>
<body fit="true" style="width: 100%;height: 100%;">
	<div>
		<table id="inittab"></table>
	</div>
	<div id="DLG" style="display: none;width:400px;height:200px;padding:10px">
		<form id="fromXD">
			<input id="id" name="id" type="hidden">
			<table>
				<tr><td>编码</td><td><input id="funCode" name="funCode" type="text" ></td></tr>
				<tr><td>名称</td><td><input id="funName" name="funName" type="text" ></td></tr>
				<tr><td>URI</td><td><input id="jdurl" name="jdurl" type="text" ></td></tr>
				<tr><td>顺序</td><td><input id="orderd" name="orderd" type="text" ></td></tr>
				<tr><td>父ID</td><td> <input id="parentId" name="parentId" type="text"></td></tr>
			</table>
		</form>
	</div>
</body>
</html>