<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:insertDefinition name="defaultTemplate">
  <tiles:putAttribute name="content">

    <div class="col-lg-8">

      <h1 class="page-header">
        ${user.userName}
        <small>email: ${user.email}</small>
      </h1>

      <h3>Published Posts:</h3>

      <c:choose>
        <c:when test="${isEmpty}">
          <p>Not yet published any post</p>
        </c:when>

        <c:otherwise>
          <div class="postList">
            <jsp:include page="posts.jsp" />
          </div>
        </c:otherwise>
      </c:choose>


      <!-- Pager -->
      <div class="loadMorePosts">
        <button type="button" class="btn btn-info">More Posts</button>
      </div>

    </div>

    <jsp:include page="sidebar.jsp" />


  </tiles:putAttribute>
</tiles:insertDefinition>