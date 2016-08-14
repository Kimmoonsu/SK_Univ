
$(document)
.ready(
function() {
start();
					function start() {
$("msgText").focus();
var query = {
encode : $("#encode").val()
};
$.ajax({
			type : "GET",
url : "chattest.jsp",
data : query,
dataType : 'xml',
timeout : 1000,
error : function() {
	alert('Error loading XML document');
},
success : function(xml) {
	$("#parent").html("");

var turn = true;
var myname = "[" + $("#myname").val()
+ "] ";

$Message = $(xml).find('Message');
$Message
		.each(function() {
			var name = $(this).find(
					"name").text();
var msg = $(this).find(
		"text").text();
var time = $(this).find(
		"time").text();
var url = $(this).find(
		"url").text();
var view = "<ul><li><div class='leftalign'><img class='' src='Imagefile/"
+ url
+ "' width='50' height='50'><div class='bubble' id='messageView'>"
+ name
+ " : "
+ msg
+ "</div> <span class='time round'>"
+ time
+ "</span></div></li></ul>"
var view2 = "<ul><li><div class='rightalign'><div class='bubble2' id='messageView2'>"
+ name
+ " : "
+ msg
+ "</div> <span class='time2 round'>"
+ time
+ "</span></div></li></ul>"
// var view = "<div>이름 : " +
// name + "</div><br>" +
// "<div>메세지 : " + msg +
"</div><br>";
if (myname == name) {
	$("#parent").append(
			view2);

} else if (myname != name) {

	$("#parent").append(
			view);
	turn = true;
}

// $("#chattotal").animate({
// scrollTop:
// $(document).height() },
// "fast");
						});

			}
		});

$("msgText").focus();

}

setInterval(
		function() {
			$("msgText").focus();
var query = {
	encode : $("#encode").val()
};
$.ajax({
	type : "post",
url : "chattest.jsp",
data : query,
success : function(data) {

var query = {
		encode : $("#encode").val()
};
$.ajax({
	type : "GET",
url : "chattest.jsp",
data : query,
dataType : 'xml',
timeout : 1000,
error : function() {
alert('Error loading XML document');
},
success : function(xml) {
	$("#parent").html("");
var turn = true;
var myname = "["+ $("#myname").val()+ "] ";
$Message = $(xml).find('Message');$Message.each(function() {
var name = $(this).find("name").text();
var msg = $(this).find("text").text();
var time = $(this).find("time").text();
var url = $(this).find("url").text();
var view = "<ul><li><div class='leftalign'><img class='' src='Imagefile/" 
+ url + "' width='50' height='50'><div class='bubble' id='messageView'>"
+ name + " : " + msg + "</div> <span class='time round'>" + time 
+ "</span></div></li></ul>" 
var view2 = "<ul><li><div class='rightalign'><div class='bubble2' id='messageView2'>"
+ name + " : " + msg + "</div> <span class='time2 round'>" + time
+ "</span></div></li></ul>"
									
if (myname == name) {
	$("#parent").append(view2);
} 
else if (myname != name) {
	$("#parent").append(view);
					}
				});
			}
		});
	}
	});
	}, 1000);

$("#msgbtn")
.click(
		function() {
			$("msgText").focus();
var query = {
	id : $("#id").val(),
msgText : $("#msgText").val(),
encode : $("#encode").val()
};
$
		.ajax({
			type : "post",
url : "inputMessage.jsp",
data : query,
success : function(xml) {

	var query2 = {
		encode : $(
				"#encode")
			.val()
};

$
		.ajax({
			type : "GET",
url : "chattest.jsp",
data : query2,
dataType : 'xml',
timeout : 1000,
error : function() {
	alert('Error loading XML document');
},
success : function(
		xml) {

	$(
			"#parent")
.html(
		"");
var myname = "["
+ $(
		"#myname")
		.val()
+ "] ";
var turn = true;
$Message = $(
		xml)
		.find(
				'Message');
$Message
		.each(function() {
			var name = $(
					this)
					.find(
							"name")
		.text();
var msg = $(
		this)
		.find(
				"text")
		.text();
var time = $(
		this)
		.find(
				"time")
		.text();
var url = $(
		this)
		.find(
				"url")
		.text();
var view = "<ul><li><div class='leftalign'><img class='' src='Imagefile/"
+ url
+ "' width='50' height='50'><div class='bubble' id='messageView'>"
+ name
+ " : "
+ msg
+ "</div> <span class='time round'>"
+ time
+ "</span></div></li></ul>"
var view2 = "<ul><li><div class='rightalign'><div class='bubble2' id='messageView2'>"
+ name
+ " : "
+ msg
+ "</div> <span class='time2 round'>"
+ time
+ "</span></div></li></ul>"

if (myname == name) {
	$(
			"#parent")
			.append(
					view2);

} else if (myname != name) {
	$(
			"#parent")
			.append(
					view);
}

// $("#chattotal").animate({
// scrollTop: $(document).height()
// }, "fast");
															});

												}
											});
								}
							});

				});

$("#finish").click(function() {
alert("클릭");
var query = {
	encode : $("#encode").val()
};
$.ajax({
	type : "post",
url : "finishEnrol.jsp",
data : query,
success : function(data) {
	alert("check : " + data);
if (data == 1) {
	alert("회의가 종료되었습니다!");
	window.close();
} else {
	alert("회의를 종료하실 수 없습니다.");
				}
			}
		});
	});

});
