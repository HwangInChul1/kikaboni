<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
    
    
<div class="d-flex justify-content-center mb-5">
	<h1>오시는 길</h1>
</div>   

<div class="d-flex justify-content-center">
	<div id="map" style="width:500px;height:400px;"></div>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9ac954e1f638a59453a4fd50ef4908f3"></script>
		<script>
			var container = document.getElementById('map');
			var options = {
				center: new kakao.maps.LatLng(33.450701, 126.570667),
				level: 3
			};
	
			var map = new kakao.maps.Map(container, options);
		</script>
</div>    


    
    