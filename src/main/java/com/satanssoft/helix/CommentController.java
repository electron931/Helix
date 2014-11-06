package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.*;
import com.satanssoft.helix.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    private static final Logger logger = Logger.getLogger(CommentController.class);

    private PostService postService;
    private CommentService commentService;


    @Autowired(required = true)
    @Qualifier(value = "postService")
    public void setPostService(PostService postService){
        this.postService = postService;

    }

    @Autowired(required = true)
    @Qualifier(value = "commentService")
    public void setCommentService(CommentService commentService){
        this.commentService = commentService;
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getComments(Model model, @RequestParam("postId") int postId) {
        Post post = this.postService.getPostById(postId);
        List<Comment> comments = this.postService.getAllCommentsForPost(post);

        model.addAttribute("comments", comments);

        return "comments";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addComment(Model model, @RequestParam("userName") String userName,
                                          @RequestParam("postId") int postId,
                                          @RequestParam("postSlug") String postSlug,
                                          @RequestParam("commentText") String commentText) {
        Comment comment = new Comment();
        comment.setUserName(userName);
        comment.setText(commentText);

        Post post = this.postService.getPostById(postId);

        comment.setPost(post);
        comment.setCreatedDate(new Date());

        int commentId = this.commentService.addComment(comment);

        return "redirect:/posts/" + postSlug + "#comment" + commentId;
    }


}
