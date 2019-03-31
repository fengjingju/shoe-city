package shoecity.shoecity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.service.CategoryService;

import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/7/28 13:26
 *
 * 访问首页
 */
@Controller
public class Index {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    public String index(ModelMap map){
        System.out.println("访问首页成功");
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);

        return "index";
    }
}
