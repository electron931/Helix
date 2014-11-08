<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <!-- Blog Entries Column -->
    <div class="col-md-12 adminBlock">

      <h1 class="page-header">Create Tag</h1>

      <form role="form" action="/admin/addTag" method="post">

        <div class="form-group">
          <label>Name</label>
          <input class="form-control" required
                 data-bv-notempty-message="Field is required" placeholder="Tag Name" name="name">
        </div>


        <button type="reset" class="btn btn-default">Reset</button>

        <button type="submit" class="btn btn-default pull-right">Save Tag</button>

      </form>

    </div>


  </tiles:putAttribute>
</tiles:insertDefinition>