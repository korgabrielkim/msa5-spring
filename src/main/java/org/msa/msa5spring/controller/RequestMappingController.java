package org.msa.msa5spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 해당 클래스가 restcontroller 역할을 하는 클래스라고 알려주는 명령어.
// 해당 어노테이션이 있어야지 정상적인 작동을 한다고 함.
public class RequestMappingController {
    // 유저가 유저의 정보 조회를 요청했을 때(get/users)
    @GetMapping("/users")
    public String index(){
        return "유저 조회 수정";
    }
//     http://localhost:8080/(접근 루트→)users 스프링 부트에 작업한 것을 왓슨을 이용해서 확인하고 싶을때 기본적인 주소.
    @PostMapping("/users/1")
    public String store(){
        return "유저 생성";
    }

}
