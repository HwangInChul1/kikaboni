<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp" %>


<div class="container d-flex justify-content-center">
	<h1></h1>
</div>

<div class="container d-flex justify-content-center imgslide mb-2 mt-5">
         <img alt="이미지1" src="${ctxPath}/resources/images/image1.jpg" class="img">
         <img alt="이미지2" src="${ctxPath}/resources/images/image2.jpg" class="img">
         <img alt="이미지3" src="${ctxPath}/resources/images/image3.jpg" class="img">
         <img alt="이미지4" src="${ctxPath}/resources/images/image4.jpg" class="img">
         <img alt="이미지5" src="${ctxPath}/resources/images/image5.jpg" class="img">
</div>



<div class="container d-flex justify-content-center mb-5">

</div>

<%@ include file="includes/footer.jsp" %>

<style>

.img {

width : 90%;
height : 500px;

}

.imgbtn {

height : 20px;
width : 20px;
border-radius : 50%;
background-color : white;
border : 1px solid blue;

}


</style>


<script>

var index = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("img");
   
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    
   	index++;
   	
    if (index > slides.length) {
        index = 1
    }
    slides[index - 1].style.display = "block";

    setTimeout(showSlides, 4000); 
    
}


</script>


