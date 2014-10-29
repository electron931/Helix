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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    private static final Logger logger = Logger.getLogger(PostController.class);

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

        /*Post post = new Post();
        post.setTitle("Опять пост на русском!");
        post.setShortDescription("снова дождались");
        post.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin porta placerat ipsum pulvinar malesuada. Mauris lobortis aliquam neque sit amet consectetur. Donec et nibh a metus mollis dictum finibus a massa. Curabitur in sem est. Nunc a scelerisque libero. Maecenas sit amet neque nisi. Phasellus sed fermentum diam. Nunc sed ipsum enim. Praesent non augue est. Etiam quis tempus risus.");
        post.setUrlSlug("russian2");
        post.setPublished(true);
        post.setPostedOnDate(new Date());
        post.setCategory(category);

        this.postService.addPost(post);

        System.out.println("Post saved!");*/

        List<Post> posts = this.categoryService.getAllPostsForCategory(category);
        for (Post item : posts) {
            logger.info(item.getTitle() + ", " + item.getShortDescription());
            System.out.println(item.getTitle() + ", " + item.getShortDescription());
        }

        /*this.categoryService.removeCategory(category_id);
        System.out.println("Category deleted!");*/

        return "redirect:/home";
    }

}
