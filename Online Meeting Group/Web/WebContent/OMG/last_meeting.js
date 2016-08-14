

$(document).ready(
		function() {
			start();
			
			
			function start () {
				var query = {
						encode : $("#encode").val()
	};
	
$.ajax({
	type : "GET",
url : "chattest.jsp",
data : query,
dataType : 'xml',
timeout : 1000,
error : function()
{
	alert('Error loading XML document');
},
success : function(xml) {
	$("#parent").html("");

var turn = true;
var myname = "["+$("#myname").val()+"] ";

$Message = $(xml).find('Message');
$Message.each(function(){
	var name = $(this).find("name").text();
var msg = $(this).find("text").text();
var time = $(this).find("time").text();
var view = "<ul><li><div class='bubble' id='messageView'>" + name + " : " + msg + "<span class='personName'></span> <br> <span class='personSay'></span></div> <span class='time round'>"+time+"<span><img src='img/right.png' width='21' height='15'></span></span></li></ul><ul>"
var view2 = "<ul><li><div class='bubble2' id='messageView2'>" + name + " : " + msg + "<span class='personName2'></span> <br> <span class='personSay2'></span></div> <span class='time round2'>"+time+"<span><img src='img/right.png' width='21' height='15'></span></span></li></ul><ul>"
//var view = "<div>이름 : " + name + "</div><br>" + "<div>메세지 : " + msg + "</div><br>";
if (myname==name)
{
	$("#parent").append(view2);
	
}
else if (myname != name)
{
	
	$("#parent").append(view);
	turn = true;
}


//$("#chattotal").animate({ scrollTop: $(document).height() }, "fast");
		});
		
	}
});
$("msgText").focus();	
	}
	
	
});
