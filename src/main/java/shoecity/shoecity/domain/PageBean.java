package shoecity.shoecity.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author FENGJINGJU
 * @date 2018/8/13 22:00
 *
 * 分页Bean
 */
@Getter
public class PageBean<T> {
    @Setter
    private Integer persentPage;//当前页码
    @Setter
    private Integer sumCount;//总记录数
    @Setter
    private Integer perPageCount;//每页记录数
    private Integer pageCount;// 总页数
    private Integer startIndex;// 传给limit分页从第多少条开始
    @Setter
    private String url;//请求路径和参数【因为每次换页码时，还要保持同样的查询结果，路径和参数必须带过来】
    /**
     * 给前端返回的参数，不仅要返回一个查询出来的List，同时要把页码等等参数一起返回，因为前端要根据返回的参数展示页码导航条
     * 所以这个返回的实体List必须写在PageBean中与其他参数一起返回
     *
     * 同时由于只要分页 就一定会传入分页参数查询，所以pageBean不可能为null，所以将查出的实体放在pageBean中
     * */
    @Setter
    private List<T> beanList;
    @Setter
    private String sort;// 排序，单独加一个太费劲了，直接放到分页实体里面吧【格式：排序字段_desc/asc】



    /**
     * 根据总页码和当前前端页码 计算 传给limit分页从第多少条开始
     * */
    public void setStartIndex() {
        if (this.perPageCount != null && this.persentPage != null) {
            this.startIndex = this.perPageCount * (this.persentPage - 1);
        }
    }

    /**
     * 计算总页数
     * */
    public void setPageCount() {
        int num = this.sumCount / this.perPageCount;
        if (this.sumCount % this.perPageCount == 0) {
            this.pageCount = num;
        } else {
            this.pageCount = num + 1;
        }
    }
}
