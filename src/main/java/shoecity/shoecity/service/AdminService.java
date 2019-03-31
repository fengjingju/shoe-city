package shoecity.shoecity.service;

import shoecity.shoecity.domain.User;

/**
 * Created by FENGJINGJU on 2018/7/25.
 */
public interface AdminService {

    /**
     * 用户登录
     * */
    User adminLogin(User user);
}
