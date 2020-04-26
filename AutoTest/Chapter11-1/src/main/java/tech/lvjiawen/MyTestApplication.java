package tech.lvjiawen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MyTestApplication {

    private  static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        MyTestApplication.context = SpringApplication.run(MyTestApplication.class, args);
    }

    @PreDestroy
    public void close(){
        MyTestApplication.context.close();
    }

}
