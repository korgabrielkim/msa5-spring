package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResposeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // GlobalExceptionHandler로 사용한다고 spring boot에게 알려주는 어노테이션임.
public class GlobalExceptionHandler {
    // 예외 처리의 단위는 메소드가 됨.
    @ExceptionHandler(Exception.class) // .class가 붙은 이유 : 인스턴스화 되어 있는 형태를 주입. / 만약 Exception만 적으면 타입만 이갸기하는 거임.
    // 타입만 전송하면 비교를 못함. => Exception.class는 인스터스화가 된 객체라고 보면됨.
    public ResponseEntity<GlobalResposeDTO<Void>> handle(Exception e){
    // null을 제너릭 타입으로 지정하고 싶으면 Void를 넣어 주면 됨.
        return ResponseEntity
                .status(500)
                .body(
                        new GlobalResposeDTO<>(
                                "E99",
                                "규정집 좀 보자!",
                                null
                        )
                );
    }
}
