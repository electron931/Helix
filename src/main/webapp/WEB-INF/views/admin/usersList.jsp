<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="table-responsive users">
  <table class="table table-hover table-striped">
    <thead>
    <tr>
      <th>Username</th>
      <th>Email</th>
      <th>Password</th>
      <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${users}">

      <tr id="user${user.id}">
        <td><a href="/admin/users/${user.id}">${user.userName}</a></td>
        <td>${user.email}</td>
        <td>${user.password}</td>
        <td><span class="deleteUser" id="${user.id}">Delete</span></td>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>