<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">


        <div class="col-lg-8 singlePost" data-postId="${post.id}">

            <!-- Blog Post -->

            <!-- Title -->
            <h1>${post.title}</h1>

            <!-- Author -->
            <p class="lead">
                by <a href="/users/${post.author.id}">${post.author.userName}</a>
            </p>

            <hr>

            <!-- Date/Time -->
            <p><span class="glyphicon glyphicon-time"></span> Posted on <fmt:formatDate value="${post.postedOnDate}" pattern="yyyy-MM-dd HH:mm" /></p>

            <hr>

            <!-- Preview Image -->
            <img class="img-responsive" src="/resources/img/posts/${post.thumbnail}" alt="">

            <hr>

            <!-- Post Content -->
            ${post.description}

            <hr>

            <!-- Blog Tags -->

            <div class="tags">
                <c:forEach var="tag" items="${tags}">
                    <a href="/tag/${tag.urlSlug}" class="label label-info">${tag.name}</a>
                </c:forEach>
            </div>

            <!-- Blog Comments -->

            <!-- Comments Form -->
            <div class="well">
                <h4>Leave a Comment:</h4>
                <form role="form" action="/comments/add" method="post">
                    <div class="form-group">
                        <sec:authorize access="!hasRole('ROLE_MODERATOR')">
                            <input type="text" required class="form-control commentName" name="userName" placeholder="Name" >
                        </sec:authorize>
                        <input type="hidden" name="postId" value="${post.id}" >
                        <input type="hidden" name="postSlug" value="${post.urlSlug}" >
                        <textarea class="form-control" required rows="3" name="commentText" placeholder="Your thoughts..." ></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>

            <hr>

            <!-- Posted Comments -->
            <div class="comments">
                <h3>Comments:</h3>
                <jsp:include page="comments.jsp" />
            </div>

        </div>

        <jsp:include page="sidebar.jsp" />



        <script>
            //with this doesn't work anchor links
            /*$( document ).ready(function() {

                //Comments
                var alreadyGet = false;
                $(window).scroll(function() {
                    var scrollTo = $('.tags');
                    var hT = scrollTo.offset().top,
                            hH = scrollTo.outerHeight(),
                            wH = $(window).height(),
                            wS = $(this).scrollTop();
                    if (wS > (hT + hH - wH)){

                        if (!alreadyGet) {
                            alreadyGet = true;
                            var postId = +$(".singlePost").data("postid");
                            console.log(postId);
                            getCommentsForThePost(postId);
                        }

                    }
                });

            });


            function getCommentsForThePost(postId) {
                $.ajax({
                    type: "POST",
                    url: "/comments/get",
                    data: {
                        'postId': postId
                    },
                    success: function(data) {
                        console.log($.trim(data));

                        if ($.trim(data) != "") {
                            $(".comments").append(data);
                        }
                        else {
                            $(".comments").append('<p>No Comments</p>');
                        }
                    },
                    fail: function() {
                        console.log('error');
                    }
                });
            }*/

        </script>


    </tiles:putAttribute>
</tiles:insertDefinition>
