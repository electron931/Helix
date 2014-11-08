<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:forEach var="comment" items="${comments}">

    <div class="media">
        <a name="#comment${comment.id}"></a>
        <div class="media-body">
            <h4 class="media-heading">${comment.userName}
                <small><fmt:formatDate value="${comment.createdDate}" pattern="yyyy-MM-dd HH:mm" /></small>
            </h4>
            ${comment.text}
        </div>
    </div>

</c:forEach>
