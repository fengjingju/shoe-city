package shoecity.shoecity.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author FENGJINGJU
 * @date 2018/9/16 17:21
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        if(!StringUtils.isEmpty(session.getAttribute("admin"))){
            return true;
        }else {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().print("<h1>sorry!~用户未登录</h1>");
        }
        return false;
    }
}
