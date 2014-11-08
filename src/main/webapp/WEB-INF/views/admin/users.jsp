<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12">

      <c:if test="${isUserCreated}">
        <div class="alert alert-success">
          Moderator has been added.
        </div>
      </c:if>

      <c:if test="${isUserUpdated}">
        <div class="alert alert-success">
          Moderator has been updated.
        </div>
      </c:if>


      <c:if test="${isError}">
        <div class="alert alert-danger">
          Error occurred while saving the moderator.
        </div>
      </c:if>

      <h1 class="page-header">Moderators</h1>

      <c:choose>
        <c:when test="${isEmpty}">
          <p>No moderators. Perhaps, you should add one?</p>
        </c:when>

        <c:otherwise>
          <div class="adminPostList">
            <jsp:include page="usersList.jsp" />
          </div>
        </c:otherwise>
      </c:choose>



    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>