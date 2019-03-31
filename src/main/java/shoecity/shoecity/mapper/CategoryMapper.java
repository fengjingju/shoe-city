package shoecity.shoecity.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shoecity.shoecity.domain.Category;

import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/8/18 22:01
 */
@Mapper
@Repository
public interface CategoryMapper {
    // 插入分类
    @Insert("INSERT INTO tbl_category (cid,category_name,isDisplayIndex) VALUES (#{cid},#{categoryName},#{isDisplayIndex})")
    void insertCategory(Category category);

    // 查询所有分类
    @Select("SELECT cid,category_name AS categoryName,isDisplayIndex FROM tbl_category")
    List<Category> findAllCategory();

    // 根据cid查询分类
    @Select("SELECT cid,category_name AS categoryName,isDisplayIndex FROM tbl_category WHERE cid = #{cid}")
    Category findAllCategoryByCid(String cid);

    // 根据cid删除分类
    @Delete("DELETE FROM tbl_category WHERE cid = #{cid}")
    void deleteCategoryByCid(String cid);

    // 修改分类
    @Update("UPDATE tbl_category SET isDisplayIndex = #{isDisplayIndex} WHERE cid = #{cid}")
    void modifyCategory(Category category);
}
