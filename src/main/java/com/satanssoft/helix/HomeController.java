package com.satanssoft.helix;


import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.service.CategoryService;
import com.satanssoft.helix.service.PostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;



@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    private int POSTS_PER_PAGE = 10;
    private int pageNumber;

    private PostService postService;
    private CategoryService categoryService;

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

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(Model model) {

        pageNumber = 1;

        List<Post> posts = this.postService.getPostsForPage(pageNumber, POSTS_PER_PAGE);
        List<Category> categories = this.categoryService.getAllCategories();


        model.addAttribute("posts", posts);
        model.addAttribute("title", "Helix");
        model.addAttribute("categories", categories);

        return "home";
    }


    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public String loadMorePosts(Model model) {

        pageNumber++;
        List<Post> posts = this.postService.getPostsForPage(pageNumber, POSTS_PER_PAGE);


        model.addAttribute("posts", posts);
        //model.addAttribute("categories", categories);

        return "posts";
    }


}