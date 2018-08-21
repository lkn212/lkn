var zhname = null;
var version = null;
var keys = null;
var releaseDate = null;
var implDate = null;
var saveBtn = null;
var backBtn = null;

$(function() {
	zhname = $("#zhname");
	version = $("#version");
	keys = $("#keys");
	releaseDate = $("#releaseDate");
	implDate = $("#implDate");
	saveBtn = $("#save");
	backBtn = $("#back");
	
	saveBtn.bind("click",function(){
		if(confirm("是否提交数据")){
			$("#messageForm").submit();
		}
	});
	
	backBtn.on("click",function(){
		history.back(-1);
	});
});