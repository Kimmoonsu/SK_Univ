<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% response.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="modify.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="col-md-6 portlets" id="enrolmain">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="pull-left">회의 등록</div>
				<div class="widget-icons pull-right">
				
					<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
					<a href="#" class="wclose"><i class="fa fa-times"></i></a>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="panel-body">
				<div class="padd">

					<div class="form quick-post">

						<form class="form-horizontal"  id="okform">
							
							<div class="form-group">
								<label class="control-label col-lg-2" for="title">Title</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id = "title" name="title">
									<br>	
								</div>
								
								
								<label class="control-label col-lg-13">Select Meeting day</label>
								<div class="col-lg-13">
									<select class="form-control" name="month" id = "month">
										<option value="">- 월 -</option>
										<option value="01">1월</option>
										<option value="02">2월</option>
										<option value="03">3월</option>
										<option value="04">4월</option>
										<option value="05">5월</option>
										<option value="06">6월</option>
										<option value="07">7월</option>
										<option value="08">8월</option>
										<option value="09">9월</option>
										<option value="10">10월</option>
										<option value="11">11월</option>
										<option value="12">12월</option>
									</select> <select class="form-control" name="day" id="day">
										<option value="">- 일 -</option>
										<option value="01">1일</option>
										<option value="02">2일</option>
										<option value="03">3일</option>
										<option value="04">4일</option>
										<option value="05">5일</option>
										<option value="06">6일</option>
										<option value="07">7일</option>
										<option value="08">8일</option>
										<option value="09">9일</option>
										<option value="10">10일</option>
										<option value="11">11일</option>
										<option value="12">12일</option>
										<option value="13">13일</option>
										<option value="14">14일</option>
										<option value="15">15일</option>
										<option value="16">16일</option>
										<option value="17">17일</option>
										<option value="18">18일</option>
										<option value="19">19일</option>
										<option value="20">20일</option>
										<option value="21">21일</option>
										<option value="22">22일</option>
										<option value="23">23일</option>
										<option value="24">24일</option>
										<option value="25">25일</option>
										<option value="26">26일</option>
										<option value="27">27일</option>
										<option value="28">28일</option>
										<option value="29">29일</option>
										<option value="30">30일</option>
										<option value="31">31일</option>
									</select> <select class="form-control" name="hour" id="hour">
										<option value="">- 시 -</option>
										<option value="00">0시</option>
										<option value="01">1시</option>
										<option value="02">2시</option>
										<option value="03">3시</option>
										<option value="04">4시</option>
										<option value="05">5시</option>
										<option value="06">6시</option>
										<option value="07">7시</option>
										<option value="08">8시</option>
										<option value="09">9시</option>
										<option value="10">10시</option>
										<option value="11">11시</option>
										<option value="12">12시</option>
										<option value="13">13시</option>
										<option value="14">14시</option>
										<option value="15">15시</option>
										<option value="16">16시</option>
										<option value="17">17시</option>
										<option value="18">18시</option>
										<option value="19">19시</option>
										<option value="20">20시</option>
										<option value="21">21시</option>
										<option value="22">22시</option>
										<option value="23">23시</option>
									</select> <select class="form-control" name="minute" id="minute">
										<option value="">- 분 -</option>
										<option value="00">0분</option>
										<option value="05">5분</option>
										<option value="10">10분</option>
										<option value="15">15분</option>
										<option value="20">20분</option>
										<option value="25">25분</option>
										<option value="30">30분</option>
										<option value="35">35분</option>
										<option value="40">40분</option>
										<option value="45">45분</option>
										<option value="50">50분</option>
										<option value="55">55분</option>

									</select>
								<br>
								
								</div>
								
								<label class="control-label col-lg-2" for="tags">Search</label>
								<div class="nav search-row" id="top_menu">
									<!--  search form start -->

									<ul class="nav top-menu">
										<li>
											
												<input id="searchname" class="form-control"
													placeholder="Search" type="text"><br>
												<div id="nametarget"></div>	
										</li>
										
									</ul>

								</div>
								<br>
								
								<input type="submit" id ="okbtn" class="btn btn-primary" value="OK">
							</div>
							
							<div class="form-group">
							
							</div>
							<br>
							<div class="form-group">
															</div>

							<div class="form-group">
								
							</div>
							<div class="form-group">

								
							</div>
							
						</form>
					</div>
				</div>
				<div class="widget-foot"></div>
			</div>
		</div>
	</div>

</body>
</html>