<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="post" items="${posts}">

    <!-- Blog Post -->
    <h2>
        <a href="/posts/${post.urlSlug}">${post.title}</a>
    </h2>
    <p class="lead">
        by <a href="/users/${post.author.id}">${post.author.userName}</a>
    </p>
    <p><span class="glyphicon glyphicon-time"></span> Posted on <fmt:formatDate value="${post.postedOnDate}" pattern="yyyy-MM-dd HH:mm" /></p>
    <hr>
    <img class="img-responsive" src="/resources/img/posts/${post.thumbnail}" alt="Post Image">
    <hr>
    <p>${post.shortDescription}</p>
    <a class="btn btn-primary" href="/posts/${post.urlSlug}">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>

    <hr>

</c:forEach>