package dao;

import com.myssm.dao.UserMapper;
import com.myssm.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:16
 * @description: TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testFindUser() {
        User user = userMapper.findUserByName("abmin");
        System.out.println(user);
    }
}
