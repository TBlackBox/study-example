package cn.yongzhi.example.db.controller;

import cn.yongzhi.example.db.domain.User;
import cn.yongzhi.example.db.multiple.rw.DataSourceEnum;
import cn.yongzhi.example.db.multiple.rw.DataSourceSwitcher;
import cn.yongzhi.example.db.multiple.rw.RoutingDataSourceContext;
import cn.yongzhi.example.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yongzhi
 * @Date 2021/3/10 14:21
 **/
@RestController
public class TestController {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    UserService userService;


    @GetMapping("/testMS")
    public Object testMS() throws Exception {
        try(RoutingDataSourceContext ctx = new RoutingDataSourceContext(DataSourceEnum.SLAVE)){

            User user = new User();

            user.setName("老李");
            userService.save(user);

            log.info("在里面查询出来的数据：");
            log.info(userService.selectById(1).toString());
        }

        return userService.selectById(1);
    }

    @DataSourceSwitcher(DataSourceEnum.SLAVE)
    @GetMapping("/testMSAnotation")
    public Object testMSAnotation() throws Exception {
//        String key = "masterDataSource";
        User user = new User();

        user.setName("老李");
        userService.save(user);

        log.info("在里面查询出来的数据：");
        log.info(userService.selectById(1).toString());

        return userService.selectById(1);
    }

    @GetMapping("/testT")
    public Object testT() throws Exception {
//        String key = "masterDataSource";
        User user = new User();

        user.setName("数据存入写数据库");
        userService.save(user);

        return userService.selectById(1);
    }
}
