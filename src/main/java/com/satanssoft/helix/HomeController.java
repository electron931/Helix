package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.service.CategoryService;
import com.satanssoft.helix.service.PostService;
import com.satanssoft.helix.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    private static final int POSTS_PER_PAGE = 10;
    private int pageNumber;

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
    public void setPostService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired(required = true)
    @Qualifier(value = "tagService")
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(Model model) {
        pageNumber = 1;

        List<Post> posts = this.postService.getPostsForPage(pageNumber, POSTS_PER_PAGE);
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags =  this.tagService.getAllTags();


        model.addAttribute("posts", posts);
        model.addAttribute("title", "Helix");
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);

        return "home";
    }


    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String loadMorePosts(Model model) {
        pageNumber++;
        List<Post> posts = this.postService.getPostsForPage(pageNumber, POSTS_PER_PAGE);

        model.addAttribute("posts", posts);

        if (posts.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        return "posts";
    }


}