package shoecity.shoecity.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.service.CategoryService;

import java.util.List;

/**
 * 启动加载配置类
 *
 * @author FENGJINGJU
 * @date 2018/9/23 0:05
 */
@Component
public class CategoryConfig implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===启动加载配置类===");

//        List<Category> categoryList = categoryService.findAllCategory();
//        ModelMap map = new ModelMap();
//        map.addAttribute("categoryList", categoryList);
    }
}
