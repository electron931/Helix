package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.service.CategoryService;
import com.satanssoft.helix.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class PostController {

    private PostService postService;
    private CategoryService categoryService;

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


    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    //public String test(@RequestParam("category_id") int category_id, Model model) {
    public String test(Model model) {

        Category category = this.categoryService.getCategoryById(1);

        Post post = new Post();
        post.setTitle("А Вы готовы?");
        post.setShortDescription("ala");
        post.setDescription("alalalalal");
        post.setUrlSlug("ready");
        post.setPublished(true);
        post.setPostedOnDate(new Date());
        post.setCategoryId(category);

        this.postService.addPost(post);

        System.out.println("Post saved!");

        /*this.categoryService.removeCategory(category_id);
        System.out.println("Category deleted!");*/

        return "redirect:/home";
    }

}
