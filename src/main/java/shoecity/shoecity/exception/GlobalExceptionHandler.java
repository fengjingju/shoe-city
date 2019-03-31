package shoecity.shoecity.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常捕获
 *
 * @author FENGJINGJU
 * @date 2018/9/22 22:25
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private CategoryService categoryService;

    /**
     * 参数校验异常(包括使用了注解的，以及格式异常NumberFormatException)
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ModelMap handleBindException(BindException e) {
        List<FieldError> fieldErrorList = e.getFieldErrors();
        if (!CollectionUtils.isEmpty(fieldErrorList)) {
            FieldError fieldError = fieldErrorList.get(0);// 多个异常只取第一个
            String message = fieldError.getDefaultMessage();
            ModelMap map = new ModelMap();
            if (message.contains("NumberFormatException")) {
                map.addAttribute("fieldError", "[" + fieldError.getField() + "] 输入类型错误！");
            } else {
                map.addAttribute("fieldError", message);
            }
            List<Category> categoryList = categoryService.findAllCategory();
            map.addAttribute("categoryList", categoryList);
            return map;
        }
        return null;
    }

    /**
     * 捕获全局异常【自定义异常统一处理】
     */
    @ExceptionHandler(Exception.class)
    public ModelMap defultExcepitonHandler(Exception e) {
        ModelMap map = new ModelMap();
        map.addAttribute("fieldError", e.getMessage());
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        return map;
    }
}
