<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/admin/dashboard">Helix Admin</a>
        <a class="navbar-brand" href="/">Back to Blog</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-user"></i> <sec:authentication property="principal.username" /> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li class="divider"></li>
                <li>
                    <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>


    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="navbar-collapse navbar-ex1-collapse collapse" aria-expanded="false" style="height: 1px;">
        <ul class="nav navbar-nav side-nav">
            <li class="active">
                <a href="/admin/dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Posts <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="demo" class="collapse">
                    <li>
                        <a href="/admin/addPost">Add Post</a>
                    </li>
                    <li>
                        <a href="/admin/posts">All Posts</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#demo2"><i class="fa fa-fw fa-arrows-v"></i> Categories <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="demo2" class="collapse">
                    <li>
                        <a href="/admin/addCategory">Add Category</a>
                    </li>
                    <li>
                        <a href="/admin/categories">All Categories</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#demo3"><i class="fa fa-fw fa-arrows-v"></i> Tags <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="demo3" class="collapse">
                    <li>
                        <a href="/admin/addTag">Add Tag</a>
                    </li>
                    <li>
                        <a href="/admin/tags">All Tags</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="/admin/comments"><i class="fa fa-fw fa-desktop"></i> Comments</a>
            </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li>
                    <a href="javascript:;" data-toggle="collapse" data-target="#demo4"><i class="fa fa-fw fa-arrows-v"></i> Moderators <i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="demo4" class="collapse">
                        <li>
                            <a href="/admin/addUser">Add Moderator</a>
                        </li>
                        <li>
                            <a href="/admin/users">All Moderators</a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>