<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色管理</title>
	<script type="text/javascript">
		$(function() {
			$('#inittab').datagrid({
				loadMsg:"loading.....",
				rownumbers:true,
				singleSelect:true,
				pagination:true,
			    url:'${ctx}/jdshow/rolefun/queryRole',
			    columns:[[
					{field:'id',title:'id',width:"5%"},
			        {field:'roleCode',title:'roleCode',width:"20%"},
			        {field:'roleName',title:'roleName',width:"20%"},
			        {field:'operator',title:'operator',width:"20%", formatter:function(value, rowData, rowIndex){
			        	return  "<a href='#' class='easyui-linkbutton' onclick=\"distributFunction("+rowData.id+")\">DIS FUN</a>";		        
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
		
		function distributFunction(id) {
			$("#treeFun").jdZtree({url:"${ctx}/jdshow/rolefun/queryRoleFuns",data:{roleId:id}});
			$('#FUNDLG').dialog({
			    title: 'Distribut Function',
			    modal: true,
			    buttons:[{
					text:'save',
					iconCls:'icon-add',
					handler:function(){
						saveRoleFun(id);
					}
				}]
			});
		}
		
		function saveRoleFun(id) {
			// 获取选中的节点
			var nodes = $.fn.zTree.getZTreeObj("treeFun").getCheckedNodes(true);
			var nodeIds = [];
			for(var i=0; i<nodes.length; i++) {
				if(nodes[i].pId) {
					nodeIds.push(nodes[i].id);
				}
			}
			if(nodeIds.length>0) {
				$.post("${ctx}/jdshow/rolefun/saveRoleFuns",{roleId:id, ids:nodeIds.join(",")}, function(data) {
					if(data!=null && data.success) {
						$.messager.alert('notice','save success!','info');
						$('#FUNDLG').dialog('close');
					} else {
						$.messager.alert('notice','save fail!','error');
					}
				},"json");
			} else {
				$.messager.alert('notice','no select dont save!','info');
			}
		}
		
		function deleteRow() {
			$("#inittab").jdshowGridDelAjax({url:"${ctx}/jdshow/rolefun/deleteRole",success:deleteCallback});
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
			$("#fromXD").jdshowFormAjax({url:"${ctx}/jdshow/rolefun/insOrUpdRole",success: successCallback});
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
				<tr><td>编码</td><td><input id="roleCode" name="roleCode" type="text" ></td></tr>
				<tr><td>名称</td><td><input id="roleName" name="roleName" type="text" ></td></tr>
			</table>
		</form>
	</div>
	
	<div id="FUNDLG"  style="display: none;width:400px;height:200px;padding:10px">
		<ul id="treeFun" class="ztree"></ul>
	</div>
</body>
</html>