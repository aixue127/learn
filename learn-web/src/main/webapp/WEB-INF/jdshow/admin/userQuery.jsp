<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title>
	<script type="text/javascript">
		$(function() {
			$('#inittab').datagrid({
				loadMsg:"loading.....",
				rownumbers:true,
				singleSelect:true,
				pagination:true,
			    url:'${ctx}/jdshow/user/queryUser',
			    columns:[[
			        {field:'id',title:'id',width:"10%"},
			        {field:'userName',title:'userName',width:"20%"},
			        {field:'loginName',title:'loginName',width:"20%"},
			        {field:'passWord',title:'passWord',width:"20%"},
			        {field:'operator',title:'operator',width:"20%", formatter:function(value, rowData, rowIndex){
			        	return  "<a href='#' class='easyui-linkbutton' onclick=\"distributRole("+rowData.id+")\">DIS ROLE</a>";		        
			        	}}
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
		
		
		function distributRole(id) {
			$("#treeRole").jdZtree({url:"${ctx}/jdshow/user/queryUserRole",data:{userId:id,id:0}});
			$('#ROLEDLG').dialog({
			    title: 'Distribut Role',
			    modal: true,
			    buttons:[{
					text:'save',
					iconCls:'icon-add',
					handler:function(){
						saveUserRole(id);
					}
				}]
			});
		}
		
		function saveUserRole(id) {
			// 获取选中的节点
			var nodes = $.fn.zTree.getZTreeObj("treeRole").getCheckedNodes(true);
			var nodeIds = [];
			for(var i=0; i<nodes.length; i++) {
				nodeIds.push(nodes[i].id);
			}
			if(nodeIds.length>0) {
				$.post("${ctx}/jdshow/user/saveUserRole",{userId:id,roleIds:nodeIds.join(",")}, function(data) {
					if(data!=null && data.success) {
						$.messager.alert('notice','save success!','info');
						$('#ROLEDLG').dialog('close');
					} else {
						$.messager.alert('notice','save fail!','error');
					}
				},"json");
			} else {
				$.messager.alert('notice','no select dont save!','info');
			}
		}
		
		function deleteRow() {
			$("#inittab").jdshowGridDelAjax({url:"${ctx}/jdshow/user/deleteUser",success:deleteCallback});
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
			$("#fromXD").jdshowFormAjax({url:"${ctx}/jdshow/user/insOrUpdUser",success: successCallback});
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
<body>
	<div>
		<table id="inittab"></table>
	</div>
	<div id="DLG" style="display: none;width:400px;height:200px;padding:10px">
		<form id="fromXD">
			<input id="id" name="id" type="hidden">
			<table>
				<tr><td>用户名</td><td><input id="userName" name="userName" type="text" ></td></tr>
				<tr><td>登录名</td><td><input id="loginName" name="loginName" type="text" ></td></tr>
				<tr><td>密码</td><td><input id="passWord" name="passWord" type="text" ></td></tr>
			</table>
		</form>
	</div>
	
	<div id="ROLEDLG"  style="display: none;width:400px;height:200px;padding:10px">
		<ul id="treeRole" class="ztree"></ul>
	</div>
</body>
</html>