$( document ).ready(function() {


    $(".deletePost").click(function() {

        var isDelete = confirm("Delete Post?");
        if (isDelete) {

            var postId = $(this).attr('id');

            $.ajax({
                type: "POST",
                url: "/admin/deletePost",
                data: {
                    'postId': postId
                },
                success: function(data) {
                    console.log('success');
                    $(".posts table tbody #post" + postId).remove();
                },
                fail: function() {
                    console.log('error');
                }
            });
        }

    });

});
