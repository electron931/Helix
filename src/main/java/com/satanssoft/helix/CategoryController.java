package com.satanssoft.helix;

import com.satanssoft.helix.hibernate.model.Category;
import com.satanssoft.helix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired(required = true)
    @Qualifier(value = "categoryService")
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String about(Model model) {

        Category category = new Category();
        category.setTitle("Handyman");
        category.setDescription("Awesome handyman");
        category.setUrlSlug("handyman");

        this.categoryService.addCategory(category);
        System.out.println("Category saved!");

        return "redirect:/home";
    }

}
