<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>产品管理</title>
		<style type="text/css">
			.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		</style>
	<script type="text/javascript">
		$(function() {
			var setting = {
					check: {
						enable: true,
						chkStyle : "checkbox", 
						chkboxType: { "Y" : "s", "N" : "ps" }
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
			$("#treeProdType").jdZtree({url:"${ctx}/jdshow/prodAndType/queryTypeTree",treeSetting:setting});
			
			$('#inittab').datagrid({
				loadMsg:"loading.....",
				rownumbers:true,
				singleSelect:true,
				pagination:true,
			    url:'${ctx}/jdshow/prodAndType/queryProduct',
			    columns:[[
			        {field:'id',title:'id',width:"10%"},
			        {field:'prodCode',title:'prodCode',width:"20%"},
			        {field:'prodName',title:'prodName',width:"20%"},
			        {field:'ProductType',title:'ProductType',width:"20%",formatter: function(value,row,index){
						if (row.producTypeList){
							var tmp_type="";
							for(var k=0;k<row.producTypeList.length;k++) {
								tmp_type += row.producTypeList[k]["typeName"] + ";"
							}
							return tmp_type;
						} else {
							return "";
						}
					}},
			        {field:'brand',title:'brand',width:"20%",formatter: function(value,row,index){
						if (row.brand){
							return row.brand["braName"];
						} else {
							return "";
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
						
					}
				}]
			});
		}
	</script>
</head>
<body>
	<div>
		<table id="inittab"></table>
	</div>
	
	<div id="DLG" style="display: none;width:400px;height:600px;padding:10px">
		<form id="fromXD">
			<input id="id" name="id" type="hidden">
			<table>
				<tr><td>产品编码</td><td><input id="userName" name="userName" type="text" ></td></tr>
				<tr><td>产品名称</td><td><input id="loginName" name="loginName" type="text" ></td></tr>
				<tr><td>产品类型</td><td><div id="prodTypeDiv" stype="display:none"><ul id="treeProdType" class="ztree"></ul></div>
				</td></tr>
				<tr><td>产品品牌</td><td></td></tr>
			</table>
		</form>
	</div>
</body>
</html>