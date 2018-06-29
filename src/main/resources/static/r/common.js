//10秒取一次消息
var timeInterval = setInterval(function() {
   sysMsg();
}, 10000);	

//页面显示消息
function msgDiv(msg){
	$("body").prepend('<div id="msgDiv" class="alert alert-warning alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+msg+'</div>');
	$("#msgDiv").fadeOut().fadeIn().fadeOut().fadeIn(); //闪啊闪啊闪	
	$('html, body').animate({scrollTop: $('#msgDiv').offset().top}, 1000); //滚动到锚点
}

//获取后台消息
function sysMsg(){
	$.post("/msg",
        {
  	    },
        function(data,status){
	        if(status == 'success'){
	        	if(data != ""){
	        		msgDiv(data);
	        	}
	        }else{
	        	console.log("error");	
	        }
    });
}