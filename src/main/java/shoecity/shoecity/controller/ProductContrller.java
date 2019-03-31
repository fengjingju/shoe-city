package shoecity.shoecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.domain.PageBean;
import shoecity.shoecity.domain.Product;
import shoecity.shoecity.exception.ParamRuleException;
import shoecity.shoecity.service.CategoryService;
import shoecity.shoecity.service.ProductService;
import shoecity.shoecity.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by FENGJINGJU on 2018/7/25.
 */
@Controller
@RequestMapping(value = "/product")
@SessionAttributes("productSort")
public class ProductContrller {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 发布商品
     */
    @RequestMapping(value = "/publishProduct", method = RequestMethod.POST)
    public String publishProduct(@RequestParam("productImage") List<MultipartFile> productImageList,
                                 @RequestParam("productDetailImage") List<MultipartFile> productDetailImageList,
                                 @Validated Product product, ModelMap map) throws IOException, ParamRuleException {

        if (CollectionUtils.isEmpty(productImageList) || productImageList.size() == 1 && StringUtils.isEmpty(productImageList.get(0).getOriginalFilename())) {
            throw new ParamRuleException("8商品展示缩略图不能为空！");
        } else {
            if (productImageList.size() > 5) {
                throw new ParamRuleException("商品展示缩略图限制5张！");
            }
        }
        if (CollectionUtils.isEmpty(productDetailImageList) && productDetailImageList.size() > 20) {
            throw new ParamRuleException("商品详情展示图限制20张！");
        }
        productService.publishProduct(productImageList, productDetailImageList, product);
        map.addAttribute("publishRestlt", "商品发布成功！");
        return "product/publishProductResult";
    }

    /**
     * 商品下架
     */
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public void deleteProduct(String pid){
        productService.deleteProduct(pid);
    }

    /**
     * 根据条件查询product
     * */
    @RequestMapping(value = "/findProduct", method = RequestMethod.GET)
    public String findProduct(Product product, PageBean<Product> pageBean, ModelMap map,
                              @ModelAttribute("productSort") String productSort, HttpServletRequest request, String priceStrat, String priceEnd) {
        if (pageBean.getSort() != null) {
            if (productSort != null && !productSort.equals(pageBean.getSort())) {
                pageBean.setPersentPage(1);// 每次更换排序字段，都要将当前页码置1
            }
        } else {
            pageBean.setSort("publishTime_desc");// 如果没有传入排序字段，默认按照发布时间降序
        }
        map.addAttribute("productSort", pageBean.getSort());// 将当前排序字段加入session域，下次访问该请求时还要取出该值

        // 处理分页参数，设置历史请求URL
        UrlUtil.getHistoryUrl("persentPage", request, pageBean);


        PageBean<Product> productPage = productService.findProduct(product, pageBean, priceStrat, priceEnd);
        map.addAttribute("productPage", productPage);
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        return "product/productList";
    }

    /**
     * 根据pid查询product
     * */
    @RequestMapping(value = "/findProductByPid", method = RequestMethod.GET)
    public String findProductByPid(String pid, ModelMap map) {
        Product product = productService.findProductByPid(pid);
        if(product != null) {
            map.addAttribute("product", product);
        }
        List<Category> categoryList = categoryService.findAllCategory();
        map.addAttribute("categoryList", categoryList);
        return "product/producDetail";
    }

    /**
     * 此方法用于获取session中的productSort，解决域对象中没有该值的情况
     * */
    @ModelAttribute("productSort")
    public String getProductSort() {
        return null;
    }
}
