$(function() {
	$(".addBtn").on("click",function(){
		window.location.href="message/messageadd.html";
	});
	
	$(".modifyMessage").on("click",function(){
		var obj = $(this);
		window.location.href="message/modify.html?id="+obj.attr("id");
	});
	
	
	$(".delBtn").on("click",function(){
		var idsArray = new Array();
		$("input[name='checkedmessagetd']:checked").each(function(i) {
			idsArray.push($(this).val());
		});
		var ids = idsArray.join(",");
		alert(ids);
		if(ids.length<1){
			alert("请选择删除项！");
		}else{
			if(confirm("确定删除吗？")){
				$.ajax({
					type:"POST",
					url:"message/del.html",
					data:{id:ids},
					dataType:"json",
					success:function(data){
						if("success" == data){
							alert("删除成功！");
							window.location.href="message/showmessage.html?pageIndex="+$("#pageIndex").val();
						}
						if("error" == data){
							alert("删除出错！");
						}
					},
					error:function(data){
						alert("删除失败！");
					}
				});
			}
		}
	});
});
