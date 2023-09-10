<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberVO" var="authInfo"/>
	<sec:authentication property="principal.memberVO.authList" var="authList"/>
</sec:authorize>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>

let ctxPath = '${ctxPath}';
let duplicationLogin = '${duplicationLogin}';

let memberId = '${authInfo.memberId}';
let auth = '${authList}';

let csrfHeaderName = '${_csrf.headerName}';
let csrfTokenValue = '${_csrf.token}';

if(duplicationLogin){
	alert(duplicationLogin);
}

$(document).ajaxSend(function(e, xhr, options){
	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
})


</script>
</head>
<style>

.pageLogo {
	position : relative;
	box : top;
	display : block;
	width : 20%;
	text-align : center;
}

.pagetitle {
	position : absolute;
	text-align : center;
	top : 0;
	left : 50%;
	width : 100px;
	height : 100px;
	transform : translate(-90%, +10%);
}

.threeBtn{
	position : absolute;
	top : 0;
	right : 0;
	margin-right : 50px;
	margin-top : 20px;
}

span {

width : 800px;

}



body {
	min-height : 100vh;
}

</style>
<body>

<div class="container">

		<img src="${ctxPath}/resources/images/kikaboniLogo.jpg" alt="제목" class="pageLogo mx-auto d-block"/>

	<div class="mb-3">
		<h1 class="pagetitle">KIKABONI</h1>
	</div>
	
	<div class="threeBtn">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="${ctxPath}/admin/adminPage" class="btn btn-outline-primary">관리자페이지</a>
		</sec:authorize>
		<a href="${ctxPath}/member/joinForm"><button class="btn btn-outline-primary">회원가입</button></a>
		<sec:authorize access="isAnonymous()">
			<a href="${ctxPath}/login" class="btn btn-outline-primary">로그인</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<a href="${ctxPath}/mypage" class="btn btn-outline-primary">마이페이지</a>
			<a href="${ctxPath}/logout" class="btn btn-outline-primary logout">로그아웃</a>
		</sec:authorize>
	</div>
</div>

<nav class="navbar navbar-expand-sm navbar-blue justify-content-center" 
style="border:1px solid; background-color:LightGray;">

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item mr-5">
      <a class="nav-link active" href="${ctxPath}/">홈</a>
    </li>
 	<li class="nav-item dropdown mr-5">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
       	메뉴 / 주문하기
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="${ctxPath}/menu/orderMenu_bread">빵</a>
        <a class="dropdown-item" href="${ctxPath}/menu/orderMenu_cake">케이크</a>
        <a class="dropdown-item" href="${ctxPath}/menu/orderMenu_coffee">커피</a>
      </div>
    </li>
	<li class="nav-item dropdown mr-5">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        자유게시판
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="${ctxPath}/boardkind/breadGoodList">빵 추천합니다.</a>
        <a class="dropdown-item" href="${ctxPath}/boardkind/breadMenuProposalList">빵 메뉴 건의</a>
        <a class="dropdown-item" href="${ctxPath}/boardkind/CEOtalkList">사장님께 한 마디</a>
      </div>
    </li>
	<li class="nav-item dropdown mr-5">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        커뮤니티
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="${ctxPath}/boardkind/eventList">공지사항/이벤트</a>
      </div>
    </li>
    <li class="nav-item mr-5">
      <a class="nav-link" href="${ctxPath}/introduce/breadHouse">빵집 소개</a>
    </li>
    <li class="nav-item mr-5">
      <a class="nav-link" href="${ctxPath}/coming/comingStreet">오시는 길</a>
    </li>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
	    <li class="nav-item">
	      <a class="nav-link" href="${ctxPath}/menu/menuRegister">메뉴 등록</a>
	    </li>
    </sec:authorize>
  </ul>
</nav>

<script>

$(function(){
	
	$('.logout').click(function(e){
		e.preventDefault(); // 기본동작 막고
		let form = $('<form/>',{action:$(this).attr('href'), method:'post'});
		form.append($('<input>',{type:'hidden', name:'${_csrf.parameterName}', value:'${_csrf.token}'}))
			.appendTo('body')
			.submit();
	})
})

</script>









