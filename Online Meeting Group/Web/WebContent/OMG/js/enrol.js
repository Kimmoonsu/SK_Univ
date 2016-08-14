var status = true;

$(document).ready(
		function() {
			
			$("#enrolmain0").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button0'class='btn btn-danger' value='X'>";
				$("#deleteEnrol0").html(change);
			});

			$("#enrolmain1").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button1' class='btn btn-danger' value='X'>";
				$("#deleteEnrol1").html(change);
			});
			$("#enrolmain2").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button2'class='btn btn-danger' value='X'>";
				$("#deleteEnrol2").html(change);
			});

			$("#enrolmain3").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button3' class='btn btn-danger' value='X'>";
				$("#deleteEnrol3").html(change);
			});
			$("#enrolmain4").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button4'class='btn btn-danger' value='X'>";
				$("#deleteEnrol4").html(change);
			});

			$("#enrolmain5").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button5' class='btn btn-danger' value='X'>";
				$("#deleteEnrol5").html(change);
			});
			$("#enrolmain6").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button6'class='btn btn-danger' value='X'>";
				$("#deleteEnrol6").html(change);
			});

			$("#enrolmain7").click(function() {// [회원정보수정]버튼 클릭
				var change = "<input type='button7' class='btn btn-danger' value='X'>";
				$("#deleteEnrol7").html(change);
			});
			
			
			
			$("#deleteEnrol0").click(function () {
				var query = {
					encode : $("#encode0").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});

			$("#deleteEnrol1").click(function () {
				var query = {
					encode : $("#encode1").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#deleteEnrol2").click(function () {
				var query = {
					encode : $("#encode2").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#deleteEnrol3").click(function () {
				var query = {
					encode : $("#encode3").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#deleteEnrol4").click(function () {
				var query = {
					encode : $("#encode4").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#deleteEnrol5").click(function () {
				var query = {
					encode : $("#encode5").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#deleteEnrol6").click(function () {
				var query = {
					encode : $("#encode6").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#deleteEnrol7").click(function () {
				var query = {
					encode : $("#encode7").val()
				};
				$.ajax({
					type : "post",
					url : "deleteEnrol.jsp",
					data : query,
					success : function(data) {	
						if (data == 1) {
							alert("회의가 삭제되었습니다.");
							window.location.reload();
						}
						else if (data == -1)
							alert("다시 시도하여 주십시오");
					}
				});
				
			});
			
			$("#viewMeeting").click(function() {
				alert("클릭");
				var child = window.open("","","width=1000, height=1000" );
				child.moveBy(0,0);
				child.location = "ViewMeeting.jsp";
			});
		});