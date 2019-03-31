package shoecity.shoecity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import shoecity.shoecity.common.CommonContant;
import shoecity.shoecity.config.ShoeImagePath;
import shoecity.shoecity.domain.PageBean;
import shoecity.shoecity.domain.Product;
import shoecity.shoecity.mapper.ProductMapper;
import shoecity.shoecity.service.ProductService;
import shoecity.shoecity.util.FileUtil;
import shoecity.shoecity.util.UuidUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by FENGJINGJU on 2018/7/25.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ShoeImagePath shoeImagePath;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void publishProduct(List<MultipartFile> productImageList, List<MultipartFile> productDetailImageList, Product product) throws IOException {
        String springStaticLocation = shoeImagePath.getSpringStaticLocation();
        String productImageUrl = FileUtil.getImageUrl(productImageList, shoeImagePath.getWindowsProductIndex(), shoeImagePath.getLinuxProductIndex(), springStaticLocation, "exact");

        if (productDetailImageList.size() == 1 && StringUtils.isEmpty(productDetailImageList.get(0).getOriginalFilename())) {
            product.setProductDetailImageUrl(null);
        } else {
            String productDetailImageUrl = FileUtil.getImageUrl(productDetailImageList, shoeImagePath.getWindowsProductDetail(), shoeImagePath.getLinuxProductDetail(), springStaticLocation, "width");
            product.setProductDetailImageUrl(productDetailImageUrl);
        }
        product.setPid(UuidUtil.getUuid());
        product.setProductImageUrl(productImageUrl);
        product.setPublishTime(new Date().getTime());
        productMapper.publishProduct(product);
    }

    @Override
    public void deleteProduct(String pid) {
//        String imageUrl =
//        productMapper.deleteProduct(pid);
    }

    @Override
    public PageBean<Product> findProduct(Product product, PageBean<Product> pageBean, String priceStrat, String priceEnd) {
        pageBean.setPerPageCount(CommonContant.PRODUCT_PAGE_SIZE);
        pageBean.setStartIndex();
        List<Product> productList = productMapper.findProduct(product, pageBean, priceStrat, priceEnd);
        pageBean.setBeanList(productList);
        pageBean.setSumCount(this.findProductSum(product, priceStrat, priceEnd));
        pageBean.setPageCount();
        return pageBean;
    }

    @Override
    public int findProductSum(Product product, String priceStrat, String priceEnd) {
        return productMapper.findProductSum(product, priceStrat, priceEnd);
    }

    @Override
    public Product findProductByPid(String pid) {
        return productMapper.findProductByPid(pid);
    }
}
