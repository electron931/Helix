<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

        <script>
            $( document ).ready(function() {
                $(".signFailed").hide();
            });
        </script>


        <c:if test="${authFailed}">
            <script>
                $( document ).ready(function() {
                    $(".form-signin").addClass("has-error");
                    $(".signFailed").show();
                });
            </script>
        </c:if>

        <c:url value="/process-login" var="loginUrl" />

        <form class="form-signin" role="form" action="${loginUrl}" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <p class="bg-danger signFailed">Invalid username or password</p>
            <input type="test" class="form-control" required
                   data-bv-notempty-message="Field is required" placeholder="Username" name="j_username" required="" autofocus="">
            <input type="password" class="form-control" required
                   data-bv-notempty-message="Field is required" placeholder="Password" name="j_password" required="">
            <button class="btn btn-primary btn-block" type="submit">Sign in</button>
        </form>

    </tiles:putAttribute>
</tiles:insertDefinition>