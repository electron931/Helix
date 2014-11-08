<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12 adminBlock">

      <h1 class="page-header">Update Category</h1>

      <form role="form" action="/admin/updateCategory" method="post">

        <div class="form-group">
          <label>Title</label>
          <input class="form-control" required
                 data-bv-notempty-message="Field is required" placeholder="Post Title" name="title" value="${category.title}">
        </div>

        <div class="form-group">
          <label>Description</label>
          <textarea class="form-control" rows="5" placeholder="Category Description..." name="description">${category.description}</textarea>
        </div>

        <input type="hidden" name="categoryId" value="${category.id}">

        <button type="reset" class="btn btn-default">Reset</button>

        <button type="submit" class="btn btn-default pull-right">Update Category</button>

      </form>

    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>