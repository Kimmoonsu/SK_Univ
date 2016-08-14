<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Creative - Bootstrap Admin Template</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->
<link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link href="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
<link href="css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="css/fullcalendar.css">
<link href="css/widgets.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link href="css/xcharts.min.css" rel=" stylesheet">
<link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.7.js"></script>
<script src="modify.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
    <script>
    
    var identify = function()
	{
		$("#main-content2").load("allIdentify.jsp");
		
	}
    
    //회의 등록 클릭 시 동작 함수
    var enrol = function()
    {
    	$("#main-content2").load("enrol_meeting.jsp");
    }
    
    var enrol_meetingView = function()
    {
    	$("#main-content2").load("postit.jsp");
    }
    var last_meeting = function()
    {
    	$("#main-content2").load("last_meeting.jsp");
    }
   
   
    
    </script>
    
</head>

<body>
<%

	ConnectDB connectDB= ConnectDB.getConnectDB();
	//아이디와 비밀번호에 해당하는 사용자의 정보를 얻어냄
	String id = (String)session.getAttribute("id");
	
	DataModel dataModel = connectDB.getMember(id,"1"); // "1" 은 필요없는것
	
	String name =dataModel.getName();
	String department = dataModel.getDepartment();
	String position = dataModel.getPosition();
	String email = dataModel.getEmail();
	System.out.println(dataModel.getURL());
	String profile_url = "Imagefile/"+dataModel.getURL();
	
%>
	<!-- container section start -->
	<section id="container" class="">
		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>

			<!--logo start-->
			<a href="index.jsp" class="logo">O.M.G. &nbsp;<span class="lite">homepage</span></a>
			<!--logo end-->

			


		</header>
		<!--header end-->

		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu">

					<li class="active"><a class="" href="index.jsp"> <i
							class="icon_house_alt"></i> <span>홈으로 가기</span>
					</a></li>
					<li class="sub-menu"><a class="" href="" onclick = "identify(); return false;"  > <i
							class="icon_document_alt" ></i> <span>명함보기</span>
					</a>
						
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_desktop"></i> <span>회의 보기</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="" onclick="last_meeting(); return false;">지난 회의 보기</a></li>
							<li><a class="" href="#" onclick="enrol_meetingView(); return false;">등록 회의 보기</a></li>
							<li><a class="" id = "chatting" name = "chatting" href="#" onclick = "chatting(); return false;">진행 중인 회의 보기</a></li>
						</ul></li>
					<li><a class="" href="" onclick="enrol(); return false;"> <i
							class="icon_genius"></i> <span>회의등록</span>
					</a></li>
					
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<!--main content start-->
		
		<section id="main-content">
			<section class="wrapper" id ="main-content2">
				<!--overview start-->
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="fa fa-laptop"></i> 
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="index.jsp">Home</a></li>
							<li><i class="fa fa-laptop"></i>O.M.G.</li>
						</ol>
					</div>
				</div>

				<div class="row" id="calendarview">
					<div class="col-md-6 portlets">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<strong>Calendar</strong>
								</h2>
								<div class="panel-actions">
									<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="fa fa-times"></i></a>
								</div>

							</div>
							<br>
							<br>
							<br>
							<div class="panel-body">
								<!-- Widget content -->

								<!-- Below line produces calendar. I am using FullCalendar plugin. -->
								<div id="calendar"></div>

							</div>
						</div>

					</div>
					<!-- 
				 <div class="col-md-6 portlets">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="pull-left">Quick Post</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="panel-body">
                  <div class="padd">
                    
                      <div class="form quick-post">
                   
                                      <form class="form-horizontal">
                   
                                          <div class="form-group">
                                            <label class="control-label col-lg-2" for="title">Title</label>
                                            <div class="col-lg-10"> 
                                              <input type="text" class="form-control" id="title">
                                            </div>
                                          </div>   
                   
                                          <div class="form-group">
                                            <label class="control-label col-lg-2" for="content">Content</label>
                                            <div class="col-lg-10">
                                              <textarea class="form-control" id="content"></textarea>
                                            </div>
                                          </div>                           
                   
                                          <div class="form-group">
                                            <label class="control-label col-lg-2">Category</label>
                                            <div class="col-lg-10">                               
                                                <select class="form-control">
                                                  <option value="">- Choose Cateogry -</option>
                                                  <option value="1">General</option>
                                                  <option value="2">News</option>
                                                  <option value="3">Media</option>
                                                  <option value="4">Funny</option>
                                                </select>  
                                            </div>
                                          </div>              
                   
                                          <div class="form-group">
                                            <label class="control-label col-lg-2" for="tags">Tags</label>
                                            <div class="col-lg-10">
                                              <input type="text" class="form-control" id="tags">
                                            </div>
                                          </div>
                                          <div class="form-group">
                   
											 <div class="col-lg-offset-2 col-lg-9">
												<button type="submit" class="btn btn-primary">Publish</button>
												<button type="submit" class="btn btn-danger">Save Draft</button>
												<button type="reset" class="btn btn-default">Reset</button>
											 </div>
                                          </div>
                                      </form>
                                    </div>
                  </div>
                  <div class="widget-foot">
                   
                  </div>
                </div>
              </div>
            </div>
                 -->
					<div class="col-md-6 portlets">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="pull-left">명함</div>
								<div class="widget-icons pull-right">
									<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
									<a href="#" class="wclose"><i class="fa fa-times"></i></a>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="panel-body" id ="credit">
								<div class="padd">

									<div class="form quick-post">

										<div class="">

											<table>
												<tr>
													<td rowspan="5"><img alt="" src='<%=profile_url %>' width="200" height="200"></td>
													<td><br> <br> <br></td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td width="300" align="right"></td>
												</tr>
												<tr>
													<td width="300" align="right"></td>
												</tr>
												<tr>
													<td width="300" align="right" ><%=name%><br><%=department%><br><%=position%><br><%=email %></td>
												</tr>
												
											</table>
											<br> <br>

											<div class="form-group">

												<div class="col-lg-offset-2 col-lg-9">

													<input type="button" class="btn btn-primary" id="update"
														value="회원정보 수정"> 
													<input type="button" class="btn btn-default" id="logout" value="로그아웃">

												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="widget-foot"></div>
							</div>
						</div>
					</div>
				</div>
				<!-- project team & activity end -->

			</section>
		</section>
		
		<!--main content end-->
	</section>
	<!-- container section start -->

	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.4.min.js"></script>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- charts scripts -->
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>
	<script src="js/jquery.sparkline.js" type="text/javascript"></script>
	<script src="assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="js/owl.carousel.js"></script>
	<!-- jQuery full calendar -->
	<
	<script src="js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
	<!--script for this page only-->
	<script src="js/calendar-custom.js"></script>
	<script src="js/jquery.rateit.min.js"></script>
	<!-- custom select -->
	<script src="js/jquery.customSelect.min.js"></script>
	<script src="assets/chart-master/Chart.js"></script>

	<!--custome script for all page-->
	<script src="js/scripts.js"></script>
	<!-- custom script for this page-->
	<script src="js/sparkline-chart.js"></script>
	<script src="js/easy-pie-chart.js"></script>
	<script src="js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="js/xcharts.min.js"></script>
	<script src="js/jquery.autosize.min.js"></script>
	<script src="js/jquery.placeholder.min.js"></script>
	<script src="js/gdp-data.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/sparklines.js"></script>
	<script src="js/charts.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>
	<script>

      //knob
      $(function() {
        $(".knob").knob({
          'draw' : function () { 
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
          $("#owl-slider").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });
	  
	  /* ---------- Map ---------- */
	$(function(){
	  $('#map').vectorMap({
	    map: 'world_mill_en',
	    series: {
	      regions: [{
	        values: gdpData,
	        scale: ['#000', '#000'],
	        normalizeFunction: 'polynomial'
	      }]
	    },
		backgroundColor: '#eef3f7',
	    onLabelShow: function(e, el, code){
	      el.html(el.html()+' (GDP - '+gdpData[code]+')');
	    }
	  });
	});



  </script>

</body>
</html>
