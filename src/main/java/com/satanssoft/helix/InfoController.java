package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.service.CategoryService;
import com.satanssoft.helix.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class InfoController {

    private CategoryService categoryService;
    private TagService tagService;


    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setPostService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Autowired(required = true)
    @Qualifier(value = "tagService")
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }


    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String about(Model model) {
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags =  this.tagService.getAllTags();

        model.addAttribute("title", "About | Helix");
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);

        return "about";
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contact(Model model) {
        List<Category> categories = this.categoryService.getAllCategories();
        List<Tag> allTags =  this.tagService.getAllTags();


        model.addAttribute("title", "Contact | Helix");
        model.addAttribute("categories", categories);
        model.addAttribute("allTags", allTags);

        return "contact";
    }

}
