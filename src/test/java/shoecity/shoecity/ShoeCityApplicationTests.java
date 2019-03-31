package shoecity.shoecity;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ShoeCityApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public static void getUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println("随机数字为：" + uuid + ",长度为：" + uuid.length());
    }

}
