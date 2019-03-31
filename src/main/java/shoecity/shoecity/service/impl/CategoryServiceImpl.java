package shoecity.shoecity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.mapper.CategoryMapper;
import shoecity.shoecity.service.CategoryService;
import shoecity.shoecity.util.UuidUtil;

import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/8/18 22:07
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void insertCategory(Category category) {
        category.setCid(UuidUtil.getUuid());
        categoryMapper.insertCategory(category);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryMapper.findAllCategory();
    }

    @Override
    public Category findAllCategoryByCid(String cid) {
        return categoryMapper.findAllCategoryByCid(cid);
    }

    @Override
    public void deleteCategoryByCid(String cid) {
        categoryMapper.deleteCategoryByCid(cid);
    }

    @Override
    public void modifyCategory(Category category) {
        categoryMapper.modifyCategory(category);
    }
}
