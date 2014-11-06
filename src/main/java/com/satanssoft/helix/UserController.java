package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.hibernate.model.User;
import com.satanssoft.helix.service.CategoryService;
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
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private static final int POSTS_PER_PAGE = 10;
    private int pageNumber;

    private User currentUser;

    private UserService userService;
    private CategoryService categoryService;
    private TagService tagService;


    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
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


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String singlePost(Model model, @PathVariable("userId") int userId) {
        pageNumber = 1;

        User user = this.userService.getUserById(userId);
        this.currentUser = user;

        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags = this.tagService.getAllTags();
        List<Post> posts = this.userService.getAllUserPosts(user, pageNumber, POSTS_PER_PAGE);

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);
        model.addAttribute("title", user.getUserName() + " | Helix");

        if (posts.size() == 0) {
            model.addAttribute("isEmpty", true);
        }
        else {
            model.addAttribute("isEmpty", false);
        }

        return "singleUser";
    }

    @RequestMapping(value = {"/{userId}"}, method = RequestMethod.POST)
    public String loadMorePosts(Model model, @PathVariable("userId") int userId) {
        pageNumber++;
        List<Post> posts = this.userService.getAllUserPosts(this.currentUser,
                pageNumber,
                POSTS_PER_PAGE);

        model.addAttribute("posts", posts);

        return "posts";
    }

}
