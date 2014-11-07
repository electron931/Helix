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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);


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


    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String admin(Model model) {
        return "redirect:admin/dashboard";
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String dashboard(Model model) {

        model.addAttribute("title", "Admin | Helix");

        return "admin/dashboard";
    }


    @RequestMapping(value = {"/posts"}, method = RequestMethod.GET)
    public String posts(Model model, @RequestParam(required = false) String isPostCreated,
                        @RequestParam(required = false) String isError) {
        List<Post> posts = this.postService.getAllPosts();

        model.addAttribute("posts", posts);
        model.addAttribute("title", "Admin | Helix");


        if (posts.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        if (isPostCreated != null && isPostCreated.equals("yes")) {
            model.addAttribute("isPostCreated", true);
        }
        else {
            model.addAttribute("isPostCreated", false);
        }

        if (isError != null && isError.equals("yes")) {
            model.addAttribute("isError", true);
        }
        else {
            model.addAttribute("isError", false);
        }

        return "admin/posts";
    }


    @RequestMapping(value = {"/addPost"}, method = RequestMethod.GET)
    public String addPostView(Model model) {
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> tags = this.tagService.getAllTags();

        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        model.addAttribute("title", "Admin | Helix");

        return "admin/addPost";
    }


    @RequestMapping(value = {"/addPost"}, method = RequestMethod.POST)
    public String addPost(Model model, @RequestParam("title") String title,
                                       @RequestParam("shortDescription") String shortDescription,
                                       @RequestParam("description") String description,
                                       @RequestParam("thumbnail") String thumbnail,
                                       @RequestParam(required = false) String tags,
                                       @RequestParam("category") String category,
                                       @RequestParam("author") String author) {
        List<String> tagsIds = null;
        if (tags != null) {
            tagsIds = new ArrayList<String>(Arrays.asList(tags.split(",")));
        }


        Post post = new Post();
        post.setTitle(title);
        post.setShortDescription(shortDescription);
        post.setDescription(description);
        post.setUrlSlug(title.replaceAll(" ", "_").toLowerCase());
        post.setPostedOnDate(new Date());
        post.setThumbnail(thumbnail);

        Category postCategory = this.categoryService.getCategoryById(Integer.valueOf(category));
        post.setCategory(postCategory);

        User user = this.userService.getUserByName(author);
        post.setAuthor(user);

        if (tagsIds != null) {
            List<Tag> postTags = new ArrayList<Tag>();
            for (String tagId : tagsIds) {
                Tag tag = this.tagService.getTagById(Integer.valueOf(tagId));
                postTags.add(tag);
            }

            post.setTags(postTags);
        }


        try {
            this.postService.addPost(post);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
        }

        model.addAttribute("isPostCreated", "yes");

        return "redirect:/admin/posts";
    }


}
