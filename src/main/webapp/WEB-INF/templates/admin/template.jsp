<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="/resources/vendor/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/vendor/bootstrap/css/bootstrapvalidator.css">

    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/admin.css">


    <script src="/resources/vendor/jquery/jquery-1.11.1.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrapValidator.min.js"></script>


    <script src="/resources/js/admin.js"></script>
</head>
<body>



<div id="wrapper">

    <tiles:insertAttribute name="adminHeader" />

    <div id="page-wrapper">
        <div class="container-fluid">
            <tiles:insertAttribute name="adminContent" />
        </div>
    </div>

</div>


</body>
</html>