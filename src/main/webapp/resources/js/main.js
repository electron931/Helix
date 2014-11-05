$( document ).ready(function() {

    $(".loadMorePosts button").click(function() {
        $.ajax({
            type: "POST",
            url: "/",
            success: function(data) {
                console.log(data);
                $(".postList").append(data);
            },
            fail: function() {
                console.log('error');
            }
        });
    });


    $(document).ajaxStart(function () {
        $(".loadMorePosts button").html("Loading...");
    }).ajaxStop(function () {
        $(".loadMorePosts button").html("More Posts");
    });



});