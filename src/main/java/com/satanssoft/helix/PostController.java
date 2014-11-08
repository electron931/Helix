package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Comment;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
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

import java.util.List;


@Controller
@RequestMapping(value = "/posts")
public class PostController {

    private static final Logger logger = Logger.getLogger(PostController.class);

    private static final int POSTS_PER_PAGE = 10;
    private int pageNumber;
    private String search;

    private PostService postService;
    private CategoryService categoryService;
    private TagService tagService;


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


    @RequestMapping(value = "/{postSlug}")
    public String singlePost(Model model, @PathVariable("postSlug") String postSlug) {

        Post post = this.postService.getPostByUrlSlug(postSlug);
        List<Tag> tags = this.postService.getAllTagsForPost(post);
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags = this.tagService.getAllTags();
        List<Comment> comments = this.postService.getAllCommentsForPost(post);

        model.addAttribute("post", post);
        model.addAttribute("title", post.getTitle() + " | Helix");
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);
        model.addAttribute("comments", comments);

        return "singlePost";
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model, @RequestParam("search") String search) {
        pageNumber = 1;
        this.search = search;

        List<Post> posts = this.postService.getAllPostsForSearch(search, pageNumber, POSTS_PER_PAGE);
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags =  this.tagService.getAllTags();

        model.addAttribute("search", search);
        model.addAttribute("posts", posts);
        model.addAttribute("title", "Search | Helix");
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);

        if (posts.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        return "postsForSearch";
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String loadMorePosts(Model model) {
        pageNumber++;
        List<Post> posts = this.postService.getAllPostsForSearch(this.search,
                pageNumber,
                POSTS_PER_PAGE);

        model.addAttribute("posts", posts);

        return "posts";
    }


}
