package org.msa.msa5spring.response;

public record GlobalResposeDTO<T>(
        String code,
        String message,
        T result
        ) {}
