package shoecity.shoecity.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author FENGJINGJU
 * @date 2018/9/16 17:30
 */
@Configuration
public class UserInterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 拦截controller，由于WEB-INF下的jsp文件不能直接访问，必须通过controller，所以不用单独拦截
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/product/publishProduct")
                .addPathPatterns("/product/deleteProduct")
                .addPathPatterns("/category/insertCategory")
                .addPathPatterns("/category/modifyCategory")
                .addPathPatterns("/category/deleteCategoryByCid")
                .addPathPatterns("/category/toAddCategory");

        super.addInterceptors(registry);
    }

    /**
     * 静态资源拦截
     * */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/image/**");
//    }
}
