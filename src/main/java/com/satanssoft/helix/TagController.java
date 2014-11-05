package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/tag")
public class TagController {

    private static final Logger logger = Logger.getLogger(TagController.class);

    private int POSTS_PER_PAGE = 10;
    private int pageNumber;

    private Tag tag;

    private CategoryService categoryService;
    private UserService userService;
    private TagService tagService;
    private PostService postService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setPostService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "tagService")
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }


    @RequestMapping(value = "/{tagSlug}", method = RequestMethod.GET)
    public String postsForTag(Model model, @PathVariable("tagSlug") String tagSlug) {
        pageNumber = 1;

        Tag tag = this.tagService.getTagByUrlSlug(tagSlug);
        this.tag = tag;

        List<Post> posts = this.tagService
                .getAllPostsForTag(tag, pageNumber, POSTS_PER_PAGE);
        List<Category> categories = this.categoryService.getAllCategories();

        model.addAttribute("posts", posts);
        model.addAttribute("title", tag.getName() + " | Helix");
        model.addAttribute("currentTag", tag);
        model.addAttribute("categories", categories);

        return "postsForTag";
    }

    @RequestMapping(value = "/{tagSlug}", method = RequestMethod.POST)
    public String loadMorePosts(Model model, @PathVariable("tagSlug") String tagSlug) {

        pageNumber++;
        List<Post> posts = this.tagService
                .getAllPostsForTag(tag, pageNumber, POSTS_PER_PAGE);


        model.addAttribute("posts", posts);
        //model.addAttribute("categories", categories);

        return "posts";
    }

}
