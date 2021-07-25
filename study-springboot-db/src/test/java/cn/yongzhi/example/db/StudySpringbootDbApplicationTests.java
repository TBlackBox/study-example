package cn.yongzhi.example.db;

import cn.yongzhi.example.db.domain.User;
import cn.yongzhi.example.db.multiple.rw.DataSourceEnum;
import cn.yongzhi.example.db.multiple.rw.RoutingDataSourceContext;
import cn.yongzhi.example.db.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudySpringbootDbApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void testMS() throws Exception {

        try(RoutingDataSourceContext ctx = new RoutingDataSourceContext(DataSourceEnum.SLAVE)){

            User user = new User();

            user.setName("老李");
            userService.save(user);
        }




//        userService.selectById(1);

    }

}
