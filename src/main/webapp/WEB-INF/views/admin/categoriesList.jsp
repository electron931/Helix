<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="table-responsive categories">
  <table class="table table-hover table-striped">
    <thead>
    <tr>
      <th>Title</th>
      <th>Description</th>
      <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="category" items="${categories}">

      <tr id="category${category.id}">
        <td><a href="/admin/categories/${category.urlSlug}">${category.title}</a></td>
        <td>${category.description}</td>
        <td><span class="deleteCategory" id="${category.id}">Delete</span></td>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>