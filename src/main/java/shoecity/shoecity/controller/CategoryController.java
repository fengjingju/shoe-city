package shoecity.shoecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/8/18 22:26
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转至添加分类界面
     * */
    @RequestMapping(value = "/toAddCategory")
    public String toAddCategory(ModelMap map){
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);

        return "category/addCategory";
    }

    @RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
    public String insertCategory(ModelMap map, @Validated Category category){
        categoryService.insertCategory(category);
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        return "category/categoryList";
    }

    @RequestMapping(value = "/findAllCategory", method = RequestMethod.GET)
    public String findAllCategory(ModelMap map, String requestType) {
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        if (requestType.contains("category")) {
            return "category/categoryList";
        } else if (requestType.contains("product")) {
            return "product/publishProduct";
        }
        return null;
    }

    @RequestMapping(value = "/modifyCategory")
    public String modifyCategory(String cid,ModelMap map) {
        Category category = categoryService.findAllCategoryByCid(cid);
        if (category.getIsDisplayIndex() == 0) {
            category.setIsDisplayIndex(1);
        } else {
            category.setIsDisplayIndex(0);
        }
        categoryService.modifyCategory(category);
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        return "category/categoryList";
    }

    @RequestMapping(value = "/deleteCategoryByCid")
    public String deleteCategoryByCid(String cid,ModelMap map){
        categoryService.deleteCategoryByCid(cid);
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        return "category/categoryList";
    }
}
