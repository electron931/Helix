<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">


        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="page-header">
                Page Heading
                <small>Secondary Text</small>
            </h1>

            <c:forEach var="post" items="${posts}">

                <!-- Blog Post -->
                <h2>
                    <a href="/posts/${post.urlSlug}">${post.title}</a>
                </h2>
                <p class="lead">
                    by <a href="/users/${post.author.id}">${post.author.userName}</a>
                </p>
                <p><span class="glyphicon glyphicon-time"></span> Posted on ${post.postedOnDate}</p>
                <hr>
                <img class="img-responsive" src="/resources/img/posts/${post.thumbnail}" alt="Post Image">
                <hr>
                <p>${post.shortDescription}</p>
                <a class="btn btn-primary" href="/posts/${post.urlSlug}">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>

                <hr>

            </c:forEach>


            <!-- Pager -->
            <%--<ul class="pager">
                <li class="previous">
                    <a href="#">← Older</a>
                </li>
                <li class="next">
                    <a href="#">Newer →</a>
                </li>
            </ul>--%>


        </div>





        <!-- Blog Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Blog Search Well -->
            <div class="well">
                <h4>Blog Search</h4>
                <div class="input-group">
                    <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                </div>
                <!-- /.input-group -->
            </div>

            <!-- Blog Categories Well -->
            <div class="well">
                <h4>Blog Categories</h4>
                <div class="row">
                    <div class="col-lg-6">
                        <ul class="list-unstyled">

                            <c:forEach var="category" items="${categories}">
                                <li><a href="/categories/${category.urlSlug}">${category.title}</a></li>
                                <%--<c:if test="${loopStatus.index % 2 != 0">

                                    <li><a href="#">Category Name</a></li>

                                </c:if>--%>
                            </c:forEach>

                        </ul>
                    </div>
                    <!-- /.col-lg-6 -->
                    <div class="col-lg-6">
                        <ul class="list-unstyled">

                                <%--<c:forEach var="category" items="${categories}" varStatus="loopStatus">
                                    <c:if test="${loopStatus.index % 2 == 0">

                                        <li><a href="#">${category.title}</a></li>

                                    </c:if>
                                </c:forEach>--%>

                        </ul>
                    </div>
                    <!-- /.col-lg-6 -->
                </div>
                <!-- /.row -->
            </div>

            <!-- Side Widget Well -->
            <div class="well">
                <h4>Side Widget Well</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.</p>
            </div>

        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>