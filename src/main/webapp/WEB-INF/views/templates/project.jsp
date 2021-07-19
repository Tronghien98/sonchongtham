<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sơn Chống Thấm</title>
    
    <c:url value="/resources/project" var="projectContextPath" scope="application" ></c:url>

    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${projectContextPath}/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${projectContextPath}/css/style.css" type="text/css">
</head>

<jsp:useBean id="stringUtil" class="spring.util.StringUtil" scope="application"></jsp:useBean>
<jsp:useBean id="dateUtil" class="spring.util.DateUtil" scope="application"></jsp:useBean>
<c:url value="/" var="urlIndex" scope="application"></c:url>
<c:url value="/tin-tuc" var="urlBlog" scope="application"></c:url>
<c:url value="/danh-muc" var="urlCat" scope="application"></c:url>
<c:url value="/chi-tiet" var="urlDetail" scope="application"></c:url>
<c:url value="/lien-he" var="urlContact" scope="application"></c:url>

<body>
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <tiles:insertAttribute name="header" ></tiles:insertAttribute>

    <tiles:insertAttribute name="body" ></tiles:insertAttribute>

    <tiles:insertAttribute name="footer" ></tiles:insertAttribute>

    <script src="${projectContextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${projectContextPath}/js/bootstrap.min.js"></script>
    <script src="${projectContextPath}/js/jquery.nice-select.min.js"></script>
    <script src="${projectContextPath}/js/jquery-ui.min.js"></script>
    <script src="${projectContextPath}/js/jquery.slicknav.js"></script>
    <script src="${projectContextPath}/js/mixitup.min.js"></script>
    <script src="${projectContextPath}/js/owl.carousel.min.js"></script>
    <script src="${projectContextPath}/js/main.js"></script>
</body>
</html>