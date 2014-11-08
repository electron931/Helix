$( document ).ready(function() {

    var isEmpty = false;
    var loadMorePostsButton = $(".loadMorePosts button");

    loadMorePostsButton.click(function() {
        $.ajax({
            type: "POST",
            url: window.location.pathname,
            success: function(data) {
                console.log($.trim(data));
                if ($.trim(data) == "") {
                    isEmpty = true;
                    loadMorePostsButton.prop("disabled", true);
                }
                else {
                    $(".postList").append(data);
                    isEmpty = false;
                }
            },
            fail: function() {
                console.log('error');
            }
        });
    });


    $(document).ajaxStart(function () {
        loadMorePostsButton.html("Loading...");
    }).ajaxStop(function () {
        if (isEmpty) {
            loadMorePostsButton.html("No More Posts");
        }
        else {
            loadMorePostsButton.html("More Posts");
        }
    });


    $(document).on('click','.comments .media button',function(){
        var commentId = $(this).attr('id');
        $.ajax({
            type: "POST",
            url: "/comments/deleteComment",
            data: {
                'commentId': commentId
            },
            success: function(data) {
                console.log('success');
                $(".comments #commentBlock" + commentId).remove();
            },
            fail: function() {
                console.log('error');
            }
        });
    });




});
