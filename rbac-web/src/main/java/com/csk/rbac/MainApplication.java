package com.csk.rbac;

import com.csk.rbac.common.properties.RbacProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/27 17:14
 **/
@SpringBootApplication //springboot默认扫描当前module下的包和类，scanBasePackages用来指定扫描的包
@EnableConfigurationProperties({RbacProperties.class})
@MapperScan(basePackages={"com.csk.rbac.system.dao"})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
