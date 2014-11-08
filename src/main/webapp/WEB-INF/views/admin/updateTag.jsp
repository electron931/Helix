<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12 adminBlock">

      <h1 class="page-header">Update Tag</h1>

      <form role="form" action="/admin/updateTag" method="post">

        <div class="form-group">
          <label>Name</label>
          <input class="form-control" placeholder="Tag Name" required
                 data-bv-notempty-message="Field is required" name="name" value="${tag.name}">
        </div>

        <input type="hidden" name="tagId" value="${tag.id}">

        <button type="reset" class="btn btn-default">Reset</button>

        <button type="submit" class="btn btn-default pull-right">Update Tag</button>

      </form>

    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>