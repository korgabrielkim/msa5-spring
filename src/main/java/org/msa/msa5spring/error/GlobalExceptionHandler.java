package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice // GlobalExceptionHandler로 사용한다고 spring boot에게 알려주는 어노테이션임.
public class GlobalExceptionHandler {
    // 예외 처리의 단위는 메소드가 됨.
// 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
// 유효성 체크 예외 관련
    @ExceptionHandler(MethodArgumentTypeMismatchException.class) // 타입 변환 에러가 발견될때, (단 하나만 발견 되어도) 예외가 발견되어도 바로 넘어 옴.
        public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 가장 권장되는 방식은 상수가 있을 때 상수를 쓰는 것!
                .body(
                        new GlobalResponseDTO<>(
                                "E01",
                                "유효성 체크 에러",
                                String.format("%s를 확인해 주세요!", e.getName()) // value가 잘 못 작성되어도 Key를 확인하라고 보내야함. value를 그대로 전송하는 것은 리스크 있음.
                        )
                );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)  // 모든 체크를 하고 예외를 발견시 넘어 옴.
        // public ResponseEntity<GlobalResposeDTO<Void>> handle(MethodArgumentNotValidException e){
        public ResponseEntity<GlobalResponseDTO<Map<String, String>>>  handle(MethodArgumentNotValidException e){
            Map<String, String> errors = e.getBindingResult() // get 들어가면 getter 메소드라고 예상해도 무방함.
                .getFieldErrors() // 필드 정보를 받아서 리스트로 가져옴.
                .stream() // stream : 콜렉션 프레임워크를 공통된 처리방식으로 일관성 있게 처리하기 위해서 만들어진 객체. ※ 스트림은 가독성 때문에 쓰는거라고 보면됨. 사실 그냥 for문을 쓰는 것이 더 빠름.
                .collect(Collectors.toMap(
                        // collect : 특정 하나 하나 요소들을 추출을 해서 map 형태로 변환 시킬 수 있는 최종 연산 메소드.
                        // 첫번째 : Key 두번째 : value 세번째 : 중복된 key가 발생했을 때 처리 방식
                        // (fieldError) ->fieldError.getField(),
                        FieldError::getField, // 위에 람다식을 변환한 것. 필드명을 가져와서 Map의 Key로 사용
                        (fieldError) -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다",
                                                                               // != null ? 널이 아닐 때 값 : null 일때 값.
                        (existing, replacement) -> existing // 첫번째 [existing] : 해당 필드의 기존 값, 두번째 [replacement] : 새로운 값
                ));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new GlobalResponseDTO<>(
                                    "E01"
                                    ,"유효성 체크"
                                    , errors
                            )
                    );

    }
// 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
// 그 외 모든 예외 처리 핸들러 메소드
    @ExceptionHandler(Exception.class) // .class가 붙은 이유 : 인스턴스화 되어 있는 형태를 주입. / 만약 Exception만 적으면 타입만 이갸기하는 거임.
    // 타입만 전송하면 비교를 못함. => Exception.class는 인스터스화가 된 객체라고 보면됨.
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e){
    // null을 제너릭 타입으로 지정하고 싶으면 Void를 넣어 주면 됨.
        return ResponseEntity
                .status(500)
                .body(
                        new GlobalResponseDTO<>(
                                "E99",
                                "규정집 참조",
                                null
                        )
                );
    }
}
// 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
