package shoecity.shoecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.domain.User;
import shoecity.shoecity.service.AdminService;
import shoecity.shoecity.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by FENGJINGJU on 2018/7/25.
 *
 * 管理员Contrller
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转至登录界面
     * */
    @RequestMapping("/login")
    public String index(ModelMap map, HttpServletRequest request) {
        // 登录按钮返回当前界面用
//        String requestURL = request.getHeader( "Referer" );
//        request.getSession().setAttribute("beforeLoginRequestURL", requestURL);
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);

        return "admin/login";
    }

    /**
     * 用户登录，登录后跳转至添加商品页面
     * */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String adminLogin(User user, ModelMap map, HttpServletRequest request) {
        User userResult = adminService.adminLogin(user);
        if (userResult != null) {// 登录成功
            request.getSession().setAttribute("admin", userResult);
//            String requestURL = (String) request.getSession().getAttribute("beforeLoginRequestURL");
//            String beforeLoginRequestURL = requestURL.substring(requestURL.indexOf("0/")+1);
//            return beforeLoginRequestURL;
            List<Category> categoryList = categoryService.findAllCategory();
            map.addAttribute("categoryList", categoryList);
            return "index";
        } else {
            map.addAttribute("error", "用户名或密码错误！");
            return null;
        }
    }

    /**
     * 退出登录
     * */
    @RequestMapping(value = "/quit")
    public String quit(HttpServletRequest request){
        request.getSession().invalidate();//销毁session
        return "admin/login";//重定向到登录界面
    }
}
