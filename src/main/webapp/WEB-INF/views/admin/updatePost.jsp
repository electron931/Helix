<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12 adminBlock">

      <h1 class="page-header">Update Post</h1>

      <form role="form" action="/admin/updatePost" method="post">

        <div class="form-group">
          <label>Title</label>
          <input class="form-control" required
                 data-bv-notempty-message="Field is required" placeholder="Post Title" name="title" value="${post.title}">
        </div>

        <div class="form-group">
          <label>Short Descriprion</label>
          <textarea class="form-control" rows="5" required
                    data-bv-notempty-message="Field is required" placeholder="Short Description..." name="shortDescription">${post.shortDescription}</textarea>
        </div>

        <div class="form-group">
          <label>Post Body</label>
          <textarea class="form-control" rows="12" required
                    data-bv-notempty-message="Field is required" placeholder="Post Body..." name="description">${post.description}</textarea>
        </div>

        <div class="form-group">
          <label>Post Thumbnail Path</label>
          <input class="form-control" placeholder="Thumbnail" required
                 data-bv-notempty-message="Field is required" name="thumbnail" value="${post.thumbnail}">
        </div>


        <c:set var="postTagsId" value="${0}"/>
        <c:set var="maxPostTagsId" value="${postTags.size()}"/>
        <div class="form-group">
          <p><label>Tags</label></p>
          <c:forEach var="tag" items="${tags}" varStatus="loopStatus">

            <label>
              <span class="label label-info" style="cursor: pointer;">${tag.name}</span>

              <c:choose>

                <c:when test="${postTagsId < maxPostTagsId}">
                  <c:choose>

                    <c:when test="${postTags.get(postTagsId).id == tag.id}">
                      <input type="checkbox" class="checkbox-inline" checked name="tags" value="${tag.id}" >
                      <c:set var="postTagsId" value="${postTagsId + 1}"/>
                    </c:when>

                    <c:otherwise>
                      <input type="checkbox" class="checkbox-inline" name="tags" value="${tag.id}" >
                    </c:otherwise>
                  </c:choose>
                </c:when>

                <c:otherwise>
                  <input type="checkbox" class="checkbox-inline" name="tags" value="${tag.id}" >
                </c:otherwise>
              </c:choose>



            </label>

          </c:forEach>
        </div>


        <div class="form-group">
          <label>Categories</label>
          <select class="form-control" size="1" name="category">
            <c:forEach var="category" items="${categories}">

              <c:choose>
                <c:when test="${post.category.id == category.id}">
                  <option value="${category.id}" selected>${category.title}</option>
                </c:when>

                <c:otherwise>
                  <option value="${category.id}">${category.title}</option>
                </c:otherwise>
              </c:choose>


            </c:forEach>
          </select>
        </div>

        <sec:authentication property="principal.username"
                            var="userId"/>

        <input type="hidden" name="author" value="${userId}">

        <input type="hidden" name="postId" value="${post.id}">

        <button type="reset" class="btn btn-default">Reset</button>

        <button type="submit" class="btn btn-default pull-right">Update Post</button>



      </form>



    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>