<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="table-responsive">
  <table class="table table-hover table-striped">
    <thead>
    <tr>
      <th>Title</th>
      <th>Short Description</th>
      <th>Published</th>
      <th>Author</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="post" items="${posts}">

      <!-- Blog Post -->
      <tr>
        <td><a href="/admin/posts/${post.urlSlug}">${post.title}</a></td>
        <td>${post.shortDescription}</td>
        <td><fmt:formatDate value="${post.postedOnDate}" pattern="yyyy-MM-dd HH:mm" /></td>
        <td><a href="/admin/users/${post.author.id}">${post.author.userName}</a></td>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>