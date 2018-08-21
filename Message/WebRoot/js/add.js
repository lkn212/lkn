var stdNum = null;
var zhname = null;
var version = null;
var keys = null;
var releaseDate = null;
var implDate = null;
var a_packagePath = null;
var errorinfo = null;

/**
 * 提示信息显示
 * element:显示提示信息的元素（font）
 * css：提示样式
 * tipString:提示信息
 * status：true/false --验证是否通过
 */
function validateTip(element,css,tipString,status){
	element.css(css);
	element.html(tipString);
	
	element.prev().attr("validateStatus",status);
}

$(function() {
	a_packagePath = $("#a_packagePath");
	errorinfo = $("#errorinfo");
	
	stdNum = $("#stdNum");
	zhname = $("#zhname");
	version = $("#version");
	keys = $("#keys");
	releaseDate = $("#releaseDate");
	implDate = $("#implDate");
	addBtn = $("#add");
	backBtn = $("#back");
	
	stdNum.prev().html("*");
	zhname.prev().html("*");
	version.prev().html("*");
	keys.prev().html("*");
	
	//验证
	stdNum.bind("blur",function(){
		$.ajax({
			type:"GET",
			url:"message/snexist.html",
			data:{stdNum:stdNum.val()},
			dataType:"json",
			success:function(data){
				if(data.stdNum == "exist"){
					validateTip(stdNum.prev(),{"color":"red"},"该标准号已存在",false);
				}else{
					validateTip(stdNum.prev(),{"color":"green"},"该标准号可用",true);
				}
			},
			error:function(data){
				validateTip(stdNum.prev(),{"color":"red"}," 您访问的页面不存在",false);
			}
		});
	});
	
	addBtn.bind("click",function(){
		if(confirm("是否提交数据")){
			$("#messageForm").submit();
		}
	});
	
	backBtn.on("click",function(){
		history.back(-1);
	});
});