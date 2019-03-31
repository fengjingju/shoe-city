package shoecity.shoecity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FENGJINGJU on 2018/7/25.
 *
 * 最初测试程序，hello World
 */
@RestController
public class Test {

    @RequestMapping("/hello")
    public String out(){
        System.out.println("springBoot启动成功");
        return "hello world!";
    }
}
