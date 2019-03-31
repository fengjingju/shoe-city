package shoecity.shoecity.service;

import org.springframework.web.multipart.MultipartFile;
import shoecity.shoecity.domain.PageBean;
import shoecity.shoecity.domain.Product;

import java.io.IOException;
import java.util.List;

/**
 * Created by FENGJINGJU on 2018/7/25.
 */
public interface ProductService {
    void publishProduct(List<MultipartFile> productImageList, List<MultipartFile> productDetailImage, Product product) throws IOException;

    void deleteProduct(String pid);

    PageBean<Product> findProduct(Product product, PageBean<Product> pageBean, String priceStrat, String priceEnd);

    int findProductSum(Product product, String priceStrat, String priceEnd);

    Product findProductByPid(String pid);
}
