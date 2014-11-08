$( document ).ready(function() {

    $('form').bootstrapValidator();

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
                    $(".alert").remove();
                },
                fail: function() {
                    console.log('error');
                }
            });
        }

    });


    $(".deleteCategory").click(function() {

        var isDelete = confirm("Delete Category?");
        if (isDelete) {

            var categoryId = $(this).attr('id');

            $.ajax({
                type: "POST",
                url: "/admin/deleteCategory",
                data: {
                    'categoryId': categoryId
                },
                success: function(isDeleted) {
                    if (isDeleted == "yes") {
                        console.log('success');
                        $(".categories table tbody #category" + categoryId).remove();
                        $(".alert").remove();
                    }
                    else {
                        alert("You can\'t delete category with posts");
                    }

                },
                fail: function() {
                    console.log('error');
                }
            });
        }

    });




    $(".deleteTag").click(function() {

        var isDelete = confirm("Delete Tag?");
        if (isDelete) {

            var tagId = $(this).attr('id');

            $.ajax({
                type: "POST",
                url: "/admin/deleteTag",
                data: {
                    'tagId': tagId
                },
                success: function(data) {
                    console.log('success');
                    $(".tags table tbody #tag" + tagId).remove();
                    $(".alert").remove();
                },
                fail: function() {
                    console.log('error');
                }
            });
        }

    });

});
