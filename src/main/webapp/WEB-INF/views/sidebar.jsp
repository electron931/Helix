<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Blog Sidebar Widgets Column -->
<div class="col-md-4">

    <!-- Blog Search Well -->
    <div class="well">
        <h4>Blog Search</h4>
        <div class="input-group">
            <input type="text" class="form-control">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
        </div>
        <!-- /.input-group -->
    </div>

    <!-- Blog Categories Well -->
    <div class="well">
        <h4>Blog Categories</h4>
        <div class="row">
            <div class="col-lg-6">
                <ul class="list-unstyled">

                    <c:forEach var="category" items="${categories}" varStatus="loopStatus">
                        <c:if test="${(loopStatus.index + 1) % 2 != 0}">
                            <li><a href="/category/${category.urlSlug}">${category.title}</a></li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div>
            <!-- /.col-lg-6 -->
            <div class="col-lg-6">
                <ul class="list-unstyled">

                    <c:forEach var="category" items="${categories}" varStatus="loopStatus">
                        <c:if test="${(loopStatus.index + 1) % 2 == 0}">
                            <li><a href="/category/${category.urlSlug}">${category.title}</a></li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div>
            <!-- /.col-lg-6 -->
        </div>

    </div>

    <!-- Side Widget Well -->
    <div class="well">
        <h4>Side Widget Well</h4>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.</p>
    </div>

</div>