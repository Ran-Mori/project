package whu.izumisakaizy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("whu.izumisakaizy.mapper")
public class IzumisakaiZyApplication {

    public static void main(String[] args) {
        SpringApplication.run(IzumisakaiZyApplication.class, args);
    }

}
