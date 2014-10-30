<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Default tiles template</title>

    <link rel="stylesheet" href="/resources/vendor/bootstrap/css/bootstrap.min.css" />

    <link rel="stylesheet" href="/resources/css/style.css">


    <script src="/resources/vendor/jquery/jquery-1.11.1.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <header>
        <tiles:insertAttribute name="header" />
    </header>

    <div class="content">
        <tiles:insertAttribute name="content" />
    </div>

    <footer>
        <tiles:insertAttribute name="footer" />
    </footer>

</body>
</html>