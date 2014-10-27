<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Default tiles template</title>
    <link rel="stylesheet" href="/resources/css/style.css">
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