package com.example.demo.mapper;

import com.example.demo.dto.request.QuadraticRequestDto;
import com.example.demo.dto.response.QuadraticResponseDto;
import com.example.demo.model.Quadratic;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class QuadraticMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static QuadraticResponseDto quadratic2QQuadraticResponseDto(Quadratic quadratic) {
        return objectMapper.convertValue(quadratic, QuadraticResponseDto.class);
    }

    public static Quadratic.QuadraticKey QuadraticRequestDto2quadraticKey(QuadraticRequestDto quadraticRequestDto) {
        return objectMapper.convertValue(quadraticRequestDto, Quadratic.QuadraticKey.class);
    }
}
