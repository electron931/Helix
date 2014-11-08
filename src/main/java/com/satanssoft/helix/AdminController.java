package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.*;
import com.satanssoft.helix.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private CommentService commentService;
    private RoleService roleService;


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

    @Autowired(required = true)
    @Qualifier(value = "commentService")
    public void setCommentService(CommentService commentService){
        this.commentService = commentService;
    }

    @Autowired(required = true)
    @Qualifier(value = "roleService")
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
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

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = this.userService.getUserByName(userDetails.getUsername());

        List<Post> posts;
        if (currentUser.getRole().getId() == 1) {       //ROLE_ADMIN
            posts = this.postService.getAllPosts();
        }
        else {                                          //ROLE_MODERATOR
            posts = this.userService.getAllUserPosts(currentUser);
        }


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
        post.setUrlSlug(this.generateUrlSlugByTitle(title));

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



    /*
    * Tags
    * */

    @RequestMapping(value = {"/tags"}, method = RequestMethod.GET)
    public String tags(Model model, @RequestParam(required = false) String isTagCreated,
                             @RequestParam(required = false) String isTagUpdated,
                             @RequestParam(required = false) String isError) {
        List<Tag> tags = this.tagService.getAllTags();

        model.addAttribute("tags", tags);
        model.addAttribute("title", "Admin | Helix");


        if (tags.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        if (isTagCreated != null && isTagCreated.equals("yes")) {
            model.addAttribute("isTagCreated", true);
        }
        else {
            model.addAttribute("isTagCreated", false);
        }

        if (isTagUpdated != null && isTagUpdated.equals("yes")) {
            model.addAttribute("isTagUpdated", true);
        }
        else {
            model.addAttribute("isTagUpdated", false);
        }

        if (isError != null && isError.equals("yes")) {
            model.addAttribute("isError", true);
        }
        else {
            model.addAttribute("isError", false);
        }

        return "admin/tags";
    }


    @RequestMapping(value = {"/addTag"}, method = RequestMethod.GET)
    public String addTagView(Model model) {
        model.addAttribute("title", "Admin | Helix");
        return "admin/addTag";
    }


    @RequestMapping(value = {"/addTag"}, method = RequestMethod.POST)
    public String addTag(Model model, @RequestParam("name") String name) {

        Tag tag = new Tag();
        tag.setName(name);
        tag.setUrlSlug(this.generateUrlSlugByTitle(name));

        model.addAttribute("isTagCreated", "yes");

        try {
            this.tagService.addTag(tag);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isTagCreated", "no");
        }

        return "redirect:/admin/tags";
    }


    @RequestMapping(value = {"/tags/{tagSlug}"}, method = RequestMethod.GET)
    public String updateTagView(Model model, @PathVariable("tagSlug") String tagSlug) {
        Tag tag = this.tagService.getTagByUrlSlug(tagSlug);

        model.addAttribute("tag", tag);
        model.addAttribute("title", "Admin | Helix");

        return "admin/updateTag";
    }


    @RequestMapping(value = {"/updateTag"}, method = RequestMethod.POST)
    public String updateTag(Model model, @RequestParam("name") String name,
                                 @RequestParam("tagId") int tagId) {

        Tag oldTag = this.tagService.getTagById(tagId);

        Tag tag = new Tag();
        tag.setId(oldTag.getId());
        tag.setName(name);
        tag.setUrlSlug(this.generateUrlSlugByTitle(name));

        model.addAttribute("isTagUpdated", "yes");

        try {
            this.tagService.updateTag(tag);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isTagUpdated", "no");
        }

        return "redirect:/admin/tags";
    }


    @RequestMapping(value = {"/deleteTag"}, method = RequestMethod.POST)
    public String deleteTag(Model model, @RequestParam("tagId") int tagId) {
        this.tagService.removeTag(tagId);
        return "redirect:/admin/tags";
    }


    /*
    * End Tags
    * */


    @RequestMapping(value = {"/comments"}, method = RequestMethod.GET)
    public String comments(Model model) {
        List<Comment> comments = this.commentService.getAllComments();

        if (comments.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        model.addAttribute("comments", comments);
        model.addAttribute("title", "Admin | Helix");

        return "admin/comments";
    }


    /*
    * Users
    * */


    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public String users(Model model, @RequestParam(required = false) String isUserCreated,
                       @RequestParam(required = false) String isUserUpdated,
                       @RequestParam(required = false) String isError) {
        List<User> users = this.userService.getAllModerators();

        model.addAttribute("users", users);
        model.addAttribute("title", "Admin | Helix");


        if (users.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        if (isUserCreated != null && isUserCreated.equals("yes")) {
            model.addAttribute("isUserCreated", true);
        }
        else {
            model.addAttribute("isUserCreated", false);
        }

        if (isUserUpdated != null && isUserUpdated.equals("yes")) {
            model.addAttribute("isUserUpdated", true);
        }
        else {
            model.addAttribute("isUserUpdated", false);
        }

        if (isError != null && isError.equals("yes")) {
            model.addAttribute("isError", true);
        }
        else {
            model.addAttribute("isError", false);
        }

        return "admin/users";
    }


    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String addUserView(Model model) {
        model.addAttribute("title", "Admin | Helix");
        return "admin/addUser";
    }


    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(Model model, @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email) {

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);

        //roles must be stored in db (require initial setup)
        Role role = this.roleService.getRole(2);    //ROLE_MODERATOR
        user.setRole(role);

        model.addAttribute("isUserCreated", "yes");

        try {
            this.userService.addUser(user);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isUserCreated", "no");
        }

        return "redirect:/admin/users";
    }


    @RequestMapping(value = {"/users/{userId}"}, method = RequestMethod.GET)
    public String updateUserView(Model model, @PathVariable("userId") int userId) {
        User user = this.userService.getUserById(userId);

        model.addAttribute("user", user);
        model.addAttribute("title", "Admin | Helix");

        return "admin/updateUser";
    }


    @RequestMapping(value = {"/updateUser"}, method = RequestMethod.POST)
    public String updateUser(Model model, @RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email,
                                          @RequestParam("userId") int userId) {

        User oldUser = this.userService.getUserById(userId);

        User user = new User();
        user.setId(oldUser.getId());
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);

        //roles must be stored in db (require initial setup)
        Role role = this.roleService.getRole(2);    //ROLE_MODERATOR
        user.setRole(role);

        model.addAttribute("isUserUpdated", "yes");

        try {
            this.userService.updateUser(user);
        }
        catch (Exception e) {
            model.addAttribute("isError", "yes");
            model.addAttribute("isUserUpdated", "no");
        }

        return "redirect:/admin/users";
    }


    @RequestMapping(value = {"/deleteUser"}, method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(Model model, @RequestParam("userId") int userId) {
        User user = this.userService.getUserById(userId);
        List<Post> posts = this.userService.getAllUserPosts(user);

        model.addAttribute("isUserDeleted", "yes");

        if (posts.size() == 0) {
            this.userService.removeUser(userId);
            return "yes";
        }
        else {
            return "no";
        }

    }


    /*
    * End Users
    * */


     private String generateUrlSlugByTitle(String title) {
        return title.replaceAll(" ", "_").toLowerCase();

    }

}
