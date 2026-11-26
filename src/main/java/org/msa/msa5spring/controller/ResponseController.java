package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResponseController {
    @GetMapping("/res")
    public ResponseEntity<GlobalResponseDTO<String>> index(){
        // return ResponseEntity.status(200).body("3"); // 시스템 마다 바디에 들어가는 형태는 대체로 고정시켜둠.
        // return ResponseEntity.ok("3");
        // return ResponseEntity.notFound().build();
        GlobalResponseDTO<String> globalResponseDTO = new GlobalResponseDTO<>(
            "00"
                ,"정상처리"
                ,"레스폰스 테스트");
    return ResponseEntity.status(200).body(globalResponseDTO);

    // .body(globalResposeDTO) 여기에 담긴 타입 형태인, GlobalResposeDTO<String>을 제네릭 타입에 넣어 줘야지 오류가 발생하지 않음.
    }
    @GetMapping("/res/test")
    public ResponseEntity<GlobalResponseDTO<QueryRequestDTO>> test() {
        return ResponseEntity.status(200).body(new GlobalResponseDTO<>(
                "00",
                "정상처리",
                new QueryRequestDTO(3L,"홍길동")
            ) // body 안에 new 키워드가 있으면, new 키워드가 먼저 실행됨.
        );
    }
}
