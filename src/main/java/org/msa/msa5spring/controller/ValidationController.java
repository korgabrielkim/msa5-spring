package org.msa.msa5spring.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.msa.msa5spring.request.RegistrationRequestDTO;
import org.springframework.web.bind.annotation.*;

// dependencies {
// // 외부 라이브러리나 프레임워크를 추가하는 곳. 추가 또는 수정을 했다면 반드시 그리드를 새로 고침해 줘야 함.
//     implementation 'org.springframework.boot:spring-boot-starter-validation'
//     implementation 'org.springframework.boot:spring-boot-starter-webmvc'
//     testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test'
//     testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
// } 유효성 검사를 진행하기 위해서는 build.gradle에서 validation 추가를 해야함.

@RestController
@RequestMapping("/api")
public class ValidationController {
    @GetMapping("/validation")
    public String validation(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO){
        return "유효성 검사"; // 우선 컨트롤러에 맵핑 작성을 해두고 작업을 이어 나가는 것이 좋음.
    }
    @GetMapping("/validation/{id}")
    public String validation(
            @Min(value = 1,message = "1 이상") @PathVariable Long id
    ){
        return id.toString();
    }
}

// 유효성 검사 Note!
// 1. build.gradle에 validation 추가.
// 2. DTO에 적용할 vlaid 추가.
// 3. 실행할 클래스에 속성 값으로 @Valid @RequestBody를 추가.
// 4. 실행하면 됨.
