package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.*;
import com.satanssoft.helix.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Date;
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
    private RoleService roleService;


    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired(required = true)
    @Qualifier(value = "postService")
    public void setPostService(PostService postService){
        this.postService = postService;
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

    @Autowired(required = true)
    @Qualifier(value = "roleService")
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.GET)
    public String postsForCategory(Model model, @PathVariable("categorySlug") String categorySlug) {
        pageNumber = 1;

        Category category = this.categoryService.getCategoryByUrlSlug(categorySlug);
        this.category = category;

        List<Post> posts = this.categoryService
                .getAllPostsForCategory(category, pageNumber, POSTS_PER_PAGE);
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags =  this.tagService.getAllTags();

        model.addAttribute("posts", posts);
        model.addAttribute("title",  category.getTitle() + " | Helix");
        model.addAttribute("currentCategory", category);
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);

        if (posts.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        return "postsForCategory";
    }


    @RequestMapping(value = "/{categorySlug}", method = RequestMethod.POST)
    public String loadMorePosts(Model model, @PathVariable("categorySlug") String categorySlug) {
        pageNumber++;
        List<Post> posts = this.categoryService
                .getAllPostsForCategory(this.category, pageNumber, POSTS_PER_PAGE);

        model.addAttribute("posts", posts);

        return "posts";
    }


    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setup(Model model) {

        Category category1 = new Category();
        category1.setTitle("News");
        category1.setDescription("Awesome news");
        category1.setUrlSlug("news");
        this.categoryService.addCategory(category1);

        Category category2 = new Category();
        category2.setTitle("Games");
        category2.setDescription("Games are the future");
        category2.setUrlSlug("games");
        this.categoryService.addCategory(category2);

        Role role = new Role();
        role.setRole("admin");
        this.roleService.addRole(role);

        Role role2 = new Role();
        role2.setRole("moderator");
        this.roleService.addRole(role2);

        User user = new User();
        user.setUserName("Admin");
        user.setPassword("12345");
        user.setEmail("admin@test.com");
        user.setRole(role);
        this.userService.addUser(user);

        User user2 = new User();
        user2.setUserName("Sergey");
        user2.setPassword("qwerty");
        user2.setEmail("sergey@test.com");
        user2.setRole(role2);
        this.userService.addUser(user2);

        User user3 = new User();
        user3.setUserName("Alex");
        user3.setPassword("54321");
        user3.setEmail("alex@test.com");
        user3.setRole(role2);
        this.userService.addUser(user3);


        for (int i = 1; i <= 10; i++) {
            Tag tag = new Tag();
            tag.setName("tag" + i);
            tag.setUrlSlug("tag" + i);
            this.tagService.addTag(tag);
        }

        List<Tag> tags = this.tagService.getAllTags();

        int imageCounter = 1;

        for (int i = 1; i <= 30; i++) {
            Post post = new Post();
            post.setTitle("Post" + i);
            post.setShortDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin porta placerat ipsum pulvinar malesuada." + i);
            post.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin porta placerat ipsum pulvinar malesuada. Mauris lobortis aliquam neque sit amet consectetur. Donec et nibh a metus mollis dictum finibus a massa. Curabitur in sem est. Nunc a scelerisque libero. Maecenas sit amet neque nisi. Phasellus sed fermentum diam. Nunc sed ipsum enim. Praesent non augue est. Etiam quis tempus risus.");
            post.setUrlSlug("post" + i);
            post.setPostedOnDate(new Date());
            post.setThumbnail("pic" + imageCounter++ + ".jpg");

            if (imageCounter > 9) {
                imageCounter = 1;
            }

            if (i % 2 == 0) {
                post.setCategory(this.categoryService.getCategoryById(2));
                post.setAuthor(user3);
                post.setTags(tags.subList(5, 9));
            }
            else {
                post.setCategory(this.categoryService.getCategoryById(1));
                post.setAuthor(user2);
                post.setTags(tags.subList(0, 4));
            }

            this.postService.addPost(post);
        }

        return "redirect:/";
    }

}
