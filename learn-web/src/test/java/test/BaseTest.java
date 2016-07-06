package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//使用junit4进行测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置文件
@ContextConfiguration({"classpath:config/applicationContext.xml","classpath:config/jdshow-servlet.xml"})  
public class BaseTest {

}
