<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/header.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>产品类型管理</title>
		<style type="text/css">
			.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		</style>
	<script type="text/javascript">
		
		$(function() {
			var setting = {
					data: {
						simpleData: {
							enable: true
						}
					},
					check:{
						enable:false,
					},
					callback: {
						onClick:onClick
					}
				};
			$("#treeProdType").jdZtree({url:"${ctx}/jdshow/prodAndType/queryTypeTree",treeSetting:setting});
			
			$("#addBrother").click(function() {
				// 获取当前选中节点
				var treeObj = $.fn.zTree.getZTreeObj("treeProdType");
				var node = treeObj.getNodeByParam("name", $("#currNode").text(), null);
				if(node != null) {
					$("#id").val("");
					$("#parentId").val(node.pId?node.pId:"" );
					$("#treeNodeForm").jdshowFormAjax({url:"${ctx}/jdshow/prodAndType/saveOrUpdType",success: successCallback});
				} else {
					$.messager.alert('notice','place select node','info');
				}
			});
			
			$("#addChild").click(function() {
				// 获取当前选中节点
				var treeObj = $.fn.zTree.getZTreeObj("treeProdType");
				var node = treeObj.getNodeByParam("name", $("#currNode").text(), null);
				if(node != null) {
					$("#id").val("");
					$("#parentId").val(node.id );
					$("#treeNodeForm").jdshowFormAjax({url:"${ctx}/jdshow/prodAndType/saveOrUpdType",success: successCallback});
				} else {
					$.messager.alert('notice','place select node','info');
				}
			});
			
			$("#update").click(function() {
				$("#treeNodeForm").jdshowFormAjax({url:"${ctx}/jdshow/prodAndType/saveOrUpdType",success: successCallback});
			});
			
			$("#remove").click(function() {
				// 获取当前选中节点
				var treeObj = $.fn.zTree.getZTreeObj("treeProdType");
				var node = treeObj.getNodeByParam("name", $("#currNode").text(), null);
				if(node != null) {
					$.post("${ctx}/jdshow/prodAndType/deleteType",{id:node.id},function(data) {
						$("#treeNodeForm").jdclear();
						$("#currNode").html("");
						$.messager.alert('notice','operator success!','info');
					})
				} else {
					$.messager.alert('notice','place select node','info');
				}
			}) ;
			
			
			$("#reload").click(function() {
				$("#treeNodeForm").jdclear();
				$("#currNode").html("");
				$("#treeProdType").jdZtree({url:"${ctx}/jdshow/prodAndType/queryTypeTree",treeSetting:setting});
			});
			
			
		});
		
		function onClick(event, treeId, treeNode, clickFlag) {
			$("#currNode").html(treeNode.name);
			if(treeNode.pid) {
				$("#parentId").val(treeNode.pid);
			}
			$("#id").val(treeNode.id);
			$("#typeCode").val(treeNode.treeCode);
			$("#typeName").val(treeNode.name);
		}
		
		function successCallback(data) {
			if(data!=null && data.success) {
				// 关闭
				$.messager.alert('notice','operator success!','info');
				//$("#treeProdType").jdZtree({url:"${ctx}/jdshow/prodAndType/queryTypeTree",treeSetting:setting});
			} else {
				$.messager.alert('notice','operator fail!','error');
			}
		}
		
	</script>
</head>
<body>
	<div class="easyui-layout"  style="width:98%;height:98%;">
        <div id="p" data-options="region:'west'" title="West" style="width:30%;padding:10px">
        	<div>
        		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px" id="reload">Reload</a>
        	</div>
          	<ul id="treeProdType" class="ztree"></ul>
        </div>
        <div data-options="region:'center'" title="Center">
        	<div style="height: 30px;text-align: left;">当前节点：<label id="currNode"></label></div>
        	<div align="center">
        		<form id="treeNodeForm">
        			<input id="id" name="id" type="hidden">
        			<input id="parentId" name="parentId" type="hidden">
        			<table>
        				<tr><td>编码</td><td><input id="typeCode" name="typeCode" type="text" ></td></tr>
						<tr><td>名称</td><td><input id="typeName" name="typeName" type="text" ></td></tr>
						<tr><td colspan="2">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="update">Update</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="addBrother">Add Brother</a>
        					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="addChild">Add Child</a>
        					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="remove">Remove</a>
						</td></tr>
        			</table>
        		</form>
        	</div>
        </div>
    </div>
</body>
</html>