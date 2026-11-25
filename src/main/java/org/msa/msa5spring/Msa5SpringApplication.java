package org.msa.msa5spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Msa5SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Msa5SpringApplication.class, args);
    }

}
// 레코드는 기본적으로 프라이빗 필드를 만들고, 초기화를 해주는 작업을 기본적으로 해준다고 보면됨. 즉, 불변하는 코드를 작성하는 곳.