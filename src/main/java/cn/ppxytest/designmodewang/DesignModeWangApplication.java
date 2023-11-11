package cn.ppxytest.designmodewang;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableApolloConfig
@EnableJpaRepositories
@SpringBootApplication
public class DesignModeWangApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignModeWangApplication.class, args);
    }

}
