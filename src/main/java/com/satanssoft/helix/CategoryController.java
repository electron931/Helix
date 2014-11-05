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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private static final Logger logger = Logger.getLogger(CategoryController.class);

    private int POSTS_PER_PAGE = 10;
    private int pageNumber;

    private Category category;

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


    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.GET)
    public String postsForCategory(Model model, @PathVariable("categorySlug") String categorySlug) {
        pageNumber = 1;

        Category category = this.categoryService.getCategoryByUrlSlug(categorySlug);
        this.category = category;

        List<Post> posts = this.categoryService
                .getAllPostsForCategory(category, pageNumber, POSTS_PER_PAGE);
        List<Category> categories = this.categoryService.getAllCategories();

        model.addAttribute("posts", posts);
        model.addAttribute("categories", categories);
        model.addAttribute("currentCategory", category);

        return "postsForCategory";
    }

    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.POST)
    public String loadMorePosts(Model model, @PathVariable("categorySlug") String categorySlug) {

        pageNumber++;
        List<Post> posts = this.categoryService
                .getAllPostsForCategory(this.category, pageNumber, POSTS_PER_PAGE);


        model.addAttribute("posts", posts);
        //model.addAttribute("categories", categories);

        return "posts";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    //public String test(@RequestParam("category_id") int category_id, Model model) {
    public String test(Model model) {

        /*Category category = new Category();
        category.setTitle("News");
        category.setDescription("Awesome news");
        category.setUrlSlug("news");

        this.categoryService.addCategory(category);


        User user = new User();
        user.setUserName("Alex");
        user.setPassword("12345");
        user.setEmail("alex@gmail.com");
        user.setType("author");
        this.userService.addUser(user);


        Tag tag1 = new Tag();
        tag1.setName("news");
        tag1.setUrlSlug("news");
        this.tagService.addTag(tag1);

        Tag tag2 = new Tag();
        tag2.setName("awesome");
        tag2.setUrlSlug("awesome");
        this.tagService.addTag(tag2);*/

        /*this.categoryService.removeCategory(category_id);
        System.out.println("Category deleted!");*/

        return "redirect:/";
    }

}
