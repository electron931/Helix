<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12">

      <c:if test="${isPostCreated}">
        <div class="alert alert-success">
          Post has been saved.
        </div>
      </c:if>

      <c:if test="${isPostUpdated}">
        <div class="alert alert-success">
          Post has been updated.
        </div>
      </c:if>

      <c:if test="${isError}">
        <div class="alert alert-danger">
          Error occurred while saving/updating the post.
        </div>
      </c:if>

      <h1 class="page-header">Posts</h1>

      <c:choose>
        <c:when test="${isEmpty}">
          <p>No posts. Perhaps, you should create one?</p>
        </c:when>

        <c:otherwise>
          <div class="adminPostList">
            <jsp:include page="postsList.jsp" />
          </div>
        </c:otherwise>
      </c:choose>



    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>