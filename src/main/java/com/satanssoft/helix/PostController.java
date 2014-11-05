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

        model.addAttribute("post", post);
        model.addAttribute("tags", tags);

        return "singlePost";
    }

    @RequestMapping(value = "/addAll", method = RequestMethod.GET)
    //public String test(@RequestParam("category_id") int category_id, Model model) {
    public String test(Model model) {



        Category category = this.categoryService.getCategoryById(1);
        User user = this.userService.getUserById(1);

        List<Tag> tags = this.tagService.getAllTags();

        for (int i = 0; i < 30; i++) {
            Post post = new Post();
            post.setTitle("Post" + i);
            post.setShortDescription("lorem ipsum");
            post.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin porta placerat ipsum pulvinar malesuada. Mauris lobortis aliquam neque sit amet consectetur. Donec et nibh a metus mollis dictum finibus a massa. Curabitur in sem est. Nunc a scelerisque libero. Maecenas sit amet neque nisi. Phasellus sed fermentum diam. Nunc sed ipsum enim. Praesent non augue est. Etiam quis tempus risus.");
            post.setUrlSlug("post" + i);
            post.setPublished(true);
            post.setPostedOnDate(new Date());
            post.setCategory(category);
            post.setAuthor(user);

            post.setTags(tags);

            this.postService.addPost(post);
        }


        return "redirect:/";
    }

}
