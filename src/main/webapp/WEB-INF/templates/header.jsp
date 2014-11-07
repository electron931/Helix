<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-default navbar-fixed-top" role="navigation">

    <sec:authorize access="hasRole('ROLE_MODERATOR')">

        <div class="adminHeader">
            <div class="insideHeader">
                <span class="welcome">Hello, <sec:authentication property="principal.username" />!</span>
                <a href="/admin/logout" class="adminLinks">Logout</a>
                <a href="/admin" class="adminLinks">Go to Admin</a>
            </div>
        </div>


    </sec:authorize>


    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Helix</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/about">About</a></li>
                <li><a href="/contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>