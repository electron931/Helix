<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Blog Sidebar Widgets Column -->
<div class="col-md-4">

    <!-- Blog Search Well -->
    <div class="well">
        <h4>Blog Search</h4>
        <form action="/posts" method="get">
            <div class="input-group">
                <input type="text" class="form-control" name="search">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
            </div>
        </form>
    </div>

    <!-- Blog Categories Well -->
    <div class="well">
        <h4>Blog Categories</h4>
        <div class="row">
            <div class="col-lg-6">
                <ul class="list-unstyled">

                    <c:forEach var="category" items="${categories}" varStatus="loopStatus">
                        <c:if test="${(loopStatus.index + 1) % 2 != 0}">
                            <li><a href="/category/${category.urlSlug}">${category.title}</a></li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div>

            <div class="col-lg-6">
                <ul class="list-unstyled">

                    <c:forEach var="category" items="${categories}" varStatus="loopStatus">
                        <c:if test="${(loopStatus.index + 1) % 2 == 0}">
                            <li><a href="/category/${category.urlSlug}">${category.title}</a></li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div>

        </div>
    </div>

    <!-- Blog Tags Well -->
    <c:set var="count" scope="session" value="${1}"/>
    <div class="well">
        <h4>Tags</h4>
        <div class="row">
            <div class="col-lg-3">
                <ul class="list-unstyled">

                    <c:forEach var="tag" items="${allTags}" begin="0" step="4">
                        <li><a href="/tag/${tag.urlSlug}" class="label label-info">${tag.name}</a></li>
                    </c:forEach>

                </ul>
            </div>

            <div class="col-lg-3">
                <ul class="list-unstyled">

                    <c:forEach var="tag" items="${allTags}" begin="1" step="4">
                        <li><a href="/tag/${tag.urlSlug}" class="label label-info">${tag.name}</a></li>
                    </c:forEach>

                </ul>
            </div>

            <div class="col-lg-3">
                <ul class="list-unstyled">

                    <c:forEach var="tag" items="${allTags}" begin="2" step="4">
                        <li><a href="/tag/${tag.urlSlug}" class="label label-info">${tag.name}</a></li>
                    </c:forEach>

                </ul>
            </div>

            <div class="col-lg-3">
                <ul class="list-unstyled">

                    <c:forEach var="tag" items="${allTags}" begin="3" step="4">
                        <li><a href="/tag/${tag.urlSlug}" class="label label-info">${tag.name}</a></li>
                    </c:forEach>

                </ul>
            </div>

        </div>
    </div>

</div>