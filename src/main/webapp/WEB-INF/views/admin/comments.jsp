<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12">

      <h1 class="page-header">Post Comments</h1>

      <c:choose>
        <c:when test="${isEmpty}">
          <p>No comments. Blog doesn't very popular, does it?</p>
        </c:when>

        <c:otherwise>

          <div class="table-responsive comments">
            <table class="table table-hover table-striped">
              <thead>
              <tr>
                <th>Post</th>
                <th>Comment</th>
                <th>Username</th>
                <th>Date</th>
                <th></th>
              </tr>
              </thead>
              <tbody>

              <c:forEach var="comment" items="${comments}">

                <tr>
                  <td><a href="/posts/${comment.post.urlSlug}">${comment.post.title}</a></td>
                  <td>${comment.text}</td>
                  <td>${comment.userName}</td>
                  <td><fmt:formatDate value="${comment.createdDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                  <td><a href="/posts/${comment.post.urlSlug}#comment${comment.id}">Go to Comment</a></td>
                </tr>

              </c:forEach>

              </tbody>
            </table>
          </div>

        </c:otherwise>
      </c:choose>






    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>