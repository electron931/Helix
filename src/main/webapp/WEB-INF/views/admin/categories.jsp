<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12">

      <c:if test="${isCategoryCreated}">
        <div class="alert alert-success">
          Category has been saved.
        </div>
      </c:if>

      <c:if test="${isCategoryUpdated}">
        <div class="alert alert-success">
          Category has been updated.
        </div>
      </c:if>


      <c:if test="${isError}">
        <div class="alert alert-danger">
          Error occurred while saving the category.
        </div>
      </c:if>

      <h1 class="page-header">Categories</h1>

      <c:choose>
        <c:when test="${isEmpty}">
          <p>No categories. Perhaps, you should create one?</p>
        </c:when>

        <c:otherwise>
          <div class="adminPostList">
            <jsp:include page="categoriesList.jsp" />
          </div>
        </c:otherwise>
      </c:choose>



    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>