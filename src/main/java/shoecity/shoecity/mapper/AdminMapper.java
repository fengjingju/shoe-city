package shoecity.shoecity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import shoecity.shoecity.domain.Product;
import shoecity.shoecity.domain.User;

/**
 * @author FENGJINGJU
 * @date 2018/8/5 14:06
 */
@Mapper
@Repository
public interface AdminMapper {

    @Select("SELECT * FROM tbl_user WHERE username=#{userName} and password=#{password}")
    User adminLogin(User user);
}
