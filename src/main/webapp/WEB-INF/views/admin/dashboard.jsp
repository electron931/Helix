<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <div class="jumbotron">
      <h1>Dashboard</h1>
      <p>This is the admin part of the blog. From here you can manage posts, categories, tags and view comments.</p>
    </div>

  </tiles:putAttribute>
</tiles:insertDefinition>