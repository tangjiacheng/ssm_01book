package service;

import com.myssm.pojo.User;
import com.myssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:35
 * @description: TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindUser() {
        User user = userService.findUserByName("admin");
        System.out.println(user);
    }
}
