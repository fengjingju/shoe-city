package shoecity.shoecity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoecity.shoecity.domain.User;
import shoecity.shoecity.mapper.AdminMapper;
import shoecity.shoecity.service.AdminService;

/**
 * Created by FENGJINGJU on 2018/7/25.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public User adminLogin(User user) {
        return adminMapper.adminLogin(user);
    }
}
