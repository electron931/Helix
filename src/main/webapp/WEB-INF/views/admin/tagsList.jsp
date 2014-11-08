<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="table-responsive tags">
  <table class="table table-hover table-striped">
    <thead>
    <tr>
      <th>Name</th>
      <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tag" items="${tags}">

      <tr id="tag${tag.id}">
        <td><a href="/admin/tags/${tag.urlSlug}">${tag.name}</a></td>
        <td><span class="deleteTag" id="${tag.id}">Delete</span></td>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>