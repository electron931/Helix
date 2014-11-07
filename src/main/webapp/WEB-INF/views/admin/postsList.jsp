<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="table-responsive posts">
  <table class="table table-hover table-striped">
    <thead>
    <tr>
      <th>Title</th>
      <th>Short Description</th>
      <th>Published</th>
      <th>Author</th>
      <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="post" items="${posts}">

      <!-- Blog Post -->
      <tr id="post${post.id}">
        <td><a href="/admin/posts/${post.urlSlug}">${post.title}</a></td>
        <td>${post.shortDescription}</td>
        <td><fmt:formatDate value="${post.postedOnDate}" pattern="yyyy-MM-dd HH:mm" /></td>
        <td><a href="/admin/users/${post.author.id}">${post.author.userName}</a></td>
        <td><span class="deletePost" id="${post.id}">Delete</span></td>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>