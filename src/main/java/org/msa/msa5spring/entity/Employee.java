package org.msa.msa5spring.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 클래스명은 테이블명(주로 복수형으로 생성)을 단수형으로 써주는 것이 관례임.
// 테이블의 컬럼이 java에서 필드명이라고 볼 수 있음.
@Getter
@Setter
public class Employee {
    private Long empId;
    // Long을 사용하는 이유는 Null 때문에
    private String name;
    private LocalDate birth;
    private Character gender;
    // String을 사용해도 됨.
    private LocalDate hireAt;
    // 연월일까지 사용
    private LocalDate fireAt;
    private Long supId;
    private LocalDateTime createdAt;
    // 연월일시간까지 사용
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    // public void setEmpId(Long id) throws Exception {
    //     if (id >= 1) {
    //         this.empId = id;
    //     } else {
    //         throw new Exception("잘못된 사원번호입니다.");
    //     }
    // }
}
