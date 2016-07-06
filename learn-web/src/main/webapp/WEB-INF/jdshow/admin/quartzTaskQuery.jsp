<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>定时任务管理</title>
	<script type="text/javascript">
		$(function() {
			$('#inittab').datagrid({
				loadMsg:"loading.....",
				rownumbers:true,
				singleSelect:true,
				pagination:true,
			    url:'${ctx}/jdshow/quartzTask/queryQuartzTask',
			    columns:[[
					{field:'id',title:'id',width:"5%"},
					{field:'jobName',title:'jobName',width:"10%"},
				    {field:'jobGroup',title:'jobGroup',width:"10%"},
			        {field:'ip',title:'ip',width:"20%"},
			        {field:'taskType',title:'taskType',width:"10%"},
			        {field:'taskBean',title:'taskBean',width:"10%"},
			        {field:'taskCron',title:'taskCron',width:"10%"},
			        {field:'status',title:'status',width:"10%", formatter:function(value, rowData, rowIndex) {
			        	if(rowData.status == "1") {
			        		return "启用";
			        	} else {
			        		return "停用";
			        	}
			        }},
			        {field:'operator',title:'operator',width:"10%", formatter:function(value, rowData, rowIndex){
			        	if(rowData.status == "1") {
			        		return "<a href='#' class='easyui-linkbutton' onclick=\"startOrStopTask("+rowData.id+",'0')\">disable</a>";
			        	} else {
			        		return "<a href='#' class='easyui-linkbutton' onclick=\"startOrStopTask("+rowData.id+",'1')\">enable</a>";
			        	}
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
		
		function startOrStopTask(id, operator) {
			$.post("${ctx}/jdshow/quartzTask/startOrStopTask",{id:id, operator:operator}, function(data) {
				if(data!=null && data.success) {
					$.messager.alert('notice','operator success!','info');
					$('#inittab').datagrid('reload');
				} else {
					$.messager.alert('notice','save fail!','error');
				}
			},"json");
		}
		
		function deleteRow() {
			$("#inittab").jdshowGridDelAjax({url:"${ctx}/jdshow/quartzTask/deleteTask",success:deleteCallback});
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
				$("#id").val("0")
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
			$("#fromXD").jdshowFormAjax({url:"${ctx}/jdshow/quartzTask/insOrUpdTask",success: successCallback});
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
<body fit="true">
	<div>
		<table id="inittab"></table>
	</div>
	<div id="DLG" style="display: none;width:400px;height:400px;padding:10px">
		<form id="fromXD">
			<input id="id" name="id" type="hidden" value="0">
			<table>
				<tr><td>编码</td><td><input id="jobName" name="jobName" type="text" ></td></tr>
				<tr><td>所属组</td><td>
					<select id="jobGroup" name="jobGroup">
						<option value="group1">group1</option>
						<option value="group2">group2</option>
					</select>
				</td></tr>
				<tr><td>执行IP</td><td><input id="ip" name="ip" type="text" ></td></tr>
				<tr><td>任务类型</td><td><input id="taskType" name="taskType" type="text" ></td></tr>
				<tr><td>执行Bean</td><td> <input id="taskBean" name="taskBean" type="text"></td></tr>
				<tr><td>执行Corn</td><td> <input id="taskCron" name="taskCron" type="text"></td></tr>
				<tr><td>状态</td><td>
					<select id="status" name="status">
						<option value="1">enable</option>
						<option value="0">disable</option>
					</select>
				</td></tr>
			</table>
		</form>
	</div>
</body>
</html>