package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
// 해당 클래스가 restcontroller 역할을 하는 클래스라고 알려주는 명령어.
// 해당 어노테이션이 있어야지 정상적인 작동을 한다고 함.
@RequestMapping("/api") // ← 프리픽스 경로를 주는 것임.(해당 클래스에 작업하는 것은 모두 / api가 기본으로 잡힘)
public class RequestMappingController {
    // 유저가 유저의 정보 조회를 요청했을 때(get/users)
    @GetMapping
    public String index(){
        return "유저 조회 수정";
    }
//     http://localhost:8080/(접근 루트→)users 스프링 부트에 작업한 것을 왓슨을 이용해서 확인하고 싶을때 기본적인 주소.

    @PostMapping
    public String store(){
        return "유저 생성";
    }

    @PutMapping
    public String update(){
        return "유저 수정";
    }

    @DeleteMapping // ("/users") ← 프리픽스를 부였햇는데도  ("/users") 입력했다면 /users/users로 작동을 함.
    public String destroy(){
        return "유저 삭제";
    }

    // 유저가 전달해주는 값을 받는 방법.
    // Key와 value로 전송 받는 방법 (쿼리 파라미터) -> URL에 세팅
    // 패스부분(users:id)의 일부를 데이터로 인식시켜서 전송 받는 방법 (세그먼트 파라미터) -> URL에 세팅
    // http로 통신을 할 때, 크게 2가지의 영역(head / body)으로 데이터를 세팅해서 보내는데, body에 담아서 보내는 방식임 폼 데이터 파라미터는 파일 전송과 관련되서 사용함 / 일반유저에게 보이지 않음 (폼 데이터 파라미터) -> URL에 세팅 X
    // 제이슨 형태로 body에 담아서 보냄e
    @GetMapping("/query") // 쿼리 파라미터는 기본적으로 get으로 밖에 보낼 수 가 없음
    public String query(QueryRequestDTO queryRequestDTO){ // ← 생성한 리퀘스트 DTO를 받는 방법임)
        // 리퀘스트DTO 객체를 만들고 그 객체로 받는 경우가 일반적임.
        // 리퀘스트DTO의 경우에는 패키지를 request DTO와 response DTO를 나눠서 관리하는 경우도 있음.(작업량이 많을 때)
        // DTO 각 파일은 레코드로 만들면 됨.
        return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
        // 자바에서 + 연산자를 사용할 수 도 있지만, 타입 오류(int, string)를 방지하기 위해서 String.format을 이용하는 것이 좋음.
    }
    // @GetMapping("/segment/{키}/{키}") → 중괄호가 있는 부분의 세그먼트로 인식을 함. {키}는 한개 또는 수개를 설정할 수 있음.
    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){ // 만약에 @ModelAttribute을 넣어 주지 않으면 쿼리 파라미터로 받아 버림.
        return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
    // @GetMapping("/segment/1/my") // 세그먼트를 고정값으로 부여했을 뿐, 위에 있는 패스 형태와 동일한 형태임.
    // public String test(){
    //     return "test";
    // }
    // 세그먼트 파라미터는 패스의 일부를 사용하는 방식이기에, 패스의 형태와 동일한 형태의 패스가 작성되면 그것을 인식해 버림.
    // 위에 코드를 실행하면 test를 출력하는 이유임. → 세그먼트 파라미터를 이용한 Path와 겹치지 않도록 주의.

    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file) { // MultipartFile은 자바에서 편하게 사용할 수 있도록 제공하는 객체고 이때 file을 Key로 써야함)
        // 파일을 받을 때에는 DTO까지는 만들지 않는 것이 일반적임. 파일 객체를 따로 만들어서 관리하는데 이곳에서 확인 과정을 거침.
        return String.format("%s : %s" , file.getContentType(), file.getOriginalFilename());
    }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){ // @RequestBody를 넣어 주는 것은 json 형태로 받겠다는 것임)
        return String.format("json = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
}

// 데이터 전송 방법 Note!

// 1. Query Parameter 방식
// - 어노테이션 필요 X, DTO 객체 생성

// 2. Segment Parameter 방식
// - @PathVariable : 각 키에 맞는 파라미터를 별도로 컨트롤러 파라미터로 생성
// - @ModelAttribute : DTO 객체 생성

// 3. Form Data 방식
// - @ModelAttribute : DTO 객체 또는 컨트롤러 파라미터로 받을 수 있음

// 4. Json 방식
// - @RequestBody : 일반적으로 DTO 객체로 받음

// 데이터 전송 방법 Note!

// 1. Query Parameter 방식
// - URL의 ? 뒤에 데이터를 포함하여 전달
// - @RequestParam : 개별 파라미터로 받음
// - @ModelAttribute : DTO 객체로 받음
// - 일정 조건에서 어노테이션 생략 가능

// 2. Segment Parameter 방식 (Path Parameter)
// - URL 경로 자체에 데이터를 포함하여 전달
// - @PathVariable : URL 경로의 값을 개별적으로 받음
// - @ModelAttribute : DTO 객체로 바인딩 가능

// 3. Form Data 방식
// - POST 요청 시 HTTP Body에 데이터를 담아 전달
// - @ModelAttribute : DTO 객체로 받음
// - @RequestParam : 개별 파라미터로 받음
// - 파일 첨부 시 multipart/form-data 사용

// 4. JSON 방식
// - HTTP Body에 JSON 형식으로 데이터를 담아 전달
// - @RequestBody : 일반적으로 DTO 객체로 받음
// - Content-Type : application/json