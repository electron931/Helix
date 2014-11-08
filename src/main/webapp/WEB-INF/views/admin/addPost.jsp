<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12 adminBlock">

      <h1 class="page-header">Create Post</h1>

      <form role="form" action="/admin/addPost" method="post">

        <div class="form-group">
          <label>Title</label>
          <input class="form-control" placeholder="Post Title" name="title" required
                 data-bv-notempty-message="Field is required">
        </div>

        <div class="form-group">
          <label>Short Descriprion</label>
          <textarea class="form-control" required
                    data-bv-notempty-message="Field is required" rows="5" placeholder="Short Description..." name="shortDescription"></textarea>
        </div>

        <div class="form-group">
          <label>Post Body</label>
          <textarea class="form-control" required
                    data-bv-notempty-message="Field is required" rows="12" placeholder="Post Body..." name="description"></textarea>
        </div>

        <div class="form-group">
          <label>Post Thumbnail Path</label>
          <input class="form-control" required
                 data-bv-notempty-message="Field is required" placeholder="Thumbnail" name="thumbnail">
        </div>

        <div class="form-group">
          <p><label>Tags</label></p>
            <c:forEach var="tag" items="${tags}">

              <label>
                <span class="label label-info" style="cursor: pointer;">${tag.name}</span>
                <input type="checkbox" class="checkbox-inline" name="tags" value="${tag.id}" >
              </label>

            </c:forEach>
        </div>


        <div class="form-group">
          <label>Categories</label>
          <select class="form-control" size="1" name="category">
            <c:forEach var="category" items="${categories}">
              <option value="${category.id}">${category.title}</option>
            </c:forEach>
          </select>
        </div>

        <sec:authentication property="principal.username"
                                 var="userId"/>

        <input type="hidden" name="author" value="${userId}">

        <button type="reset" class="btn btn-default">Reset</button>

        <button type="submit" class="btn btn-default pull-right">Save Post</button>



      </form>



    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>