package shoecity.shoecity.util;

import java.util.UUID;

/**
 * @author FENGJINGJU
 * @date 2018/8/5 18:39
 */
public class UuidUtil {

    /**
     * 获取32位随机数
     * */
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
