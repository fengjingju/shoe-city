package shoecity.shoecity.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author FENGJINGJU
 * @date 2018/8/9 23:38
 */
@Getter
@Configuration
public class ShoeImagePath {

    /** 商品展示缩略图片存放路径公共头 */
    @Value("${shoe.windows.product.index}")
    private String windowsProductIndex;

    @Value("${shoe.linux.product.index}")
    private String LinuxProductIndex;


    /** 商品详情图片存放路径公共头 */
    @Value("${shoe.windows.product.detail}")
    private String windowsProductDetail;

    @Value("${shoe.linux.product.detail}")
    private String LinuxProductDetail;


    /** 自定义静态资源访问路径 */
    @Value("${spring.resources.static-locations}")
    private String springStaticLocation;
}
