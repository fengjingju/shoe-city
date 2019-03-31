package shoecity.shoecity.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by FENGJINGJU on 2018/7/25.
 *
 * 商品实体
 */
@Data
public class Product {
    private String pid;// 主键

    private String cids;// 分类外键，多个分类以逗号分隔

    @NotEmpty(message = "{product.productName.notEmpty}")
    @Length(max = 128, message="商品名长度不能超过128！")
    private String productName;// 商品名

    @NotNull(message = "{product.price.notEmpty}")
    @Min(value = 0, message = "{product.price.inValid}")
    //@Pattern(regexp = "^[\\\\d.]+",message = "非法的价格")
    private Double price;// 价格

    @NotEmpty(message = "{product.size.notEmpty}")
    private String size;// 鞋码

    private Long publishTime;// 上架时间

    private String productImageUrl;// 商品图片URL，每个URL使用分号分割，第一个URL默认为封面图片，限制五张

    @Length(max = 250, message="商品详情描述长度不能超过250！")
    private String description;// 商品详情描述

    private String productDetailImageUrl;// 商品详情图片URL，每个URL使用分号分割，限制20张

    private int isIncludePostage;// 是否包邮，默认0 不包邮
}
