package shoecity.shoecity.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shoecity.shoecity.domain.PageBean;
import shoecity.shoecity.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/8/12 21:23
 */
@Mapper
@Repository
public interface ProductMapper {

    // 插入product
    @Insert("INSERT INTO tbl_product (pid,cids,product_name,isIncludePostage,price,size,publish_time,image_url,description,detail_image_url) " +
            "VALUES (#{pid},#{cids},#{productName},#{isIncludePostage},#{price},#{size},#{publishTime},#{productImageUrl},#{description},#{productDetailImageUrl})")
    void publishProduct(Product product);

    // 根据pid删除product
    @Delete("DELETE FROM tbl_product WHERE pid = #{pid}")
    void deleteProduct(String pid);

    // 根据不同条件查询product
    @SelectProvider(type = ProductMapperProvider.class, method = "findProduct")
    List<Product> findProduct(Product product, PageBean<Product> pageBean, String priceStrat, String priceEnd);

    // 根据pid查询product
    @Select("SELECT pid,cids,product_name AS productName,isIncludePostage AS isIncludePostage,price,size,publish_time AS publishTime," +
            "image_url AS productImageUrl,description AS description,detail_image_url AS productDetailImageUrl" +
            " FROM tbl_product WHERE pid = #{pid}")
    Product findProductByPid(String pid);

    // 查询当前条件下的product总数目
    //@Select("SELECT COUNT(*) FROM tbl_product")
    @SelectProvider(type = ProductMapperProvider.class, method = "findProductSum")
    int findProductSum(Product product, String priceStrat, String priceEnd);

    /**
     * 接口中是不能写实现的，所以这里借用内部类来生成动态SQL
     */
    @Slf4j
    class ProductMapperProvider {

        /**
         * 公用商品查询条件
         */
        private StringBuffer commonSearchCondition(Product product, String priceStrat, String priceEnd) {
            StringBuffer sql = new StringBuffer();
            if (product.getPid() != null) {// 根据pid查询product
                sql.append(" AND pid = '" + product.getPid() + "'");
            }
            if (!StringUtils.isEmpty(product.getProductName())) {// 根据关键字模糊查询product
                sql.append(" AND product_name LIKE '%" + product.getProductName() + "%'");
            }
            if (!StringUtils.isEmpty(priceStrat) && !StringUtils.isEmpty(priceEnd)) {
                sql.append(" AND (price >= " + priceStrat + " AND price <= " + priceEnd + ")");
            } else {
                if (!StringUtils.isEmpty(priceStrat)) {
                    sql.append(" AND (price >= " + priceStrat + ")");
                }
                if (!StringUtils.isEmpty(priceEnd)) {
                    sql.append(" AND (price <= " + priceEnd + ")");
                }
            }
            if (product.getCids() != null) {
                List<String> cidList = Arrays.asList(product.getCids().split(","));
                for (String cid : cidList) {
                    sql.append(" AND cids LIKE '%" + cid + "%'");
                }

            }
            return sql;
        }

//        private List<String> mysqlSplit(String str){
//            List<String> stringList = new ArrayList<>();
//            SELECT substring_index('www.baidu.com','.', 1);
//        }

        /**
         * 多条件分页查询商品，支持排序
         * <p>
         * 排序功能：由于order by和limit同时使用时，排序的条件可能会有相同数据导致排序结果随机，但由于
         * 分页后，切换页码再次查询再次排序，结果再次随机，有概率导致 相同数据的某一数据在多次查询中重复出现多次，导致错误结果
         * 这个情况可以通过新增一个唯一性的字段排序来解决，这里加入了pid ASC
         */
        public String findProduct(Product product, PageBean<Product> pageBean, String priceStrat, String priceEnd) {
            StringBuffer sql = new StringBuffer("SELECT pid,cids,product_name AS productName,price,size," +
                    "publish_time AS publishTime,image_url AS productImageUrl FROM tbl_product WHERE 1=1");
            sql.append(commonSearchCondition(product, priceStrat, priceEnd));
            if (pageBean.getSort() != null) {
                String[] sortGroup = pageBean.getSort().split("_");
                if (sortGroup.length == 2) {
                    if ("publishTime".equals(sortGroup[0])) {// 按照发布时间排序
                        if ("desc".equalsIgnoreCase(sortGroup[1])) {//倒序
                            sql.append(" ORDER BY publish_time DESC,pid ASC");
                        } else if ("asc".equalsIgnoreCase(sortGroup[1])) {//正序
                            sql.append(" ORDER BY publish_time ASC,pid ASC");
                        } else {
                            log.info("findProduct，按照商品上架时间排序方式错误");
                        }
                    } else if ("price".equals(sortGroup[0])) {// 按照价格排序
                        if ("desc".equalsIgnoreCase(sortGroup[1])) {//倒序
                            sql.append(" ORDER BY price DESC,pid ASC");
                        } else if ("asc".equalsIgnoreCase(sortGroup[1])) {//正序
                            sql.append(" ORDER BY price ASC,pid ASC");
                        } else {
                            log.info("findProduct，按照商品价格排序方式错误");
                        }
                    } else {
                        log.info("findProduct，商品排序字段错误");
                    }
                }
            }
            if (pageBean != null && pageBean.getStartIndex() != null && pageBean.getPerPageCount() != null) {// 分页
                sql.append(" limit " + pageBean.getStartIndex() + "," + pageBean.getPerPageCount());// 从getStartIndex起，一共显示getPerPageCount个
            }
            return sql.toString();
        }

        /**
         * 查询当前条件下的product总数目
         */
        public String findProductSum(Product product, String priceStrat, String priceEnd) {
            StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM tbl_product WHERE 1=1");
            StringBuffer sqlConfident = commonSearchCondition(product, priceStrat, priceEnd);
            if (sqlConfident != null) {
                sql.append(sqlConfident);
            }
            return sql.toString();
        }
    }
}
