package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.hibernate.model.User;
import com.satanssoft.helix.service.CategoryService;
import com.satanssoft.helix.service.TagService;
import com.satanssoft.helix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private UserService userService;
    private TagService tagService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService categoryService){
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    //public String test(@RequestParam("category_id") int category_id, Model model) {
    public String test(Model model) {

        Category category = new Category();
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
        this.tagService.addTag(tag2);

        /*this.categoryService.removeCategory(category_id);
        System.out.println("Category deleted!");*/

        return "redirect:/home";
    }

}
