<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<%-- 1. 页面Datagrid初始化相关JS --%>
<%-- JS代码必须包含在页面中，引入外部JS文件会导致表格界面在未完成初始化前就显示，出现短暂的未初始化界面 --%>
<script type="text/javascript">
	//网站基本信息操作命名空间
	var Basic = {};
	$(function() {
		/*
		 * datagrid
		 */
		var dg = $("#BasicDataGrid");

		// EasyUIEx datagrid
		$("#BasicDataGrid").initDatagrid({
			/*
			 * CRUD URL
			 */
			url : "Basic/list",
			saveUrl : "Basic/save",
			updateUrl : "Basic/update",
			destroyUrl : "Basic/deleteBatch",
			
			idField : "id", // 主键列
			
			showHeaderContextMenu : true, // 表头添加右键菜单，可选择显示的列
			
			clickRowEdit:true, //注册单击行编辑，可以代替edatagrid实现带行编辑的CRUD
			
			checkbox:true,
			singleSelect:false,
			checkOnSelect:true,
			

			
			pagination : false,  // 分页
			// pageSize: 15,
			// pageList: [15, 20, 30, 40, 50],
			
			//queryParams:{"rows":dg.datagrid("options").pageSize}, // 分页查询请求参数
			
			sendRowDataPrefix : "", //提交数据前缀
			
			showMsg : true, // 是否显示添加，修改操作的成功提示
			successKey : "statusCode", //服务器端返回的成功标记key
			successValue : "200" //服务器端返回的成功标记value
			
			,mutipleDelete : true, // 多行提交删除
			mutipleDeleteProperty : "id" // 多行删除时提及给服务器的属性和值，不会添加sendRowDataPrefix前缀，支持使用数组指定多个属性名
		});

		/*
		 * Functions
		 */
		
		
		

		


	});
</script>

<%-- 2. Content Datagrid --%>
<table id="BasicDataGrid" title="网站基本信息列表" style="width: 100%"   idField="id" rownumbers="true" fitColumns="true" nowrap="false">
	<thead>
		<tr>
			<th field="ck" checkbox="true" width="50" sortable="true">多选框</th>
			<th field="email" width="50" sortable="true" editor="{type:'textbox',options:{}}">邮箱</th>
			<th field="qq" width="50" sortable="true" editor="{type:'textbox',options:{}}">QQ</th>
			<th field="tel" width="50" sortable="true" editor="{type:'textbox',options:{}}">电话</th>
			<th field="address" width="50" sortable="true" editor="{type:'textbox',options:{}}">公司地址</th>
			<th field="weibo" width="50" sortable="true" editor="{type:'textbox',options:{}}">微博地址</th>
			<th field="copyright" width="50" sortable="true" editor="{type:'textbox',options:{}}">底部版权</th>
			<th field="description" width="50" sortable="true" editor="{type:'textbox',options:{}}">小鹰简介</th>
		</tr>
	</thead>
</table>


<%-- 
<%@ include file="/WEB-INF/content/dialog/cmsmoudle/BasicAdd.jsp"%>  
<%@ include file="/WEB-INF/content/dialog/cmsmoudle/BasicEdit.jsp"%>  
--%>