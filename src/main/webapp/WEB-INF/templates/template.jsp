<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>

    <%--<link rel="stylesheet" href="/resources/vendor/bootstrap/css/bootstrap.min.css" />--%>
    <link rel="stylesheet" href="/resources/vendor/bootstrap/css/bootstrap.min.theme.css" />

    <link rel="stylesheet" href="/resources/css/style.css">

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">


    <script src="/resources/vendor/jquery/jquery-1.11.1.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <script src="/resources/js/main.js"></script>
</head>
<body>

    <sec:authorize access="hasRole('ROLE_MODERATOR')">
        <style>
            body > .container {
                padding: 95px 15px 0;
            }
        </style>
    </sec:authorize>

    <header>
        <tiles:insertAttribute name="header" />
    </header>

    <div class="container">
        <div class="row">
            <tiles:insertAttribute name="content" />
            <hr>
        </div>
    </div>

    <footer>
        <tiles:insertAttribute name="footer" />
    </footer>

</body>
</html>