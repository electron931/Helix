<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<c:forEach var="comment" items="${comments}">

    <div class="media" id="commentBlock${comment.id}" >

        <div class="media-body" style="display: inline-block;">
            <a name="comment${comment.id}"></a>
            <h4 class="media-heading">${comment.userName}
                <small><fmt:formatDate value="${comment.createdDate}" pattern="yyyy-MM-dd HH:mm" /></small>
            </h4>

            ${comment.text}

        </div>

        <sec:authorize access="hasRole('ROLE_MODERATOR')">
            <button id="${comment.id}" type="button" class="btn btn-sm btn-warning pull-right">Delete Comment</button>
        </sec:authorize>
    </div>

</c:forEach>
