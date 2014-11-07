<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:insertDefinition name="adminTemplate">
  <tiles:putAttribute name="adminContent">


    <div class="jumbotron">
      <h1>Dashboard</h1>
      <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
      <p><a href="#" class="btn btn-primary btn-lg" role="button">Learn more</a>
      </p>
    </div>

  </tiles:putAttribute>
</tiles:insertDefinition>