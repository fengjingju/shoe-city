package shoecity.shoecity.service;

import shoecity.shoecity.domain.Category;

import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/8/18 22:06
 */
public interface CategoryService {

    void insertCategory(Category category);

    List<Category> findAllCategory();

    Category findAllCategoryByCid(String cid);

    void deleteCategoryByCid(String cid);

    void modifyCategory(Category category);
}
