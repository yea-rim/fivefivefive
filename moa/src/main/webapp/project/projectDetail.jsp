<%@page import="moa.beans.RewardDto"%>
<%@page import="java.util.List"%>
<%@page import="moa.beans.RewardDao"%>
<%@page import="moa.beans.ProjectVo"%>
<%@page import="moa.beans.ProjectDto"%>
<%@page import="moa.beans.ProjectDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* int ProjectNo = Integer.parseInt(request.getParameter("projectNo")); */
	ProjectDao projectDao = new ProjectDao();
	/* 나중에 파라미터로 바꿔주기 */
	ProjectDto projectDto = projectDao.selectOne(11); /* 프로젝트불러오기 */
	/* 나중에 파라미터로 바꿔주기 */
	ProjectVo projectVo = projectDao.selectVo(11);
	
	RewardDao rewardDao = new RewardDao();
	
	/* 나중에 파라미터로 바꿔주기 */
	List<RewardDto> rewardList = rewardDao.selectProject(11); /* 해당 리워드목록 리스트 불러오기 */
	
	
	
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/commons.css">
    <%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/test.css"> --%>
</head>

<style>
    .reward {
      display: inline-flex;
      flex-direction: column;
      align-items: left; /* 가로 - 중앙으로 */
      justify-content: flex-start; /* 세로 - 상단으로 */
    }
    
    .h100p{
        height: 100%;
    }
</style>

<script type="text/javascript">
		
</script>

<body>
	<div class="container w1000 center">
        <div class="row w700 margin-auto m-b10">
        	<h1>
            <%=projectDto.getProjectName() %>
        	</h1>
        </div>

        <div class="float-container center m30">
            <!-- 상세페이지 프로필 부분 -->

            <div class="float-left w70p">
                <!-- 프로필부분의 왼쪽 플로트-->
                <div class="row layer-1">
                    <div class="img block">
                        <img src="https://via.placeholder.com/500x300" width="100%">
                    </div>

                </div>
            </div>



            <div class="float-left w30p left p10px">
                <!-- 프로필부분의 오른쪽 플로트 -->

                <div class="row fill h40">
                    <h2>
                        <%=projectVo.getDaycount() %>일 남음
                    </h2>
                </div>
                <div class="row fill h40 m20">
                    <h2>
                        <%=projectVo.getPercent() %> % 달성
                    </h2>
                </div>
                <div class="row fill h40 m20">
                    <h2>
                        <%=projectDto.getProjectPresentMoney() %>원 달성
                    </h2>
                </div>
                <div class="row fill h40">
                    <h4 class="m10">
                        후원자수
                    </h4>
                    <h3>
                        <%=projectDto.getProjectSponsorNo() %>명
                    </h3>
                </div>
                <hr>
                <div class="row fill h20">
                    <h5>
                        펀딩기간 <%=projectDto.getProjectStartDate() %> ~ <%=projectDto.getProjectSemiFinish() %>
                    </h5>
                </div>
                <div class="row fill h20 m-b10">
                    <h5>
                        목표금액 <%=projectDto.getProjectTargetMoney() %>
                    </h5>
                </div>
                <div class="row fill h80">
                    <button class="btn fill h40 m-b10">후원하기</button>
                </div>
                <div class="row fill h40">
                    <div class="float-container">
                        <div class="float-left left layer-3 h40">
                            <button class="btn w90p wrap h100p" style="font-size: 12px;">
                            	좋아요
                            	<br>
                            	<span style="font-size: 12px;">
                            	<%=projectVo.getJoacount() %>
                            	</span>
                            </button>
                        </div>
                        <div class="float-left center layer-3 h40" style="font-size: 14px;">
                            <button class="btn w90p h100p">문의</button>
                        </div>
                        <div class="float-left right layer-3 h40" style="font-size: 14px;">
                            <button class="btn w90p h100p">홍보</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>


<!-- 상세페이지 / 커뮤니티 메뉴바 -->
        <div class="row left h20 m10">
            <h5>펀딩소개</h5>
        </div>


        <div class="float-container center m30">
        
            <!-- 상세페이지 본문 부분-->

            <div class="float-left w70p">
                <img src="https://via.placeholder.com/500x2000" width="100%">
            </div>
            
            <!-- 본문 오른쪽 리워드 부분 -->
               <div class="float-left w30p p10px-left">
               		<%for(RewardDto rewardDto : rewardList){ %>	
	                	<div class="fill m-b10">
                    		<a href="#" class="link"><button class="btn fill reward" style="text-align: left;">
		                        리워드 이름
		                        <%=rewardDto.getRewardName() %>
		                        <br>
		                        리워드 내용
		                        <%=rewardDto.getRewardContent() %>
		                        <br>
		                        리워드 가격
		                        <%=rewardDto.getRewardPrice() %>
		                        <br>
		                        리워드 재고
		                        <%=rewardDto.getRewardStock() %>
                    		</button></a>
                		</div>
                	<%} %>
            	</div>
            
        </div>
    </div>

</body>
</html>