var status = true;

$(document).ready(function(){
	//[ID중복확인]버튼을 클릭하면 자동실행
	//입력한 아이디 값을 갖고 confirmId.jsp페이지 실행
	$("#checkId").click(function(){
	  if($("#id").val()){
		//아이디를 입력하고 [ID중복확인]버튼을 클릭한 경우
		var query = {id:$("#id").val()};
		
	    $.ajax({
	    	type:"post",//요청방식
	    	url:"confirmId.jsp",//요청페이지
	    	data:query,//파라미터
	    	success:function(data){//요청페이지 처리에 성공시
	    		if(data == 1){//사용할 수 없는 아이디
	    			alert("사용할 수 없는 아이디");
	    	    	$("#id").val("");
	    	     }else if(data == -1)//사용할 수 있는 아이디
	    	  	    alert("사용할 수 있는 아이디");
	 	    }
	    });
	  }else{//아이디를 입력하지 않고 [ID중복확인]버튼을 클릭한 경우
		  alert("사용할 아이디를 입력");
		  $("#id").focus();
	  }
	});
	
	//[가입하기]버튼을 클릭하면 자동실행
	//사용자가 가입폼인 registerForm.jsp페이지에 입력한 내용을 갖고
	//registerPro.jsp페이지 실행
	$("#process").click(function(){
	
	   checkIt(); //입력폼에 입력한 상황 체크
	   
	   if(status){
		var formData = new FormData();     	
		 formData.append("id", $("#id").val());
		 formData.append("passwd", $("input[name=passwd]").val());
		 formData.append("name", $("#name").val());
		 formData.append("department", $("input[name=department]").val());
		 formData.append("position", $("input[name=position]").val());
		 formData.append("email", $("input[name=email]").val());
     	  formData.append("profile", $("#profile")[0].files[0]);
     	  $.ajax({
     	    url: 'registerPro.jsp',
     	    data: formData,
		    async: false,
		    cache: false,
		    contentType: false, 
		    processData: false, 
     	    type: 'POST',
     	    success: function(data){
     	    	alert("회원가입 성공");
     	    	window.location.href="login.jsp";
     	    }
     	  });
	   }
	});
	
	//[취소]버튼을 클릭하면 자동실행
	$("#cancle").click(function(){
		window.location.href="login.jsp";
	});
 });

//사용자가 입력폼에 입력한 상황을 체크
function checkIt() {
	status = true;
	
    if(!$("#id").val()) {//아이디를 입력하지 않으면 수행
        alert("아이디를 입력하세요");
        $("#id").focus();
        status = false;
        return false;//사용자가 서비스를 요청한 시점으로 돌아감
    }
    
    if(!$("#passwd").val()) {//비밀번호를 입력하지 않으면 수행
        alert("비밀번호를 입력하세요");
        $("#passwd").focus();
        status = false;
        return false;
    }
    //비밀번호와 재입력비밀번호가 같지않으면 수행
    if($("#passwd").val() != $("#repass").val()){
        alert("비밀번호를 동일하게 입력하세요");
        $("#repass").focus();
        status = false;
        return false;
    }
    
    if(!$("#name").val()) {//이름을 입력하지 않으면 수행
        alert("사용자 이름을 입력하세요");
        $("#name").focus();
        status = false;
        return false;
    }
    
      
}