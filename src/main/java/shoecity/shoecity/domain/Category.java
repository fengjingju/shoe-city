package shoecity.shoecity.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

/**
 * @author FENGJINGJU
 * @date 2018/8/18 22:00
 */
@Data
public class Category {
    private String cid;
    @Length(max = 128, message="分类名称长度不能超过128！")
    private String categoryName;// 分类名称，长度128
    private int isDisplayIndex;// 是否在首页分类展示，默认0不展示
}
