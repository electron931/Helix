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


    /*
    * Posts
    * */

    @RequestMapping(value = {"/posts"}, method = RequestMethod.GET)
    public String posts(Model model, @RequestParam(required = false) String isPostCreated,
                        @RequestParam(required = false) String isPostUpdated,
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

        if (isPostUpdated != null && isPostUpdated.equals("yes")) {
            model.addAttribute("isPostUpdated", true);
        }
        else {
            model.addAttribute("isPostUpdated", false);
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
        post.setUrlSlug(this.generateUrlSlugByTitle(title));
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

        model.addAttribute("isPostCreated", "yes");

        try {
            this.postService.addPost(post);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isPostCreated", "no");
        }

        return "redirect:/admin/posts";
    }


    @RequestMapping(value = {"/deletePost"}, method = RequestMethod.POST)
    public String deletePost(Model model, @RequestParam("postId") int postId) {
        this.postService.removePost(postId);

        return "redirect:/admin/posts";
    }


    @RequestMapping(value = {"/posts/{postSlug}"}, method = RequestMethod.GET)
    public String updatePostView(Model model, @PathVariable("postSlug") String postSlug) {

        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> tags = this.tagService.getAllTags();
        Post post = this.postService.getPostByUrlSlug(postSlug);
        List<Tag> postTags = this.postService.getAllTagsForPost(post);

        model.addAttribute("post", post);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        model.addAttribute("postTags", postTags);
        model.addAttribute("title", "Admin | Helix");

        return "admin/updatePost";
    }


    @RequestMapping(value = {"/updatePost"}, method = RequestMethod.POST)
    public String updatePost(Model model, @RequestParam("title") String title,
                                          @RequestParam("shortDescription") String shortDescription,
                                          @RequestParam("description") String description,
                                          @RequestParam("thumbnail") String thumbnail,
                                          @RequestParam(required = false) String tags,
                                          @RequestParam("category") String category,
                                          @RequestParam("author") String author,
                                          @RequestParam("postId") int postId) {

        List<String> tagsIds = null;
        if (tags != null) {
            tagsIds = new ArrayList<String>(Arrays.asList(tags.split(",")));
        }

        Post post = new Post();
        post.setId(postId);
        post.setTitle(title);
        post.setShortDescription(shortDescription);
        post.setDescription(description);
        post.setUrlSlug(title.replaceAll(" ", "_").toLowerCase());

        Post oldPost = this.postService.getPostById(postId);
        post.setPostedOnDate(oldPost.getPostedOnDate());
        post.setModifiedDate(new Date());

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


        model.addAttribute("isPostUpdated", "yes");

        try {
            this.postService.updatePost(post);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isPostUpdated", "no");
        }

        return "redirect:/admin/posts";
    }

    /*
    * End Posts
    * */



    /*
    * Categories
    * */


    @RequestMapping(value = {"/categories"}, method = RequestMethod.GET)
    public String categories(Model model, @RequestParam(required = false) String isCategoryCreated,
                                          @RequestParam(required = false) String isCategoryUpdated,
                                          @RequestParam(required = false) String isError) {
        List<Category> categories = this.categoryService.getAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("title", "Admin | Helix");


        if (categories.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        if (isCategoryCreated != null && isCategoryCreated.equals("yes")) {
            model.addAttribute("isCategoryCreated", true);
        }
        else {
            model.addAttribute("isCategoryCreated", false);
        }

        if (isCategoryUpdated != null && isCategoryUpdated.equals("yes")) {
            model.addAttribute("isCategoryUpdated", true);
        }
        else {
            model.addAttribute("isCategoryUpdated", false);
        }

        if (isError != null && isError.equals("yes")) {
            model.addAttribute("isError", true);
        }
        else {
            model.addAttribute("isError", false);
        }

        return "admin/categories";
    }


    @RequestMapping(value = {"/addCategory"}, method = RequestMethod.GET)
    public String addCategoryView(Model model) {
        model.addAttribute("title", "Admin | Helix");
        return "admin/addCategory";
    }


    @RequestMapping(value = {"/addCategory"}, method = RequestMethod.POST)
    public String addCategory(Model model, @RequestParam("title") String title,
                          @RequestParam("description") String description) {

        Category category = new Category();
        category.setTitle(title);
        category.setUrlSlug(this.generateUrlSlugByTitle(title));
        category.setDescription(description);

        model.addAttribute("isCategoryCreated", "yes");

        try {
            this.categoryService.addCategory(category);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isCategoryCreated", "no");
        }

        return "redirect:/admin/categories";
    }


    @RequestMapping(value = {"/categories/{categorySlug}"}, method = RequestMethod.GET)
    public String updateCategoryView(Model model, @PathVariable("categorySlug") String categorySlug) {
        Category category = this.categoryService.getCategoryByUrlSlug(categorySlug);

        model.addAttribute("category", category);
        model.addAttribute("title", "Admin | Helix");

        return "admin/updateCategory";
    }


    @RequestMapping(value = {"/updateCategory"}, method = RequestMethod.POST)
    public String updateCategory(Model model, @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("categoryId") int categoryId) {

        Category oldCategory = this.categoryService.getCategoryById(categoryId);

        Category category = new Category();
        category.setId(oldCategory.getId());
        category.setTitle(title);
        category.setUrlSlug(this.generateUrlSlugByTitle(title));
        category.setDescription(description);

        model.addAttribute("isCategoryUpdated", "yes");

        try {
            this.categoryService.updateCategory(category);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isCategoryUpdated", "no");
        }

        return "redirect:/admin/categories";
    }


    @RequestMapping(value = {"/deleteCategory"}, method = RequestMethod.POST)
    @ResponseBody
    public String deleteCategory(Model model, @RequestParam("categoryId") int categoryId) {
        Category category = this.categoryService.getCategoryById(categoryId);
        List<Post> posts = this.categoryService.getAllPostsForCategory(category, 1, 10);

        if (posts.size() == 0) {
            this.categoryService.removeCategory(categoryId);
            return "yes";
        }
        else {
            return "no";
        }

    }


    /*
    * End Categories
    * */

    private String generateUrlSlugByTitle(String title) {
        return title.replaceAll(" ", "_").toLowerCase();
    }

}
