package org.msa.msa5spring.request;

public record QueryRequestDTO(
        Long id,
        String name
        // 파라미터명(id, name)이 필드명이 되는데, 클라이언트에서 사용되는 key가 일치해야 함.
) {}
