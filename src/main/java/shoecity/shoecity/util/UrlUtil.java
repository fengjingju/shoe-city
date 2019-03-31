package shoecity.shoecity.util;

import lombok.extern.slf4j.Slf4j;
import shoecity.shoecity.domain.PageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FENGJINGJU
 * @date 2018/8/26 22:31
 */
@Slf4j
public class UrlUtil {

    /**
     * 获取历史请求URL，拼接请求条件参数，保留其他参数，同时去掉页码参数，防止换页后除分页以外的其他条件丢失
     *
     * 由于其他参数请求时，不需要保持历史请求条件，比如：排序、按名称搜索等，都需要重置条件，所以无需获取历史URL，
     * 直接重新传URL就好，所以这里只需要对分页参数进行处理
     *
     * */
    public static void getHistoryUrl(String paramName, HttpServletRequest request, PageBean pageBean){

        String queryUri = request.getRequestURI();
        String queryParam = request.getQueryString();
        if (queryParam.contains(paramName)) {
            String[] paramGroup = queryParam.split("&");
            if (paramGroup.length > 1) {
                StringBuffer stringBuffer = new StringBuffer();
                for (String param : paramGroup) {
                    if (!param.contains(paramName)) {
                        stringBuffer.append(param);
                        stringBuffer.append("&");// 拼接新的参数还需要&，所以尾部的&不删除
                    }
                }
                pageBean.setUrl(queryUri + "?" + stringBuffer.toString());
            } else {// 若只有一个参数，那就是分页参数，直接去掉
                pageBean.setUrl(queryUri + "?");
            }
        }else {
            log.error("未传分页参数");
        }
    }
}
