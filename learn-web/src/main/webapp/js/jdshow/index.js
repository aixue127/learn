(function($) {
	var centerTabsDom = $("#centerTabs");
	// 获取所有的权限
	function queryFunctions(datas) {
		initMain(datas);
	}
	// 展示
	function initMain(data) {
		var fundom = $("#function");
		for(var k=0; k<data.length; k++) {
			getDom(data[k],fundom);
		}
		addClickEvent(fundom);
	}
	
	function getDom(fun, fundom) {
		// 获取父节点
		var parent = fun.parentFun;
		var panel =  fundom.accordion('getPanel',parent.funName);
		var context = '<p id="'+fun.funCode+'"><a ref="'+fun.id+'" href="#" rel="' + fun.jdurl + '">'+fun.funName+'</a></p>';
		if(panel) {
			panel.panel("body").append(context)
		} else {
			fundom.accordion('add', {
				title: parent.funName,
				content: context,
				selected: false
			});
		}
	}
	
	function addClickEvent(fundom) {
		$("a[rel]",fundom).each(function() {
			var that = $(this);
			
			that.off().on("click",function() {
				var href = that.attr("rel");
				console.log("this is :"+href);
				// tab是否存在
				var tabObj = $('#centerTabs').tabs('getTab', that.text());
				if(tabObj) {
					 $('#centerTabs').tabs('select',$('#centerTabs').tabs('getTabIndex', tabObj));
					 $('#centerTabs').tabs('update', {
							tab: tabObj,
							options: {
								content : '<iframe src="' + href + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
							}
					 });
				} else {
					 $("#centerTabs").tabs('add', {
						title : that.text(),
						content : '<iframe src="' + href + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
						closable : true
					 });
				}
			})
		});
	}
	
	$.fn.jdshowMain = function(datas) {
		queryFunctions(datas);
	};
})(jQuery);