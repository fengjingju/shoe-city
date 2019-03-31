package shoecity.shoecity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shoecity.shoecity.domain.Category;
import shoecity.shoecity.service.CategoryService;
import shoecity.shoecity.service.impl.CategoryServiceImpl;

import java.util.List;

@SpringBootApplication
@Slf4j
public class ShoeCityApplication {

    public static void main(String[] args) {
        // 设置系统路径
        String rootPath;
        String systemName = System.getProperties().getProperty("os.name");
        if (systemName.toLowerCase().contains("windows")) {// windows操作系统
            rootPath = "F:\\jiangdongxiaoShoe";
        }else {// linux
            rootPath = "/data/jiangdongxiaoShoe";
        }
        System.setProperty("rootPath",rootPath);

        SpringApplication.run(ShoeCityApplication.class, args);
        System.out.println("======================springBoot启动成功=====================");
        log.info("======================springBoot启动成功=====================");
    }
}
