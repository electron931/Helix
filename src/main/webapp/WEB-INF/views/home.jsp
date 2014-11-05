<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">


        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="page-header">
                Page Heading
                <small>Secondary Text</small>
            </h1>

            <div class="postList">
                <jsp:include page="posts.jsp" />
            </div>


            <!-- Pager -->
            <div class="loadMorePosts">
                <button type="button" class="btn btn-info">More Posts</button>
            </div>

        </div>

        <jsp:include page="sidebar.jsp" />







    </tiles:putAttribute>
</tiles:insertDefinition>