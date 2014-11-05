package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.hibernate.model.User;
import com.satanssoft.helix.service.CategoryService;
import com.satanssoft.helix.service.PostService;
import com.satanssoft.helix.service.TagService;
import com.satanssoft.helix.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    private static final Logger logger = Logger.getLogger(PostController.class);

    private PostService postService;
    private CategoryService categoryService;
    private TagService tagService;
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "postService")
    public void setPostService(PostService postService){
        this.postService = postService;

    }

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired(required = true)
    @Qualifier(value = "tagService")
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/{postSlug}")
    public String singlePost(Model model, @PathVariable("postSlug") String postSlug) {

        Post post = this.postService.getPostByUrlSlug(postSlug);
        List<Tag> tags = this.postService.getAllTagsForPost(post);
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags = this.tagService.getAllTags();

        model.addAttribute("post", post);
        model.addAttribute("title", post.getTitle() + " | Helix");
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);

        return "singlePost";
    }


}
