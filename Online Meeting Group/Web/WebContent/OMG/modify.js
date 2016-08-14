var status = true;

$(document).ready(
		function() {
//			
			setInterval (function() {
				var query = {
					};
					$.ajax({
						type : "post",
						url : "checkTime.jsp",
						data : query,
						success : function(data) {
							if(data==1){
								var child = window.open("","","width=1000, height=1000" );
								child.moveBy(0,0);
								child.location = "chatting.jsp";
							}
						}
					});
			},60000);
			 
			$("#chatting").click(function() {
				var query = {
				};
				$.ajax({
					type : "post",
					url : "checkState.jsp",
					data : query,
					success : function(data) {
						if(data==1){
							var child = window.open("","","width=1000, height=1000" );
							child.moveBy(0,0);
							child.location = "chatting.jsp";
							
						}
						else
						{
							alert("진행 중인 회의가 없습니다.^^");
						}
					}
				});
				
				
			});
		
			$("#update").click(function() {// [회원정보수정]버튼 클릭
				// 회원정보를 수정 및 회원 탈퇴를 위한 modify.jsp 페이지 요청
				$("#credit").load("modify.jsp");
			});

			// modify.jsp페이지의 [정보수정]버튼을 클릭하면 자동실행
			// 입력한 비밀번호를 갖고 memberCheck.jsp페이지 실행

			$("#modify").click(
					function() {// [회원정보수정]버튼 클릭
						var query = {
							passwd : $("#passwd").val()
						};

						$.ajax({
							type : "post",
							url : "memberCheck.jsp",
							data : query,
							success : function(data) {
								alert(data);
								if (data == 1)// 비밀번호가 맞음
									$("#status").load(
											"modifyForm.jsp?passwd="
													+ $("#passwd").val());
								else {// 비밀번호 틀림
									alert("비밀번호가 맞지 않습니다.");
									$("#passwd").val("");
									$("#passwd").focus();
								}
							}
						});
					});

			// modifyForm.jsp페이지의 [수정]버튼 클릭시 자동실행
			// 수정폼에 입력한 값을 갖고 modifyPro.jsp 실행
			$("#modifyProcess").click(function() {
				var query = {
					id : $("#id").val(),
					passwd : $("#passwd").val(),
					name : $("#name").val(),
					email : $("#email").val(),
					department : $("#department").val(),
					position : $("#position").val()
				};

				$.ajax({
					type : "post",
					url : "modifyPro.jsp",
					data : query,
					success : function(data) {
						if (data == 1) {// 정보수정 성공
							alert("회원정보가 수정되었습니다.");

							window.location.href("index.jsp");
						}
					}
				});
			});

			// modifyForm.jsp페이지의 [취소]버튼 클릭시 자동실행
			$("#cancle").click(function() {
				window.location.href("main.jsp");
			});

			// modify.jsp페이지의 [탈퇴]버튼을 클릭하면 자동실행
			// 입력한 비밀번호를 갖고 memberCheck.jsp실핼 후
			// 비밀번호가 맞으면 deletePro.jsp페이지를 실행
			$("#delete").click(function() {// [회원정보수정]버튼 클릭
				var query = {
					passwd : $("#passwd").val()
				};

				// 입력한 비밀번호를 갖고 memberCheck.jsp페이지 실행
				$.ajax({
					type : "post",
					url : "memberCheck.jsp",
					data : query,
					success : function(data) {
						if (data == 1) {// 비밀번호 맞음
							// 회원탈퇴 페이지 deletePro.jsp 실행
							$.ajax({
								type : "POST",
								url : "deletePro.jsp",
								data : query,
								success : function(data) {
									if (data == 1) {// 탈퇴 성공
										alert("회원 탈퇴가 되었습니다.");
										$("#main_auth").load("loginForm.jsp");
									}
								}
							});
						} else {// 비밀번호 틀림
							alert("비밀번호가 맞지 않습니다.");
							$("#passwd").val("");
							$("#passwd").focus();
						}
					}
				});
			});

			// [로그아웃]버튼을 클릭하면 자동실행
			// logout.jsp페이지를 생행
			$("#logout").click(function() {// [회원정보수정]버튼 클릭
				$.ajax({
					type : "POST",
					url : "logout.jsp",
					success : function(data) {
						window.location.href("login.jsp");
					}
				});
			});

			$('#searchname').keyup(function() {
				var query = {
					search : $("#searchname").val(),
					nametarget : $("#nametarget").val()
				};
				$.ajax({
					type : "post",
					url : "searchname.jsp",
					data : query,
					success : function(data) {	
						$("#nametarget").html(data);
					}
				});

			});

			
			
			$("#okform").submit(function() {

				checkIt(); // 입력폼에 입력한 상황 체크
				alert(status);
				if (status) {
					
					var selectname1=""; 
					 $('#selectname:checked').each(function() { 
				        selectname1 += "#"+$(this).val();
					   });
					var query = {
						title : $("#title").val(),
						month : $("#month").val(),
						day : $("#day").val(),
						hour : $("#hour").val(),
						minute : $("#minute").val(),
						selectname : selectname1  
					};

					$.ajax({
						type : "post",
						url : "Enrol.jsp",
						data : query,
						success : function(data) {
							if (data == 1) {
								alert("회의가 등록되었습니다.");
								window.location.href("index.jsp");
							}
//
//							else if (data == -1)
//								alert("회의 등록에 실패하셨습니다. 다시 시도하여 주십시오");
						}
					});
				}
			});
			
			 
			// 사용자가 회의 입력폼에 입력한 상황을 체크
			function checkIt() {
				status = true;

				if (!$("#title").val()) {// 아이디를 입력하지 않으면 수행
					alert("title 입력하세요");
					$("#title").focus();
					status = false;
					return false;// 사용자가 서비스를 요청한 시점으로 돌아감
				}

				if (!$("#month").val()) {// 비밀번호를 입력하지 않으면 수행
					alert("month 입력하세요");
					$("#month").focus();
					status = false;
					return false;
				}
				// 비밀번호와 재입력비밀번호가 같지않으면 수행

				if (!$("#day").val()) {// 비밀번호를 입력하지 않으면 수행
					alert("day 입력하세요");
					$("#day").focus();
					status = false;
					return false;
				}

				if (!$("#hour").val()) {// 이름을 입력하지 않으면 수행
					alert("hour 입력하세요");
					$("#hour").focus();
					status = false;
					return false;
				}
				if (!$("#minute").val()) {// 비밀번호를 입력하지 않으면 수행
					alert("minute 입력하세요");
					$("#minute").focus();
					status = false;
					return false;
				}
				if (!$("#searchname").val()) {// 비밀번호를 입력하지 않으면 수행
					alert("searchname 입력하세요");
					$("#searchname").focus();
					status = false;
					return false;
				}
			}

		});